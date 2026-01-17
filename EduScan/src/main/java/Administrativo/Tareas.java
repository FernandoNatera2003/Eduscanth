package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tareas {
    private JPanel Caja;
    private JLabel Tareas_label;
    private JLabel espacio1;
    private JButton Profesores;
    private JLabel separador2;
    private JButton Alumnos;
    private JButton Laboratorios;
    private JLabel espacio3;
    private JLabel espacio4;
    private JButton Materias;
    private JLabel espacio5;
    private JButton Salir;
    private JLabel sep;
    private JLabel sepn;
    private JButton Grupo;
    private JButton Carrera;
    private JLabel m;
    private JLabel n;
    private JButton Asignacion;
    private static JFrame frame;
    public Tareas() {
        Salir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null,"EduScanTH finalizo","Mensaje de Cierre",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
        Profesores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Maestro().Mostrar_Maestro2(frame);
                //frame.setVisible(false);
            }
        });
        Alumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Alumno().Mostrar_Alumno2(frame);
                //frame.setVisible(false);
            }
        });
        Materias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Materia().Mostrar_Materias2(frame);
                //frame.setVisible(false);
            }
        });
        Laboratorios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Laboratorio().Mostrar_Laboratorio();
                frame.setVisible(false);
            }
        });
        Grupo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Grupo().Mostrar_Grupo2(frame);
                //frame.setVisible(false);

            }
        });
        Carrera.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Carrera().Mostrar_Carrera2(frame);
                //frame.setVisible(false);

            }
        });
        Asignacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Asignacion().Mostrar_Asignacion2(frame);
                //frame.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Tareas");
        frame.setContentPane(new Tareas().Caja);
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Tareas(){
        frame = new JFrame("Tareas");
        frame.setContentPane(new Tareas().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = Toolkit.getDefaultToolkit().getImage(Tareas.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Tareas2(JFrame frame2){
        frame2.setTitle("Tareas");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame2.setResizable(false);
        frame=frame2;
    }
}
