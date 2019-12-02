package reverse.src.dfsf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Arrays.fill;

public class s {
    private final static int leng = 100;

    private static int[] create(int length) {
        int[] ints = new int[length];
        fill(ints, 0);
        return ints;
    }

    public static void main(String[] args) {
        /*Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int[][] str = new int[leng][leng];
        int n = 0, lenx = leng, leny, mx = 0;
        int[] m = create(leng);
        while (true) {
            String line = in.nextLine();
            if (line == null) {
                break;
            }
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
                leny = leng;
                if (str[n] == null) {
                    str[n] = create(leng);
                }
            }
            String[] arrs = (line).split("[^[-\\d]]");
            for (String s : arrs) {
                if (s.isEmpty()) continue;
                str[n][m[n]] = Integer.parseInt(s);
                m[n]++;
                mx = max(mx, m[n]);
                if (m[n] == leny) {
                    leny *= 2;
                    int[] b = create(leny);
                    System.arraycopy(str[n], 0, b, 0, m[n]);
                    str[n] = b;
                }
            }
            String[] arrs = (line).split(" ");
            int sum = 0;
            for (String s : arrs) {
                if (s.isEmpty()) continue;
                int l = last[sum];
                last[sum]++;
                if (m[l] == str[l].length) {
                    int[] b = create(str[l].length * 2);
                    System.arraycopy(str[l], 0, b, 0, str[l].length);
                    str[l] = b;
                }
                str[l][m[l]] = Integer.parseInt(s);
                m[l]++;
                mx = max(mx, m[l]);
                if(mx == last.length){
                    int[] b = create(last.length * 2);
                    System.arraycopy(last, 0, b, 0, last.length);
                    last = b;
                }
                sum++;
            }
         */
            /*int y = 0;
            StringBuilder s = new StringBuilder();
            while (line.length() != y) {
                if (Character.isWhitespace(line.charAt(y))) {
                    if (s.substring(0).isEmpty()) continue;
                    str[n][m[n]] = Integer.parseInt(s.substring(0));
                    m[n]++;
                    mx = max(mx, m[n]);
                    s = new StringBuilder();
                    if (m[n] == leny) {
                        leny *= 2;
                        int[] b = create(leny);
                        System.arraycopy(str[n], 0, b, 0, m[n]);
                        str[n] = b;
                    }
                } else {
                    s.append(line.charAt(y));
                }
                y++;
            }
            if (!s.substring(0).isEmpty()){
                str[n][m[n]] = Integer.parseInt(s.substring(0));
                m[n]++;
                mx = max(mx, m[n]);
            }
            n++;
        }
        for (int i = 0; i < mx; ++i) {
            for (int j = 0; j < n; ++j) {
                if(i >= m[j]) continue;
                out.print(str[j][i] + " ");
            }
            out.println();
        }
        out.flush();
                    //String[] arrs = (sc.nextLine()).split("[\\s\\h\\v]");
            for (String s : arrs) {
                if (s.isEmpty()) continue;
                str[n][m[n]] = Integer.parseInt(s);
                if (str[n][m[n]] % 2 != 0) continue;
                m[n]++;
                if (m[n] == leny) {
                    leny *= 2;
                    int[] b = create(leny);
                    System.arraycopy(str[n], 0, b, 0, m[n]);
                    str[n] = b;
                }
            }
        */
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
