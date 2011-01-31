unit uParser;

{$mode objfpc}

interface

uses
  Classes, SysUtils, uHelperCalculator;

type
  { TParser }
  TParser = class(TInterfacedObject, IParserNumbers)
  private
    FDelimiterInitToken: string;
    FDefaultDelimiter: char;
    procedure SetValidDelimiter(var Numbers: string);
    function CheckAddendsValues(const Numbers: TStringList): TIntegerList;
    procedure SetDefaultDelimiter(value: char);
    property DelimiterInitToken: string read FDelimiterInitToken;
    property DefaultDelimiter: char read FDefaultDelimiter write SetDefaultDelimiter;
  public
    constructor Create(DelimiterChar: Char);
    function SplitNumbers(Numbers: string): TIntegerList;
    function GetDefaultDelimiter: char;
  end;



implementation

{ TParser }

procedure TParser.SetValidDelimiter(var Numbers: string);
var
  longInitToken: Byte;
begin
  longInitToken := Length(DelimiterInitToken);
 // writeln('01-'+Numbers+'   long: ' + intToStr(LongInitToken));
  if (copy(Numbers, 1, longInitToken) = DelimiterInitToken) then
  begin
   // writeln('02-'+ copy(Numbers, 1, longInitToken));
   // writeln('03-'+ copy(Numbers, longInitToken + 2 , 2));
    if (copy(Numbers, longInitToken + 2 , 2) = '\n') then
    begin
      DefaultDelimiter := Numbers[longInitToken + 1];
      Numbers := copy(Numbers, longInitToken + 1 + 2 + 1, length(Numbers));
    //  writeln('04-'+ DefaultDelimiter);
    //  writeln('05-'+ Numbers);
    end
    else
      raise ExceptionDelimiterFormat.Create('Delimiter format error');
  end;
  // '\n' is not a new line in Pascal.  It must be replaced as any other string.
  Numbers := StringReplace(Numbers, '\n', DefaultDelimiter, [rfReplaceAll] );
//  writeln('06-'+ Numbers);
end;

function TParser.CheckAddendsValues(const Numbers: TStringList): TIntegerList;
var
  x: Integer;
begin
  result := TIntegerList.Create;
  for x := 0 to Numbers.Count - 1 do
    result.Add( StrToIntDef( Numbers.Strings[x], 0) );
end;

procedure TParser.SetDefaultDelimiter(value: char);
begin
  if value = '' then
    FDefaultDelimiter := ','
  else
    FDefaultDelimiter := value;
end;

constructor TParser.Create(DelimiterChar: Char);
begin
  DefaultDelimiter := DelimiterChar;
  FDelimiterInitToken := '//';
end;

function TParser.SplitNumbers(Numbers: string): TIntegerList;
var
  tempStringList: TStringList;
  x: Integer;
begin
  SetValidDelimiter(Numbers);
  tempStringList := TStringList.Create;
  try
    with tempStringList do
    begin
      Clear;
      Delimiter:= DefaultDelimiter;
      StrictDelimiter := true;
      DelimitedText:= Numbers;
    end;

    result := TIntegerList.Create;
    for x := 0 to tempStringList.Count - 1 do
      result.Add( StrToIntDef(tempStringList.Strings[x], 0) );
  finally
    FreeAndNil(tempStringList);
  end;
end;

function TParser.GetDefaultDelimiter: char;
begin
  result := DefaultDelimiter;
end;

end.

