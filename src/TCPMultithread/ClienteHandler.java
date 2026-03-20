/**
 * Leandro Rosa da Silva
 * 20/03/2026
 * * Resumo: Classe responsável por gerenciar a comunicação individual de cada cliente 
 * em uma thread separada, permitindo o processamento paralelo no servidor.
 */

package TCPMultithread;

import java.net.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ClienteHandler extends Thread {

    private Socket clientSocket;

    public ClienteHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {

        String clientInfo = clientSocket.getInetAddress() + ":" + clientSocket.getPort();
        String threadName = Thread.currentThread().getName();

        System.out.println("[" + threadName + "] Atendendo cliente: " + clientInfo);

        try (
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true
            );
        ) {

            String zoneId = in.readLine();
            String response;

            try {
                ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zoneId));
                response = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            } catch (Exception e) {
                response = "Erro: Região inválida";
            }

            out.println(response);

        } catch (Exception e) {
            System.out.println("[" + threadName + "] Erro: " + e.getMessage());

        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar socket");
            }

            System.out.println("[" + threadName + "] Finalizou cliente: " + clientInfo);
        }
    }
}