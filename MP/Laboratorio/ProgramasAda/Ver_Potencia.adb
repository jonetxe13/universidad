procedure Ver_Potencia is
  
   function Potencia (N:Natural) return Natural is
   begin --{ N Natural}
      if N = 0 then
        return 1;
      else
        return 2 * Potencia(N - 1);
      end if;
   end Potencia; --{ 2^N }

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
