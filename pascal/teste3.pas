program Teste3;
uses
  crt;
var
  i, c: integer;
begin
  ClrScr;
  WriteLn('Cabe��o Acentua��o');
  WriteLn('� divertido ou n�o �?');
  WriteLn('Freq�entemente e � vontade... ');

  writeln('Tabela ASCII Estendida');
  for i := 0 to 255 do
  begin
    if (i = 7) or (i = 8) or (i = 10) or (i = 13)
    then c := 32
    else c := i;
    write(i:3, ' ', chr(c), ' ');
    if (i + 1) mod 8 = 0 then writeln;
  end;

  readkey;
end.
