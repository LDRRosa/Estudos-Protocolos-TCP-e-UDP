/**
 * Leandro Rosa da Silva
 * 20/03/2026
 * * Resumo: Servidor UDP que recebe identificadores de fuso horário, processa 
 * a data/hora atual da região solicitada e envia a resposta ao cliente.
 */

package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(6789);
            byte[] buffer = new byte[1024];

            System.out.println("Servidor UDP rodando...");

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String zoneId = new String(request.getData(), 0, request.getLength());

                String response;

                try {
                    ZonedDateTime now = ZonedDateTime.now(java.time.ZoneId.of(zoneId));
                    response = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                } catch (Exception e) {
                    response = "Erro: Região inválida";
                }

                byte[] respBytes = response.getBytes();

                DatagramPacket reply = new DatagramPacket(
                        respBytes,
                        respBytes.length,
                        request.getAddress(),
                        request.getPort());

                socket.send(reply);
            }

        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        } finally {
            if (socket != null)
                socket.close();
        }
    }

}
