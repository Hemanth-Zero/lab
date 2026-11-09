
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client6 {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 8080);
            BufferedReader in =new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            

            System.out.println("\n6 ques");
            out.println("apple");
            out.println(2);
            String res = in.readLine();
            System.out.println("for apple,2 "+res);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
