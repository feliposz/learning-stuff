program teste;

uses
        crt,CySGfX,CySAB;

procedure EndScreen;
begin
        textbackground(black);
        clrscr;
        Box(1,1,80,24,magenta);
        Box(3,2,78,4,green);
        LightText(5,3,lightgray,'By CySAByTE and ... Somehow assisted by JazZ K! :o)');
        repeat
                Box( random(77)+2, random(18)+5, random(77)+2, random(18)+5 , random(6)+1 );
                delay(100);
        until false;
end;

procedure RainBall(x,y,rad,rot:integer);
var
        ang:integer;
begin
        for ang:=0 to 254 do
                PutPix( round(sin(ang*pi/127)*rad) + x, round(cos(ang*pi/127)*rad) + y, (ang + rot) mod 253 + 2 );
end;

var
        x, y, z: integer;
        tsin: array[0..360] of integer;
        tpal: Palette;
begin
        for x:=0 to 360 do
                tsin[x]:=round( 20 * sin(x*pi/180) );
        for x:=1 to 127 do begin
                tpal[x,0]:=64 - x div 2; {x mod 64;}
                tpal[x+127,1]:=x div 2;
                tpal[x,2]:=64 - x div 2; {64 - x div 4;}
        end;
        tpal[255,0]:=63; tpal[255,1]:=63; tpal[255,2]:=63;
        SetMCGA;
        for x:=0 to 319 do
                for y:=0 to 199 do begin
                        z:=y + tsin[x];
                               if z<0 then z:=0;
                        if z>199 then z:=199;
                        PutPix(x, y, z+28);
                end;
        FadeIn(tpal);
        repeat
                x:=Random(260)+30;
                y:=Random(140)+30;
                for z:=0 to random(20)+5 do
                        RainBall(x,y,z,z*10);
                delay(10);
        until KeyPressed;
        FadeOut(tpal);
        SetText;
        EndScreen;
end.