unit uHelperCalculator;

{$mode objfpc}

interface

uses
  Classes, SysUtils, fgl;

type
  ExceptionNegativeAddend = Class(Exception);
  ExceptionDelimiterFormat = Class(Exception);

  TIntegerList = specialize TFPGList<Integer>;

  IParserNumbers = Interface
    ['{202E5254-0732-4154-A707-B3E739FE0AEC}']
    function SplitNumbers(Numbers: string): TIntegerList;
  end;

  IValidation = interface
    ['{4B926ECB-C1D1-4757-8C6E-602E4135212E}']
    function ValidateInput(numbers: TIntegerList): TIntegerList;
  end;

implementation

end.

