use strict;
use List::Util qw(reduce);

sub add {
    my $string = shift;
    my $sum = 0;
    my $newdelims = "";
    
    $newdelims = $1 and $string = $2 if ($string =~ m#^//([^\n]+)\n(.*)#);

    my @numbers = split(createDelimitersPattern($newdelims), $string);

    my @negatives = grep($_ < 0, @numbers);
    return "negatives not allowed: ".join(", ", @negatives) if (@negatives);

    return reduce { $a + $b } 0, grep($_ <= 1000, @numbers);
}

sub createDelimitersPattern {
    my $input = shift;

    my @delimiters = ($input =~ m/\[([^\]]+)\]/g);
    push @delimiters, $input if ($input and not @delimiters);
    
    my $regex = join("|", sanitizePatternList(",", "\n", @delimiters));
    return qr/$regex/;
}

sub sanitizePatternList {
    my @list = @_;

    return map { s/\*/\\*/g; $_ } grep(defined $_, @list);
}

1;
