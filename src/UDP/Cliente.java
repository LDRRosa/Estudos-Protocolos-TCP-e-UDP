package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        String zone; // pode trocar
        while(true) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a região (ex: America/Sao_Paulo): ");
        zone = scanner.nextLine();
        byte[] msg = zone.getBytes();
        
        if (msg.length == 0) {
            System.out.println("Região não pode ser vazia. Tente novamente.");
        } else {
            sendRequest(zone);
        }}

    }

    public static void sendRequest(String zone) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(5000); // 5 segundos

            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] msg = zone.getBytes();

            DatagramPacket request = new DatagramPacket(msg, msg.length, serverAddress, 6789);
            socket.send(request);

            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);

            socket.receive(response);
            String reply = new String(response.getData(), 0, response.getLength());
            System.out.println("Resposta do servidor: " + reply);

        } catch (SocketTimeoutException e) {
            System.out.println("Tempo de espera esgotado. O servidor pode estar offline.");
        } catch (Exception e) {
            System.out.println("Erro no cliente: " + e.getMessage());
        } finally {
            if (socket != null)
                socket.close();
        }
    }
}
