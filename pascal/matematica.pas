program Matematica;

uses
  math;

var
  x, y, z: real;
  i, j: integer;

begin
  x := 2.0;
  y := 3.0;
  writeln('x=', x);
  writeln('y=', y);
  z := power(x, y);
  writeln('power(x,y)=',z);
  z := exp(ln(x)*y); {outra forma de calcular a potenciacao}
  writeln('exp(ln(x)*y)=',z);
  z := sqrt(x);
  writeln('sqrt(x)=', z);
  z := exp(ln(x)*(1/y)); {uma forma de calcular raises arbitr rias}
  writeln('exp(ln(x)/y)=', z);
  i := 10;
  j := 3;
  writeln(i div j);
  writeln(i mod j);
  writeln(i / j);
  readln;
end.
