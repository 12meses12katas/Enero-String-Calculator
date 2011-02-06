-module(string_calculator).
-export([add/1]).

add("") ->
  0;
add(String) ->
  {Delimiter, NewString} = parse_first_line(String),
  Tokens = re:split(NewString, Delimiter, [{return, list}]),
  Numbers = parse_number_list(Tokens),
  NegativeNumbers = lists:filter(fun(N) -> N < 0 end, Numbers),
  if
    NegativeNumbers =:= [] ->
      lists:sum(Numbers);
    true ->
      NegativeNumbersStr = string:join(
        lists:map(fun integer_to_list/1, NegativeNumbers), ", "),
      {negative_number,
       "negatives are not allowed: [" ++ NegativeNumbersStr ++ "]"}
  end.

parse_first_line([$/, $/ | RestOfString]) ->
  {FirstLine, NewString} = lists:splitwith(fun(S) -> S =/= $\n end, RestOfString),
  Delimiter = parse_delimiters(FirstLine),
  {Delimiter, string:substr(NewString, 2)};
parse_first_line(String) ->
  {",|\n", String}.

parse_number_list(Tokens) ->
  lists:filter(fun(N) -> N =< 1000 end,
               lists:map(fun(S) -> list_to_integer(string:strip(S)) end,
                         Tokens)).

parse_delimiters(Delimiters) ->
  case re:run(Delimiters, "\\[([^\\]]+)\\]", [global]) of
    {match, ListOfMatches} ->
      Matches = [string:substr(Delimiters, Start+1, Length) || [_, {Start, Length}] <- ListOfMatches],
      FilteredMatches = lists:map(fun filter_re/1, Matches),
      string:join(FilteredMatches, "|");
    nomatch ->
      Delimiters
  end.

filter_re(String) ->
  re:replace(String,
             "\\\\|\\^|\\$|\\.|\\[|\\]|\\||\\(|\\)|\\?|\\*|\\+|\\{|\\}",
             "\\\\&",
             [global, {return, list}]).
