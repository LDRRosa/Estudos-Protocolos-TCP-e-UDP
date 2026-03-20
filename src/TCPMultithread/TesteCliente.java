/**
 * Leandro Rosa da Silva
 * 20/03/2026
 * * Resumo: Classe de teste para o ambiente multithread que permite ao usuário 
 * interagir com o servidor enviando regiões de fuso horário via console.
 */

package TCPMultithread;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TesteCliente {
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

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                out.println(zone);

                String resposta = in.readLine();
                System.out.println("Resposta do Servidor: " + resposta);

            } catch (IOException e) {
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