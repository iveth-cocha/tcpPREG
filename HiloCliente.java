import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

public class HiloCliente extends Thread {
    private Socket socketCliente;

    public HiloCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    public void run() {
        try (BufferedReader buferEntrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
             PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)) {

            // Iterador para recorrer las preguntas
            Iterator<Map.Entry<String, String>> iteradorPreguntas = Servidor.preguntas.entrySet().iterator();

            while (iteradorPreguntas.hasNext()) {
                Map.Entry<String, String> preguntaActual = iteradorPreguntas.next();
                String pregunta = preguntaActual.getKey();
                String respuestaCorrecta = preguntaActual.getValue();

                // Enviar la pregunta al cliente
                salida.println("Pregunta: " + pregunta);

                // Leer la respuesta del cliente
                String respuestaCliente = buferEntrada.readLine();
                System.out.println("Respuesta del cliente: " + respuestaCliente);

                // Evaluar la respuesta
                if (respuestaCliente != null && respuestaCliente.equalsIgnoreCase(respuestaCorrecta)) {
                    salida.println("Correcto!");
                } else {
                    salida.println("Incorrecto! La respuesta correcta es: " + respuestaCorrecta);
                }
            }

            // Enviar un mensaje final al cliente
            salida.println("No hay más preguntas. Gracias por participar!");

        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
}

