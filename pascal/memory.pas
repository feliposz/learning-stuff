program memory;

var
  p1, p2, p3: ^integer;

begin
  new(p1);
  new(p2);
  new(p3);

  p1^ := 10;
  p2^ := 20;
  p3^ := p1^ + p2^;

  writeln('address of p1 = ', PtrUInt(@p1),
          ', p2 = ', PtrUInt(@p2), ', p3 = ', PtrUInt(@p3));
  writeln('address pointed by p1 = ', PtrUInt(p1),
          ', p2 = ', PtrUInt(p2), ', p3 = ', PtrUInt(p3));
  writeln('value at address pointed by p1 = ', p1^, ', p2 = ', p2^,
          ', p3 = ', p3^);

  dispose(p1);
  dispose(p2);
  dispose(p3);

  readln;
end.

