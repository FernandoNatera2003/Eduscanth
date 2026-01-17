package Interfaces;

import Administrativo.Admin_Sesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Registro_Alumnos {
    private JPanel Caja;
    private JPanel Arriba;
    private JPanel Izquierdo;
    private JButton Salir;
    private JPanel Centro;
    private JButton Guardar;
    private JPanel Arriba1;
    private JLabel Titulo;
    private JLabel Logo_EdoMex;
    private JLabel Logo_Tesh;
    private JPanel Arriba2;
    private JLabel Label_Nombre;
    private JLabel Asignatura_Label;
    private JLabel Fecha_label;
    private JLabel Entrada_label;
    private JLabel Salida_label;
    private JLabel Profesor;
    private JLabel Asignatura;
    private JLabel Fecha;
    private JLabel Entrada;
    private JLabel Salida;
    private JLabel Horas_label;
    private JLabel Horas;
    private JLabel Grupo_label;
    private JLabel Carrera_label;
    private JLabel Laboratorio_label;
    private JLabel Semestre_label;
    private JLabel Nombre_label;
    private JLabel Numero_label;
    private JPanel Arriba3;
    private JPanel Buscador;
    private JTextField Matricula;
    private JButton agregarAlumnoButton;
    private JTable Alumnos;
    private JLabel Semestre;
    private JLabel Grupo;
    private JLabel Carrera;
    private JLabel Laboratorio;
    private JLabel Nombre_practica;
    private JLabel num_practica;
    private int num=0;

    public Registro_Alumnos() {
        //String Profesor,



    }
    public Registro_Alumnos(String datos[]) {
        Profesor.setText(datos[0]);
        //String Asignatura,
        Asignatura.setText(datos[1]);
        //String fecha
        Fecha.setText(datos[2]);
        //String entrada,
        Entrada.setText(datos[3]);
        //String salida,
        Salida.setText(datos[4]);
        //String horas,
        Horas.setText(datos[5]);
        //String semestre
        Semestre.setText(datos[6]);
        //String grupo,
        Grupo.setText(datos[7]);
        //String carrera
        Carrera.setText(datos[8]);
        //String laboratorio,
        Laboratorio.setText(datos[9]);
        //String nombrepractica
        Nombre_practica.setText(datos[10]);
        //String num_practica
        num_practica.setText(datos[11]);
        this.num=0;
        String[] columnas = {"No","Nombre", "Apellido Paterno", "Apellido Materno","Matricula","Número Equipo","Observacion"};
        Object[][] dato = {
                {"Numero", "Nombre", "Apellido Paterno","Apellido Materno","Matricula","Numero de Equipo","Observacion"},
        };
        //DefaultTableModel modelo = new DefaultTableModel(dato, columnas);
        DefaultTableModel modelo = new DefaultTableModel(dato, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        Alumnos.setModel(modelo);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Matricula.requestFocusInWindow();  // Poner el cursor en el JTextField
            }
        });
        agregarAlumnoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                boolean bandera=Buscar_Alumnos();
                if(bandera){

                }else{
                    RegistrarAlumno_Tabla(Matricula.getText());
                }
            }
        });
        Matricula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean bandera=Buscar_Alumnos();
                if(bandera){

                }else{
                    RegistrarAlumno_Tabla(Matricula.getText());
                }
            }

        });
        Guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Creacion_Excel obj=new Creacion_Excel();
                String observacion=JOptionPane.showInputDialog(null,"Observaciones","Observación",JOptionPane.INFORMATION_MESSAGE);
                obj.Recopilacion_D1P(Profesor.getText(),Asignatura.getText(),Fecha.getText(),Entrada.getText(),Salida.getText(),Horas.getText());
                obj.Recopilacion_D2P(Grupo.getText(),Semestre.getText(),Carrera.getText(),Laboratorio.getText(),Nombre_practica.getText(),num_practica.getText());
                String cad=Fecha.getText()+"_"+Grupo.getText()+"_"+Asignatura.getText()+"_"+Profesor.getText()+Matricula.getText()+"_"+Nombre_practica.getText()+"_"+num_practica.getText();
                obj.Crear_Excel(Alumnos,cad,Laboratorio.getText(),observacion);
            }
        });
        Salir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null,"El programa ha concluido","Mensaje de Cierre", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);

            }
        });
    }

    public void RegistrarAlumno_Tabla(String matricula){
        try {
            String DA[] = new Conexion_EduScan().Registro_Alumno(matricula);
            if(DA!=null){
                this.num++;
            }
            DefaultTableModel modelo = (DefaultTableModel) Alumnos.getModel();
            if (modelo.getRowCount() == 0) {
                String[] columnas = {"No", "Nombre", "Apellido Paterno", "Apellido Materno", "Matricula", "Número Equipo"};
                modelo = new DefaultTableModel(null, columnas);
                Alumnos.setModel(modelo);
            }

            Object[] nuevaFila = {"" + this.num, DA[0], DA[1], DA[2], DA[3], "Equipo " + this.num,"Ninguna"};
            modelo.addRow(nuevaFila);
            Matricula.setText("");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Alumno no Encontrado", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
            Matricula.setText("");
        }
    }
    public boolean Buscar_Alumnos(){
        //String[] columnas = {"No","Nombre", "Apellido Paterno", "Apellido Materno","Matricula","Número Equipo"};
        boolean bandera =false;
        String matricula="";
        for (int i = 1; i < Alumnos.getRowCount(); i++) {
             matricula=Alumnos.getValueAt(i, 4).toString();
            if(matricula.equals(Matricula.getText())){
                bandera=true;
                JOptionPane.showMessageDialog(null, "Alumno ya Registrado", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
                Matricula.setText("");
                break;
            }

        }
        return bandera;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro_Alumnos");
        frame.setContentPane(new Registro_Alumnos().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public void Ventana_Alumnos(){
        Tiempo_Respuesta obo= new Tiempo_Respuesta();
        obo.Iniciar_Rendimiento();
        JFrame frame = new JFrame("Registro_Alumnos");
        frame.setContentPane(Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono = Toolkit.getDefaultToolkit().getImage(Registro_Alumnos.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.out.println("Alumnos");
        obo.Terminar_Rendimiento();
    }
    public void Ventana_Alumnos2(JFrame frame){
        Tiempo_Respuesta obo= new Tiempo_Respuesta();
        obo.Iniciar_Rendimiento();
        frame.setContentPane(Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.out.println("Alumnos");
        obo.Terminar_Rendimiento();
    }
}
