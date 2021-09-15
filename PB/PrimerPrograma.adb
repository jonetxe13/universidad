with Ada.Numerics.Elementary_Functions;
	use Ada.Numerics.Elementary_Functions;
with Ada.Integer_Text_IO;
	use Ada.Integer_Text_IO;
with Ada.Float_Text_IO;
	use Ada.Float_Text_IO;
procedure PrimerPrograma is
	pi: constant float := 3.14592;
       Texto: string (1 .. 10);
	Numero: integer;
  	Real: float;
  	Logico: boolean;
	Inicial: character;
begin
       null;
       numero := 1234_5 * 2 + 6*2;
       real := 3.14_5;--es igual q 3.145
       logico := 5 > 10; --:=false
       inicial := 'z';
       texto := "hola" & ' ' & "amigo";
       Get(Numero);
       Put(Numero);
end PrimerPrograma;
