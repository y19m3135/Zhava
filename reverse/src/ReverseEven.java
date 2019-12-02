package reverse.src;

import java.util.Scanner;

import static java.util.Arrays.fill;

public class ReverseEven {
    private final static int leng = 10;

    private static int[] create(int length) {
        int[] ints = new int[length];
        fill(ints, 0);
        return ints;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] str = new int[leng][leng];
        int n = 0, lenx = leng;
        int[] m = create(leng);
        int leny;
        while (sc.hasNextLine()) {
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
            String line = sc.nextLine();
            int y = 0;
            StringBuilder s = new StringBuilder();
            while (line.length() != y) {
                if (Character.isWhitespace(line.charAt(y))) {
                    if (s.substring(0).isEmpty()) continue;
                    str[n][m[n]] = Integer.parseInt(s.substring(0));
                    if (str[n][m[n]] % 2 != 0) continue;
                    m[n]++;
                    if (m[n] == leny) {
                        leny *= 2;
                        int[] b = create(leny);
                        System.arraycopy(str[n], 0, b, 0, m[n]);
                        str[n] = b;
                    }
                    s = new StringBuilder();
                } else {
                    s.append(line.charAt(y));
                }
                y++;
            }
            if (!s.substring(0).isEmpty()){
                str[n][m[n]] = Integer.parseInt(s.substring(0));
                if (str[n][m[n]] % 2 != 0) continue;
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
        }
    }
}
