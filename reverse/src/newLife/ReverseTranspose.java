package reverse.src.newLife;

import java.io.IOException;

import static java.lang.Math.max;
import static java.util.Arrays.fill;

class IsWhite implements IsToken{
    public boolean isToken(char c) {
        return !Character.isWhitespace(c);
    }
}

public class ReverseTranspose {
    private static IsWhite isWhite = new IsWhite();
    private final static int leng = 10;

    private static int[] create(int length) {
        int[] ints = new int[length];
        fill(ints, 0);
        return ints;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int[][] str = new int[leng][leng];
        int n = 0, lenx = leng, mx = 0;
        int[] m = create(leng);
        int[] last = create(leng);
        try{
            while(!in.isEmpty(isWhite)){
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
                int sum = 0;
                int sd = 0;
                while (!in.isNextLine(isWhite)) {
                    int l = last[sum];
                    last[sum]++;
                    if (m[l] == str[l].length) {
                        int[] b = create(str[l].length * 2);
                        System.arraycopy(str[l], 0, b, 0, str[l].length);
                        str[l] = b;
                    }
                    str[l][m[l]] = in.nextInt(isWhite);
                    sd = str[l][m[l]];
                    m[l]++;
                    mx = max(mx, m[l]);
                    if(mx == last.length){
                        int[] b = create(last.length * 2);
                        System.arraycopy(last, 0, b, 0, last.length);
                        last = b;
                    }
                    sum++;
                }
                if(sd == -1) break;
                if(m[n] != 0) n++;
                in.skipSeparator(isWhite);
                in.hasNext(isWhite);
            }
        } finally {
            in.close();
        }
        for (int i = 0; i < mx; ++i) {
            for (int j = 0; j < n; ++j) {
                if(i >= m[j]) break;
                System.out.print(str[j][i] + " ");
            }
            System.out.println();
        }
    }
}
/*
1 2
3
* */
