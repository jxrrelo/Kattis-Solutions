import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
        din = new DataInputStream(System.in);
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

    public char nextWord() throws IOException {
        do {
            if (bufferPointer == bytesRead)
                fillBuffer();
        } while(buffer[bufferPointer++] <= ' ');

        int bufferIter = --bufferPointer;

        char c = (char) buffer[bufferIter];
        while(true) {
            if(bufferIter == bytesRead) {
                fillBuffer();
                bufferIter = 0;
            }
            if(buffer[bufferIter] <= ' ') break;
            bufferIter++;
        }
        bufferPointer = bufferIter;

        return c;
    }

    public String readLine() throws IOException {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1)
        {
            if (c == '\n')
                break;
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
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