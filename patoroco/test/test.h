//
//  test.h
//  test
//
//  Created by Jorge Maroto García on 31/08/11.
//  Copyright 2011 http://www.tactilapp.com. All rights reserved.
//

#import <SenTestingKit/SenTestingKit.h>

@class StringCalculator;

@interface test : SenTestCase{
    StringCalculator *calculadora;
}
@property (nonatomic, retain) StringCalculator *calculadora;

@end
