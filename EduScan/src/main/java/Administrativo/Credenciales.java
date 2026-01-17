package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Credenciales {
    private JLabel Titulo1;
    private JLabel usu_label;
    private JLabel clave_label;
    private JTextField Usuario;
    private JPasswordField clave;
    private JButton Crear;
    private JPanel Caja;
    private JLabel se;
    private JLabel ad;
    private JLabel host_label;
    private JTextField Hostname;
    private JLabel puerto_label;
    private JTextField Puerto;
    private JTextField NombreBase;
    private JLabel base_label;
    private static JFrame frame;
    private String credenciales="Credencial.txt";
    public Credenciales() {
        if (new File(credenciales).exists()) {
            new Admin_Sesion().Mostrar_Admin();
            frame.setVisible(false);
        }
        Crear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        Crear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String usu=Usuario.getText();
                String clav=new String(clave.getPassword());
                String puer=Puerto.getText();
                String host=Hostname.getText();
                String base=NombreBase.getText();
                if(usu.equals("") || clav.equals("") || puer.equals("") || host.equals("") || base.equals("")){
                    JOptionPane.showMessageDialog(null,"Campos incompletos","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    new Obtener_Credenciales().Crear(usu,clav,puer,host,base);
                    boolean bandera =new Exportar_Base().importarBaseDeDatos();
                    if(bandera){
                        //frame.setVisible(false);
                        Usuario.setText("");
                        clave.setText("");
                        Puerto.setText("");
                        Hostname.setText("");
                        NombreBase.setText("");
                    }else{
                        new Admin_Sesion().Mostrar_Admin2(frame);
                    }


                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Credenciales");
        frame.setContentPane(new Credenciales().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Credenciales() {
        frame = new JFrame("Credenciales Administrador");
        frame.setContentPane(new Credenciales().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono = Toolkit.getDefaultToolkit().getImage(Credenciales.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }


}
