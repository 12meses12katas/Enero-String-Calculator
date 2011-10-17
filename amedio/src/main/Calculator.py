from src.main.constants import _constants

class Calculator(object):
    
    def __init__(self):
        pass
    
    def add(self, addParams):
        
        result = 0
        
        if addParams != "":
        
            const = _constants()
            
            if const.COMMA_SEP() in addParams:
                params = addParams.split(const.COMMA_SEP())
                result = int(params[0]) + int(params[1]) 
            else:
                result = int(addParams)
        
        return result