unit uvalidator;

{$mode objfpc}

interface

uses
  Classes, SysUtils, uHelperCalculator;

type
  { Validator }
  TValidator = class(TInterfacedObject, IValidation)
  private
    function AnyNegativeNumbers(numbers: TIntegerList): Boolean;
  public
    function ValidateInput(numbers: TIntegerList): TIntegerList;
  end;




implementation


{ Validator }

function TValidator.AnyNegativeNumbers(numbers: TIntegerList): Boolean;
var
  number: Integer;
begin
  result := false;
  for number in numbers do
    result := result or (number < 0);
end;

function TValidator.ValidateInput(numbers: TIntegerList): TIntegerList;
begin
  if AnyNegativeNumbers(numbers) then
      raise ExceptionNegativeAddend.Create('negatives not allowed')
  else
     result := numbers;
end;

end.

