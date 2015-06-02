program graficos;

uses
  WinCrt, { Para usar o ReadKey no modo Windows }
  Graph;  { Funá‰es gr†ficas OBS: s¢ utilizar para gr†ficos bem simples! }

var
  gd, gm: integer; { ê preciso declarar estas vari†veis para o initgraph }

begin

  { Inicia o modo gr†fico c/ suporte a 256 cores em resoluá∆o 640x480 }
  gd := D8bit;     { 8 bits = 256 cores }
  gm := m640x480;  { resoluá∆o }
  InitGraph(gd, gm, '');

  { Limpa a tela. (Somente um exemplo, n∆o Ç necess†rio no comeáo.) }
  { S¢ est† aqui para diferenciar do ClrScr !!! }

  ClearDevice;

  { Desenha duas linhas coloridas, nas diagonais da tela }

  SetColor(LightBlue);
  Line(0, 0, 639, 479);
  SetColor(LightRed);
  Line(0, 479, 639, 0);

  { Espera uma tecla ser apertada }
  ReadKey;

  { Define o estilo e a cor de preenchimento da figura }
  SetFillStyle(SolidFill, Blue);

  { Desenha um retangulo preenchido nas coordenadas dadas }
  Bar(50, 50, 590, 430);

  ReadKey;

  { Define a cor da linha }
  SetColor(White);

  { Desenha um retÉngulo (somente a linha) }
  Rectangle(50, 50, 590, 430);

  ReadKey;

  { Altera as cores para desenhar uma elipse }
  SetColor(LightGray);
  SetFillStyle(SolidFill, DarkGray);

  { Desenha a figura a partir do ponto 320,240 (centro) }
  { Com a largura 200 e a altura 100}
  FillEllipse(320, 240, 200, 100);

  ReadKey;

  { Desenha alguns c°rculos em volta }
  SetColor(Magenta);
  Circle(100, 100, 30);
  ReadKey();
  SetColor(Cyan);
  Circle(540, 100, 30);
  ReadKey();
  SetColor(Black);
  Circle(100, 380, 30);
  ReadKey();
  SetColor(Yellow);
  Circle(540, 380, 30);
  ReadKey;

  { Muda as cores para desenhar a barra 3D a seguir }
  SetColor(Green);
  SetFillStyle(SolidFill, Red);

  { Desenha uma caixa em 3D bem fajuta. Para desenhar gr†ficos de barra... }
  Bar3D(280, 200, 360, 280, 20, True);

  ReadKey;

  SetColor(White);
  OutTextXY(290, 235, 'FIM \o/');

  ReadKey;

  { Fecha o modo gr†fico }
  CloseGraph;

end.
