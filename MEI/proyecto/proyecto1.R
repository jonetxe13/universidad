lectura <- function(archivo, n) {
  # Abrir el archivo y leer los datos
  
  datos <- read.table(archivo)
  datos <- unlist(datos)
  datos <- sort(datos)
  
  media <- mean(datos)
  mediana <- median(datos)
  
  minimo <- min(datos)
  maximo <- max(datos)

  desv_est <- sd(datos)
  
  # Devolver los resultados en una lista
  return(list(media, mediana, minimo, maximo, desv_est))
}

representar_distribucion <- function(lista, a, b) {
  media <- lista$media
  desv_tipica <- lista$desv_est
  print(media)
  # Determinar si se debe utilizar la distribución "t de Student" o la distribución normal
  if (50 < 30) {
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

aniadir_area <- function(listaMedidas, a, b) {
  # Calcular la media y la desviación típica
  media <- listaMedidas$media
  desv_tipica <- listaMedidas$desv_est
  
  # Determinar si se debe utilizar la distribución "t de Student" o la distribución normal
  if (listaMedidas$muestra < 30) {
    # Utilizar la distribución "t de Student"
    x <- seq(a, b, length.out = 100)
    y <- dt(x, df = length(listaMedidas) - 1)
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

library(ggplot2)

# función 5: representar la distribución de la varianza
funcion_5 <- function(datos, n) {
  s2 <- var(a)
  x <- seq(1, 2 * s2, length.out = 100)
  y <- dchisq(x, df = n - 1)
  ggplot(data.frame(x, y), aes(x, y)) + geom_line() + 
    ggtitle(paste0("Distribución de la varianza (n = ", n, ")")) + 
    xlab("Varianza") + ylab("Densidad")
}

# función 6: calcular un intervalo de confianza para la varianza
funcion_6 <- function(datos, nivel_confianza) {
  s2 <- var(a)
  n <- length(a)*10000
  alpha <- 1 - nivel_confianza
  limite_inferior <- qchisq(alpha / 2, df = n - 1) * (n - 1) / n^2 * s2
  print(n-1)
  limite_superior <- qchisq(1 - alpha / 2, df = n - 1) * (n - 1) / n^2 * s2
  c(limite_inferior, limite_superior)
}

# función 7: rellenar el área correspondiente al intervalo de confianza en la gráfica de la función 5
funcion_7 <- function(datos, nivel_confianza) {
  s2 <- var(a)
  n <- length(a)
  limites <- funcion_6(datos, nivel_confianza)
  ggplot(data.frame(x = c(limites[1], limites[2]), y = c(0, 0)), 
         aes(x, y)) + geom_area(alpha = 0.2, fill = "blue") +
    scale_x_continuous(limits = c(0, 2 * s2)) +
    funcion_6(datos, nivel_confianza)
}

#funcion 8
lectura2 <- function(archivo) {
  a <- read.table(archivo, quote="\"", comment.char="")
  a <- unlist(a)
  a <- sort(a)
  # Abrir el archivo y leer los datos
  datos <- read.table(archivo)
  datos <- unlist(datos)
  datos <- sort(datos)
  # Tomar una muestra de tamaño 1 
  muestra <- sample(datos, size = 1)
  
  # Calcular la media
  media <- mean(datos)
  
  # Calcular la mediana
  mediana <- median(datos)
  
  # Calcular el mínimo y el máximo
  minimo <- min(datos)
  maximo <- max(datos)
  
  # Calcular la desviación estándar
  desv_est <- sd(datos)
  
  # agregar a la lista el nuevo campo muestra
  lista <- list(media, mediana, minimo, maximo, desv_est, muestra)
  names(lista) <- c("media", "mediana", "minimo", "maximo", "desv_est", "muestra")
  
  # Devolver los resultados en una lista
  return(lista)
}

