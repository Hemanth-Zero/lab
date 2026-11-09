import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class slidingwindow {

    static int p = 0;
    static boolean ack[] = new boolean[3];

    static String time() {
        return LocalTime.now().format(
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
        );
    }

    static void send(int q) {
        try {
                Thread.sleep(2000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        int i = 0;
        while (i < 3 && q + i < 10) {
            System.out.println(time() + " Packet sent " + (q + i));
            i++;
        }
    }

    static void acking(int q) {
        Random random = new Random();
        for (int w = 0; w < 3 && q + w < 10; w++) {
            if (random.nextInt(2) == 1) {
                ack[w] = true;
            } else {
                ack[w] = false;
            }
        }
    }

    static void evaluate(int q) {
        for (int k = 0; k < 3 && q + k < 10; k++) {
            if (!ack[k]) {
                System.out.println(time() + " Packet " + (q + k) + " not acknowledged");
                p = q + k;
                System.out.println("\n\n Resending from Packet " + p);
                return;
            }
            System.out.println(time() + " Packet " + (q + k) + " acknowledged");
        }
        p = q + 3;
        for (int w = 0; w < 3; w++) {
            ack[w] = false;
        }
    }

    public static void main(String[] args) {
        while (p < 10) {
            send(p);
            acking(p);
            evaluate(p);
        }
    }
}