import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * CS2040S One Day Lab Assignment 10
 * LOH FAH YAO, JARYL A0202023H
 */

public class HumanCannonball {
    public static double[][] grid;
    public static double[] timings;

    public static void dijkstra() {
        boolean[] visited = new boolean[timings.length];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.remove();
            if (visited[curr.index]) continue;
            visited[curr.index] = true;
            for (int i = 0; i < timings.length; i++) {
                if (i != curr.index && !visited[i] && curr.dist + grid[curr.index][i] < timings[i]) {
                    timings[i] = curr.dist + grid[curr.index][i];
                    pq.add(new Node(i, timings[i]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //Read inputs
        String[] params = br.readLine().split(" ");
        double x1 = Double.parseDouble(params[0]);
        double y1 = Double.parseDouble(params[1]);

        params = br.readLine().split(" ");
        double x2 = Double.parseDouble(params[0]);
        double y2 = Double.parseDouble(params[1]);

        int n = Integer.parseInt(br.readLine());
        double[][] coordinates = new double[n][2];
        grid = new double[n + 2][n + 2]; //declare grid size

        //Write coordinates
        for (int i = 0; i < n; i++) {
            String[] coor = br.readLine().split(" ");
            double x = Double.parseDouble(coor[0]);
            double y = Double.parseDouble(coor[1]);
            coordinates[i] = new double[]{x, y};
        }

        double distX = x1 - x2;
        double distY = y1 - y2;
        grid[0][1] = Math.sqrt(distX * distX + distY * distY) / 5;
        grid[1][0] = grid[0][1];

        //for x1, y1
        for (int i = 0; i < n; i++) {
            distX = coordinates[i][0] - x1;
            distY = coordinates[i][1] - y1;
            double dist = Math.sqrt(distX * distX + distY * distY);
            grid[0][i + 2] = dist / 5;
            grid[i + 2][0] = Math.abs(dist - 50)/5 + 2;
        }

        //for x2, y2
        for (int i = 0; i < n; i++) {
            distX = coordinates[i][0] - x2;
            distY = coordinates[i][1] - y2;
            double dist = Math.sqrt(distX * distX + distY * distY);
            grid[1][i + 2] = dist/5;
            grid[i + 2][1] = Math.abs(dist - 50)/5 + 2;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                distX = coordinates[j][0] - coordinates[i][0];
                distY = coordinates[j][1] - coordinates[i][1];
                double dist = Math.sqrt(distX * distX + distY * distY);
                grid[i + 2][j + 2] = Math.abs(dist - 50)/5 + 2;
                grid[j + 2][i + 2] = grid[i + 2][j + 2];
            }
        }

        timings = new double[n + 2];
        Arrays.fill(timings, Double.MAX_VALUE);
        dijkstra();

        bw.write(Double.toString(timings[1])); //print timing
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
