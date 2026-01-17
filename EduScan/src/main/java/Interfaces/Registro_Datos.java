package Interfaces;

import Administrativo.Admin_Sesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Registro_Datos {
    private JPanel Caja;
    private JLabel Titulo_label;
    private JLabel Asignatura_label;
    private JLabel Grupo_label;
    private JComboBox Asignatura;
    private JComboBox Grupo;
    private JLabel Entrada_label;
    private JLabel Salida_label;
    private JComboBox Entrada;
    private JComboBox Salida;
    private JLabel Laboratorio_label;
    private JLabel Nopractica_label;
    private JComboBox No_practica;
    private JComboBox Laboratorio;
    private JLabel Nombre_label;
    private JTextField Nombre_Practica;
    private JButton Generar;
    private JLabel Logo_label;
    private String nombre;
    private static  JFrame frame;
    private static  JFrame frame2;
    public Registro_Datos(String nombre) {
        this.Numero_Practicas();
        this.Hora_Entrada_Salida();
        this.Materias_Disponibles(nombre);
        //this.Laboratorio_Disponible();
        new Conexion_EduScan().Laboratorios(Laboratorio);
        this.nombre = nombre;
        Asignatura.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String seleccion = (String) Asignatura.getSelectedItem();
                Grupo_Correspondiente(seleccion);
            }
        });
        Generar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    String vec[] = Informacion();
                    if (vec == null) {
                        JOptionPane.showMessageDialog(null, "Error al llenar los campos", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        Registro_Alumnos obj = new Registro_Alumnos(vec);
                        obj.Ventana_Alumnos2(frame2);
                        //frame.setVisible(false);

                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al llenar los campos", "Mensaje de Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }

            }
        });
    }

    private void Hora_Entrada_Salida(){
        for (int i=7; i<=11;i++){
            if(i<10){
                this.Entrada.addItem("0"+i+":00 AM");
                this.Salida.addItem("0"+i+":00 AM");
            }else {
                this.Entrada.addItem(i + ":00 AM");
                this.Salida.addItem(i + ":00 AM");
            }
        }
        for (int i=12;i<=19;i++){
            this.Entrada.addItem(i+":00 PM");
            this.Salida.addItem(i+":00 PM");
        }
    }



    private void Numero_Practicas(){
        for (int i=1; i<=10;i++){
            this.No_practica.addItem(""+i);
        }

    }
    private void Materias_Disponibles(String nombre){
        String Tira_Materia[]=new Conexion_EduScan().Materias(nombre);
        for (int i = 0; i < Tira_Materia.length; i++) {
            Asignatura.addItem(Tira_Materia[i]);
        }
    }
    private void Laboratorio_Disponible(){
        for (int i=1; i<=5;i++){
            this.Laboratorio.addItem("Laboratorio "+i);
        }
    }
    private String[] Informacion(){
        String datos[]=new String[12];
        datos[0]=nombre;
        datos[1]=(String) Asignatura.getSelectedItem();
        datos[2]=""+ LocalDate.now();
        datos[3]=(String) Entrada.getSelectedItem();
        datos[4]=(String) Salida.getSelectedItem();
        datos[5]=Obtener_Horas()+" Horas";
        String s=(String) Grupo.getSelectedItem();
        datos[6]=""+new Conexion_EduScan().Obtener_semestre(s);
        //datos[6]=""+s.charAt(1); //Grupo
        int gr=Integer.parseInt(s);
        String car=new Conexion_EduScan().carrera(gr);
        datos[7]=s;
        datos[8]=""+car;
        datos[9]=(String) Laboratorio.getSelectedItem();
        datos[10]=Nombre_Practica.getText();
        datos[11]=(String)No_practica.getSelectedItem();
        if(Obtener_Horas()<=0 || s.equals("") || Nombre_Practica.getText().equals("")){
            datos=null;
        }
        //String Profesor,
        //String Asignatura,
        //String fecha
        //String entrada,
        //String salida,
        //String horas,
        //String semestre
        //String grupo,
        //String carrera
        //String laboratorio,
        //String nombrepractica
        //String num_practica
            return datos;
    }
    private void Grupo_Correspondiente(String materia){
        String grupos[]=new Conexion_EduScan().Grupos(this.nombre,materia);
        Grupo.removeAllItems();
            for (int i=0;i<grupos.length;i++){
                this.Grupo.addItem(grupos[i]);
            }
        }
    private int  Obtener_Horas(){
        String entrada=(String) Entrada.getSelectedItem();
        String salida=(String) Salida.getSelectedItem();
        int horas =Integer.parseInt(salida.substring(0,2))-Integer.parseInt(entrada.substring(0,2));
        return horas;
    }

    public void Cambiar_Ventana(){
        Tiempo_Respuesta obo= new Tiempo_Respuesta();
        obo.Iniciar_Rendimiento();
        frame = new JFrame("Registro_Datos");
        frame.setContentPane(Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("Registro de Datos");
        Image icono = Toolkit.getDefaultToolkit().getImage(Registro_Datos.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println("Datos");
        obo.Terminar_Rendimiento();
    }
    public void Cambiar_Ventana2(JFrame frame){
        Tiempo_Respuesta obo= new Tiempo_Respuesta();
        obo.Iniciar_Rendimiento();
        frame.setContentPane(Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("Registro de Datos");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        System.out.println("Datos");
        obo.Terminar_Rendimiento();
        frame2=frame;
    }


}
