package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Alumno {
    private JPanel Caja;
    private JLabel Titulo;
    private JLabel separador1;
    private JTextField Nombre;
    private JTextField Paterno;
    private JTextField Materno;
    private JTextField Matricula;
    private JLabel Nombre_Label;
    private JButton Agregar;
    private JLabel Titulo2;
    private JLabel separador4;
    private JLabel separador5;
    private JLabel separador6;
    private JLabel Mat_label;
    private JTextField CambioMatricula;
    private JButton Eliminar;
    private JLabel separador7;
    private JLabel s1;
    private JLabel s2;
    private JLabel s3;
    private JLabel Titulo3;
    private JButton Buscar;
    private JTable registros;
    private JButton Regresar;
    private JButton AlumnosTira;
    private JLabel separador111;
    private JButton Inactivar;
    private JButton Activar;
    private JTextField id_buscar;
    private JButton Actulizar;
    private static JFrame frame;
    public Alumno() {
        //new Conexion_Administrador().Mostrar_Alumnos(registros);
        Agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Agregar_Alumnos(Nombre.getText(),Paterno.getText(),Materno.getText(),Matricula.getText());
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
                new Conexion_Administrador().Eliminar_Alumno(CambioMatricula.getText());
                CambioMatricula.setText("");
            }
        });
        Buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //new Conexion_Administrador().Mostrar_Alumnos(registros);
                new Conexion_Administrador().Buscar_Alumno(registros, id_buscar.getText());
                id_buscar.setText("");
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
        AlumnosTira.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Tira_Alumnos().seleccionarArchivo();
            }
        });
        Inactivar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Desactivar(CambioMatricula.getText());
                CambioMatricula.setText("");
            }
        });
        Activar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Activar(CambioMatricula.getText());
                CambioMatricula.setText("");
            }
        });
        Actulizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Conexion_Administrador().Actualizar_Alumno(registros);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Alumno");
        frame.setContentPane(new Alumno().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }
    public void Mostrar_Alumno(){
        frame = new JFrame("Alumno");
        frame.setContentPane(new Alumno().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono = Toolkit.getDefaultToolkit().getImage(Alumno.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public void Mostrar_Alumno2(JFrame frame2){
        frame2.setTitle("Alumno");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame=frame2;
    }
}
