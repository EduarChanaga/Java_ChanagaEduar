import java.util.Random;

public class CarreraPorRelevos {

    static boolean relevo = false;
    
    public static void main(String[] args) {
        // Crear los hilos de atletas
        Thread atleta1 = new Thread(() -> correr("Atleta 1"));
        Thread atleta2 = new Thread(() -> correr("Atleta 2"));
        Thread atleta3 = new Thread(() -> correr("Atleta 3"));
        Thread atleta4 = new Thread(() -> correr("Atleta 4"));

        // Guardar tiempo de inicio
        long inicio = System.currentTimeMillis();
        
        // Iniciar la carrera
        atleta1.start();
        atleta2.start();
        atleta3.start();
        atleta4.start();
        
        // Esperar a que terminen todos
        try {
            atleta1.join();
            atleta2.join();
            atleta3.join();
            atleta4.join();
        } catch (InterruptedException e) {
        }
        
        // Guardar tiempo final
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
    }
    
    // Método para simular correr
    public static void correr(String nombre) {
        synchronized (CarreraPorRelevos.class) {
            while (relevo) {
                try {
                    CarreraPorRelevos.class.wait();
                } catch (InterruptedException e) {
                }
            }
            relevo = true;
            // Simular correr
            try {
                int tiempo = 9000 + new Random().nextInt(2000);
                System.out.println(nombre + " empieza a correr. Tiempo: " + tiempo / 1000 + "s");
                Thread.sleep(tiempo);
                System.out.println(nombre + " ha terminado de correr.");
            } catch (InterruptedException e) {
            }
            relevo = false;
            CarreraPorRelevos.class.notify();
        }
    }
}
