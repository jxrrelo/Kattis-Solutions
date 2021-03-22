import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public int start;
public int end;
public int weight;

public RoadEdge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        }

@Override
public int compareTo(RoadEdge o) {
        return this.weight - o.weight;
        }

        int getStart() {
        return this.start;
        }

        int getEnd() {
        return this.end;
        }

public class Graph {
    public int V, E;
    public Edge[] edge;

    public Graph(int , int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    public int find(subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    public void union(subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public void kruskal() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Edge result[] = new Edge[V];

        int e = 0;
        int i = 0;

        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edge);
        subset[] subsets = new subset[V];

        for (i = 0; i < V; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < V - 1) {
            Edge next_edge;
            next_edge = edge[i++];
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
            }
        }
        for (i = 0; i < e; ++i) {
            bw.write(Integer.toString(result[i].src + 1) + " " + Integer.toString(result[i].dest + 1));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    };

    public static class subset {
        int parent;
        int rank;
    };
}
