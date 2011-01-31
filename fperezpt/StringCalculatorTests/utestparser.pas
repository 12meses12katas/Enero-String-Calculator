unit uTestParser;

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, fpcunit, testutils, testregistry,
  uhelpercalculator, uParser;

type

  { TestParser }

  TestParser= class(TTestCase)
  private
    FReceived: TIntegerList;
    FNumbers: string;
    FParser: TParser;  // Ref.Counted
    procedure UnMetodoGenerico;
  protected
    procedure SetUp; override; 
    procedure TearDown; override; 
  published
    procedure TestAnElement;
    procedure TestTwoElements;
    procedure TestNElements;
    procedure TestChangeDefaultDelimiter;
  end; 

implementation

procedure TestParser.TestAnElement;
begin
  FReceived := FParser.SplitNumbers('1');
  AssertEquals(1, FReceived.Items[0]);
  FReceived := FParser.SplitNumbers('0');
  AssertEquals(0, FReceived.Items[0]);
end;

procedure TestParser.TestTwoElements;
begin
  FReceived := FParser.SplitNumbers('1,2');
  AssertEquals(1, FReceived.Items[0]);
  AssertEquals(2, FReceived.Items[1]);
  FReceived := FParser.SplitNumbers('458,-2');
  AssertEquals(458, FReceived.Items[0]);
  AssertEquals(-2, FReceived.Items[1]);
end;

procedure TestParser.TestNElements;
begin
  FReceived := FParser.SplitNumbers(',,,,0,1,');
  AssertEquals(0, FReceived.Items[0]);
  AssertEquals(0, FReceived.Items[4]);
  AssertEquals(1, FReceived.Items[5]);
  AssertEquals(0, FReceived.Items[6]);
  AssertEquals(7, FReceived.Count);
  FReceived := FParser.SplitNumbers('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18');
  AssertEquals(18, FReceived.Count);
  AssertEquals(1, FReceived.Items[0]);
  AssertEquals(18, FReceived.Items[17]);
end;

procedure TestParser.TestChangeDefaultDelimiter;
begin
  FReceived := FParser.SplitNumbers('//;\n1;2');
  AssertEquals(2, FReceived.Count);
  AssertEquals(';', FParser.GetDefaultDelimiter);

  FReceived := FParser.SplitNumbers('//;\n150@2');    // hay errores en el delimitador.
  AssertEquals(0, FReceived.Items[0]);
  AssertEquals(1, FReceived.Count);
  AssertEquals(';', FParser.GetDefaultDelimiter);

  FReceived := FParser.SplitNumbers('//;\n150;2');
  AssertEquals(150, FReceived.Items[0]);
  AssertEquals(2, FReceived.Items[1]);
  AssertEquals(2, FReceived.Count);
  AssertEquals(';', FParser.GetDefaultDelimiter);

  FReceived := FParser.SplitNumbers('//@\n150@2');
  AssertEquals(150, FReceived.Items[0]);
  AssertEquals(2, FReceived.Items[1]);
  AssertEquals(2, FReceived.Count);
  AssertEquals('@', FParser.GetDefaultDelimiter);

  FNumbers := '//@\150@2';
  AssertException('Delimiter format error', ExceptionDelimiterFormat, @UnMetodoGenerico);
end;

procedure TestParser.UnMetodoGenerico;
begin
  FParser.SplitNumbers(FNumbers);
end;

procedure TestParser.SetUp; 
begin
  FParser := TParser.Create(',');
end;

procedure TestParser.TearDown; 
begin
  FreeAndNil(FParser);
end; 

initialization
  RegisterTest(TestParser); 
end.

