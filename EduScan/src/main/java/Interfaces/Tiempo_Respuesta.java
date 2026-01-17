package Interfaces;

public class Tiempo_Respuesta {
    long startTime = 0;
    public void Iniciar_Rendimiento(){
        startTime = System.nanoTime();
    }
    public void Terminar_Rendimiento(){
        long endTime = System.nanoTime(); // Termina de medir el tiempo
        long durationInMillis = (endTime - startTime) / 1_000_000; // Convertir a milisegundos
        System.out.println("Tiempo de respuesta: " + durationInMillis + " ms");
    }
}
