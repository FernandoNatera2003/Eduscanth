package Interfaces;

import Administrativo.Admin_Sesion;
import Administrativo.Credenciales;

import java.io.File;

public class Principal {
    public static void main(String[] args) {
        String credencial="Credencial.txt";
        if (new File(credencial).exists()) {
            // Si el archivo existe, no mostrar el formulario de login
            new Iniciar_Sesion().Mostrar_InicioSesion();
        } else {
            // Si el archivo no existe, mostrar el formulario de login
            new Credenciales().Mostrar_Credenciales();
        }
    }
}
