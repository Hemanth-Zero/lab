package w10;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class q1aclient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6001);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter 7-bit Hamming code: ");
            String input = scanner.nextLine();
            out.println(input);
            
            if (input.equals("exit")) break;
            
            System.out.println("Server response: " + in.readLine());
        }
        
        socket.close();
        scanner.close();
    }
}