
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client5 {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 8080);
            BufferedReader in =new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.print("\n5 ques\n");
            for (int i = 0; i < 5; i++) {
                out.println(i);
                String result = in.readLine();
                System.out.println(
                        "Sent: " + i + "  Received: " + result);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
