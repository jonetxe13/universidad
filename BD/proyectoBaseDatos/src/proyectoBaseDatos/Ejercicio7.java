package proyectoBaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio7{
    public static void main(String[] args) {
    	// inicializar la conexion a null
        Connection conn = null;
        try {
            // crear la conexion
            conn = DriverManager.getConnection("jdbc:oracle:thin:@vsids11.si.ehu.es:1521:gipuzkoa", "BDC49", "BDC49");
            System.out.println("Conexion hecha");
            // autocommit a falso
            conn.setAutoCommit(false);
            // seleccionar los viajes con fecha en 2022 y annadirle 12 meses, insertar estos datos en la tabla viaje
            String sqlSelect = "INSERT INTO viaje (Destino, fechaSalida, dias, ciudadSalida, dni, precioDia)"
            		+ "SELECT Destino, add_months(fechaSalida, 12), dias, ciudadSalida, dni, precioDia FROM viaje WHERE EXTRACT(YEAR FROM fechasalida) = 2022";
            try (PreparedStatement statement = conn.prepareStatement(sqlSelect)) {
            	statement.executeUpdate();
            	System.out.println("Insert completado");
            }
            // terminar con commit si sale bien
            conn.commit();
        } catch (SQLException e) {
        	System.out.println("error");
            // con rollback si hay algun problema
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
        	// cerrar si o si la conexion
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Cerrando la conexion...");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}