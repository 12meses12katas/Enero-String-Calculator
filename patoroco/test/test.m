//
//  test.m
//  test
//
//  Created by Jorge Maroto García on 31/08/11.
//  Copyright 2011 http://www.tactilapp.com. All rights reserved.
//

#import "test.h"
#import "StringCalculator.h"

@implementation test
@synthesize calculadora;

- (void)setUp{
    [super setUp];
    calculadora = [[StringCalculator alloc] init];
}

- (void)tearDown{
    [super tearDown];
    [calculadora release];
}

- (void)testEmptyString{
    STAssertEquals([calculadora add:@""], 0, @"");
}

-(void)testUnNumero{
    STAssertEquals([calculadora add:@"1"], 1, @"");
}

-(void)testDosNumeros{
    STAssertEquals([calculadora add:@"1,2"], 3, @"");
}

-(void)testMuchosNumeros{
    STAssertEquals([calculadora add:@"1,1,1,2,3"], 8, @"");
}

-(void)testSaltosDeLinea{
    STAssertEquals([calculadora add:@"1\n2,3"], 6, @"");
}

-(void)testDelimitadorPuntoYComa{
    STAssertEquals([calculadora add:@"//;\n1;2"], 3, @"");
}

-(void)testDelimitadorMenos{
    STAssertEquals([calculadora add:@"//&\n3&4\n3"], 10, @"");
}

-(void)testNumeroNegativo{
    STAssertThrows([calculadora add:@"1,-5,-8,9,25"], @"");
}

-(void)testNumerosMayoresQue1000{
    STAssertEquals([calculadora add:@"1000,1,1"], 2, @"");
}
@end
