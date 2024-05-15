import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Creo socket y pongo la IP del cliente
            Socket socketCliente = new Socket("localhost", 5000);
            System.out.println("Cliente conectado");

            // CREAR BUFFER DE ENTRADA Y SALIDA
            BufferedReader buferEntrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Leer la pregunta del servidor
                String pregunta = buferEntrada.readLine();
                System.out.println(pregunta);

                // Verificar si no hay más preguntas
                if (pregunta.equals("No hay más preguntas. Gracias por participar!")) {
                    break;
                }

                // Leer la respuesta del usuario desde el teclado
                System.out.print("Tu respuesta: ");
                String respuestaUsuario = scanner.nextLine();

                // Enviar la respuesta al servidor
                salida.println(respuestaUsuario);

                // Leer la retroalimentación del servidor
                String feedback = buferEntrada.readLine();
                System.out.println(feedback);
            }

            // Cerrar recursos
            //scanner.close();
            //socketCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
