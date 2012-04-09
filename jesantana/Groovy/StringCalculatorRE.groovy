package kataenerore;

class StringCalculatorRE{

	def int add(number)
	{
		 
		def result=0
		def numberLocal=number
		List limitadores=["\\n","\\+","q"];
		
        if(numberLocal!=0)
        {
            numberLocal=separaDelimitadores(numberLocal, limitadores)
			numberLocal=sustituyeLimitadores(numberLocal, limitadores, ',')
			def splitted=numberLocal.tokenize(',')
            
			splitted=splitted.collect 
			{  
				it.toInteger()
			}
			
			def negativos=splitted.findAll
			{
				it<0	
			}
			
			if(negativos.size()>0) throw new Exception("negatives not allowed "+negativos.toString())
			
			splitted=splitted.findAll
			{
				it<1000	
			}
			
			splitted.each 
            {
                elemento->
                def intElemento=elemento.toInteger()
                result+=intElemento
            }
          
        }
        
        return result
	}

def String separaDelimitadores(cad,delimitadores)
{
	def patron= /\/\/(.+)\n/
	cad.find(patron)
	{
	   match,g1->
		def patLlaves=/\[([^(\]|\[)]+)\]/
		def punto="."
		
		if(g1=~patLlaves){
			g1.findAll(patLlaves)
			{
				g11,g12->
				delimitadores<<g12
			}
		}
		else if(g1==~punto)
		{
			 g1.find(punto)
			{
				g11->
				
				delimitadores<<g11
			}
		}
		
	}
	
	return cad-~patron
}


def String sustituyeLimitadores(cadena,list,caractASust)
{
	def result=cadena
	result=list.inject(result)
	{
		res,element->
		["\\*":"\\\\*",
			"\\+":"\\\\+",
			"\\?":"\\\\?"
			].each { k,v ->
				element = element.replaceAll(k, v)
			}
		res.replaceAll(element,caractASust)
	}
	
	return result;
}


}