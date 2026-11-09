package w10;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class q1bserver {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9877);
        byte[] receiveData = new byte[1024];
        System.out.println("UDP Server running...");

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String code = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            String result = "BAD DATA";

            if (code.length() == 7) {
                int[] b = new int[8];
                for (int i = 0; i < 7; i++) {
                    b[i + 1] = code.charAt(i) - '0';
                }

                int c1 = b[1] ^ b[3] ^ b[5] ^ b[7];
                int c2 = b[2] ^ b[3] ^ b[6] ^ b[7];
                int c3 = b[4] ^ b[5] ^ b[6] ^ b[7];

                if (c1 == 0 && c2 == 0 && c3 == 0) {
                    result = "GOOD DATA";
                }
            } else {
                result = "INVALID LENGTH";
            }

            byte[] sendData = result.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            socket.send(sendPacket);
        }
    }
}