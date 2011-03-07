unit UStringCalculator;

interface

uses
  classes,
  windows;

type

  TStringCalculator = class
  private
    Delimiters : TStringList;
    function strToken(var S: String; Delimiters: TStringList): String; overload;
    function strToken(var S: String; Separator: string): String; overload;
    function ExtractOperandos(expresion: string; aStringList: TStringList): integer;
  public
    Result : integer;
    constructor Add(expression: string);
  published
  end;

implementation

{ TStringCalculator }

uses
  sysutils;

constructor TStringCalculator.Add(expression: string);
var
  i, NumeroOperandos: integer;
  Operandos: TStringList;
begin
  Result := 0;
  if expression = '' then exit;

  Delimiters := TStringList.Create;
  Delimiters.Add(',');
  Delimiters.Add('\n');

  Operandos := TStringList.Create;
  NumeroOperandos := ExtractOperandos(expression, Operandos);;

  for i:=0 to NumeroOperandos-1 do
  begin
    Result := Result + StrToInt(Operandos[i]);
  end;
  Operandos.Free;
  Delimiters.Free;
end;

function TStringCalculator.ExtractOperandos(expresion: string; aStringList: TStringList): integer;
var
  Operando: string;
begin
  Result := 0;
  while expresion <> '' do
  begin
    Operando := strToken(expresion, Delimiters);
    aStringList.Add(Operando);
    Inc(Result);
  end;
end;

function TStringCalculator.strToken(var S: String; Delimiters: TStringList): String;
var
  i : integer;
begin
  Result := '';
  for i := 0 to Delimiters.Count-1 do
  begin
    Result := strToken(S, Delimiters[i]);
    if Result <> '' then break;
  end;

  if Result = '' then
  begin
    Result := S;
    S := '';
  end;
end;

function TStringCalculator.strToken(var S: String; Separator: string): String;
var
  I : Word;
begin
  Result := '';
  I:=Pos(Separator,S);
  if I<>0 then
  begin
    Result:=System.Copy(S,1,I-1);
    System.Delete(S,1,I-1+Length(Separator));
  end;
end;

end.
