import java.io.*;
import java.util.*;

public class Islands {
    public static int row, col;
    public static char[][] image;
    public static int[][] visited;
    public static int numIsland = 0;

    //up, down, left, right
    public static int[] dx = { 0, 0, -1, 1 };
    public static int[] dy = { 1, -1, 0, 0 };

    public static void bfs(Land land) {
        Queue<Land> q = new ArrayDeque<>();
        q.offer(land);
        while (true) {
            if (q.isEmpty()) break;
            Land currLand = q.poll();
            if (visited[currLand.r][currLand.c] == 1) continue;
            visited[currLand.r][currLand.c] = 1;
            for (int i = 0; i < 4; i++) {
                int r = currLand.r + dx[i];
                int c = currLand.c + dy[i];
                if ((r >= 0 && r < row) && (c >= 0 && c < col) && (image[r][c] == 'L' || image[r][c] == 'C'))
                    q.offer(new Land(r, c));
            }
        }
        numIsland++;
    }

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        OutputStream out = new BufferedOutputStream(System.out);
        row = r.nextInt();
        col = r.nextInt();
        image = new char[row][col];
        visited = new int[row][col];
        Queue<Land> q = new ArrayDeque<>();

        //read inputs for image
        for (int i = 0; i < row; i++) {
            String params = r.readLine();
            for (int j = 0; j < col; j++) {
                image[i][j] = params.charAt(j);
                if (image[i][j] == 'L') q.offer(new Land(i, j));
            }
        }

        while (true) {
            if (q.isEmpty()) break;
            Land currLand = q.poll();
            if (visited[currLand.r][currLand.c] == 1) continue;
            bfs(currLand);
        }

        out.write((numIsland + "\n").getBytes());
        out.flush();
        out.close();
    }
}