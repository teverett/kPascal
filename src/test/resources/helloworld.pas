
program HelloWorld;   
var
	i,j,k:  integer;

procedure adder(var a,b : integer; d :integer);
var
   i: integer;
 
begin
  for i := 1 to size do
     b[i] := a[i] + b[i];
end;

begin    
 write('Hello World.');
 readln;   
end.

