package Administrativo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Servidor {
    public static void main(String[] args) {
        String url = "jdbc:mysql://sql3.freemysqlhosting.net\t:3306/sql3743457";
        String usuario = "sql3743457";
        String contraseña = "2EsS9CnHSi";
        Connection conector = null;
            try {
                conector = DriverManager.getConnection(url, usuario,contraseña);
                System.out.println("Conexion Exitosa");
            } catch (SQLException error) {
                System.out.println("Fallo al conexion");
            }
    }
}
