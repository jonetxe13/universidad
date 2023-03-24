setwd("~/Desktop/universidad/universidad/MEI/proyecto")
source("proyecto1.R")
# Leer el archivo .txt y guardar los datos en una lista
datos <- lectura2("a.txt")

# Representar la distribución de x̄
representar_distribucion(datos, 5, 10)

# Calcular el intervalo de confianza para x̄ con un nivel de confianza del 95%
intervalo <- calcular_intervalo(a, alpha = 0.05)
print(intervalo)

# Generar un gráfico con la distribución de x̄ y el área correspondiente al intervalo de confianza
aniadir_area(datos, intervalo[1], intervalo[2])

# Representar la distribución de la varianza
funcion_5(datos, 50)

# Calcular el intervalo de confianza para la varianza con un nivel de confianza del 95%
funcion_6(datos, 0.05)
intervalo_varianza <- funcion_6(datos, 0.05)
# Rellenar el área correspondiente al intervalo de confianza en el gráfico de la distribución de la varianza
funcion_7(datos, intervalo_varianza[1])

