program Teste2;
{Testando a impress∆o das vari†veis}

uses crt;

procedure AguardarTecla;
begin
  WriteLn('Pressione qualquer tecla para continuar.');
  ReadKey;
end;

var
  num_inteiro: integer;
  num_real: double;
  letra: char;
  nome: string[30];
  v_ou_f: boolean;


begin
num_inteiro := 30;
num_real := -13.5123131;
letra := 'X';
nome := 'Fulano';
v_ou_f := true;
ClrScr;
WriteLn(num_inteiro);
WriteLn(num_real:10:2);
WriteLn(letra);
WriteLn(nome);
WriteLn(v_ou_f);
AguardarTecla;
end.
