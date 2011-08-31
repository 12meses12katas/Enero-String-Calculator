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
    
    NSArray *componentes = [numbers componentsSeparatedByString:@","];
    for (NSString *comp in componentes){
        result += [comp intValue];
    }
    return result;
}

@end
