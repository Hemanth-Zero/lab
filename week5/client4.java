
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class client4 {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 8080);
            BufferedReader in =new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            Random random = new Random();
            System.out.print("\n4 ques");
            int y = random.nextInt(100);
            out.println(y);
            System.out.print("\n Grade of "+y+" is "+in.readLine());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
