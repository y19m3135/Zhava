package reverse.src.day4;

import javafx.util.Pair;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class WordStatCount {
    public static void main(String[] args) throws IOException {
        Map<Integer, Pair<Integer, String>> m = new TreeMap<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(args[0]), "utf8")
        );
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), "utf8")
            );
            try {
                while (true) {
                    /*String[] ars = line.split("[^['][\\p{Pd}][\\p{IsAlphabetic}]]");
                    for (String ar : ars) {
                        if (ar.isEmpty()) continue;
                        ar = ar.toLowerCase();
                        if (!m.containsKey(ar)) {
                            m.put(ar, 1);
                        } else {
                            m.put(ar, m.get(ar) + 1);
                        }
                    }*/
                }
            } finally {
                writer.close();
            }
        } finally {
            reader.close();
        }
    }
}