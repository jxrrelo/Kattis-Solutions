import java.io.*;

public class Spelling {
    private static StringBuilder resolve(String input) {
        StringBuilder sb = new StringBuilder();
        char[] arr = input.toCharArray();
        int prev = -1;
        int key = -1;
        int numTimes = 0;

        for(char c : arr) {
            int ascii = (int) c;
            if(ascii >= 97 && ascii <= 114) {
                key = (ascii - 97) / 3 + 2;
                numTimes = (ascii - 97) % 3 + 1;
            } else if(ascii == 115) {
                key = 7;
                numTimes = 4;
            } else if(ascii >= 116 && ascii <= 121) {
                key = (ascii - 98) / 3 + 2;
                numTimes = (ascii - 98) % 3 + 1;
            } else if(ascii == 122) {
                key = 9;
                numTimes = 4;
            } else {
                key = 0;
                numTimes = 1;
            }
            if(key == prev)
                sb.append(" ");
            for (int i = 0; i < numTimes; i++) {
                sb.append(key);
                prev = key;
            }
        }
        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numInputs = Integer.parseInt(br.readLine());
        for (int i = 0; i < numInputs; i++) {
            StringBuilder sb = new StringBuilder("Case #");
            String input = br.readLine();
            sb.append(i + 1);
            sb.append(": ");
            sb.append(resolve(input));
            bw.write(sb.toString());
            bw.newLine();
        }
        bw.flush();
    }
}