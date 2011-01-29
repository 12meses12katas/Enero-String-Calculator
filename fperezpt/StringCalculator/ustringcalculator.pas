unit uStringCalculator; 

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, fgl;

type
  TIntegerList = specialize TFPGList<Integer>;
  ExceptionNegativeAddend = Class(Exception);

  { TStringCalculator }
  TStringCalculator = class
  private
    FDefaultDelimiter: char;
    procedure ChangeValidDelimiters(var Addends: string);
    function SplitSumands(Addends: string): TStringList;
    function CheckSumandsValues(const Addends: TStringList): TIntegerList;
  public
    constructor Create(DefaultDelimiter: char=',');
    function Add(const Addends: string): Integer;
    property DefaultDelimiter: char read FDefaultDelimiter;
  end;


implementation

{ TStringCalculator }

procedure TStringCalculator.ChangeValidDelimiters(var Addends: string);
begin
  if (copy(Addends, 1, 2) = '//') then
  begin
    FDefaultDelimiter := Addends[3];
    Addends := copy(Addends, 4, length(Addends))
  end;
  // '\n' is not a new line in Pascal.  It must be replaced as any other string.
  Addends := StringReplace(Addends, '\n', DefaultDelimiter, [rfReplaceAll] );
end;

function TStringCalculator.SplitSumands(Addends: string): TStringList;
begin
  ChangeValidDelimiters(Addends);
  result := TStringList.Create;
  with result do
  begin
    Clear;
    Delimiter:= DefaultDelimiter;
    StrictDelimiter := true;
    DelimitedText:= Addends;
  end;
end;

function TStringCalculator.CheckSumandsValues(const Addends: TStringList): TIntegerList;
var
  x: Integer;
begin
  result := TIntegerList.Create;
  for x := 0 to Addends.Count - 1 do
  begin
    result.Add( StrToIntDef( Addends.Strings[x], 0) );
    if (result.Get(result.Count-1) < 0) then
      raise ExceptionNegativeAddend.Create('negatives not allowed');
  end;
end;

constructor TStringCalculator.Create(DefaultDelimiter: char);
begin
  if (DefaultDelimiter = '') then
    FDefaultDelimiter := ','
  else
    FDefaultDelimiter := DefaultDelimiter;
end;

function TStringCalculator.Add(const Addends: string): Integer;
var
  AddendList: TIntegerList;
  Addend: Integer;
begin
  if Addends = '' then exit(0);

  result := 0;

  AddendList := CheckSumandsValues( SplitSumands(Addends) );
  try
    for Addend in AddendList do
    begin
      inc(result, Addend);
    end;
  finally
    FreeAndNil(AddendList);
  end;
end;

end.

