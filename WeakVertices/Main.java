import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        OutputStream bw = new BufferedOutputStream(System.out);
        while(true) {
            int n = r.nextInt();
            if (n == -1) break;

            int[][] matrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = r.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                boolean weak = true;
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (i == k || i == j || j == k) continue;
                        if (matrix[i][k] == 1 && matrix[i][j] == 1 && matrix[j][k] == 1)
                            weak = false;
                    }
                }

                if (weak)
                    bw.write((i + " ").getBytes());
            }
            bw.write("\n".getBytes());
        }
        bw.flush();
        bw.close();
    }
}
