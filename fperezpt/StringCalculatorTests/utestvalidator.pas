unit utestvalidator;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, fpcunit, testutils, testregistry,
  uhelpercalculator, uvalidator;

type

  { TestValidator }

  TestValidator= class(TTestCase)
  private
    FListNumbers: TIntegerList;
    FValidator : TValidator;  // Ref.Counted
    procedure UnMetodoGenerico;
  protected
    procedure SetUp; override; 
    procedure TearDown; override; 
  published
    procedure TestIntegerListNumbersEmpty;
    procedure TestIntegerListNumbersWithNegatives;
    procedure TestIntegerListNumbers;
  end; 

implementation

procedure TestValidator.TestIntegerListNumbersEmpty;
begin
  AssertSame(FListNumbers, FValidator.ValidateInput(FListNumbers) );
end;

procedure TestValidator.TestIntegerListNumbers;
begin
  FListNumbers.Add(0);
  FListNumbers.Add(1);
  FListNumbers.Add(2);
  FListNumbers.Add(1);
  FListNumbers.Add(2);
  AssertSame(FListNumbers, FValidator.ValidateInput(FListNumbers) );
end;

procedure TestValidator.TestIntegerListNumbersWithNegatives;
begin
  FListNumbers.Add(1);
  AssertSame(FListNumbers, FValidator.ValidateInput(FListNumbers) );
  FListNumbers.Add(-3);
  AssertException('No deberia permitir valores negativos', ExceptionNegativeAddend, @UnMetodoGenerico);
end;

procedure TestValidator.UnMetodoGenerico;
begin
  FValidator.ValidateInput(FListNumbers);
end;

procedure TestValidator.SetUp; 
begin
  FValidator := TValidator.Create;
  FListNumbers := TIntegerList.Create;
end; 

procedure TestValidator.TearDown; 
begin
  FreeAndNil(FListNumbers);
  FreeAndNil(FValidator);
end; 

initialization
  RegisterTest(TestValidator); 
end.

