
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class client2 {
    static String[] words = {"happy", "sad", "brave", "smart", "quick", "calm", "angry", "kind", "big", "small", "strong", "weak", "honest", "rich", "poor", "beautiful", "fast", "slow", "clever", "bright", "lazy", "friendly"};
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 8080);
            BufferedReader in =new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.println("\n2 ques");
            String response = "NOT_FOUND";
            Random random = new Random();
            String word = words[random.nextInt(words.length)];
            System.out.println("Selected word: " + word);
            out.println(word);
            response = in.readLine();
            System.out.print(response);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
