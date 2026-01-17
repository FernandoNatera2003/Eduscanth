package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin_Sesion {
    private JLabel Admin_label;
    private JLabel nombre_label;
    private JTextField usuario;
    private JButton Ingresar;
    private JLabel Clave_admin;
    private JPasswordField clave;
    private JPanel Caja;
    private JLabel imagen;
    private JLabel espacio;
    private JLabel espacio2;
    private static JFrame frame;
    public Admin_Sesion() {
        Ingresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String contra=new String (clave.getPassword());
                boolean bandera=new Conexion_Administrador().Verificacion_Administrador(usuario.getText(),contra);
                if(bandera){
                    JOptionPane.showMessageDialog(null,"Bienvenido Administrador","Mensaje de Exito",JOptionPane.INFORMATION_MESSAGE);
                    new Tareas().Mostrar_Tareas2(frame);
                    //frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,"Datos incorrectos","Mensaje de Advertencia",JOptionPane.INFORMATION_MESSAGE);
                    usuario.setText("");
                    clave.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Admin_Sesion");
        frame.setTitle("EduScanTH");
        frame.setContentPane(new Admin_Sesion().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = Toolkit.getDefaultToolkit().getImage(Admin_Sesion.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void Mostrar_Admin(){
        frame = new JFrame("EduScanTH");
        frame.setContentPane(new Admin_Sesion().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = Toolkit.getDefaultToolkit().getImage(Admin_Sesion.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void Mostrar_Admin2(JFrame frame2){
        frame2.setTitle("EduScanTH");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame2.setResizable(false);
        frame=frame2;
    }

}
