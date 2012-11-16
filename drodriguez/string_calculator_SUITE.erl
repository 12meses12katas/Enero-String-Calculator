-module(string_calculator_SUITE).
-include_lib("common_test/include/ct.hrl").

%% Test server callbacks
-export([suite/0, all/0,
         init_per_suite/1, end_per_suite/1,
         init_per_testcase/2, end_per_testcase/2]).

%% Test cases
-export([add_empty_string/1, add_one_element_string/1,
         add_two_elements_string/1, add_more_than_two_elements_string/1,
         add_numbers_with_newline_string/1, add_numbers_with_mixed_string/1,
         add_string_with_delimiter/1, add_with_negative_numbers/1,
         add_numbers_above_1000/1, add_delimiter_of_any_length/1,
         add_multiple_delimiters/1, add_multiple_delimiters_of_any_length/1]).

suite() ->
  [{timetrap, {minutes, 1}}].

init_per_suite(Config) ->
  Config.

end_per_suite(_Config) ->
  ok.

init_per_testcase(_Case, Config) ->
  Config.

end_per_testcase(_Case, _Config) ->
  ok.

all() ->
  [add_empty_string, add_one_element_string, add_two_elements_string,
   add_more_than_two_elements_string, add_numbers_with_newline_string,
   add_numbers_with_mixed_string, add_string_with_delimiter,
   add_with_negative_numbers, add_numbers_above_1000,
   add_delimiter_of_any_length, add_multiple_delimiters,
   add_multiple_delimiters_of_any_length].

add_empty_string(_Config) ->
  0 = string_calculator:add(""),
  ok.

add_one_element_string(_Config) ->
  1 = string_calculator:add("1"),
  ok.

add_two_elements_string(_Config) ->
  3 = string_calculator:add("1, 2"),
  ok.

add_more_than_two_elements_string(_Config) ->
  111 = string_calculator:add("1, 10, 30, 20, 50"),
  ok.

add_numbers_with_newline_string(_Config) ->
  42 = string_calculator:add("1\n2\n39"),
  ok.

add_numbers_with_mixed_string(_Config) ->
  53 = string_calculator:add("1, 2\n50"),
  ok.

add_string_with_delimiter(_Config) ->
  10 = string_calculator:add("//;\n1;2;3;4"),
  ok.

add_with_negative_numbers(_Config) ->
  {negative_number, "negatives are not allowed: [-1, -2, -3]"} =
    string_calculator:add("-1, 1, -2, 2, -3, 3"),
  ok.

add_numbers_above_1000(_Config) ->
  2 = string_calculator:add("2, 1001"),
  ok.

add_delimiter_of_any_length(_Config) ->
  6 = string_calculator:add("//[***]\n1***2***3"),
  ok.

add_multiple_delimiters(_Config) ->
  6 = string_calculator:add("//[*][%]\n1*2%3"),
  ok.

add_multiple_delimiters_of_any_length(_Config) ->
  6 = string_calculator:add("//[**][%%%]\n1**2%%%3"),
  ok.
