package reverse.src;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class WordStatWords {
    public static void main(String[] args) throws IOException{
        Map<String, Integer> m = new TreeMap<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8)
        );
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
            );
            try {
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        for (String s : m.keySet()) {
                            writer.write(s + " " + m.get(s) + '\n');
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
                                m.put(ar, 1);
                            } else {
                                m.put(ar, m.get(ar) + 1);
                            }
                            s = new StringBuilder();
                        }
                        y++;
                    }
                    if (!s.substring(0).isEmpty()){
                        String ar = s.substring(0);
                        ar = ar.toLowerCase();
                        if (!m.containsKey(ar)) {
                            m.put(ar, 1);
                        } else {
                            m.put(ar, m.get(ar) + 1);
                        }
                    }
                }
            } finally {
                writer.close();
            }
        } finally {
            reader.close();
        }
    }
}