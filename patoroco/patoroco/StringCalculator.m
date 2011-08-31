//
//  StringCalculator.m
//  patoroco
//
//  Created by Jorge Maroto García on 31/08/11.
//  Copyright 2011 http://www.tactilapp.com. All rights reserved.
//

#import "StringCalculator.h"

@implementation StringCalculator

-(int)add:(NSString *)numbers{
    int result = 0;
    NSMutableArray *errores = [[NSMutableArray alloc] init];
    
    NSString *delimitador = @",";
    
    NSArray *componentesSaltoLinea = [numbers componentsSeparatedByString:@"\n"];
    for (NSString *comp in componentesSaltoLinea){
        if ([comp hasPrefix:@"//"]){
            delimitador = [comp substringFromIndex:2];
            continue;
        }
        NSArray *componentesComa = [comp componentsSeparatedByString:delimitador];
        for (NSString *c in componentesComa){
            int valor = [c intValue];
            if (valor < 0){
                [errores addObject:c];
            }else if (valor < 1000){
                result += valor;
            }
        }
    }
    
    if ([errores count] > 0){
        NSMutableString *cadenaError = [[NSMutableString alloc] initWithString:@"No se pueden usar números negativos: "];
        for (NSString *numero in errores){
            [cadenaError appendString:numero];
        }
        @throw([NSException exceptionWithName:@"No negativos" reason:cadenaError userInfo:nil]);
        [cadenaError release];
        [errores release];
    }    

    return result;
}

@end
