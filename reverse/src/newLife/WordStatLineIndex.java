package reverse.src.newLife;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

class Tuple {
    int sum;
    String ans;
    public Tuple(int sum, String ans) {
        this.sum = sum;
        this.ans = ans;
    }
}

public class WordStatLineIndex {
    public static void main(String[] args) throws IOException {
        Map<String, Tuple> m = new TreeMap<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8)
        );
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
            );
            try {
                int indy = 1;
                while (true) {
                    int indx = 1;
                    String line = reader.readLine();
                    if (line == null) {
                        for (String s : m.keySet()) {
                            Tuple t = m.get(s);
                            writer.write(s + " " + t.sum + " ");
                            int f = 0;
                            writer.write(t.ans);
                            writer.write('\n');
                        }
                        return;
                    }
                    int y = 0;
                    StringBuilder s = new StringBuilder();
                    while (line.length() > y) {
                        if (Character.getType(line.charAt(y)) == Character.DASH_PUNCTUATION ||
                                Character.isAlphabetic(line.charAt(y)) || line.charAt(y) == '\'') {
                            s.append(line.charAt(y));
                        } else {
                            if (s.substring(0).isEmpty()) {
                                y++;
                                continue;
                            }
                            String ar = s.substring(0);
                            ar = ar.toLowerCase();
                            if (!m.containsKey(ar)) {
                                String ans = indy + ":" + indx;
                                m.put(ar, new Tuple(1, ans));
                            } else {
                                Tuple t = m.get(ar);
                                t.sum++;
                                t.ans = t.ans + " " + indy + ":" + indx;
                                m.put(ar, t);
                            }
                            indx++;
                            s = new StringBuilder();
                        }
                        y++;
                    }
                    if (!s.substring(0).isEmpty()){
                        String ar = s.substring(0);
                        ar = ar.toLowerCase();
                        if (!m.containsKey(ar)) {
                            String ans = indy + ":" + indx;
                            m.put(ar, new Tuple(1, ans));
                        } else {
                            Tuple t = m.get(ar);
                            t.sum++;
                            t.ans = t.ans + " " + indy + ":" + indx;
                            m.put(ar, t);
                        }
                    }
                    indy++;
                }
            } finally {
                writer.close();
            }
        } finally {
            reader.close();
        }
    }
}