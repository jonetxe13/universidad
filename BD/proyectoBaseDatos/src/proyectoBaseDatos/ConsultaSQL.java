package proyectoBaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleConnection;

public class ConsultaSQL{
    public static void main(String[] args) {
        // Establecer conexión con la base de datos MySQL
        try (Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/DBC49?&useSSL=false", "DBC49", "DBC49")) {
            // Obtener todos los datos del cliente con DNI 10000001
            String query = "SELECT h.idhotel, h.Nombre, COUNT(h.IdHotel) as numIdHotel\n"
            		+ "FROM hotel_viaje_cliente hvc RIGHT JOIN hotel h\n"
            		+ "ON hvc.IdHotel=h.IdHotel\n"
            		+ "WHERE h.IdHotel LIKE 'h%'\n"
            		+ "GROUP BY h.IdHotel\n"
            		+ "HAVING COUNT(h.IdHotel)<=2";
            try (PreparedStatement statement = mysqlConnection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                	int num = 0;
                	for(int i = 0; i < 30; i++) {
                		if (resultSet.next()) {
                			num++;
                			//coger los datos de la consulta select
                			String idHotel = resultSet.getString("idHotel");
                			String nombre = resultSet.getString("Nombre");
                			int numIdHotel = resultSet.getInt("numIdHotel");
                			System.out.println(idHotel + " " + nombre + " " + numIdHotel);
                		}
                	}
                	System.out.println("\nEl numero total de tuplas es: " + num + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}