/* 
 * Kata de Enero
 * Suma de numeros en cadena con distintos separadores
 * Autoejecutable
 */

public class KataEnero
{

    public static int Add ( String frase ) 
    {
	int resultado = 0;
	
	if ( frase.startsWith("//") ) 
	    {
		int contador = 2;
		char separador = frase.charAt( contador );
		while ( separador != '\n' ) 
		    {
			frase = frase.replace( separador, ',' );
			separador = frase.charAt( ++contador );
		    }
		frase = frase.substring( frase.indexOf('\n') + 1 );
		frase = frase.replace( '\n', ',' );
	    }
	


	if ( frase != null ) 
	    if ( frase.indexOf(',') == -1 ) 
		{
		    resultado = Integer.parseInt( frase );
		} 
	    else  
		{
		    int index;
		    boolean ultimo = false;
		    
		    do {
			index = frase.indexOf( ',' );
			if ( index == -1 ) 
			    {
				resultado += Integer.parseInt( frase );
				ultimo = true;
			    } 
			else 
			    {
				resultado += Integer.parseInt( frase.substring( 0, index ) );
				frase = frase.substring(index + 1);
			    }
		    } while ( !ultimo ); 
		    
		}
	
	return resultado;
    }


    public static void main( String arg[] ) 
        {
	    System.out.println( Add( "//;-\n1\n3-76\n5,4;2" ) );
	}

}