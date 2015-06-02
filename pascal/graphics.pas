Program Graphics;
{$apptype GUI}

Uses WinCrt, Graph;
  Var GrDriver, GrMode: Integer;
      {These vars are needed to initialize the graphics}
      ch : char;
    Begin
    // ClrScr;

     GrDriver := 0;
     GrMode := 0;
     //ShowWindow(GetActiveWindow,0);
     InitGraph (GrDriver, GrMode, '');
     {InitGraph is used to initialize the graphics mode}

     If GraphResult <> 0 Then
         {If an error occurs, print an error message}
      Begin
       WriteLn ('Couldn''t initialize graphics!');
       Halt (0); {This will halt the program}
      End;

     Line (0, 0, 640, 480);
     {Draw a line from point 0, 0 to 640, 480 on the screen}
     {This line will be drawn from the top left corner}
     {to the bottom right corner of the screen.}

     Repeat
      ch := ReadKey;
     Until ch = #27;
     {Repeat until ESC (chr value 27) is pressed}

     CloseGraph;
     {CloseGraph is used to clear the graph-memory
     once the program is done.}

End.
