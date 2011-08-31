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
    NSString *delimitador = @",";
    
    NSArray *componentesSaltoLinea = [numbers componentsSeparatedByString:@"\n"];
    for (NSString *comp in componentesSaltoLinea){
        if ([comp hasPrefix:@"//"]){
            delimitador = [comp substringFromIndex:2];
            continue;
        }
        NSArray *componentesComa = [comp componentsSeparatedByString:delimitador];
        for (NSString *c in componentesComa){
            result += [c intValue];
        }
    }
    return result;
}

@end
