unit TestStringCalculatorAdd;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, fpcunit, testutils, testregistry, ustringcalculator;

type

  { TTestStringCalculator }

  TTestStringCalculator= class(TTestCase)
  private
    FStringCalculator: TStringCalculator;
    FValorMetodoGenerico: string;
    procedure UnMetodoGenerico;
  protected
    procedure SetUp; override;
    procedure TearDown; override;
  published
    procedure TestAddWithZeroArguments;
    procedure TestAddWithOneArgument;
    procedure TestAddWithNArguments;
    procedure TestAddWithNewLineDelimiter;
    procedure TestAddWithOtherDelimiter;
  public
    property StringCalculator: TStringCalculator read FStringCalculator write FStringCalculator;
  end;

implementation

procedure TTestStringCalculator.TestAddWithZeroArguments;
begin
  AssertEquals(0, StringCalculator.Add(''));
end;

procedure TTestStringCalculator.TestAddWithOneArgument;
begin
  AssertEquals(   0, StringCalculator.Add('0'));
  AssertEquals(   1, StringCalculator.Add('1'));
  AssertEquals(   2, StringCalculator.Add('2'));
  AssertEquals(1234, StringCalculator.Add('1234'));
  AssertEquals(1234, StringCalculator.Add('1234,'));
  FValorMetodoGenerico := '-1';
  AssertException('No deberia permitir valores negativos', ExceptionNegativeAddend, @UnMetodoGenerico);
end;

procedure TTestStringCalculator.TestAddWithNArguments;
begin
  AssertEquals(  1, StringCalculator.Add('0,1'));
  AssertEquals(  2, StringCalculator.Add('1,1'));
  AssertEquals(  3, StringCalculator.Add('1,1,1'));
  AssertEquals(  1, StringCalculator.Add(',,,,0,1,'));
  AssertEquals(171, StringCalculator.Add('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18'));
end;

procedure TTestStringCalculator.TestAddWithNewLineDelimiter;
begin
  AssertEquals(  2, StringCalculator.Add('1\n1'));
end;

procedure TTestStringCalculator.TestAddWithOtherDelimiter;
begin
  AssertEquals(  3, StringCalculator.Add('//;\n1;2'));
  AssertEquals(152, StringCalculator.Add('//;\n150;2'));
  AssertEquals(  0, StringCalculator.Add('//,\n150;2'));  // hay errores en el delimitador.
  AssertEquals(152, StringCalculator.Add('//,\n150,2'));
  AssertEquals(152, StringCalculator.Add('//@\n150@2'));
  FValorMetodoGenerico := '//@\150@2';
  AssertException('Delimiter format error', ExceptionDelimiterFormat, @UnMetodoGenerico);
end;

procedure TTestStringCalculator.UnMetodoGenerico;
begin
  StringCalculator.Add(FValorMetodoGenerico);
end;

procedure TTestStringCalculator.SetUp; 
begin
  FStringCalculator := TStringCalculator.Create();
end; 

procedure TTestStringCalculator.TearDown; 
begin
  FreeAndNil(FStringCalculator);
end; 

initialization
  RegisterTest(TTestStringCalculator); 
end.

