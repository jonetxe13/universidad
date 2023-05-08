with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;

procedure Ver_Potencia is
   -- entrada: 1 natural, Num 
   -- salida: 4 naturales
   -- post: Los cuatro naturales corresponden a 4 de casos de prueba
   --    de la funci�n Potencia.

   function Potencia (N:Natural) return Natural is
   -- EJERCICIO 1- ESPECIFICA E IMPLEMENTA recursivamente el subprograma
   --   Potencia que calcula la n-�sima potencia de 2.

   begin
      -- Completar
      if N = 0 then
        return 1;
      else
        return 2 * Potencia(N - 1);
      end if;
   end Potencia;

  Num: Natural;

begin
      ---------- PRUEBAS EXPL�CITAS A PROBAR
   Put_Line ("--------------------------------");
   Put("   CASO1: 2^0= "); put(2**0, 0); put(", y con tu programa es --> ");    Put (Potencia(0), 0); Put_Line(".");
   New_Line;New_Line;
   Put_Line ("--------------------------------");
   Put("   CASO2: 2^1= "); put(2**1, 0); put(", y con tu programa es --> ");    Put (Potencia(1), 0); Put_Line(".");
   New_Line;New_Line;
   Put_Line ("--------------------------------");
   Put("   CASO3: 2^5= "); put(2**5, 0); put(", y con tu programa es --> ");    Put (Potencia(5), 0); Put_Line(".");
   New_Line;New_Line;
   Put_Line ("--------------------------------");
   Put("   CASO4: 2^15= "); put(2**15, 0); put(", y con tu programa es --> ");    Put (Potencia(15), 0); put_line(".");
   New_Line;New_Line;
   Put_Line ("--------------------------------");
   Put_Line ("--------------------------------");
   Put("   CASO5: Elige el exponente: ");
   Get (Num);
   Put("       2^");put(Num,0);put("= ");put(2**Num,0);put(", y con tu programa es --> ");
   Put(Potencia(Num), 0);
end Ver_Potencia;
