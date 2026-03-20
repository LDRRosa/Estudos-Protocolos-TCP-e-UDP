/**
 * Leandro Rosa da Silva
 * 20/03/2026
 * * Resumo: Servidor TCP Multithread que aceita múltiplas conexões simultâneas, 
 * delegando cada novo cliente para um ClienteHandler independente.
 */


package TCPMultithread;
import java.net.*;

public class TCPServerMulti {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(6789)) {

            System.out.println("Servidor TCP Multithread rodando...");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // cria uma nova thread para cada cliente
                ClienteHandler handler = new ClienteHandler(clientSocket);
                handler.start(); // 🔥 dispara a thread
            }

        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}