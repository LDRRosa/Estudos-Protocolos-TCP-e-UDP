/**
 * Leandro Rosa da Silva
 * 20/03/2026
 * * Resumo: Cliente TCP simples que estabelece uma nova conexão para cada 
 * envio de fuso horário e exibe a data retornada pelo servidor.
 */


package TCPSingleThread;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Socket socket = null;

            try {
                System.out.print("Digite a região (ou 'sair'): ");
                String zone = scanner.nextLine();

                if (zone.equalsIgnoreCase("sair")) {
                    System.out.println("Encerrando cliente...");
                    break;
                }

                socket = new Socket("localhost", 6789);

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                PrintWriter output = new PrintWriter(
                        socket.getOutputStream(), true
                );

                // envia a região
                output.println(zone);

                // recebe resposta
                String response = input.readLine();

                System.out.println("Resposta: " + response);

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());

            } finally {
                try {
                    if (socket != null) socket.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar socket");
                }
            }
        }

        scanner.close();
    }
}