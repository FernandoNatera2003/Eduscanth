package Administrativo;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Exportar_Base {
    //private  String direccion = "jdbc:mysql://localhost:3306/";
    private  String direccion="";
    private  String usuario = "";
    private  String clave = "";
    private  String host="";
    private String puerto="";
    private  String nombre_base="";
    //private  String nombre_base = "EduScan";
    private String Credencial="Credencial.txt";

    // Metodo para verificar si existe la base
    private boolean tablaExiste(Connection connection, String dbName) throws SQLException {
        Obtener_Credenciales();
        String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dbName);
            statement.setString(2, "Administrativo");
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    private void Obtener_Credenciales()  {
            try (BufferedReader reader = new BufferedReader(new FileReader(Credencial))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Leer las credenciales y almacenarlas
                    if (line.startsWith("username=")) {
                        usuario = line.split("=")[1];
                    } else if (line.startsWith("password=")) {
                        clave = line.split("=")[1];
                    }
                    else if (line.startsWith("host=")) {
                        host = line.split("=")[1];
                    } else if(line.startsWith("puerto=")) {
                        puerto = line.split("=")[1];
                    }
                    else if(line.startsWith("nb=")) {
                        nombre_base = line.split("=")[1];
                    }
                }
                //private  String direccion = "jdbc:mysql://localhost:3306/";
                direccion="jdbc:mysql://"+host+":"+puerto+"/";
            }catch(Exception error){

            }
    }
    // Metodos para ejecutar los scripts
    public boolean importarBaseDeDatos() {
        Obtener_Credenciales();
        boolean bandera=false;
        try (Connection connection = DriverManager.getConnection(direccion, usuario, clave)) {
            // Verificar si la base de datos ya existe
            if (tablaExiste(connection, nombre_base)) {
                //System.out.println("La base de datos ya existe. No se realizará ninguna importación.");
            }else {
                // Leer el archivo .sql desde los recursos
                InputStream carrera = Exportar_Base.class.getResourceAsStream("/Carrera.sql");
                InputStream materia = Exportar_Base.class.getResourceAsStream("/Materia.sql");
                InputStream profesor = Exportar_Base.class.getResourceAsStream("/Profesor.sql");
                InputStream grupo = Exportar_Base.class.getResourceAsStream("/Grupo.sql");
                InputStream MPG = Exportar_Base.class.getResourceAsStream("/Materia_Profesor_Grupo.sql");
                InputStream alumno = Exportar_Base.class.getResourceAsStream("/Alumno.sql");
                InputStream laboratorio = Exportar_Base.class.getResourceAsStream("/Laboratorio.sql");
                InputStream Adm = Exportar_Base.class.getResourceAsStream("/Administrativo.sql");
                InputStream AU = Exportar_Base.class.getResourceAsStream("/AU.sql");
                InputStream CU = Exportar_Base.class.getResourceAsStream("/CreacionUsuario.sql");
                InputStream AP = Exportar_Base.class.getResourceAsStream("/Privilegios.sql");
                // Leer todo el contenido del archivo SQL
                String s1 = new String(carrera.readAllBytes(), StandardCharsets.UTF_8);
                String s2 = new String(materia.readAllBytes(), StandardCharsets.UTF_8);
                String s3 = new String(profesor.readAllBytes(), StandardCharsets.UTF_8);
                String s4 = new String(grupo.readAllBytes(), StandardCharsets.UTF_8);
                String s5 = new String(MPG.readAllBytes(), StandardCharsets.UTF_8);
                String s6 = new String(alumno.readAllBytes(), StandardCharsets.UTF_8);
                String s7 = new String(laboratorio.readAllBytes(), StandardCharsets.UTF_8);
                String s8 = new String(Adm.readAllBytes(), StandardCharsets.UTF_8);
                String s9 = new String(AU.readAllBytes(), StandardCharsets.UTF_8);
                String s10 = new String(CU.readAllBytes(), StandardCharsets.UTF_8);
                String s11 = new String(AP.readAllBytes(), StandardCharsets.UTF_8);
                // Crear la base de datos si no existe y luego importar los datos
                Statement statement = connection.createStatement();
                statement.execute("CREATE DATABASE IF NOT EXISTS " + nombre_base); // Crear la base de datos si no existe
                statement.execute("USE " + nombre_base); // Seleccionar la base de datos para importar

                // Ejecutar el script SQL para importar la estructura y los datos
                statement.execute(s1);
                statement.execute(s2);
                statement.execute(s3);
                statement.execute(s4);
                statement.execute(s5);
                statement.execute(s6);
                statement.execute(s7);
                statement.execute(s8);
                statement.execute(s9);
                JOptionPane.showMessageDialog(null, "Base de datos importada correctamente.", "Mensaje de Exito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | IOException e) {
            bandera=true;
            JOptionPane.showMessageDialog(null,"Error con las Credenciales","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            Borrar_Credenciales();
        }
        return bandera;
    }
    private void Borrar_Credenciales(){
        String rutaArchivo = "Credencial.txt";
        File archivo = new File(rutaArchivo);
        if (archivo.delete()) {
            System.out.println("El archivo fue borrado exitosamente.");
        } else {
            System.out.println("No se pudo borrar el archivo.");
        }
    }
}

