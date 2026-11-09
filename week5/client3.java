
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class client3 {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 8080);
            BufferedReader in =new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            Random random = new Random();
            System.out.println("\n3 ques");
            int x = random.nextInt(10);
            out.println(x);
            System.out.print("fact of "+x+" "+in.readLine());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
