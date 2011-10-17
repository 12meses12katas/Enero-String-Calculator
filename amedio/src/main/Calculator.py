class Calculator(object):
    
    def __init__(self):
        pass
    
    def add(self, addParams):
        
        result = 0
        
        if addParams != "":
            if ',' in addParams:
                params = addParams.split(',')
                result = int(params[0]) + int(params[1]) 
            else:
                result = int(addParams)
        
        return result