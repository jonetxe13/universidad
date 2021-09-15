with ada.float_text_io; use ada.float_text_io;

  --AreaCuadrado--

procedure AreaCuadrado is
  lado: float;
  area: float;
begin
  Get(lado);
  Area := lado ** 2;
  Put(area);
end AreaCuadrado;