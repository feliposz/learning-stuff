program teste4;
uses crt;
var i: integer;
begin
  clrscr;
  while true do
  begin
    gotoxy(1,1); {vai para primeira linha}
    writeln('':80); {limpa a linha toda}
    gotoxy(1,1); {volta para o come‡o da linha}
    write('Digite um valor: ');
    readln(i);
    writeln('Valor digitado: ', i);
  end;
end.