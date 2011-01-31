#import <Foundation/Foundation.h>
#import "StringCalculator.h"

int main (int argc, const char * argv[]) {
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
	StringCalculator* calculator = [StringCalculator new];
	[calculator add:@"//:\n2:3:1"];
    [pool drain];
    return 0;
}
