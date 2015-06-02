unit CySGfX;

interface

        type
                Palette = array [0..255, 0..2] of byte;

        procedure SetText;
        procedure SetMCGA;
        procedure PutPix(PosX, PosY: integer; Color: byte);
        procedure SetColor(Color, Red, Green, Blue: byte);
        procedure SetPal(Pal: Palette);
        procedure FadeIn(Pal: Palette);
        procedure FadeOut(Pal: Palette);
        procedure FadeToGray(Pal: Palette);
        procedure Light;

implementation uses crt;

procedure SetText;
begin
        asm
                xor ah,ah
                mov al,03h
                int 10h
        end;
end;

procedure SetMCGA;
begin
        asm
                xor ah,ah
                mov al,13h
                int 10h
        end;
end;

procedure PutPix(PosX, PosY: integer; Color: byte);
begin
        asm
                mov ax,PosY
                mov di,PosX

                mov cl,6
                shl ax,cl
                add di,ax

                mov cl,2
                shl ax,cl
                add di,ax

                mov ax,0A000h
                mov es,ax

                xor ax,ax
                mov al,Color
                mov [es:di],al
        end;
end;

procedure SetColor(Color, Red, Green, Blue: byte);
begin
        asm
                mov dx,3C8h
                mov al,Color
                out dx,al
                inc dx
                mov al,Red
                out dx,al
                mov al,Green
                out dx,al
                mov al,Blue
                out dx,al
        end;
end;

procedure SetPal(Pal: Palette);
var
        a, b: integer;
        c: byte;
begin
        asm
                xor al,al
                mov dx,3C8h
                out dx,al
        end;
        for a:=0 to 255 do
                for b:=0 to 2 do begin
                        c:=Pal[a,b];
                        asm
                                mov al,c
                                mov dx,3C9h
                                out dx,al
                        end;
                end;
end;

procedure FadeIn(Pal: Palette);
var
        a, b, c: integer;
        TmpPal: Palette;
begin
        for a:=0 to 255 do
                for b:=0 to 2 do
                        TmpPal[a,b]:=0;
        for c:=0 to 63 do begin
                for a:=0 to 255 do
                        for b:=0 to 2 do
                                if TmpPal[a,b]<Pal[a,b] then TmpPal[a,b]:=TmpPal[a,b]+1;
                SetPal(TmpPal);
                delay(10);
        end;
end;

procedure FadeOut(Pal: Palette);
var
        a, b, c: integer;
        TmpPal: Palette;
begin
        for a:=0 to 255 do
                for b:=0 to 2 do
                        TmpPal[a,b]:=Pal[a,b];
        for c:=0 to 63 do begin
                for a:=0 to 255 do
                        for b:=0 to 2 do
                                if TmpPal[a,b]>0 then TmpPal[a,b]:=TmpPal[a,b]-1;
                SetPal(TmpPal);
                delay(5);
        end;
end;

procedure FadeToGray(Pal: Palette);
var
        a, b, c: integer;
        d: byte;
        TmpPal: Palette;
begin
        for a:=0 to 255 do begin
                c:=(Pal[a,0]+Pal[a,1]+Pal[a,2]) div 3;
                for b:=0 to 2 do
                        TmpPal[a,b]:=c;
        end;
        for c:=0 to 63 do begin
                for a:=0 to 255 do
                        for b:=0 to 2 do begin
                                if Pal[a,b]>TmpPal[a,b] then Pal[a,b]:=Pal[a,b]-1;
                                if Pal[a,b]<TmpPal[a,b] then Pal[a,b]:=Pal[a,b]+1;
                        end;
                SetPal(Pal);
                delay(5);
        end;
end;


procedure Light;
var
        x, c:integer;
begin
        for c:=63 downto 0 do begin
                delay(10);
                for x:=0 to 255 do
                        SetColor(x, c, c, c);
        end;
end;

end.
