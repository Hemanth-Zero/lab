package w10;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class q1bclient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 7-bit Hamming code: ");
        String str = scanner.nextLine();

        byte[] sendData = str.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, 9877);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        System.out.println("Server response: " + new String(receivePacket.getData(), 0, receivePacket.getLength()));

        socket.close();
        scanner.close();
    }
}