package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Materia {
    private JPanel Caja;
    private JLabel Titulo;
    private JTextField Nombre;
    private JLabel separador;
    private JLabel separador2;
    private JLabel nombre_label;
    private JButton Agregar;
    private JLabel Eliminar_label;
    private JLabel nomeliminar_label;
    private JTextField NombreEliminar;
    private JButton Eliminar;
    private JLabel separador5;
    private JLabel separador6;
    private JLabel sep2;
    private JLabel sep1;
    private JLabel Titulo3;
    private JTable Registros;
    private JButton actualizarListaButton;
    private JButton Regresar;
    private static JFrame frame;
    public Materia() {
        new Conexion_Administrador().Mostrar_Materias(Registros);
        Agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Agregar_Materias(Nombre.getText());
                Nombre.setText("");
            }
        });
        Eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Eliminar_Materia(NombreEliminar.getText());
                NombreEliminar.setText("");
            }
        });
        actualizarListaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Mostrar_Materias(Registros);
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
        frame = new JFrame("Materia");
        frame.setContentPane(new Materia().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Materias() {
        frame = new JFrame("Materia");
        frame.setContentPane(new Materia().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = Toolkit.getDefaultToolkit().getImage(Materia.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Materias2(JFrame frame2) {
        frame2.setTitle("Materia");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame=frame2;
    }
}
