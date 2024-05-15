import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {
    private static final int PORT = 5000;
    static Map<String, String> preguntas = new HashMap<>();

    static {
        preguntas.put("Pais más grande", "Rusia");
        preguntas.put("Película en que el barco se hunde por un glaciar", "Titanic");
        preguntas.put("Tiburón prehistórico", "Megalodon");
        preguntas.put("Ave que renace de las cenizas", "Fénix");
        preguntas.put("Religión predominante en México", "Catolicismo");
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);

            while (true) {
                Socket socketCliente = serverSocket.accept();
                new HiloCliente(socketCliente).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
