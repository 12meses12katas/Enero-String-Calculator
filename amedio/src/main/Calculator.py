class Calculator(object):
    
    def __init__(self):
        pass
    
    def add(self, addParams):
        
        result = 0
        
        if addParams != "":
            result = int(addParams)
        
        return result