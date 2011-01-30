//
//  StringCalculator_test.m
//  StringCalculator
//
//  Created by Roberto Perez Cubero on 29/01/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "StringCalculator_test.h"


@implementation StringCalculator_test

- (void) setUp 
{
	calculator = [[StringCalculator alloc] init];
}

- (void) tearDown
{
	[calculator release];
}

-(void) test_emptyString 
{
	int expected = 0;
	int result = [calculator add:@""];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);
}

-(void) test_2numbers 
{
	int expected = 1002;
	int result = [calculator add:@"2,1000"];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);
}

-(void) test_3numbers 
{
	int expected = 13;
	int result = [calculator add:@"2,3,8"];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);	
}

-(void) test_nnumbers 
{
	int expected = 205;
	int result = [calculator add:@"21,3,1,90,1,87,2"];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);
}

-(void) test_3numbers_new_line 
{
	int expected = 11;
	int result = [calculator add:@"2,3\n6"];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);
}

-(void) test_nnumbers_new_line 
{
	int expected = 205;
	int result = [calculator add:@"21\n3,1\n90,1,87\n2"];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);
}

-(void) test_custom_delimiter
{
	int expected = 10;
	int result = [calculator add:@"//:\n1:2:7"];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);
}

-(void) test_custom_delimiter2
{
	int expected = 22;
	int result = [calculator add:@"//pp:s\n1pp:s2\n7pp:s12"];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);
}

-(void) test_negative_number
{
	STAssertThrows([calculator add:@"//pp:s\n1pp:s2\n-7pp:s12"], @"Should Raise and exception");
}

-(void) test_numbers_greaterthan1000
{
	int expected = 5;
	int result = [calculator add:@"1001,2,3"];
	STAssertEquals(expected, result, @"Expected %d, got %d", expected, result);
}

@end
