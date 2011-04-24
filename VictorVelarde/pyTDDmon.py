#coding: utf-8
from Tkinter import *
import sys
import os
import glob

from time import gmtime, strftime

run_tests_script_file = 'pyTDDmon_tmp.py'

def build_run_script(files):
	header = 	'''\
import unittest

suite = unittest.TestSuite()
load_module_tests = unittest.defaultTestLoader.loadTestsFromModule

'''
	middle = ""
	for filename in files:
		module = filename[:-3]
		middle += 'import ' + module + '\n'
		middle += 'suite.addTests(load_module_tests(' + module + '))\n\n'
	footer = '''\
if __name__ == '__main__':
	unittest.TextTestRunner().run(suite)
'''

	return header + middle + footer

def calculate_checksum(filelist, fileinfo):
	val = 0
	for f in filelist:
		val += fileinfo.get_modified_time(f) + fileinfo.get_size(f)
	return val

class ColorPicker:
	''' ColorPicker avgör bakgrundsfärgen i pyTDDmon-fönstret mha.
	    antalet gröna tester, och totala antalet tester. Dessutom
	    "pulserar" färgen mha API:t pulse(). '''
	
	def __init__(self):
		self.color = 'green'
		self.reset_pulse()

	def pick(self):
		return (self.light, self.color)

	def pulse(self):
		self.light = not self.light
	
	def reset_pulse(self):
		self.light = True
		
	def set_result(self, (green, total)):
		old_color = self.color
		self.color = 'green'
		if green == total-1:
			self.color = 'red'
		if green < total-1:
			self.color = 'gray'
		if self.color != old_color:
			self.reset_pulse()
						
def win_text(total_tests, passing_tests=0, prev_total_tests=0):
	if prev_total_tests > total_tests:
		return "%d of %d tests green\n"% (passing_tests, total_tests) +\
					 "Warning: number of tests decreased!" 
	if total_tests == 0:
		return "No tests found!"
	if passing_tests == total_tests:
		return "All %d tests green" % total_tests
	txt = "%d of %d tests green"
	if passing_tests+1 < total_tests:
		txt = "Warning: only " + txt + "!"
	return txt % (passing_tests, total_tests)

class ScriptWriter:
	'''
	ScriptWriter: gets it's modules from the Finder, and
	writes a test script using the FileWriter+script_builder
	'''
	def __init__(self, finder, file_writer, script_builder):
		self.finder = finder
		self.file_writer = file_writer
		self.script_builder = script_builder
		
	def write_script(self):
		result = self.script_builder.build_script_from_modules(self.finder.find_modules())
		self.file_writer.write_file(run_tests_script_file, result)

class TestScriptRunner:
	''' TestScriptRunner - 
	  Collaborators:
       CmdRunner, runs a specified command line, returns stderr as string
       Analyzer, analyses unittest-output into green,total number of tests
	'''
	def __init__(self, cmdrunner, analyzer):
		self.cmdrunner = cmdrunner
		self.analyzer = analyzer
		
	def run(self, test_script):
		output = self.cmdrunner.run_cmdline('python '+test_script)
		return self.analyzer.analyze(output)
		
class Analyzer:
	'''
	Analyzer
	Analyserar unittest-output efter gröna test, och antal test.
	Medarbetare: Log, dit loggmeddelande skrivs.
	'''
	def __init__(self, logger):
		self.logger = logger
		
	def analyze(self, txt):
		if len(txt.strip()) == 0:
			return (0, 0)
		toprow =  txt.splitlines()[0]
		green = toprow.count('.')
		total = len(toprow)
		if green<total:
			self.logger.log(txt)
		return (green, total)

class Logger:
	''' Logger, samlar ihop loggmeddelanden till en lång sträng. '''
	
	def __init__(self):
		self.clear()
		
	def log(self, message):
		self.complete_log = self.complete_log + message
		
	def get_log(self):
		return self.complete_log
		
	def clear(self):
		self.complete_log = ""
		
## Rows above this are unit-tested.
## Rows below this are not unit-tested.

class RealFileInfo:
	def get_size(self, f):
		return os.stat(f).st_size
	def get_modified_time(self, f):
		return os.stat(f).st_mtime

class Finder:
	def find_modules(self):
		return glob.glob("test_*.py")
	
class FinderWithFixedFileSet:
	def __init__(self, files):
		self.files = files
	
	def find_modules(self):
		return self.files
		
def safe_remove(path):
	try: os.unlink(path)
	except: pass

class CmdRunner:
	def run_cmdline(self, cmdline):
		os.system(cmdline + " 2>tmp2.txt")
		try:
			f = open('tmp2.txt', "r")
			output = f.read()
		finally:
			f.close()
		safe_remove('tmp2.txt')
		return output
		
class FileWriter:
	def write_file(self, filename, content):
		f = open(filename, 'w')
		f.write(content)
		f.close()
		
class ScriptBuilder:
	def build_script_from_modules(self, modules):
		return build_run_script(modules)

def message_window(message):
	win = Toplevel()
	win.title('Log')
	def destroy(something):
		win.destroy()
	white = '#ffffff'
	label=Label(win, text=message, bg=white, activebackground=white)
	label.pack()
	label.bind("<Button-2>", destroy)
	label.bind("<Button-3>", destroy)

class pyTDDmonFrame(Frame):

	def __init__(self, files=None):
		Frame.__init__(self, None)
		self.grid()
		self.create_button()
		self.failures = 0
		self.last_checksum = 0
		self.num_tests = 0
		self.num_tests_prev = 0
		self.num_tests_diff = 0
		self.logger = Logger()
		self.color_picker = ColorPicker()
		self.runner = TestScriptRunner(CmdRunner(), Analyzer(self.logger))
		finder = Finder()
		if files != None:
			finder = FinderWithFixedFileSet(files)
		self.script_writer = ScriptWriter(finder, FileWriter(), ScriptBuilder())
		self.color_table = {
			(True, 'green'): '0f0',
			(False, 'green'): '0c0',
			(True, 'red'): 'f00',
			(False, 'red'): 'c00',
			(True, 'gray'): '999',
			(False, 'gray'): '555'
		}
		self.run()
				
	def compute_checksum(self):
		files = glob.glob('*.py')
		try: files.remove(run_tests_script_file)
		except: pass
		return calculate_checksum(files, RealFileInfo())

	def get_number_of_failures(self):
		self.script_writer.write_script()
		(green, total) = self.runner.run(run_tests_script_file)
		self.num_tests_prev = self.num_tests
		self.num_tests = total
		return total - green

	def clock_string(self):
		return strftime("%H:%M:%S", gmtime())

	def create_button(self):
		self.button = Label(self, text = 'pyTDDmon')
		self.button.bind("<Button-1>", self.button_clicked)
		self.button.bind("<Button-2>", self.end_program)
		self.button.bind("<Button-3>", self.end_program)
		self.button.grid()

	def button_clicked(self, widget):
		message_window(self.logger.get_log())

	def end_program(self, widget):
		safe_remove(run_tests_script_file)
		self.quit()

	def run(self):
		print("")
		print(" _______________________________________")
		print("|       pyTDDmon window opened          |")
		print("|_______________________________________|")
		print("|                                       |")
		print("| Left click pyTDDmon: show test output |")
		print('| Right click  - " - : quit             |')
		print('|_______________________________________|')

	def update_gui(self):
		(green, total, prev_total) = (self.num_tests-self.failures, self.num_tests, self.num_tests_prev)
		self.update_gui_color(green, total)
		self.update_gui_text(green, total, prev_total)
	
	def update_gui_color(self, green, total):
		self.color_picker.set_result( (green, total) )
		(light, color) = self.color_picker.pick()
 		self.color_picker.pulse()
		rgb = '#' + self.color_table[(light, color)]
		self.button.configure(bg=rgb, activebackground=rgb)
		
	def update_gui_text(self, green, total, prev_total):
		txt = "pyTDDmon\n" + win_text(passing_tests = green, total_tests = total, prev_total_tests = prev_total)
		self.button.configure(text=txt)

	def look_for_changes(self):
		newval = self.compute_checksum()
		if newval != self.last_checksum:
			self.last_checksum = newval
			self.logger.clear()
			self.logger.log('[%s] Running all tests...\n' % self.clock_string())
			self.failures = self.get_number_of_failures()
			self.logger.log('[%s] Number of failures: %d\n' % (self.clock_string(), self.failures))
		self.update_gui()
		self.after(750, self.look_for_changes)

def file_exists(f):
	try:
		o = open(f, "r")
		o.close()
	except:
		print(f + " does not exist")
		return False
	print(f + " exists")
	return True

def filter_existing_files(files):
	return [f for f in files if file_exists(f)]

if __name__ == '__main__':
	filtered = filter_existing_files(sys.argv[1:])
	if len(filtered)>0:
		app = pyTDDmonFrame(filtered)
	else:
		app = pyTDDmonFrame()
	app.master.title(" ")
	app.master.resizable(0,0)
	app.look_for_changes()
	try:
		app.mainloop() 
	except:
		pass
