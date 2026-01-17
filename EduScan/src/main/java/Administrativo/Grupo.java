package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grupo {
    private JLabel Titulo;
    private JPanel Caja;
    private JLabel separador1;
    private JLabel separador2;
    private JTextField Nombre;
    private JTextField Semestre;
    private JTextField Carrera;
    private JLabel Nombre_label;
    private JLabel Semestre_label;
    private JLabel Carrera_label;
    private JLabel separador3;
    private JButton Agregar;
    private JLabel separador4;
    private JLabel separador5;
    private JLabel Titulo2;
    private JLabel separador6;
    private JLabel separador7;
    private JTextField NombreEliminar;
    private JLabel Eliminar_label;
    private JLabel separador8;
    private JButton Eliminar;
    private JLabel separador9;
    private JLabel sep10;
    private JLabel Titulo3;
    private JButton Actualizar;
    private JTable Registros;
    private JButton Regresar;
    private static JFrame frame;

    public Grupo() {
        new Conexion_Administrador().Mostrar_Grupos_Carrera(Registros);
        Agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Agregar_Grupo(Semestre.getText(),Carrera.getText(),Nombre.getText());
                Semestre.setText("");
                Carrera.setText("");
                Nombre.setText("");
            }
        });
        Eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Eliminar_Grupo(NombreEliminar.getText());
                NombreEliminar.setText("");
            }
        });
        Actualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Mostrar_Grupos_Carrera(Registros);
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
        frame = new JFrame("Grupo");
        frame.setContentPane(new Grupo().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Grupo(){
        frame = new JFrame("Grupo");
        frame.setContentPane(new Grupo().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono = Toolkit.getDefaultToolkit().getImage(Grupo.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Grupo2(JFrame frame2){
        frame2.setTitle("Grupo");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame=frame2;
    }
}
