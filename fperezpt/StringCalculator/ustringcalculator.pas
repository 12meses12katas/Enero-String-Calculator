unit uStringCalculator; 

{$mode objfpc}{$H+}

interface

uses
  Classes, SysUtils, fgl;

type
  TIntegerList = specialize TFPGList<Integer>;
  ExceptionNegativeAddend = Class(Exception);
  ExceptionDelimiterFormat = Class(Exception);

  { TStringCalculator }
  TStringCalculator = class
  private
    FDelimiterInitToken: string;
    FDefaultDelimiter: char;
    procedure SetValidDelimiters(var Addends: string);
    function SplitAddends(Addends: string): TStringList;
    function CheckAddendsValues(const Addends: TStringList): TIntegerList;
    property DelimiterInitToken: string read FDelimiterInitToken;
  public
    constructor Create(DefaultDelimiter: char=',');
    function Add(const Addends: string): Integer;
    property DefaultDelimiter: char read FDefaultDelimiter;
  end;


implementation

{ TStringCalculator }

procedure TStringCalculator.SetValidDelimiters(var Addends: string);
var
  longInitToken: Byte;
begin
  longInitToken := Length(DelimiterInitToken);
  if (copy(Addends, 1, longInitToken) = DelimiterInitToken) then
  begin
    if (copy(Addends, longInitToken + 2 , 2) = '\n') then
    begin
      // Get default token
      FDefaultDelimiter := Addends[longInitToken + 1];
      // Remove init token + delimiter (1) + \n separator (2).
      Addends := copy(Addends, longInitToken + 1 + 2 + 1, length(Addends));
    end
    else
    begin
      raise ExceptionDelimiterFormat.Create('Delimiter format error');
    end;
  end;
  // '\n' is not a new line in Pascal.  It must be replaced as any other string.
  Addends := StringReplace(Addends, '\n', DefaultDelimiter, [rfReplaceAll] );
end;

function TStringCalculator.SplitAddends(Addends: string): TStringList;
begin
  SetValidDelimiters(Addends);
  result := TStringList.Create;
  with result do
  begin
    Clear;
    Delimiter:= DefaultDelimiter;
    StrictDelimiter := true;
    DelimitedText:= Addends;
  end;
end;

function TStringCalculator.CheckAddendsValues(const Addends: TStringList): TIntegerList;
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

  FDelimiterInitToken := '//';
end;

function TStringCalculator.Add(const Addends: string): Integer;
var
  AddendList: TIntegerList;
  Addend: Integer;
begin
  if Addends = '' then exit(0);

  result := 0;

  AddendList := CheckAddendsValues( SplitAddends(Addends) );
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

