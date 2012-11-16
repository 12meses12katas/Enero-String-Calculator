unit UStringCalculator_Test;

interface

Uses
  sysutils,
  TestFramework, TestExtensions, GUITesting;

type

  TStringCalculator_Test = class(TGUITestCase)
  private
  protected
  published
    procedure AnEmptyStringReturnZero;
    procedure OneIntegerReturnOneInteger;
    procedure TwoIntegersSeparatedWithComaReturnSum;
    procedure TwoIntegersSeparatedWithReturnReturnSum;
    procedure MoreIntegersReturnSum;
  end;


implementation

uses
  uStringCalculator;

procedure TStringCalculator_Test.AnEmptyStringReturnZero;
begin
  CheckEquals(TStringCalculator.Add('').Result, 0);
end;

procedure TStringCalculator_Test.OneIntegerReturnOneInteger;
begin
  CheckEquals(TStringCalculator.Add('1').Result, 1);
end;

procedure TStringCalculator_Test.TwoIntegersSeparatedWithComaReturnSum;
begin
  CheckEquals(TStringCalculator.Add('1,2').Result, 3);
end;

procedure TStringCalculator_Test.TwoIntegersSeparatedWithReturnReturnSum;
begin
  CheckEquals(3, TStringCalculator.Add('1\n2').Result);
end;

procedure TStringCalculator_Test.MoreIntegersReturnSum;
begin
  CheckEquals(TStringCalculator.Add('1,2,3').Result, 6);
end;

initialization
  TestFramework.RegisterTest('', TStringCalculator_Test.Suite);
end.
