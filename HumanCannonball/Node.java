/**
 * CS2040S One Day Lab Assignment 10
 * LOH FAH YAO, JARYL A0202023H
 */

public class Node implements Comparable<Node> {
    int index;
    double dist;

    public Node(int index, double dist) {
        this.index = index;
        this.dist = dist;
    }

    public int compareTo(Node other) {
        return Double.compare(dist, other.dist);
    }
}
