program vr_ajust;

uses
    crt;

label
    volta, erro;

var
    vr, nvr, dias: real;
    cod, op: char;


begin

volta:;
     clrscr;
     write('Entre com o valor:'); readln(vr);
     write('N§ de dias:'); readln(dias);

erro:;
     write('Codigo de ajuste:'); readln(cod);

     if (cod <> '+') and (cod <> '-') then
        begin
             writeln('O codigo deve ser "+" ou "-"!');
             goto erro;
        end;

     if dias>30 then
        if cod='+' then
           nvr:=vr*1.15
        else
           nvr:=vr*1.10
     else
        if cod='+' then
           nvr:=vr*1.07
        else
           nvr:=vr*1.05;

     writeln('Valor calculado:', nvr:10:2);

     write('Deseja continuar (s/n)?'); readln(op);
     if upcase(op)='S' then goto volta;

end.
