#!/bin/sh

# Kata 1: String Calculator
# aabilio - aabilio@gmail.com
# Lenguaje: Bash
# 
# LO MALO:
# En Bash solo se permiten devolver valores entre 0 y 255 (dedicados a destinar 
# los errores)
# Por lo tanto no voy a usar return sino un variable global llamada "result".

# Se presentan dos funciones:
# 1.- add() que intenta seguir paso a paso lo dictado en los guiones. Creciendo
#     incrementalmente según se piden cosas.
# 2.- add2() que cumple con todo lo dado en los guiones pero no se queda ahí, 
#     sino que sumará todos los números,
#     se le pase como se le pase la cadena formateada, así por ejemplo tragaría 
#     con: add2 "as32**3**2vc2;4;;5,p2" dando como resultado el valor 50.


TEMP_FILE=/tmp/kst.tmp

#-------------------------------------------------------------------------------
function add2()
{
    #Borrar datos antiguos
    unset suma
    #Formatear la cadena para quedarse solo con los números (y "-") separador 
    #por espacios
    cadena=$(echo $1 | tr -cs '0123456789-' ' ')
    #Comprobar si existe algún número negativo
    echo $cadena | grep - > /dev/null
    if [ $? -eq 0 ]; then
        echo "Números negativos no permitidos"
    else
        #Comprobar valores mayores que 1000 y eliminarlos de la cadena
        for i in `echo $cadena`; do
            if [ $i -gt 1000 ]; then
                cadena=$(echo $cadena | sed s/$i/\ /g)
            fi
        done
        #Bucle para sumar la cadena
        suma=0 && for i in `echo $cadena`; do let suma=$i+$suma; done
    fi
    result=$suma
}

#-------------------------------------------------------------------------------

function add()
{
    #Borrar los resultados anteriores
    unset result
    unset suma
    #Comprobar si hay separador nuevo:
    echo "$1" | grep ^//.*$ > /dev/null
    if [ $? -ne 0 ]; then
        SEP=","
        #Convertir separador a espacios:
        cadena=$(echo $1 | tr $SEP ' ')
    else
        echo $1 > $TEMP_FILE
        echo "$1" | grep ^//\\[.*\\].*$ > /dev/null
        if [ $? -ne 0 ]; then
            SEP=$(head -n1 $TEMP_FILE | cut -d/ -f3)
        else
            echo "$1" | grep ^//\\[.*\\]\\[.*\\].*$ > /dev/null
            if [ $? -ne 0 ]; then
                SEP=$(head -n1 $TEMP_FILE | cut -d[ -f2 | cut -d] -f1)
            else
                SEP=$(head -n1 $TEMP_FILE |  tr -d [//\\[\\]])
            fi
        fi
        cadena=$(tail -n1 $TEMP_FILE | tr "$SEP" ' ')
    fi
    
    #Comprobar núeros negativos:
    echo $cadena | grep - > /dev/null
    if [ $? -eq 0 ]; then
        echo "Números negativos no permitidos"
    else
        #Comprobar valores mayores que 1000:
        for i in `echo $cadena`; do
            if [ $i -gt 1000 ]; then
                cadena=$(echo $cadena | sed s/$i/\ /g)
            fi
        done
        suma=0 && for i in `echo $cadena`; do let suma=$i+$suma; done
    fi
    
    # return $suma
    result=$suma
}

#-------------------------------------------------------------------------------

#### Probar add:
echo Ningún parámetro \"\":
add ""
echo $result
echo Un parámtro \"1\":
add "1"
echo $result
echo Dos parámetros \"1,2\":
add "1,2"
echo $result
echo Pasando la '\\n --> ''"1\\n2,3"':
add "1\n2,3" 
echo $result
echo Varios parámetros con un separador definido "//;\\\\n1;2;3":
add "//;\n1;2;3"
echo $result
echo Varios Parámetros con separador multicaracter "//[***]\\\\n250***2***3":
add "//[***]\n250***3***3"
echo $result
echo Varios parámetros con un separador definido y con un número negativo "//;\\\\n1;-2;3":
add "//;\n1;-2;3"
echo $result
echo Varios parámetros con un separador definido con números mayor que mil "//;\\\\n1;2000;3":
add "//;\n1;2000;3"
echo $result
echo Varios separadores de varios caracteres "//[::][;][+++]\\\\n3::2;1+++4":
add "//[::][;][+++]\\n3::2;1+++4"
echo $result


echo "Probar add2() tragándose \"as32**3**2vc2;4;;5,p2\"":
add2 "as32**3**2vc2;4;;5,p2"
echo $result
echo "add2() multiples separadores, múltiples caracteres \"//[***][%%%]\\\\n250***2%%%3\"":
add2 "//[***][%%%]\n250***2%%%3"
echo $result

