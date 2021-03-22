import java.io.*;
import java.util.*;

/*
*   LOH FAH YAO, JARYL
*   A0202023H
*   CS2040S ODA Lab 9
 */

public class LostMap {
    public static class Prims {
        public static class Edge implements Comparable<Edge> {
            public int source;
            public int dest;
            public int cost;

            public Edge(int s, int d, int c) {
                source = s;
                dest = d;
                cost = c;
            }

            public int compareTo(Edge e) {
                return this.cost != e.cost ? this.cost - e.cost : this.source != e.source ? this.source - e.source : this.dest - e.dest;
            }
        }

        private final PriorityQueue<Edge> edgePQ = new PriorityQueue<Edge>();
        private final ArrayList<Integer> visited = new ArrayList<Integer>();
        private final ArrayList<ArrayList<Edge>> edgeList = new ArrayList<ArrayList<Edge>>();

        public void insertE(int source, int dest, int cost) {
            int visitedSize = this.visited.size();
            int edgeListSize = this.edgeList.size();

            if (visitedSize <= source)
                this.visited.add(0);
            if (edgeListSize <= source)
                this.edgeList.add(new ArrayList<Edge>());

            ArrayList<Edge> src = this.edgeList.get(source);
            src.add(new Edge(source, dest, cost));
        }

        public void examineV(int v) {
            visited.set(v, 1);

            int size = edgeList.get(v).size();

            for (int i = 0; i < size; i++) {
                Edge e = edgeList.get(v).get(i);
                int isTaken = visited.get(e.dest);

                if (isTaken == 0) edgePQ.offer(new Edge(e.source, e.dest, e.cost));
            }
        }

        public void run() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            examineV(0);

            while (!edgePQ.isEmpty()) {
                Edge edge = edgePQ.poll();
                int isVisited = visited.get(edge.dest);

                if (isVisited == 0) {
                    examineV(edge.dest);

                    int dest = edge.dest + 1;
                    int source = edge.source + 1;

                    if (source > dest)
                        bw.write(dest + " " + source + "\n");
                    else
                        bw.write(source + " " + dest + "\n");
                }
            }

            bw.flush();
            bw.close();
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Prims mst = new Prims();

        for (int i = 0; i < n; i++) {
            String[] params = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                if (i != j) mst.insertE(i, j, Integer.parseInt(params[j]));
            }
        }

        mst.run();

    }
}
