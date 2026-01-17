package Administrativo;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Obtener_Credenciales {
    private String credencial="Credencial.txt";
    public void Crear2(String usuario,String clave,String puerto,String host,String nb){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credencial))) {
            writer.write("username=" + usuario);
            writer.newLine();
            writer.write("password=" + clave);
            writer.newLine();
            writer.write("host=" + host);
            writer.newLine();
            writer.write("puerto=" + puerto);
            writer.newLine();
            writer.write("nb=" + nb);
            writer.newLine();
            //JOptionPane.showMessageDialog(null, "Credenciales guardadas con éxito!","Mensaje de Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar las credenciales.","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public void Crear(String usuario, String clave, String puerto, String host, String nb) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credencial))) {
            // Escribir las credenciales en el archivo
            writer.write("username=" + usuario);
            writer.newLine();
            writer.write("password=" + clave);
            writer.newLine();
            writer.write("host=" + host);
            writer.newLine();
            writer.write("puerto=" + puerto);
            writer.newLine();
            writer.write("nb=" + nb);
            writer.newLine();

            // Marcar el archivo como oculto (Windows)
            Path path = Paths.get(credencial);
            Files.setAttribute(path, "dos:hidden", true);

            // Mensaje opcional
            System.out.println("Archivo creado y marcado como oculto.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar las credenciales.", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
