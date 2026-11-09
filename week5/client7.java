
import java.io.*;
import java.net.*;
import java.util.Random;

public class client7 {
    static String[] words = {"happy", "sad", "brave", "smart", "quick", "calm", "angry", "kind", "big", "small", "strong", "weak", "honest", "rich", "poor", "beautiful", "fast", "slow", "clever", "bright", "lazy", "friendly"};
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            BufferedReader in =new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String dateTime = in.readLine();
            System.out.println("\n1 ques");
            System.out.println("Date and Time from Server: " + dateTime);
            System.out.println("\n2 ques");
            String response = "NOT_FOUND";
            Random random = new Random();
            String word = words[random.nextInt(words.length)];
            System.out.println("Selected word: " + word);
            out.println(word);
            response = in.readLine();
            System.out.print(response);

            
            System.out.println("\n3 ques");
            int x = random.nextInt(10);
            out.println(x);
            System.out.print("fact of "+x+" "+in.readLine());

            System.out.print("\n4 ques");
            int y = random.nextInt(100);
            out.println(y);
            System.out.print("\n Grade of "+y+" is "+in.readLine());

            System.out.print("\n5 ques\n");
            for (int i = 0; i < 5; i++) {
                out.println(i);
                String result = in.readLine();
                System.out.println(
                        "Sent: " + i + "  Received: " + result);
            }

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
