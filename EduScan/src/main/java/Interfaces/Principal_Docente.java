package Interfaces;

import Administrativo.Credenciales;

import java.io.File;

public class Principal_Docente {
    public static void main(String[] args) {
        String credencial="Credencial.txt";
        if (new File(credencial).exists()) {
            // Si el archivo existe, no mostrar el formulario de login
            new Iniciar_Sesion().Mostrar_InicioSesion();
        } else {
            // Si el archivo no existe, mostrar el formulario de login
            new CredencialesM().Mostrar_Cre();
        }
    }
}
