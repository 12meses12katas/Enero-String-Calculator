@echo off
set CLASSPATH=lib\scalatest-1.2.jar

echo Compiling...
call scalac -cp %CLASSPATH% src\*.scala -d out

echo Running tests...
call scala -cp %CLASSPATH% org.scalatest.tools.Runner -p out -o -s StringCalculatorSpec
rem 