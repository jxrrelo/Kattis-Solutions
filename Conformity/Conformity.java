import java.io.*;
import java.util.*;

/**
 * Loh Fah Yao, Jaryl A0202023H
 */

public class Conformity {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        HashMap<List<Integer>, Integer> combi = new HashMap<>();
        List<Integer> courses = new ArrayList<>();
        int frosh = r.nextInt();
        int max = 0;
        int total = 0;

        //read all
        for(int i = 0; i < frosh; i++) {
            courses = Arrays.asList(r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt());
            Collections.sort(courses);
            int val = combi.getOrDefault(courses, 0) + 1;
            combi.put(courses, val);
            if(val > max) total = 0;
            max = Math.max(max, val);
            if(val == max) total += val;
        }

        System.out.println(total);
    }
}