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
    
    NSArray *componentesSaltoLinea = [numbers componentsSeparatedByString:@"\n"];
    for (NSString *comp in componentesSaltoLinea){
        NSArray *componentesComa = [comp componentsSeparatedByString:@","];
        for (NSString *c in componentesComa){
            result += [c intValue];
        }
    }
    return result;
}

@end
