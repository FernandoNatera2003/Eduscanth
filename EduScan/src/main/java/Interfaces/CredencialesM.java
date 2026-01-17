package Interfaces;

import Administrativo.Alumno;
import Administrativo.Obtener_Credenciales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CredencialesM {
    private JPanel Caja;
    private JLabel Titulo;
    private JLabel Advertencia;
    private JLabel usuario_label;
    private JLabel clave_label;
    private JLabel hostname_label;
    private JLabel nombre_label;
    private JTextField Usuario;
    private JPasswordField clave;
    private JTextField HN;
    private JTextField NameBase;
    private JLabel sep1;
    private JButton Agregar;
    private JLabel puerto_label;
    private JTextField Puerto;
    private static JFrame frame;
    public CredencialesM() {
        Agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String u=Usuario.getText();
                String cl=new String(clave.getPassword());
                String pu=Puerto.getText();
                String host=HN.getText();
                String base=NameBase.getText();
                if(u.equals("") || cl.equals("") || pu.equals("") || host.equals("") || base.equals("")){
                    JOptionPane.showMessageDialog(null, "Campos Imcompletos","Mensaje de Advertencia", JOptionPane.ERROR_MESSAGE);
                }else{
                    //frame.setVisible(false);
                    new Obtener_Credenciales().Crear(u,cl,pu,host,base);
                    boolean bandera= new Conexion_EduScan().Credenciales();
                    if(bandera){
                        JOptionPane.showMessageDialog(null, "Credenciales guardadas con éxito!","Mensaje de Exito", JOptionPane.INFORMATION_MESSAGE);
                        new Iniciar_Sesion().Mostrar_InicioSesion2(frame);
                    }else{
                        JOptionPane.showMessageDialog(null,"Credenciales Incorrectas","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        new Conexion_EduScan().Borrar_Credenciales();
                        Usuario.setText("");
                        clave.setText("");
                        Puerto.setText("");
                        HN.setText("");
                        NameBase.setText("");
                    }

                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("CredencialesM");
        frame.setContentPane(new CredencialesM().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        Image icono = Toolkit.getDefaultToolkit().getImage(Alumno.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setResizable(false);
    }
    public void Mostrar_Cre() {
        Tiempo_Respuesta obj=new Tiempo_Respuesta();
        obj.Iniciar_Rendimiento();
        frame = new JFrame("Credenciales Docente");
        frame.setContentPane(Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        Image icono = Toolkit.getDefaultToolkit().getImage(Alumno.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setResizable(false);
        System.out.println("Credenciales");
        obj.Terminar_Rendimiento();
    }
}
