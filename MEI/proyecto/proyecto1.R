lectura <- function(archivo, n) {
  # Abrir el archivo y leer los datos
  datos <- read.table(archivo)

  # Convertir la lista de listas a una lista plana
  datos <- unlist(datos)
  
  # Ordenar los datos
  datos <- sort(datos)
  
  # Calcular la media
  media <- mean(datos)
  
  # Calcular la mediana
  mediana <- median(datos)
  
  # Calcular el mínimo y el máximo
  minimo <- min(datos)
  maximo <- max(datos)
  
  # Calcular la desviación estándar
  desv_est <- sd(datos)
  
  # Devolver los resultados en una lista
  return(list(media, mediana, minimo, maximo, desv_est))
}

representar_distribucion <- function(lista, a, b) {
  # Calcular la media y la desviación típica
  media <- mean(lista)
  desv_tipica <- sd(lista)
  
  # Determinar si se debe utilizar la distribución "t de Student" o la distribución normal
  if (length(lista) < 30) {
    # Utilizar la distribución "t de Student"
    x <- seq(a, b, length.out = 100)
    y <- dt(x, df = length(lista) - 1)
  } else {
    # Utilizar la distribución normal
    x <- seq(a, b, length.out = 100)
    y <- dnorm(x, mean = media, sd = desv_tipica)
  }
  
  # Generar el gráfico
  plot(x, y, type = "l", xlab = "x̄", ylab = "Probabilidad")
}

calcular_intervalo <- function(lista, alpha) {
  # Calcular el intervalo de confianza
  resultado <- t.test(lista, conf.level = 1 - alpha)
  
  # Devolver los extremos del intervalo
  return(c(resultado$conf.int[1], resultado$conf.int[2]))
}

aniadir_area <- function(lista, a, b) {
  # Calcular la media y la desviación típica
  media <- mean(lista)
  desv_tipica <- sd(lista)
  
  # Determinar si se debe utilizar la distribución "t de Student" o la distribución normal
  if (length(lista) < 30) {
    # Utilizar la distribución "t de Student"
    x <- seq(a, b, length.out = 100)
    y <- dt(x, df = length(lista) - 1)
  } else {
    # Utilizar la distribución normal
    x <- seq(a, b, length.out = 100)
    y <- dnorm(x, mean = media, sd = desv_tipica)
  }
  
  # Generar el gráfico
  plot(x, y, type = "l", xlab = "x̄", ylab = "Probabilidad")
  
  # Añadir el área correspondiente a P (a ≤ x ≤ b) al gráfico
  polygon(c(a, x, b), c(0, y, 0), col = "lightblue")
}
funcion_5<- function(lista) {
  # Calcular la varianza muestral
  varianza <- var(lista)
  
  # Generar valores de x en el intervalo [0, 2 ∗ s2 ]
  x <- seq(0, 2 * varianza, length.out = 100)
  
  # Calcular valores de y para cada valor de x
  y <- dchisq(x, df = length(lista) - 1)
  
  # Generar el gráfico
  curve(y, xlab = "Varianza", ylab = "Probabilidad", from = 0, to = 2 * varianza)
}

funcion_6 <- function(lista, alpha) {
  # Calcular la varianza muestral
  varianza <- var(lista)
  
  # Calcular los límites del intervalo de confianza para la varianza
  limite_inferior <- varianza * ((length(lista) - 1) / qt(1 - alpha / 2, df = length(lista) - 1))
  limite_superior <- varianza * ((length(lista) - 1) / qt(alpha / 2, df = length(lista) - 1))
  
  # Devolver los límites del intervalo de confianza
  return(c(limite_inferior, limite_superior))
}

funcion_7 <- function(lista, alpha) {
  # Calcular la varianza muestral
  varianza <- var(lista)
  
  # Generar valores de x en el intervalo [0, 2 ∗ s2 ]
  x <- seq(0, 2 * varianza, length.out = 100)
  
  # Calcular valores de y para cada valor de x
  y <- dchisq(x, df = length(lista) - 1)
  
  # Generar el gráfico
  curve(y, xlab = "Varianza", ylab = "Probabilidad", from = 0, to = 2 * varianza)
  
  # Calcular los límites del intervalo de confianza para la varianza
  limite_inferior <- varianza * ((length(lista) - 1) / qt(1 - alpha / 2, df = length(lista) - 1))
  limite_superior <- varianza * ((length(lista) - 1) / qt(alpha / 2, df = length(lista) - 1))
  
  # Añadir el área correspondiente al intervalo de confianza al gráfico
  polygon(c(limite_inferior, x, limite_superior), c(0, y, 0), col = "lightblue")
}


# Leer el archivo .txt y guardar los datos en una lista
datos <- lectura("~/Desktop/universidad/universidad/MEI/proyecto/a.txt")
datos <- read.table("~/Desktop/universidad/universidad/MEI/proyecto/a.txt")

# Convertir la lista de listas a una lista plana
datos <- unlist(datos)

# Ordenar los datos
datos <- sort(datos)
# Representar la distribución de x̄
representar_distribucion(datos, 5, 10)

# Calcular el intervalo de confianza para x̄ con un nivel de confianza del 95%
intervalo <- calcular_intervalo(datos, alpha = 0.05)
print(intervalo)

# Generar un gráfico con la distribución de x̄ y el área correspondiente al intervalo de confianza
aniadir_area(datos, intervalo[1], intervalo[2])

# Representar la distribución de la varianza
funcion_5(datos)

# Calcular el intervalo de confianza para la varianza con un nivel de confianza del 95%
funcion_6(datos, alpha = 0.05)

# Rellenar el área correspondiente al intervalo de confianza en el gráfico de la distribución de la varianza
funcion_7(datos, intervalo_varianza[1], intervalo_varianza[2])

