unit uStringCalculator; 

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, uHelperCalculator;

type
  { TStringCalculator }
  TStringCalculator = class
  private
    FParser: IParserNumbers;
    FValidator: IValidation;
    function AddListNumbers(NumbersList: TIntegerList): Integer;
  public
    constructor Create(Parser: IParserNumbers; Validator: IValidation);
    function Add(const Numbers: string): Integer;
  end;


implementation

{ TStringCalculator }

function TStringCalculator.AddListNumbers(NumbersList: TIntegerList): Integer;
var
  Number: Integer;
begin
  result := 0;
  for Number in NumbersList do
    inc(result, Number);
end;

constructor TStringCalculator.Create(Parser: IParserNumbers; Validator: IValidation);
begin
  FParser := Parser;
  FValidator := Validator;
end;

function TStringCalculator.Add(const Numbers: string): Integer;
begin
  result := self.AddListNumbers(
                      FValidator.ValidateInput( FParser.SplitNumbers(Numbers)));
end;

end.

