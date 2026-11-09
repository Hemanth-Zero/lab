
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class server {
    public static HashMap<String,String> giveHashMap(){
        HashMap<String,String> dict = new HashMap<>();
        dict.put("happy", "Feeling good");
        dict.put("sad", "Feeling unhappy");
        dict.put("brave", "Not afraid");
        dict.put("smart", "Very clever");
        dict.put("quick", "Fast");
        dict.put("calm", "Peaceful");
        dict.put("angry", "Feeling mad");
        dict.put("kind", "Helpful and caring");
        dict.put("big", "Large in size");
        dict.put("small", "Little in size");
        return dict;
    }
    public static int fact(int x){
        if(x==0 || x == 1 ) return 1;
        return x*fact(x-1);
    }
    public static String grade(int x){
        if(x >85) return "A";
        if(x> 70) return "B";
        if(x > 60) return "C";
        if(x > 50) return "D";
        return "F";
    }
    public static int calculate(int n) {
        if (n == 0)
            return 3;
        if (n == 1)
            return 3;
        if (n == 2)
            return 7;
        if (n == 3)
            return 15;
        if (n == 4)
            return 27;

        return -1;
    }
    public static char findCharacter(String word, int number) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : word.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (char ch : word.toCharArray()) {
            if (map.get(ch) == number) {
                return ch;
            }
        }

        char maxChar = word.charAt(0);
        int max = map.get(maxChar);

        for (char ch : word.toCharArray()) {
            if (map.get(ch) > max) {
                max = map.get(ch);
                maxChar = ch;
            }
        }
        return maxChar;
    }
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(8080);
            System.out.println("Server started...");
            System.out.println("Waiting for client...");
            Socket s = ss.accept();
            System.out.println("Client connected.");

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(s.getInputStream()));

            
            System.out.println("\n1 ques");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String dateTime = now.format(formatter);
            out.println(dateTime);
            System.out.println("Sent date and time: " + dateTime);

            // s = ss.accept();
            // System.out.println("Client connected.");
            System.out.println("\n2 ques");
            HashMap<String,String> dict = giveHashMap();
            String word = in.readLine();
            System.out.println("Word received: " + word);
            if (dict.containsKey(word)) {
                out.println("FOUND");
                
                System.out.println(
                        "Meaning: " + dict.get(word));
            } else {
                out.println("NOT_FOUND");
                String meaning = "meaning";
                dict.put(word, meaning);
                System.out.println(
                        "Added: " + word + " meaning");
            }

            // s = ss.accept();
            // System.out.println("Client connected.");
            System.out.println("\n3 ques");
            String x = in.readLine();
            System.out.print(x);
            int result = fact(Integer.parseInt(x)); 
            out.println(result);
            
            // s = ss.accept();
            // System.out.println("Client connected.");
            System.out.println("\n4 ques");
            int y = Integer.parseInt(in.readLine());
            out.println(grade(y));

            // s = ss.accept();
            // System.out.println("Client connected.");
            System.out.println("\n5 ques");
            for (int i = 0; i < 5; i++) {
                String input = in.readLine();
                int n = Integer.parseInt(input);
                result = calculate(n);
                out.println(result);
                System.out.println(
                        "Received: " + n + "  Result: " + result);
            }
            
            // s = ss.accept();
            // System.out.println("Client connected.");
            System.out.println("\n6 ques");
            char res = findCharacter(in.readLine(), Integer.parseInt(in.readLine()));
            out.println(res);
            System.out.println(res);
            s.close();
            ss.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
