program Teste;
{Testando o escopo das vari veis}

uses crt;

var
  g1 : integer;

procedure MyProc;
var
  l1 : integer;
begin
  g1 := 1;
  l1 := 1;
  {g2 := 2;}
  WriteLn(g1);
  WriteLn(l1);
end;

procedure AguardarTecla;
begin
  WriteLn('Pressione qualquer tecla para continuar.');
  ReadKey;
end;

var
  g2 : integer;

begin
  ClrScr;
  MyProc;
  WriteLn(g1);
  WriteLn(g2);
  AguardarTecla;
end.
