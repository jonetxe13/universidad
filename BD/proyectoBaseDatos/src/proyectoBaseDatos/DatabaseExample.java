package proyectoBaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExample {
    public static void main(String[] args) {
        // Establecer conexión con la base de datos MySQL
        try (Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/DBC49?&useSSL=false", "DBC49", "DBC49")) {
            // Obtener los datos del cliente con DNI 10000001
            String query = "SELECT * FROM cliente WHERE DNI = ?";
            try (PreparedStatement statement = mysqlConnection.prepareStatement(query)) {
                statement.setInt(1, 10000001);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("Nombre");
                        int address = resultSet.getInt("NTelefono");
                        // ...

                        // Establecer conexión con la base de datos Oracle
                        try (Connection oracleConnection = DriverManager.getConnection("jdbc:oracle:thin:@vsids11.si.ehu.es:1521:gipuzkoa", "BDC49", "BDC49")) {
                            // Insertar al cliente como guía en la base de datos Oracle
                            String insert = "INSERT INTO GUIA(DNI, NOMBRE, NTELEFONO) VALUES (?, ?, ?)";
                            try (PreparedStatement insertStatement = oracleConnection.prepareStatement(insert)) {
                                insertStatement.setInt(1, 10000001);
                                insertStatement.setString(2, name);
                                insertStatement.setInt(3, 123456789);
                                // ...
                                insertStatement.executeUpdate();
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}