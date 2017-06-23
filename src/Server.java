import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(4446);
            ReadThread readThread = new ReadThread(socket);
            readThread.start();

            String message;
            Scanner scanner = new Scanner(System.in);

            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("10.0.0.255"), 4446);

            while (true) {
                message = scanner.nextLine();
                message = "[Admin] " + message;
                packet.setData(message.getBytes());
                socket.send(packet);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ReadThread extends Thread {
    private DatagramSocket socket;
    public ReadThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());

              // if(!received.startsWith("[Adi] ")){
                    System.out.println(received);
             //  }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

