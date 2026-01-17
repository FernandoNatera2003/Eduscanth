package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Laboratorio {
    private JPanel Caja;
    private JLabel Titulo;
    private JLabel separador1;
    private JLabel separador2;
    private JTextField Nombre;
    private JButton Agregar;
    private JLabel separador3;
    private JLabel Nombre_label;
    private JLabel separador4;
    private JLabel separador5;
    private JLabel Titulo2;
    private JLabel separador6;
    private JLabel separador7;
    private JTextField NombreEliminar;
    private JLabel Nom_label;
    private JLabel separador8;
    private JButton Eliminar;
    private JLabel sep1;
    private JLabel sep2;
    private JLabel Titulo3;
    private JButton Actualizar;
    private JTable registros;
    private JButton Regresar;
    private static JFrame frame;
    public Laboratorio() {
        new Conexion_Administrador().Mostrar_Laboratorios(registros);
        Agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Agregar_Laboratorio(Nombre.getText());
                Nombre.setText("");
            }
        });
        Eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Eliminar_Laboratorio(NombreEliminar.getText());
                NombreEliminar.setText("");

            }
        });
        Actualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Mostrar_Laboratorios(registros);
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
        frame = new JFrame("Laboratorio");
        frame.setContentPane(new Laboratorio().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Laboratorio(){
        frame = new JFrame("Laboratorio");
        frame.setContentPane(new Laboratorio().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = Toolkit.getDefaultToolkit().getImage(Laboratorio.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Laboratorio2(JFrame frame2){
        frame2.setTitle("Laboratorio");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame2.setResizable(false);
        frame=frame2;
    }
}
