program exGoto;
label 1; 
var
   a : integer;

begin
   a := 10;
   (* repeat until loop execution *)
   1: repeat
      if( a = 15) then
      
      begin
         (* skip the iteration *)
         a := a + 1;
         goto 1;
      end;
      
      writeln('value of a: ', a);
      a:= a +1;
   until a = 20;
end.
