unit CySAB;

interface
        procedure LightText(x,y,cor:integer; msg:string);
        procedure Box(x1,y1,x2,y2,cor:integer);

implementation uses crt;

procedure LightText(x,y,cor:integer; msg:string);
var
        c,tmp:integer;
begin
        tmp:=length(msg)-1;
        for c:=0 to tmp do begin
                gotoxy(x+c,y);
                textcolor(cor); write(copy(msg,c+1,1));
                if c < tmp - 1 then textcolor(cor+8); write(copy(msg,c+2,1));
                if c < tmp - 2 then textcolor(cor+8); write(copy(msg,c+3,1));
                if c < tmp - 3 then textcolor(white); write(copy(msg,c+4,1));
                if c < tmp - 4 then textcolor(white); write(copy(msg,c+5,1));
                delay(30);
        end;
end;

procedure Box(x1,y1,x2,y2,cor:integer);
var
        x,y:integer;
begin
if (abs(x1-x2)>1) or (abs(y1-y2)>1) then begin

        if x1>x2 then begin
                x:=x2;
                x2:=x1;
                x1:=x;
        end;
        if y1>y2 then begin
                y:=y2;
                y2:=y1;
                y1:=y;
        end;
        for x:=x1 to x2 do begin
                if (x<=x1+3) or (x>=x2-3) then textcolor(cor+8) else textcolor(cor);
                gotoxy(x,y1); write('Õ');
                gotoxy(x,y2); write('Õ');
        end;
        for y:=y1 to y2 do begin
                if (y=y1+1) or (y=y2-1) then textcolor(cor+8) else textcolor(cor);
                gotoxy(x1,y); write('∫');
                gotoxy(x2,y); write('∫');
        end;

        textcolor(white);
        gotoxy(x1,y1); write('…Õ');
        gotoxy(x2-1,y1); write('Õª');
        gotoxy(x1,y2); write('»Õ');
        gotoxy(x2-1,y2); write('Õº');
end;
end;

end.