
package week3;

public class hamming {
    public static void main(String[] args) {
        String msg = "1101011011";
        int m = msg.length();

        int r = 0;
        while (Math.pow(2, r) < m + r + 1) r++;

        char[] ans = new char[m + r + 1];
        char[] data = msg.toCharArray();

        int ar = 0;
        int di = 0;

        for (int i = 1; i <= m + r; i++) {
            if (i == (int)Math.pow(2, ar)) {
                ans[i] = '0';
                ar++;
            } else {
                ans[i] = data[di++];
            }
        }

        for (int i = 0; i < r; i++) {
            int parityPos = (int)Math.pow(2, i);
            int count = 0;

            for (int j = 1; j <= m + r; j++) {
                if ((j & parityPos) != 0 && ans[j] == '1') {
                    count++;
                }
            }

            ans[parityPos] = (count % 2 == 0) ? '0' : '1';
        }

        StringBuilder ham = new StringBuilder();

        for (int i = 1; i <= m + r; i++) {
            ham.append(ans[i]);
        }

        System.out.println("Hamming code: " + ham);

        // Introduce an error at position 5
        ans[5] = (ans[5] == '0') ? '1' : '0';

        int errorPos = 0;

        for (int i = 0; i < r; i++) {
            int parityPos = (int)Math.pow(2, i);
            int count = 0;

            for (int j = 1; j <= m + r; j++) {
                if ((j & parityPos) != 0 && ans[j] == '1') {
                    count++;
                }
            }

            if (count % 2 != 0) {
                errorPos += parityPos;
            }
        }

        System.out.println("Error position: " + errorPos);

        if (errorPos > 0 && errorPos <= m + r) {
            ans[errorPos] = (ans[errorPos] == '0') ? '1' : '0';
        }

        System.out.print("Corrected code: ");

        for (int i = 1; i <= m + r; i++) {
            System.out.print(ans[i]);
        }
    }
}
