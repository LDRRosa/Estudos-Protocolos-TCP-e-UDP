/**
 * Leandro Rosa da Silva
 * 20/03/2026
 * * Resumo: Servidor TCP que atende uma requisição por vez (single-thread), 
 * processando o fuso horário enviado pelo cliente de forma sequencial.
 */



package TCPSingleThread;

import java.net.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(6789);
            System.out.println("Servidor TCP rodando...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 🔴 BLOQUEIA aqui
                System.out.println("Cliente conectado");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );

                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true
                );

                String zoneId = in.readLine();
                String response;

                try {
                    ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zoneId));
                    response = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                } catch (Exception e) {
                    response = "Erro: Região inválida";
                }

                out.println(response);

                clientSocket.close(); // 🔴 encerra conexão
                System.out.println("Cliente atendido e desconectado");
            }

        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}