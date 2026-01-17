package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tira_Alumnos extends Component {
    private  String direccion="";
    private  String usuario = "";
    private  String clave = "";
    private  String host="";
    private String puerto="";
    private  String nombre_base="";
    //private  String nombre_base = "EduScan";
    private String Credencial="Credencial.txt";
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
            direccion="jdbc:mysql://"+host+":"+puerto+"/"+nombre_base;
        }catch(Exception error){

        }
    }
    public void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Seleccionar archivo CSV"); // Establecer un título personalizado

        // Crear un icono personalizado
        //ImageIcon icon = new ImageIcon("ruta/a/tu/icono.png");
        Image icono = Toolkit.getDefaultToolkit().getImage(Admin_Sesion.class.getResource("/Imagenes/Logo.png"));
// Reemplaza con la ruta a tu icono
        JFrame ventanaPadre = new JFrame();
        ventanaPadre.setIconImage(icono);

        int seleccion = fileChooser.showDialog(ventanaPadre, "Abrir archivo"); // Cambia el botón de acción

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (archivo.getName().endsWith(".csv")) {
                importarCSV(archivo);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un archivo CSV válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void importarCSV(File archivo) {
        Obtener_Credenciales();
        Connection conexion = null; // Declarar la conexión fuera del try
        try {
            conexion = DriverManager.getConnection(direccion, usuario, clave);
            conexion.setAutoCommit(false); // Desactiva el auto-commit para manejar transacciones manualmente

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                String sql = "INSERT INTO Alumno(Nombre_Alumno,Paterno_Alumno, Materno_Alumno,Matricula_Alumno) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conexion.prepareStatement(sql);

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(","); // Asume que los valores están separados por comas
                    ps.setString(1, datos[0].trim());
                    ps.setString(2, datos[1].trim());
                    ps.setString(3, datos[2].trim());
                    ps.setString(4, datos[3].trim());
                    ps.addBatch();
                }

                ps.executeBatch(); // Ejecuta el lote de inserciones
                conexion.commit(); // Realiza el commit si todo sale bien
                JOptionPane.showMessageDialog(null, "Datos importados exitosamente","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            if (conexion != null) {
                try {
                    conexion.rollback(); // Deshace todas las operaciones si ocurre un error
                } catch (SQLException rollbackEx) {
                    JOptionPane.showMessageDialog(null, "Error al deshacer cambios: " + rollbackEx.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    rollbackEx.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "Error al importar: " + ex.getMessage(), "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close(); // Cierra la conexión para liberar recursos
                } catch (SQLException closeEx) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + closeEx.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    closeEx.printStackTrace();
                }
            }
        }
    }


}
