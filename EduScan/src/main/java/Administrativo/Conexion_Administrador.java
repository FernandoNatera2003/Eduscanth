package Administrativo;

import Interfaces.Conexion_EduScan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Conexion_Administrador {
    //private String url = "jdbc:mysql://localhost:3306/EduScan";
    private String url="";
    private String usuario="";
    //private String usuario = "root";
    private String contraseña="";
    private String host="";
    //private String contraseña = "2003";
    Connection conector = null;
    private String puerto="";
    private String nombrebase="";
    private String Credencial="Credencial.txt";

    private void Conectar_EduScan() {
        Obtener_Credenciales();
        try {
            conector = DriverManager.getConnection(this.url, this.usuario, this.contraseña);
        } catch (SQLException error) {
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
    // Metodos para ejecutar los scripts

    private void Desconectar_EduScan() {
        try {
            conector.close();
        } catch (SQLException error) {

        }
    }
    public boolean Verificacion_Administrador(String nombre, String clave) {
        this.Conectar_EduScan();
        boolean bandera = false;
        try {
            String sql = "SELECT * FROM Administrativo where usuario_admin='" + nombre + "' and clave_admin='" + clave+ "'";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bandera = true;
            } else {
                bandera = false;
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
        return bandera;
    }

    //Metodos para Agregar y Eliminar Maestros
    public void Agregar_Maestro(String nombre,String paterno,String materno,String matricula){
        this.Conectar_EduScan();
        String sentencia="insert into Profesor(Nombre_Profesor,Paterno_Profesor,Materno_Profesor,Matricula_Profesor) values('"+nombre+"','"+paterno+"','"+materno+"','"+matricula+"')";
        try{
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            if(nombre.equals("") || paterno.equals("") || materno.equals("") || matricula.equals("")){
                statement=null;
            }
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null,"Registro agregado exitosamente","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
    }
    public void Eliminar_Maestro(String Matricula) {
        int id_profesor = this.Obtener_IdMaestro(Matricula);
        this.Conectar_EduScan();
        System.out.println(id_profesor);
        String sentencia = "DELETE FROM Profesor WHERE id_Profesor = "+id_profesor+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    private int Obtener_IdMaestro(String Matricula){
        this.Conectar_EduScan();
        int id_profesor=-1;
        try {
            String sql = "SELECT * FROM Profesor where Matricula_Profesor='" +  Matricula+"'";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id_profesor=rs.getInt("id_Profesor");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
        return id_profesor;
    }
    public void Mostrar_Maestros(JTable registro){
        this.Conectar_EduScan();
        int num=0;
        try {
            String sql = "SELECT * FROM Profesor";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnas = {"No", "Nombre", "Apellido Paterno", "Apellido Materno", "Matricula"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas);
            registro.setModel(modelo);
            Object[] nuevaFila = {"No" ,"Nombre", "Apellido Paterno","Apellido Materno","Matricula"};
            modelo.addRow(nuevaFila);
            while(rs.next()) {
                num++;
                String nombre=rs.getString("Nombre_Profesor");
                String paterno=rs.getString("Paterno_Profesor");
                String materno=rs.getString("Materno_Profesor");
                String matricula=rs.getString("Matricula_Profesor");
                Object[] maestro={""+num,nombre,paterno,materno,matricula};
                modelo.addRow(maestro);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }

    //Metodos para Agregar y Eliminar Alumnos
    public void Agregar_Alumnos(String nombre,String paterno,String materno,String matricula){
        int ver=this.Obtener_IdAlumno(matricula);
        this.Conectar_EduScan();
        String sentencia="insert into Alumno(Nombre_Alumno,Paterno_Alumno,Materno_Alumno,Matricula_Alumno) values('"+nombre+"','"+paterno+"','"+materno+"','"+matricula+"')";
        try{
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            if(ver>=0 || nombre.equals("") || paterno.equals("") || materno.equals("") || matricula.equals("")){
                statement=null;
            }
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null,"Alumno agregado exitosamente","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
    }
    private int Obtener_IdAlumno(String Matricula){
        this.Conectar_EduScan();
        int id_alumno=-1;
        try {
            String sql = "SELECT * FROM Alumno where Matricula_Alumno='" +Matricula+"'";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id_alumno=rs.getInt("id_Alumno");
                System.out.println("entra aqui");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
        return id_alumno;

    }
    public void Eliminar_Alumno(String Matricula){
        int id_alumno = this.Obtener_IdAlumno(Matricula);
        this.Conectar_EduScan();
        String sentencia = "DELETE FROM Alumno WHERE id_Alumno = "+id_alumno+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
    }
    public void Mostrar_Alumnos(JTable registro){
        this.Conectar_EduScan();
        int num=0;
        try {
            String sql = "SELECT * FROM Alumno ORDER BY id_Alumno DESC\n" +
                    "LIMIT 5;";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnas = {"No", "Nombre", "Apellido Paterno", "Apellido Materno", "Matricula"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas);
            registro.setModel(modelo);
            Object[] nuevaFila = {"No" ,"Nombre", "Apellido Paterno","Apellido Materno","Matricula"};
            modelo.addRow(nuevaFila);
            while(rs.next()) {
                num++;
                String nombre=rs.getString("Nombre_Alumno");
                String paterno=rs.getString("Paterno_Alumno");
                String materno=rs.getString("Materno_Alumno");
                String matricula=rs.getString("Matricula_Alumno");
                Object[] maestro={""+num,nombre,paterno,materno,matricula};
                modelo.addRow(maestro);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    public void Buscar_Alumno(JTable registro, String mat) {
        this.Conectar_EduScan();
        int num = 0;

        try {
            String sql = "SELECT * FROM Alumno where Matricula_Alumno= '" + mat + "' and Activo='true'";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnas = {"No", "Nombre", "Apellido Paterno", "Apellido Materno", "Matricula"};

            DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column != 4;
                }
            };

            registro.setModel(modelo);

            Object[] nuevaFila = {"No", "Nombre", "Apellido Paterno", "Apellido Materno", "Matricula"};
            modelo.addRow(nuevaFila);
            while (rs.next()) {
                num++;
                String nombre = rs.getString("Nombre_Alumno");
                String paterno = rs.getString("Paterno_Alumno");
                String materno = rs.getString("Materno_Alumno");
                String matricula = rs.getString("Matricula_Alumno");
                Object[] maestro = {"" + num, nombre, paterno, materno, matricula};
                modelo.addRow(maestro);
            }
            if (num == 0) {
                JOptionPane.showMessageDialog(null, "Alumno no encontrado", "Mensaje de Aviso", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception error) {
            // En caso de error, mostrar un mensaje y registrar el error
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }

        // Desconectar después de terminar
        this.Desconectar_EduScan();
    }

    public void Desactivar(String matricula){
        int id_alumno = this.Obtener_IdAlumno(matricula);
        this.Conectar_EduScan();
        String sentencia = "update Alumno set Activo='false' WHERE id_Alumno = "+id_alumno+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro desactivado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
    }
    public void Activar(String matricula){
        int id_alumno = this.Obtener_IdAlumno(matricula);
        this.Conectar_EduScan();
        String sentencia = "update Alumno set Activo='true' WHERE id_Alumno = "+id_alumno+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro activado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
    }
    public void Actualizar_Alumno(JTable registros) {
        this.Conectar_EduScan();
        try {
            DefaultTableModel modelo = (DefaultTableModel) registros.getModel();
            String nombre = (String)modelo.getValueAt(1, 1);
            String paterno = (String)modelo.getValueAt(1, 2);
            String materno = (String)modelo.getValueAt(1, 3);
            String matricula = (String)modelo.getValueAt(1, 4);
            int id_alumno = this.Obtener_IdAlumno(matricula);
            this.Conectar_EduScan();
            String sentencia = "update Alumno set Nombre_Alumno='"+nombre+"' ,Paterno_Alumno='"+paterno+"' ,Materno_Alumno='"+materno+"' WHERE id_Alumno = "+id_alumno+";";

            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();

    }






    //Metodos para Agregar y Eliminar Laboratorio
    public void Agregar_Laboratorio(String laboratorio){
        this.Conectar_EduScan();
        String sentencia="insert into Laboratorio(nombre_Laboratorio) values('"+laboratorio+"')";
        try{
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            if(laboratorio.equals("")){
                statement=null;
            }
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null,"Laboratorio agregado exitosamente","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();

    }
    private int Obtener_IdLaboratorio(String Laboratorio){
        this.Conectar_EduScan();
        int id_lab=-1;
        try {
            String sql = "SELECT * FROM Laboratorio where nombre_Laboratorio='" +Laboratorio+"'";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id_lab=rs.getInt("id_Laboratorio");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
        return id_lab;

    }
    public void Eliminar_Laboratorio(String laboratorio){
        int id_lab = this.Obtener_IdLaboratorio(laboratorio);
        this.Conectar_EduScan();
        String sentencia = "DELETE FROM Laboratorio WHERE id_Laboratorio = "+id_lab+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    public void Mostrar_Laboratorios(JTable registros){
        this.Conectar_EduScan();
        int num=0;
        try {
            String sql = "SELECT * FROM Laboratorio";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnas = {"No", "Nombre del Laboratorio"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas);
            registros.setModel(modelo);
            Object[] nuevaFila = {"No" ,"Nombre del Laboratorio"};
            modelo.addRow(nuevaFila);
            while(rs.next()) {
                num++;
                String nombre=rs.getString("nombre_Laboratorio");
                Object[] maestro={""+num,nombre};
                modelo.addRow(maestro);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    //Metodos para Agregar y Eliminar Materias
    public void Agregar_Materias(String Materia){
        this.Conectar_EduScan();
        String sentencia="insert into Materia(Nombre_Materia) values('"+Materia+"')";
        try{
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            if(Materia.equals("")){
                statement=null;
            }
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null,"Registro agregado exitosamente","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
    }
    private int Obtener_IdMateria(String Materia){
        this.Conectar_EduScan();
        int id_mat=-1;
        try {
            String sql = "SELECT * FROM Materia where Nombre_Materia='" +Materia+"'";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id_mat=rs.getInt("id_Materia");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
        return id_mat;
    }
    public void Eliminar_Materia(String Materia){
        int id_Mat=this.Obtener_IdMateria(Materia);
        this.Conectar_EduScan();
        String sentencia = "DELETE FROM Materia WHERE id_Materia = "+id_Mat+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();

    }
    public void Mostrar_Materias(JTable registro){
        this.Conectar_EduScan();
        int num=0;
        try {
            String sql = "SELECT * FROM Materia";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnas = {"No", "Nombre Materia"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas);
            registro.setModel(modelo);
            Object[] nuevaFila = {"No" ,"Nombre Materia"};
            modelo.addRow(nuevaFila);
            while(rs.next()) {
                num++;
                String nombre=rs.getString("Nombre_Materia");
                Object[] maestro={""+num,nombre};
                modelo.addRow(maestro);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    //Metodos para Agregar y Eliminar Carreras
    public void Agregar_Carrera(String Carrera){
        this.Conectar_EduScan();
        String sentencia="insert into Carrera(Nombre_Carrera) values('"+Carrera+"')";
        try{
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            if(Carrera.equals("")){
                statement=null;
            }
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null,"Registro agregado exitosamente","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
    }
    private int ObtenerId_Carrera(String Carrera){
        this.Conectar_EduScan();
        int id_car=-1;
        try {
            String sql = "SELECT * FROM Carrera where Nombre_Carrera='" +Carrera+"'";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id_car=rs.getInt("id_Carrera");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
        return id_car;
    }
    public void Eliminar_Carrera(String Carrera){
        int id_car=this.ObtenerId_Carrera(Carrera);
        this.Conectar_EduScan();
        String sentencia = "DELETE FROM Carrera WHERE id_Carrera = "+id_car+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    public void Mostrar_Carreras(JTable registro){
        this.Conectar_EduScan();
        int num=0;
        try {
            String sql = "SELECT * FROM Carrera";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnas = {"No", "Nombre de la Carrera"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas);
            registro.setModel(modelo);
            Object[] nuevaFila = {"No" ,"Nombre de la Carrera"};
            modelo.addRow(nuevaFila);
            while(rs.next()) {
                num++;
                String nombre=rs.getString("Nombre_Carrera");
                Object[] maestro={""+num,nombre};
                modelo.addRow(maestro);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();

    }
    //Metodos para Agregar y Eliminar Grupos
    public void Agregar_Grupo(String semestre,String carrera,String nombreGrupo){
        int id_car=this.ObtenerId_Carrera(carrera);
        this.Conectar_EduScan();
        System.out.println(id_car);
        String sentencia="insert into Grupo(Semestre_Grupo,id_Carrera,Num_Grupo) values("+semestre+","+id_car+",'"+nombreGrupo+"')";
        try{
            int sem=Integer.parseInt(semestre);
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            if(sem<=0 || carrera.equals("") || nombreGrupo.equals("")){
                statement=null;
            }
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null,"Registro agregado exitosamente","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();

    }
    private int  ObtenerId_Grupo(String grupo){
        this.Conectar_EduScan();
        int id_grupo=-1;
        try {
            String sql = "SELECT * FROM Grupo where Num_Grupo='" +grupo+"'";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id_grupo=rs.getInt("id_Grupo");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
        return id_grupo;
    }
    public void Eliminar_Grupo(String grupo){
        int id_grup=this.ObtenerId_Grupo(grupo);
        this.Conectar_EduScan();
        String sentencia = "DELETE FROM Grupo WHERE id_Grupo = "+id_grup+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    public void Mostrar_Grupos_Carrera(JTable registro){
        this.Conectar_EduScan();
        int num=0;
        try {
            String sql = "select\n" +
                    "Grupo.Semestre_Grupo,\n" +
                    "Grupo.Num_Grupo,\n" +
                    "Carrera.Nombre_Carrera from Grupo join Carrera\n" +
                    "on Grupo.id_Carrera =Carrera.id_Carrera;";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnas = {"No", "Nombre Grupo","Semestre","Carrera"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas);
            registro.setModel(modelo);
            Object[] nuevaFila = {"No" ,"Nombre Grupo","Semestre","Carrera"};
            modelo.addRow(nuevaFila);
            while(rs.next()) {
                num++;
                String grupo=rs.getString("Num_Grupo");
                int semestre = rs.getInt("Semestre_Grupo");
                String nombre=rs.getString("Nombre_Carrera");
                Object[] maestro={""+num,grupo,semestre,nombre};
                modelo.addRow(maestro);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }

    //Metodos para Asignar o Eliminar Materias y Grupos al Profesor
    //Mostrar Materias, Profesores y Grupos
    public void ObtenerMaestros(JComboBox Maestro){
        this.Conectar_EduScan();
        try {
            String sql = "SELECT * FROM Profesor";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String nombre=rs.getString("Nombre_Profesor");
                String paterno=rs.getString("Paterno_Profesor");
                String materno=rs.getString("Materno_Profesor");
                String matricula=rs.getString("Matricula_Profesor");
                Maestro.addItem(nombre+" "+paterno+" "+materno+" "+matricula);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    public void Obtener_Materias(JComboBox Materias){
        this.Conectar_EduScan();
        try {
            String sql = "SELECT * FROM Materia";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String nombre=rs.getString("Nombre_Materia");
                Materias.addItem(nombre);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    public void Obtener_Grupos(JComboBox Grupos){
        this.Conectar_EduScan();
        try {
            String sql = "SELECT * FROM Grupo";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String nombre=rs.getString("Num_Grupo");
                Grupos.addItem(nombre);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }

    //Verifciar si la asignacion Existe
    private boolean Verificar_Asignacion(String Materia,String Grupo){
        boolean bandera =false;
        int idMat=this.Obtener_IdMateria(Materia);
        int idG=this.ObtenerId_Grupo(Grupo);
        this.Conectar_EduScan();
        try {
            String sql = "SELECT * FROM Materia_Profesor_Grupo where id_Materia="+idMat+" and id_Grupo ="+idG;
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                bandera=true;
                break;
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
        return bandera;

    }
    public void Crear_Asignacion(String Maestro,String Materia,String Grupo){
        int id_mat=this.Obtener_IdMateria(Materia);
        int id_grup=this.ObtenerId_Grupo(Grupo);
        String matricula=Maestro.substring(Maestro.length()-8,Maestro.length());
        int id_maes=this.Obtener_IdMaestro(matricula);
        this.Conectar_EduScan();
        String sentencia="insert into Materia_Profesor_Grupo(id_Materia,id_Profesor,id_Grupo) values("+id_mat+","+id_maes+","+id_grup+")";
        try{
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            boolean bandera=Verificar_Asignacion(Materia,Grupo);
            if(id_mat<=0 || id_grup<=0 || id_maes<=0 || bandera==true) {
                statement=null;
            }
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null,"Registro agregado exitosamente","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();

    }
    private int ObtenerId_Asignacion(String Maestro,String Materia,String Grupo){
        int id_Agrupacion=-1;
        int id_mat=this.Obtener_IdMateria(Materia);
        int id_grup=this.ObtenerId_Grupo(Grupo);
        int id_maes=this.Obtener_IdMaestro(Maestro);
        this.Conectar_EduScan();
        try {
            String sql = "SELECT * FROM Materia_Profesor_Grupo where id_Materia=" +id_mat+" and id_Profesor="+id_maes+" and id_Grupo="+id_grup;
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                id_Agrupacion=rs.getInt("id_Mat_Pro_Gru");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
        return id_Agrupacion;
    }
    public void Eliminar_Asignacion(String Maestro,String Materia,String Grupo){
        int id_Asignacion=this.ObtenerId_Asignacion(Maestro,Materia,Grupo);
        this.Conectar_EduScan();
        String sentencia = "DELETE FROM Materia_Profesor_Grupo WHERE id_Mat_Pro_Gru = "+id_Asignacion+";";
        try {
            PreparedStatement statement = this.conector.prepareStatement(sentencia);
            int fila = statement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Mensaje de Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro", "Mensaje de Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Intente en otro momento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
        this.Desconectar_EduScan();
    }
    public void Mostrar_Asignaciones(JTable registros){
        this.Conectar_EduScan();
        int num=0;
        try {
            String sql = "select \n" +
                    " Profesor.Nombre_Profesor,\n" +
                    " Profesor.Paterno_Profesor,\n" +
                    " Profesor.Materno_Profesor,\n" +
                    " Profesor.Matricula_Profesor,\n" +
                    " Materia.Nombre_Materia,\n" +
                    " Grupo.Num_Grupo from Materia_Profesor_Grupo join Profesor\n" +
                    " on  Materia_Profesor_Grupo.id_Profesor=Profesor.id_Profesor join \n" +
                    " Materia on Materia_Profesor_Grupo.id_Materia=Materia.id_Materia join Grupo on \n" +
                    " Materia_Profesor_Grupo.id_Grupo=Grupo.id_Grupo;";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columnas = {"No","Profesor","Matricula Profesor","Materia","Grupo"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas);
            registros.setModel(modelo);
            Object[] nuevaFila = {"No" ,"Profesor","Matricula Profesor","Materia","Grupo"};
            modelo.addRow(nuevaFila);
            while(rs.next()) {
                num++;
                String nombre=rs.getString("Nombre_Profesor");
                String paterno = rs.getString("Paterno_Profesor");
                String materno=rs.getString("Materno_Profesor");
                String matricula=rs.getString("Matricula_Profesor");
                String Materia=rs.getString("Nombre_Materia");
                String Grupo=rs.getString("Num_Grupo");
                String completo=nombre+" "+paterno+" "+materno;
                Object[] asignacion={""+num,completo,matricula,Materia,Grupo};
                modelo.addRow(asignacion);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    public void Mostrar_AsignacionEliminar(JComboBox registros){
        this.Conectar_EduScan();
        registros.removeAllItems();
        try {
            String sql = "select \n" +
                    " Profesor.Nombre_Profesor,\n" +
                    " Profesor.Paterno_Profesor,\n" +
                    " Profesor.Materno_Profesor,\n" +
                    " Profesor.Matricula_Profesor,\n" +
                    " Materia.Nombre_Materia,\n" +
                    " Grupo.Num_Grupo from Materia_Profesor_Grupo join Profesor\n" +
                    " on  Materia_Profesor_Grupo.id_Profesor=Profesor.id_Profesor join \n" +
                    " Materia on Materia_Profesor_Grupo.id_Materia=Materia.id_Materia join Grupo on \n" +
                    " Materia_Profesor_Grupo.id_Grupo=Grupo.id_Grupo;";
            Statement stmt = conector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String nombre=rs.getString("Nombre_Profesor");
                String paterno = rs.getString("Paterno_Profesor");
                String materno=rs.getString("Materno_Profesor");
                String matricula=rs.getString("Matricula_Profesor");
                String Materia=rs.getString("Nombre_Materia");
                String Grupo=rs.getString("Num_Grupo");
                String informacion="Matricula Profesor: *"+matricula+"* Materia: *"+Materia+"* Grupo: *"+Grupo+"*";
                registros.addItem(informacion);

            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Intente en otro momento","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
        this.Desconectar_EduScan();
    }
    public String[] Obtener_DatosEliminar(String cadena){
        int j=0;
        String dat="";
        String datos[]=new String[3];
        int bandera=0;
        for (int i=0;i<cadena.length();i++){
            if(cadena.charAt(i)=='*'){
                bandera++;
                if(bandera==2){
                    bandera=0;
                    datos[j]=dat.substring(1);
                    j++;
                    dat="";
                }
            }
            if(bandera==1){
                dat=dat+cadena.charAt(i);

            }
        }
       return datos;

    }
}
