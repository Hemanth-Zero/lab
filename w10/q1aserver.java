package w10;
import java.io.*;
import java.net.*;

public class q1aserver {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6001);
        System.out.println("TCP Server running...");
        
        while (true) {
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            String code;
            while ((code = in.readLine()) != null) {
                if (code.equals("exit")) break;
                
                if (code.length() != 7) {
                    out.println("INVALID LENGTH");
                    continue;
                }
    
                int[] b = new int[8];
                for (int i = 0; i < 7; i++) {
                    b[i + 1] = code.charAt(i) - '0';
                }

                int c1 = b[1] ^ b[3] ^ b[5] ^ b[7];
                int c2 = b[2] ^ b[3] ^ b[6] ^ b[7];
                int c3 = b[4] ^ b[5] ^ b[6] ^ b[7];

                if (c1 == 0 && c2 == 0 && c3 == 0) {
                    out.println("GOOD DATA");
                } else {
                    out.println("BAD DATA");
                }
            }
            client.close();
        }
    }
}