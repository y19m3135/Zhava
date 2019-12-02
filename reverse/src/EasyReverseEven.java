package reverse.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class EasyReverseEven {
    public static void main(String[] args) {
        ArrayList<String> str = new ArrayList<>();
        FastReader in = new FastReader();
        while (true) {
            String line = in.nextLine();
            if(line == null) break;
            str.add(line);
        }
        Collections.reverse(str);
      // for (int i = 0; i < str.le) {
      //     String[] arr = s.split("\\s+");
      //     for (int j = arr.length - 1; j >= 0; --j) {
      //         if (arr[j].isEmpty()) continue;
      //         System.out.print(arr[j] + " ");
      //         System.out.println();
      //     }
      // }
    }
    static class FastReader {
        BufferedReader br;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
