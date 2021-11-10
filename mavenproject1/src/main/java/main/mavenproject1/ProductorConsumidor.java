package main.mavenproject1;

import java.io.IOException;

// La clase ProductorConsumidor que instancia y
// inicia los threads.
// A continuaci�n espera una acci�n sobre el teclado para
// detener el conjunto.
class ProductorConsumidor {

    public void Action() {

        System.out.println("Inicio Tratamiento global");

        // Instanciaci�n del thread productor
        ProductorDeFranjas miReceptorDeFranjas
                = new ProductorDeFranjas();
        Thread threadProductor
                = new Thread(miReceptorDeFranjas);
        // Arranca el thread productor
        threadProductor.start();

        // Instanciaci�n del thread consumidor
        Thread threadConsumidor
                = new Thread(
                new ConsumidorDeFranjas(miReceptorDeFranjas));
        // Arranca el thread consumidor
        threadConsumidor.start();

        // Los dos threads funcionan
        System.out.println(
                "Pulse en una tecla para parar... ");
        try {
            // ... hasta que se pulsa una tecla
            System.in.read();
        }
        catch (IOException ex) {}

        // En este momento se pide la interrupci�n
        // de los dos treads
        System.out.println("Abandono solicitado");
        threadProductor.interrupt();
        threadConsumidor.interrupt();
        try {
            // Despu�s se espera a terminar
            threadProductor.join();
            threadConsumidor.join();
        } catch (InterruptedException ex) {}

        System.out.println("Fin Tratamiento global");

    }
}
