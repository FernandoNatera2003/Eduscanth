package Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Asignacion {
    private JPanel Caja;
    private JLabel Titulo;
    private JLabel sep2;
    private JLabel sep1;
    private JLabel Materia_Label;
    private JLabel Grupo_label;
    private JButton Asignacion;
    private JComboBox Materias;
    private JComboBox Grupos;
    private JComboBox Maestros;
    private JLabel sep3;
    private JLabel sep4;
    private JLabel sep5;
    private JLabel Titulo2;
    private JLabel sep6;
    private JLabel sep7;
    private JLabel Prof_Mat_Gru;
    private JComboBox EMaterias;
    private JComboBox EGrupos;
    private JComboBox EMaestros;
    private JLabel sep8;
    private JButton EliminarAsignacion;
    private JLabel sep10;
    private JLabel sep9;
    private JLabel Titulo3;
    private JButton Actualizar;
    private JTable Registros;
    private JButton Regresar;
    private static JFrame frame;

    public Asignacion() {
        new Conexion_Administrador().Mostrar_AsignacionEliminar(EMaterias);
        new Conexion_Administrador().Mostrar_Asignaciones(Registros);
        new Conexion_Administrador().Obtener_Grupos(Grupos);
        new Conexion_Administrador().Obtener_Materias(Materias);
        new Conexion_Administrador().ObtenerMaestros(Maestros);
        Asignacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        Asignacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    String ma = Maestros.getSelectedItem().toString();
                    String mat = Materias.getSelectedItem().toString();
                    String gru = Grupos.getSelectedItem().toString();
                    new Conexion_Administrador().Crear_Asignacion(ma, mat, gru);
                    new Conexion_Administrador().Mostrar_AsignacionEliminar(EMaterias);
                }catch(Exception error){
                    JOptionPane.showMessageDialog(null,"Campos insuficientes","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Actualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                new Conexion_Administrador().Mostrar_Asignaciones(Registros);
            }
        });
        EliminarAsignacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    String cad = EMaterias.getSelectedItem().toString();
                    String datos[] = new Conexion_Administrador().Obtener_DatosEliminar(cad);
                    new Conexion_Administrador().Eliminar_Asignacion(datos[0], datos[1], datos[2]);
                    new Conexion_Administrador().Mostrar_AsignacionEliminar(EMaterias);
                }catch(Exception error){
                    JOptionPane.showMessageDialog(null,"No hay registros","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
                }
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
        frame = new JFrame("Asignacion");
        frame.setContentPane(new Asignacion().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono= Toolkit.getDefaultToolkit().getImage("EduScan/src/main/resources/Imagenes/Logo.png");
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
       frame.setResizable(false);
    }
    public void Mostrar_Asignacion(){
        frame = new JFrame("Asignacion");
        frame.setContentPane(new Asignacion().Caja);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Image icono = Toolkit.getDefaultToolkit().getImage(Asignacion.class.getResource("/Imagenes/Logo.png"));
        frame.setIconImage(icono);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    public void Mostrar_Asignacion2(JFrame frame2){
        frame2.setTitle("Asignacion");
        frame2.setContentPane(Caja);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame=frame2;
    }
}
