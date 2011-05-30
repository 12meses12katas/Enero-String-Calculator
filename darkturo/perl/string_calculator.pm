use strict;

sub add {
    my $string = shift;
    my $sum = 0;
    my $newdelims = "";
    
    
    $newdelims = $1 and $string = $2 if ($string =~ m#//([^\n])\n(.*)#);
    map { $sum += $_ } split(createDelimitersPattern($newdelims), $string);
    return $sum;
}

sub createDelimitersPattern {
    my $input = shift;
    $input = "|$input" if ($input);
    return qr/,|\n$input/
}

1;
