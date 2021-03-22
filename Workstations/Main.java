import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
*   LOH FAH YAO, JARYL A0202023H
 */

public class Main {
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

    public static void main(String[] args) throws IOException{
        Reader r = new Reader();
        PriorityQueue<Integer> arrivals = new PriorityQueue<>(new MyComparator());
        PriorityQueue<Integer> departures = new PriorityQueue<>(new MyComparator());
        int n = r.nextInt();
        int m = r.nextInt();
        int saves = 0;

        for(int i = 0; i < n; i++) {
            int a = r.nextInt();
            int s = r.nextInt();
            arrivals.add(a);
            departures.add(a + s);
        }

        while(!arrivals.isEmpty()) {
            int arrival = arrivals.poll();
            while(arrival - departures.peek() > m) //workstation goes into idle mode
                departures.poll();
            if(departures.peek() <= arrival) { //next arrival comes on time to reuse current workstation
                departures.poll();
                saves += 1; //no need to unlock another workstation
            }
        }

        System.out.println(saves);
    }
}

class MyComparator implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        return a - b;
    }
}