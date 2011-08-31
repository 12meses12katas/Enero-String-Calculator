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
    if ([[componentesSaltoLinea objectAtIndex:0] hasPrefix:@"//"])
        delimitador = [[componentesSaltoLinea objectAtIndex:0] substringFromIndex:2];
    
    for (NSString *comp in componentesSaltoLinea){
        for (NSString *c in [comp componentsSeparatedByString:delimitador]){
            if ([c intValue] < 0) [errores addObject:c];
            else if ([c intValue] < 1000) result += [c intValue];
        }
    }
    
    if ([errores count] > 0)
        @throw([NSException exceptionWithName:@"No negativos"
                    reason:[NSString stringWithFormat:@"No se pueden usar números negativos: %@",
                            [errores componentsJoinedByString:@", "]] userInfo:nil]);

    return result;
}

@end
