package Interfaces;
import Administrativo.Obtener_Credenciales;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Conexion_EduScan {
    //private String url = "jdbc:mysql://localhost:3306/EduScan";
    //private String usuario = "root";
    //private String contraseña = "2003";
    private String url="";
    private String usuario="";
    private String contraseña="";
    Connection conector = null;
    private String Credencial="Credencial.txt";
    private String host="";
    private String puerto="";
    private String nombrebase="";

    private void Conectar_EduScan() {
        Obtener_Credenciales();
        try {
            conector = DriverManager.getConnection(this.url, this.usuario, this.contraseña);
        } catch (SQLException error) {
        }
    }
    public boolean Credenciales(){
        Obtener_Credenciales();
        boolean bandera=true;
        try{
            conector = DriverManager.getConnection(this.url, this.usuario, this.contraseña);
        }catch(Exception error){
            bandera=false;
        }
        if(bandera){
            this.Desconectar_EduScan();
        }

        return bandera;
    }
    public void Borrar_Credenciales(){
        String rutaArchivo = "Credencial.txt";
        File archivo = new File(rutaArchivo);
        if (archivo.delete()) {
            System.out.println("El archivo fue borrado exitosamente.");
        } else {
            System.out.println("No se pudo borrar el archivo.");
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
                    contraseña = line.split("=")[1];
                }
                else if (line.startsWith("host=")) {
                    host = line.split("=")[1];
                } else if(line.startsWith("puerto=")) {
                    puerto = line.split("=")[1];
                }
                else if(line.startsWith("nb=")) {
                    nombrebase = line.split("=")[1];
                }
            }
            //private  String direccion = "jdbc:mysql://localhost:3306/";
            url="jdbc:mysql://"+host+":"+puerto+"/"+nombrebase;
        }catch(Exception error){

        }
    }
    private void Desconectar_EduScan() {
        try {
            conector.close();
        } catch (SQLException error) {

        }
    }
    //Verificacion
//    public boolean Verificacion(String nombre, String matricula) {
//        this.Conectar_EduScan();
//        boolean bandera = false;
//        try {
//            String sql = "SELECT * FROM Profesor where Nombre_Profesor='" + nombre + "' and Matricula_Profesor='" + matricula + "'";
//            Statement stmt = conector.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            if (rs.next()) {
//                bandera = true;
//            } else {
//                bandera = false;
//            }
//
//        } catch (SQLException error) {
//            System.out.println("error detectado");
//        }
//        this.Desconectar_EduScan();
//        return bandera;
//    }
    //Materias
//    public String[] Materias(String nombre) {
//        String Tira_Materia[] = null;
//        this.Conectar_EduScan();
//        try {
//            String sql = "select \n" +
//                    "Carrera.Nombre_Carrera,\n" +
//                    "Materia.Nombre_Materia,\n" +
//                    "Profesor.Nombre_Profesor,\n" +
//                    "Profesor.Paterno_Profesor,\n" +
//                    "Profesor.Materno_Profesor,\n" +
//                    "Grupo.Num_Grupo,\n" +
//                    "Grupo.Semestre_Grupo \n" +
//                    "from Materia_Profesor_Grupo join Materia on\n" +
//                    "Materia_Profesor_Grupo.id_Materia=Materia.id_Materia \n" +
//                    "join Profesor on \n" +
//                    "Materia_Profesor_Grupo.id_Profesor=Profesor.id_Profesor \n" +
//                    "join Grupo on \n" +
//                    "Materia_Profesor_Grupo.id_Grupo=Grupo.id_Grupo \n" +
//                    "join Carrera on \n" +
//                    "Carrera.id_Carrera=Grupo.id_Carrera where Profesor.Nombre_Profesor='" + nombre + "'";
//            Statement stmt = conector.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            int i = 0;
//            while (rs.next()) {
//                i++;
//            }
//            Tira_Materia = new String[i];
//            i = 0;
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                Tira_Materia[i] = rs.getString("Nombre_Materia");
//                i++;
//            }
//        } catch (SQLException error) {
//            System.out.println("error detectado");
//        }
//        this.Desconectar_EduScan();
//        return Tira_Materia;
//    }
    //Laboratorios
//    public void Laboratorios(JComboBox LAB){
//        this.Conectar_EduScan();
//        try {
//            String sql = "SELECT * FROM Laboratorio";
//            Statement stmt = conector.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()) {
//                String nombre=rs.getString("nombre_Laboratorio");
//                LAB.addItem(nombre);
//            }
//
//        } catch (Exception error) {
//            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
//            System.out.println(error.getMessage());
//        }
//        this.Desconectar_EduScan();
//    }
    //Grupos
//    public String[] Grupos(String nombre, String materia) {
//        String grupos[] = null;
//        try {
//            this.Conectar_EduScan();
//            String sql="select \n" +
//                    "Grupo.Num_Grupo\n" +
//                    "from Materia_Profesor_Grupo join Materia \n" +
//                    "on Materia_Profesor_Grupo.id_Materia=Materia.id_Materia \n" +
//                    "join Profesor on Materia_Profesor_Grupo.id_Profesor=Profesor.id_Profesor join Grupo\n" +
//                    "on Materia_Profesor_Grupo.id_Grupo=Grupo.id_Grupo  where Profesor.Nombre_Profesor='"+nombre+"' and Materia.Nombre_Materia='"+materia+"'";
//            Statement stmt = conector.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            int i = 0;
//            while (rs.next()) {
//                i++;
//            }
//            grupos = new String[i];
//            i = 0;
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                grupos[i] = rs.getString("Num_Grupo");
//                i++;
//            }
//
//        }catch (Exception error) {
//            System.out.println("Surgio un error");
//
//        }
//        this.Desconectar_EduScan();
//        return grupos;
//    }
    //Carrera
//    public String carrera(int grupo){
//        this.Conectar_EduScan();
//        String carrera="";
//        try{
//            String sql="select \n" +
//                    "Carrera.Nombre_Carrera from Grupo join Carrera on \n" +
//                    "Grupo.id_Carrera=Carrera.id_Carrera where Grupo.Num_Grupo="+grupo;
//            Statement stmt = conector.createStatement();
//            ResultSet rs=stmt.executeQuery(sql);
//            while (rs.next()) {
//                carrera=rs.getString("Nombre_Carrera");
//            }
//
//        } catch (Exception error){
//
//        }
//        this.Desconectar_EduScan();
//        return carrera;
//    }
    //Registro Alumno
//    public String[] Registro_Alumno(String matricula) throws SQLException {
//        this.Conectar_EduScan();
//        String Datos_Alumno[]=new String[4];
//
//            String sql="select *from Alumno where Matricula_Alumno='"+matricula+"';";
//            Statement stmt = conector.createStatement();
//            ResultSet rs=stmt.executeQuery(sql);
//            int i=0;
//            while(rs.next()){
//                i++;
//            }
//            rs=stmt.executeQuery(sql);
//            while(rs.next()){
//                Datos_Alumno[0]=rs.getString("Nombre_Alumno");
//                Datos_Alumno[1]=rs.getString("Paterno_Alumno");
//                Datos_Alumno[2]=rs.getString("Materno_Alumno");
//                Datos_Alumno[3]=rs.getString("Matricula_Alumno");
//            }
//            if(i==0){
//                Datos_Alumno=null;
//            }
//
//        this.Desconectar_EduScan();
//        return Datos_Alumno;
//    }
    //Obtener Semestre
//    public int Obtener_semestre(String grupo){
//        int semestre=0;
//        this.Conectar_EduScan();
//        try {
//            String sql = "SELECT * FROM Grupo where Num_Grupo='" +grupo+"'";
//            Statement stmt = conector.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()) {
//                semestre=rs.getInt("Semestre_Grupo");
//            }
//        } catch (Exception error) {
//            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
//        }
//        this.Desconectar_EduScan();
//        return semestre;
//    }

    //Metodos Corregidos Seguridad, Recursos y Optimizacion
    public boolean Verificacion(String nombre, String matricula) {
        this.Conectar_EduScan(); // Conecta a la base de datos
        boolean bandera = false;

        String sql = "SELECT 1 FROM Profesor WHERE Nombre_Profesor = ? AND Matricula_Profesor = ?";
        try (PreparedStatement stmt = conector.prepareStatement(sql)) {
            stmt.setString(1, nombre);  // Sustituye el primer ?
            stmt.setString(2, matricula);  // Sustituye el segundo ?

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    bandera = true;  // Existe al menos un registro que coincide
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar: " + e.getMessage()); // Manejo de errores más específico
        } finally {
            this.Desconectar_EduScan(); // Asegúrate de desconectar incluso si ocurre un error
        }

        return bandera;
    }
    public String[] Materias(String nombre) {
        this.Conectar_EduScan();  // Conectar a la base de datos
        List<String> materias = new ArrayList<>();  // Usamos ArrayList para manejar dinámicamente los resultados

        String sql = "SELECT Materia.Nombre_Materia\n" +
                "FROM Materia_Profesor_Grupo\n" +
                "JOIN Materia ON Materia_Profesor_Grupo.id_Materia = Materia.id_Materia\n" +
                "JOIN Profesor ON Materia_Profesor_Grupo.id_Profesor = Profesor.id_Profesor\n" +
                "WHERE Profesor.Nombre_Profesor = ?";

        try (PreparedStatement stmt = conector.prepareStatement(sql)) {
            stmt.setString(1, nombre);  // Sustituir el parámetro

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    materias.add(rs.getString("Nombre_Materia"));  // Agregar cada materia al ArrayList
                }
            }
        } catch (SQLException e) {
            System.out.println("Error detectado: " + e.getMessage());  // Mostrar error específico
        } finally {
            this.Desconectar_EduScan();  // Desconectar siempre
        }

        // Convertir la lista a un array y retornarla
        return materias.toArray(new String[0]);
    }
    public void Laboratorios(JComboBox<String> LAB) {
        this.Conectar_EduScan();  // Conectar a la base de datos

        String sql = "SELECT nombre_Laboratorio FROM Laboratorio";

        try (PreparedStatement stmt = conector.prepareStatement(sql);  // Usar PreparedStatement por buenas prácticas
             ResultSet rs = stmt.executeQuery()) {

            boolean hasResults = false;
            while (rs.next()) {
                String nombre = rs.getString("nombre_Laboratorio");
                LAB.addItem(nombre);  // Agregar cada laboratorio al JComboBox
                hasResults = true;
            }

            if (!hasResults) {
                JOptionPane.showMessageDialog(null, "No se encontraron laboratorios.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento.", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al obtener laboratorios: " + e.getMessage());  // Mensaje más específico
        } finally {
            this.Desconectar_EduScan();  // Desconectar siempre
        }
    }
    public String[] Grupos(String nombre, String materia) {
        String[] grupos = null;

        String sql = "SELECT Grupo.Num_Grupo " +
                "FROM Materia_Profesor_Grupo " +
                "JOIN Materia ON Materia_Profesor_Grupo.id_Materia = Materia.id_Materia " +
                "JOIN Profesor ON Materia_Profesor_Grupo.id_Profesor = Profesor.id_Profesor " +
                "JOIN Grupo ON Materia_Profesor_Grupo.id_Grupo = Grupo.id_Grupo " +
                "WHERE Profesor.Nombre_Profesor = ? AND Materia.Nombre_Materia = ?";

        try {
            this.Conectar_EduScan();  // Conectar a la base de datos

            // Usar PreparedStatement para evitar inyección SQL
            try (PreparedStatement stmt = conector.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                stmt.setString(1, nombre);
                stmt.setString(2, materia);

                try (ResultSet rs = stmt.executeQuery()) {
                    rs.last();  // Moverse al final para contar filas
                    int numRows = rs.getRow();  // Obtener cantidad de filas
                    grupos = new String[numRows];

                    rs.beforeFirst();  // Volver al inicio
                    int i = 0;
                    while (rs.next()) {
                        grupos[i++] = rs.getString("Num_Grupo");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener grupos: " + e.getMessage());
        } finally {
            this.Desconectar_EduScan();  // Asegurar desconexión
        }

        return grupos;
    }

    public String carrera(int grupo) {
        String carrera = "";
        String sql = "SELECT Carrera.Nombre_Carrera " +
                "FROM Grupo " +
                "JOIN Carrera ON Grupo.id_Carrera = Carrera.id_Carrera " +
                "WHERE Grupo.Num_Grupo = ?";

        try {
            this.Conectar_EduScan();  // Conectar a la base de datos

            // Usar PreparedStatement para evitar inyección SQL
            try (PreparedStatement stmt = conector.prepareStatement(sql)) {
                stmt.setInt(1, grupo);  // Establecer el parámetro en la consulta

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {  // Si hay resultados
                        carrera = rs.getString("Nombre_Carrera");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener carrera: " + e.getMessage());
        } finally {
            this.Desconectar_EduScan();  // Asegurar desconexión
        }

        return carrera;
    }
    public String[] Registro_Alumno(String matricula) throws SQLException {
        String[] Datos_Alumno = null;
        String sql = "SELECT Nombre_Alumno, Paterno_Alumno, Materno_Alumno, Matricula_Alumno " +
                "FROM Alumno WHERE Matricula_Alumno = ? and Activo='true'";

        try {
            this.Conectar_EduScan();  // Conectar a la base de datos

            // Usar PreparedStatement para evitar inyección SQL
            try (PreparedStatement stmt = conector.prepareStatement(sql)) {
                stmt.setString(1, matricula);  // Establecer el parámetro en la consulta

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {  // Si encontramos un alumno
                        Datos_Alumno = new String[4];
                        Datos_Alumno[0] = rs.getString("Nombre_Alumno");
                        Datos_Alumno[1] = rs.getString("Paterno_Alumno");
                        Datos_Alumno[2] = rs.getString("Materno_Alumno");
                        Datos_Alumno[3] = rs.getString("Matricula_Alumno");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos del alumno: " + e.getMessage());
            throw e;  // Rethrow the exception so that the caller can handle it
        } finally {
            this.Desconectar_EduScan();  // Asegurar desconexión
        }

        return Datos_Alumno;
    }
    public int Obtener_semestre(String grupo) {
        int semestre = 0;
        String sql = "SELECT Semestre_Grupo FROM Grupo WHERE Num_Grupo = ?";

        try {
            this.Conectar_EduScan();  // Conectar a la base de datos

            // Usar PreparedStatement para evitar inyección SQL
            try (PreparedStatement stmt = conector.prepareStatement(sql)) {
                stmt.setString(1, grupo);  // Establecer el parámetro en la consulta

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {  // Si encontramos el semestre
                        semestre = rs.getInt("Semestre_Grupo");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el semestre", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.Desconectar_EduScan();  // Asegurar desconexión
        }

        return semestre;
    }

}