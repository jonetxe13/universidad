package proyectoBaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio7{
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // 1. Create the connection initially to null
            conn = DriverManager.getConnection("jdbc:oracle:thin:@myhost:1521:orcl", "myuser", "mypassword");
            // 2. Set autocommit to false
            conn.setAutoCommit(false);

            String sql = "INSERT INTO viajes (fecha, hotel, excursion, guia) " +
                         "SELECT add_months(fecha, 12), hotel, excursion, guia " +
                         "FROM viajes WHERE EXTRACT(YEAR FROM fecha) = 2022";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            // 3. End with commit in case of success (after finishing the transaction)
            conn.commit();
        } catch (SQLException e) {
            // or with rollback in case of error (within the catch)
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}