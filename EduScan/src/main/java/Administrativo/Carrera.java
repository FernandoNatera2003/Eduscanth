package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Carrera {
    private JPanel Caja;
    private JLabel Titulo;
    private JLabel separador1;
    private JLabel separador2;
    private JTextField Nombre;
    private JLabel Nombre_label;
    private JButton Agregar;
    private JLabel separador3;
    private JLabel separador4;
    private JLabel separador5;
    private JLabel Titulo2;
    private JLabel separador6;
    private JLabel separador7;
    private JLabel NEliminar_label;
    private JTextField NombreEliminar;
    private JLabel separador8;
    private JButton Eliminar;
    private JLabel Titulo3;
    private JTable Registros;
    private JButton Actualizar;
    private JLabel sep1;
    private JLabel sep2;
    private JButton Regresar;
    private static JFrame frame;
    public Carrera() {
        new Conexion_Administrador().Mostrar_Carreras(Registros);

        Agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Agregar_Carrera(Nombre.getText());
                Nombre.setText("");
            }
        });

        Eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Eliminar_Carrera(NombreEliminar.getText());
                NombreEliminar.setText("");
            }
        });

        Actualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Mostrar_Carreras(Registros);
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
        frame = new JFrame("Carrera");
        frame.setContentPane(new Carrera().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Carrera(){
        frame = new JFrame("Carrera");
        frame.setContentPane(new Carrera().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = Toolkit.getDefaultToolkit().getImage(Carrera.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Carrera2(JFrame frame2){
        frame2.setTitle("Carrera");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame=frame2;
    }
}
