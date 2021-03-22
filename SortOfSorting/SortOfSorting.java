import java.io.*;
import java.util.*;

/**
 * Loh Fah Yao, Jaryl A0202023H
 */

public class SortOfSorting {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(n != 0) {
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) arr[i] = br.readLine();

            Arrays.sort(arr, new MyComparator());

            for(String s : arr) {
                bw.write(s);
                bw.newLine();
            }
            bw.newLine();
            n = Integer.parseInt(br.readLine());
        }
        bw.flush();
        bw.close();
    }
}

class MyComparator implements Comparator<String> {
    public int compare(String x, String y) {
        return x.charAt(0) - y.charAt(0) == 0 ? x.charAt(1) - y.charAt(1) : x.charAt(0) - y.charAt(0);
    }
}