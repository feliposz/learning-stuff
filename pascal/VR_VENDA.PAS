program Vr_Venda;

uses crt;

label
  Inicio,Volta;

var
  Estado:string;
  Nr_Itens,Qtde,Nr_Calculado:integer;
  Vr_Unit,Desc,Ipi,Vr_Item,Frete,Vr_Merc:Real;

begin
Inicio:;
  clrscr;
  writeln('Digite 0 para sair');
  write('Informe numero de itens (1-5):'); readln(Nr_Itens);

  if Nr_Itens=0 then begin
     writeln('Hasta la vista! Baby!');
     halt;
     end;

  if Nr_Itens>5 then begin
     writeln('Erro, numero de itens deve ser no maximo 5.');
     readln;
     goto inicio
     end;

Volta:;
  clrscr;
  writeln('Item no.:', Nr_Calculado + 1);
  writeln;
  write('Estado:         '); readln(Estado);
  write('Quantidade:     '); readln(Qtde);
  write('Valor Unitario: '); readln(Vr_Unit);

  Vr_Merc:=Vr_Unit * Qtde;
  if Vr_Merc <= 100 then
     Desc:=0
  else
      if Vr_Merc > 200 then
         Desc:=Vr_Merc * 0.12
      else
         Desc:=Vr_Merc * 0.1;

  if Estado='SP' then
     Ipi:=Vr_Merc * 0.15
  else
      if Estado='MG' then
         Ipi:=Vr_Merc * 0.17
      else
         Ipi:=Vr_Merc * 0.1;

  Vr_Item:=Vr_Merc + Ipi - Desc;
  if Vr_Item > 500 then
     Frete:=Vr_Item * 0.03
  else
     Frete:=Vr_Item * 0.02;

  clrscr;
  writeln('Item no.:', Nr_Calculado + 1);
  writeln;
  writeln('Estado:          ',Estado);
  writeln('Quantidade:      ',Qtde);
  writeln('Vr. Mercadoria:  ',Vr_Merc:5:2);
  writeln('Desconto:        ',Desc:5:2);
  writeln('IPI:             ',Ipi:5:2);
  writeln('Vr. Item:        ',Vr_Item:5:2);
  writeln('Frete:           ',Frete:5:2);

  Nr_Calculado:=Nr_Calculado + 1;
  if Nr_Calculado < Nr_Itens then begin
     writeln;
     writeln('Pressione ENTER para continuar');
     readln;
     goto Volta;
     end;

end.





