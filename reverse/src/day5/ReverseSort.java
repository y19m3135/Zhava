package reverse.src.day5;

import java.io.IOException;

import static java.util.Arrays.fill;

public class ReverseSort {
    private final static int leng = 10;

    private static int[] create(int length) {
        int[] ints = new int[length];
        fill(ints, 0);
        return ints;
    }

    public static void main(String[] args) {
        //BufferedReader br new BufferedReader(new Reader());
        //int[][] str = new int[leng][leng];
        //int n = 0, lenx = leng;
        //int[] m = create(leng);
        //while (true) {
        //    String line = in.nextLine();
        //    if (line == null) {
        //        break;
        //    }
        //    if(line.isEmpty()) continue;
        //    if(line.equals("a")) break;
        //    {
        //        if (n == lenx) {
        //            {
        //                int[] b = create(lenx * 2);
        //                System.arraycopy(m, 0, b, 0, lenx);
        //                m = b;
        //            }
        //            int[][] a = new int[lenx * 2][];
        //            for (int i = 0; i < lenx; ++i) {
        //                int[] b = create(m[i]);
        //                System.arraycopy(str[i], 0, b, 0, m[i]);
        //                a[i] = b;
        //            }
        //            str = a;
        //            lenx *= 2;
        //        }
        //        if (str[n] == null) {
        //            str[n] = create(leng);
        //        }
        //    }
        //    int y = 0;
        //    StringBuilder s = new StringBuilder();
        //    while (line.length() != y) {
        //        if (Character.isWhitespace(line.charAt(y))) {
        //            if (s.substring(0).isEmpty()) {
        //                y++;
        //                continue;
        //            }
        //            if (m[n] == str[n].length) {
        //                int[] b = create(str[n].length * 2);
        //                System.arraycopy(str[n], 0, b, 0, str[n].length);
        //                str[n] = b;
        //            }
        //            str[n][m[n]] = Integer.parseInt(s.substring(0));
        //            m[n]++;
        //            s = new StringBuilder();
        //        } else {
        //            s.append(line.charAt(y));
        //        }
        //        y++;
        //    }
        //    if (!s.substring(0).isEmpty()){
        //        if (m[n] == str[n].length) {
        //            int[] b = create(str[n].length * 2);
        //            System.arraycopy(str[n], 0, b, 0, str[n].length);
        //            str[n] = b;
        //        }
        //        str[n][m[n]] = Integer.parseInt(s.substring(0));
        //        m[n]++;
        //    }
        //    if(m[n] != 0) n++;
        //}
        //{
        //    int[][] a = new int[n][];
        //    for (int i = 0; i < n; ++i) {
        //        int[] b = create(m[i]);
        //        System.arraycopy(str[i], 0, b, 0, m[i]);
        //        a[i] = b;
        //    }
        //    str = a;
        //}
        //Map<Integer, Integer> map = new TreeMap<>();
        //for (int i = 0; i < n; ++i) {
        //    Arrays.sort(str[i]);
        //    int sum = 0;
        //    for(int j = 0; j < str[i].length; ++j) {
        //        sum += str[i][j];
        //    }
        //    map.put(sum, i);
        //    //System.out.println(sum + " " + i);
        //}
        //int[] revers = new int[n]; int u = 0;
        //for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        //    int a = entry.getValue();
        //    revers[u] = a;
        //    u++;
        //    System.out.println(entry.getKey() + " " + entry.getValue());
        //}
        //for (int i = 0; i < n; ++i){
        //    for (int j = str[revers[i]].length - 1; j >= 0; --j) {
        //        System.out.print(str[revers[i]][j] + " ");
        //    }
        //    System.out.println();
        //}
    }
    static class Reader  extends java.io.Reader {

        Reader(){}

        @Override
        public int read(char[] chars, int i, int i1) throws IOException {
            StringBuilder s;
            return 0;
        }

        @Override
        public void close() throws IOException {

        }
    }
}
