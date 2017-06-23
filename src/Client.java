import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        String message;
        Scanner scanner = new Scanner(System.in);
        try {
            DatagramSocket socket = new DatagramSocket(4445);
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("10.0.0.255"), 4446);
            while(true) {
                message = scanner.nextLine();
                packet.setData(message.getBytes());
                socket.send(packet);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
