import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class selectivewindow {

    static int p = 0;
    static boolean ack[] = new boolean[3];

    static String time() {
        return LocalTime.now().format(
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
        );
    }

    static void send(int q) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < 3 && q + i < 10; i++) {
            System.out.println(time() + " Packet sent " + (q + i));
        }
    }

    static void acking(int q) {
        Random random = new Random();
        for (int i = 0; i < 3 && q + i < 10; i++) {
            if (random.nextInt(2) == 1) {
                ack[i] = true;
                System.out.println(time() + " ACK received " + (q + i));
            } else {
                ack[i] = false;
                System.out.println(time() + " Packet " + (q + i) + " not acknowledged");
            }
        }
    }

    static void resend(int q) {
        Random random = new Random();
        while (true) {
            System.out.println(time() + " Resending Packet " + q);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (random.nextInt(2) == 1) {
                System.out.println(time() + " ACK received " + q);
                return;
            } else {
                System.out.println(time() + " Packet " + q + " not acknowledged");
            }
        }
    }

    static void evaluate(int q) {
        for (int i = 0; i < 3 && q + i < 10; i++) {
            if (!ack[i]) {
                resend(q + i);
                ack[i] = true;
            }
        }
        System.out.println(time() + " Frame completed\n");
        p = q + 3;
        for (int i = 0; i < 3; i++) {
            ack[i] = false;
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