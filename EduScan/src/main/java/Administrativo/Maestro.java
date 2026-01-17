package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Maestro {
    private JPanel Caja;
    private JLabel Titulo_label;
    private JTextField Nombre;
    private JTextField Paterno;
    private JTextField Materno;
    private JTextField Matricula;
    private JLabel Nombre_label;
    private JLabel paterno_label;
    private JLabel materno_label;
    private JLabel clave_label;
    private JLabel Titulo2_label;
    private JButton Agregar;
    private JTextField MatriculaEliminar;
    private JLabel matricula2_label;
    private JButton Eliminar;
    private JLabel separador2;
    private JLabel separador1;
    private JLabel separador;
    private JLabel separadorn2;
    private JLabel separadorn3;
    private JTable Registros;
    private JButton Actualizar;
    private JLabel Titulo3;
    private JLabel sep;
    private JLabel sep2;
    private JLabel s;
    private JButton Regresar;
    private static JFrame frame;
    public Maestro() {
        new Conexion_Administrador().Mostrar_Maestros(Registros);
        Agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Agregar_Maestro(Nombre.getText(),Paterno.getText(),Materno.getText(),Matricula.getText());
                Nombre.setText("");
                Paterno.setText("");
                Materno.setText("");
                Matricula.setText("");
            }
        });
        Eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Eliminar_Maestro(MatriculaEliminar.getText());
                MatriculaEliminar.setText("");
            }
        });
        Actualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Mostrar_Maestros(Registros);
            }
        });
        Regresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Tareas().Mostrar_Tareas2(frame);
                //frame.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Maestro");
        frame.setContentPane(new Maestro().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Maestro(){
        frame = new JFrame("Maestro");
        frame.setContentPane(new Maestro().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono = Toolkit.getDefaultToolkit().getImage(Maestro.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Maestro2(JFrame frame2){
        frame2.setTitle("Maestro");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame=frame2;

    }
}
