				

Program Basic_Calculator;
Uses Crt;
Var
 Operator: Char;
 Opt1,Opt2 : Integer;
 Sum,Subtract, Multiply: Integer;
 Divide : Integer;
 Result : Integer;
Begin
 clrscr;
 Opt1 := 0; Opt2 := 0;
 write('Simple Math Calculator');
 writeln; writeln;
 write('Enter Two Numbers : ');
 readln(Opt1,Opt2);
 write('Select Operators +,-,* and / : ');
 readln(Operator);
 if (Operator = '+') then
Begin
 Sum := (Opt1 + Opt2);
 Result := Sum;
End
else if (Operator = '-') then
Begin
 Subtract := (Opt1 - Opt2);
 Result := Subtract;
End
else if (Operator = '*') then
Begin
 Multiply := (Opt1 * Opt2);
 Result := Multiply;
End
else if (Operator = '/') then
Begin
 Divide := (Opt1 DIV Opt2);
 Result := Divide;
End;
 writeln;
 writeln;
 Write('The result is ' ,Result,'.');
 readln;
End.
