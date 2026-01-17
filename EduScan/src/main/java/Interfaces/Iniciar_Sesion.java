package Interfaces;

import Administrativo.Admin_Sesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;

public class Iniciar_Sesion {
    private JPanel Caja;
    private JLabel Titulo;
    private JTextField Usuario;
    private JLabel label_usuario;
    private JLabel label_clave;
    private JPasswordField Clave;
    private JButton ingresar;
    private  JLabel Imagen;
    private static JFrame frame;
    public Iniciar_Sesion() {
        ingresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (Usuario.getText().equals("") ||  Clave.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Campos Incompletos", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    Conexion_EduScan obj = new Conexion_EduScan();
                    boolean bandera = obj.Verificacion(Usuario.getText(), new String(Clave.getPassword()));
                    if (bandera) {
                        JOptionPane.showMessageDialog(null, "Bienvenido Profesor ", "Mensaje de Ingreso", JOptionPane.INFORMATION_MESSAGE);
                        Registro_Datos obj2 = new Registro_Datos(Usuario.getText());
                        obj2.Cambiar_Ventana2(frame);
                        //frame.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos Incorrectos", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
                        Usuario.setText("");
                        Clave.setText("");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Tiempo_Respuesta obo= new Tiempo_Respuesta();
        obo.Iniciar_Rendimiento();
        frame = new JFrame("Aplicacion");
        frame.setContentPane(new Iniciar_Sesion().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //Titulo de la ventana
        frame.setTitle("EduScanTH");
        //Imagen para la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage(Iniciar_Sesion.class.getResource("/Imagenes/Logo.png"));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        obo.Terminar_Rendimiento();
    }
    public void Mostrar_InicioSesion(){
        Tiempo_Respuesta obo= new Tiempo_Respuesta();
        obo.Iniciar_Rendimiento();
        frame = new JFrame("Aplicacion");
        frame.setContentPane(new Iniciar_Sesion().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //Titulo de la ventana
        frame.setTitle("EduScanTH");
        //Imagen para la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage(Iniciar_Sesion.class.getResource("/Imagenes/Logo.png"));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        obo.Terminar_Rendimiento();
    }
    public void Mostrar_InicioSesion2(JFrame frame2){
        Tiempo_Respuesta obo= new Tiempo_Respuesta();
        obo.Iniciar_Rendimiento();
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setTitle("EduScanTH");
        frame2.setLocationRelativeTo(null);
        frame2.setResizable(false);
        obo.Terminar_Rendimiento();
        frame=frame2;
    }



}
