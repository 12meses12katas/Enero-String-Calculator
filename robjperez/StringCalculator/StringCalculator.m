//
//  StringCalculator.m
//  StringCalculator
//
//  Created by Roberto Perez Cubero on 29/01/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "StringCalculator.h"


@implementation StringCalculator

- (NSString*) getDelimiterOfString: (NSString*) theString
{
	NSString* delimiter = @",";
	NSArray *lines = [theString componentsSeparatedByString:@"\n"];
	NSString* firstLine = [lines objectAtIndex:0];
	
	if ( [firstLine hasPrefix:@"//"] )
	{
		delimiter = [firstLine substringFromIndex:2];
	}
	
	return delimiter;
}

- (int) add: (NSString*) string 
{
	if ([string length] == 0) return 0;

	NSString* delimiter = [self getDelimiterOfString:string];
	
	int count = 0;	
	
	NSArray *lines = [string componentsSeparatedByString:@"\n"];
	for (NSString* line in lines)
	{
		NSArray *numbers = [line componentsSeparatedByString:delimiter];
		NSMutableArray *negativeNumbers = [[NSArray alloc] init];
	
		for (NSString* number in numbers)
		{
			int iNumber = [number intValue];
			if ( iNumber < 0 )
				[negativeNumbers addObject:number];	
			if ( iNumber <= 1000 )
				count += iNumber;
		}
		
		if ([negativeNumbers count] != 0)
		{
			NSMutableString* exceptionMessage = [NSMutableString stringWithString:@"Negative Numbers not allowed. Found:"];
			for ( NSString* i in negativeNumbers )
				[exceptionMessage appendFormat:@"%d ", [i intValue]];
			
			@throw [NSException exceptionWithName:@"NegativeNumberException" 
										   reason:exceptionMessage
										 userInfo:nil];
		}
	}
	
	return count;
}

@end
