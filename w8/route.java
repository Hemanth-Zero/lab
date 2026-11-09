import java.util.*;

public class route {
    static int N = 5;
    static final int INF = 9999;

    static void dijkstra(int[][] g, int src) {
        int[] d = new int[N];
        int[] p = new int[N];
        boolean[] used = new boolean[N];

        Arrays.fill(d, INF);
        Arrays.fill(p, -1);

        d[src] = 0;

        for (int i = 0; i < N; i++) {
            int u = -1;
            int min = INF;

            for (int j = 0; j < N; j++) {
                if (!used[j] && d[j] < min) {
                    min = d[j];
                    u = j;
                }
            }

            if (u == -1)
                break;

            used[u] = true;

            for (int j = 0; j < N; j++) {
                if (g[u][j] != INF && d[u] + g[u][j] < d[j]) {
                    d[j] = d[u] + g[u][j];
                    p[j] = u;
                }
            }
        }

        System.out.println("Destination  Cost  Path");

        for (int i = 0; i < N; i++) {
            System.out.print(i + "      " + d[i] + "  ");

            if (i == src) {
                System.out.println("0");
                continue;
            }

            int[] path = new int[N];
            int k = 0;
            int x = i;

            while (x != -1) {
                path[k++] = x;
                if (x == src)
                    break;
                x = p[x];
            }

            for (int j = k - 1; j >= 0; j--) {
                System.out.print(path[j]);

                if (j != 0)
                    System.out.print(" -> ");
            }

            System.out.println();
        }
    }

    static void distanceVector(int[][] g) {

        int[][] d = new int[N][N];
        int[][] next = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                d[i][j] = g[i][j];
                if (i == j)
                    next[i][j] = i;
                else if (g[i][j] != INF)
                    next[i][j] = j;
                else
                    next[i][j] = -1;
            }
        }

        boolean change;

        do {
            change = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {

                        if (d[i][k] != INF &&
                            d[k][j] != INF &&
                            d[i][k] + d[k][j] < d[i][j]) {

                            d[i][j] = d[i][k] + d[k][j];
                            next[i][j] = next[i][k];
                            change = true;
                        }
                    }
                }
            }

        } while (change);

        for (int i = 0; i < N; i++) {

            System.out.println("\nRouter " + i);
            
            for (int j = 0; j < N; j++) {
                
                System.out.print("To " + j +
                " cost " + d[i][j]);
                
                if (next[i][j] != -1)
                    System.out.print(
                " hop to = " + next[i][j]);
                
                System.out.println();
            }
        }
    }
    public static void  printer(int[][] g){
        System.out.print("graph is \n");
        for(int i = 0 ; i< N ; i++){
            
            for(int j = 0 ; j< N ; j++){
                System.out.print(g[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[][] g = {
            {0, 2, INF, 1, INF},
            {2, 0, 3, INF, 4},
            {INF, 3, 0, 5, 6},
            {1, INF, 5, 0, 7},
            {INF, 4, 6, 7, 0}
        };
        printer(g);
        System.out.println("LSR");
        System.out.println("\nRouting table from Router 0:");
        dijkstra(g, 0);
        
        System.out.println("\nDVR");
        distanceVector(g);
        
        System.out.println("\nToplogiy changes");
        
        int is,in,jn;
        System.out.println(" enter any changes ( -1 for no) ");
        is = sc.nextInt();
        if(is == -1) return;
        System.out.println(" enter i ");
        in = sc.nextInt();
        System.out.println(" enter j");
        jn = sc.nextInt();
        g[in][jn] = INF;
        g[jn][in] = INF;
        printer(g);
        System.out.println("\nLSR after change");
        dijkstra(g, 0);
        
        System.out.println("\nDVR after chnge");
        distanceVector(g);
    }
}