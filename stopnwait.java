import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class stopnwait {
    static int[] data = {101,102,103,104,105};
    static int p=0;
    static String time() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
    }

    static public int ack(int i){
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            System.out.println(time()+" ACK received packetnumber "+i);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time()+" ACK lost ");
            return 0;
        }
        return 1;
    }

    static public int send(int i){
        try {
                Thread.sleep(1000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        return ack(i);
    }

    public static void main(String[] ags){
        while(p<10){
            System.out.println(time()+" Sending package "+p);
            p+=send(p);
        }
    }

}
