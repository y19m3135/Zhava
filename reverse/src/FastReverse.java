package reverse.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Arrays.fill;

public class FastReverse {
    private final static int leng = 10;

    private static int[] create(int length) {
        int[] ints = new int[length];
        fill(ints, 0);
        return ints;
    }

    public static void main(String[] args) {
        FastReader in = new FastReader();
        int[][] str = new int[leng][leng];
        int n = 0, lenx = leng;
        int[] m = create(leng);
        int leny;
        while (true) {
            String line = in.nextLine();
            if (line == null) {
                break;
            }
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
            leny = leng;
            if (str[n] == null) {
                str[n] = create(leng);
            }
            String[] arrs = (line).split("[\\s\\h\\v]");
            for (String s : arrs) {
                if (s.isEmpty()) continue;
                str[n][m[n]] = Integer.parseInt(s);
                m[n]++;
                if (m[n] == leny) {
                    leny *= 2;
                    int[] b = create(leny);
                    System.arraycopy(str[n], 0, b, 0, m[n]);
                    str[n] = b;
                }
            }
            n++;
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m[i] - 1; j >= 0; --j) {
                System.out.print(str[i][j] + " ");
            }
            System.out.println();
        }
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
