package reverse.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.max;
import static java.util.Arrays.fill;

public class ReverseTranspose1 {
    private final static int leng = 10;

    private static int[] create(int length) {
        int[] ints = new int[length];
        fill(ints, 0);
        return ints;
    }

    public static void main(String[] args) {
        Reader in = new Reader();
        int[][] str = new int[leng][leng];
        int n = 0, lenx = leng, mx = 0;
        int[] m = create(leng);
        int[] last = create(leng);
        while (true) {
            String line = in.nextLine();
            if (line == null) {
                break;
            }
            if(line.isEmpty()) continue;
            if(line.equals("a")) break;
            {
                if (n == lenx) {
                    {
                        int[] b = create(lenx * 2);
                        System.arraycopy(m, 0, b, 0, lenx);
                        m = b;
                    }
                    int[][] a = new int[lenx * 2][];
                    for (int i = 0; i < lenx; ++i) {
                        int[] b = create(m[i]);
                        System.arraycopy(str[i], 0, b, 0, m[i]);
                        a[i] = b;
                    }
                    str = a;
                    lenx *= 2;
                }
                if (str[n] == null) {
                    str[n] = create(leng);
                }
            }
            int y = 0;
            int sum = 0;
            StringBuilder s = new StringBuilder();
            while (line.length() != y) {
                if (Character.isWhitespace(line.charAt(y))) {
                    if (s.substring(0).isEmpty()) {
                        y++;
                        continue;
                    }
                    int l = last[sum];
                    last[sum]++;
                    if (m[l] == str[l].length) {
                        int[] b = create(str[l].length * 2);
                        System.arraycopy(str[l], 0, b, 0, str[l].length);
                        str[l] = b;
                    }
                    str[l][m[l]] = Integer.parseInt(s.substring(0));
                    m[l]++;
                    mx = max(mx, m[l]);
                    if(mx == last.length){
                        int[] b = create(last.length * 2);
                        System.arraycopy(last, 0, b, 0, last.length);
                        last = b;
                    }
                    sum++;
                    s = new StringBuilder();
                } else {
                    s.append(line.charAt(y));
                }
                y++;
            }
            if (!s.substring(0).isEmpty()){
                int l = last[sum];
                last[sum]++;
                if (m[l] == str[l].length) {
                    int[] b = create(str[l].length * 2);
                    System.arraycopy(str[l], 0, b, 0, str[l].length);
                    str[l] = b;
                }
                str[l][m[l]] = Integer.parseInt(s.substring(0));
                m[l]++;
                mx = max(mx, m[l]);
                if(mx == last.length){
                    int[] b = create(last.length * 2);
                    System.arraycopy(last, 0, b, 0, last.length);
                    last = b;
                }
            }
            if(m[n] != 0) n++;
        }
        for (int i = 0; i < mx; ++i) {
            for (int j = 0; j < n; ++j) {
                if(i >= m[j]) break;
                System.out.print(str[j][i] + " ");
            }
            System.out.println();
        }
    }
    static class Reader {
        BufferedReader br;
        Reader() {
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
