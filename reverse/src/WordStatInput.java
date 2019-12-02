package reverse.src;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Arrays.sort;

public class WordStatInput {
    //"/Users/ilyabarishnikov/Desktop/proga/day4/Example/input.txt"
    public static void main(String[] args) throws IOException {
        Map<String, Integer> m1 = new LinkedHashMap<>();
        Map<String, Integer> m2 = new LinkedHashMap<>();
        try (Scanner in = new Scanner(new File(args[0]), StandardCharsets.UTF_8)) {
            try (PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]),
                            StandardCharsets.UTF_8
                    )
            )) {
                int n = 0;
                while (in.hasNextLine()) {
                    String [] arrs = in.nextLine().split("[^['][\\p{Pd}][\\p{IsAlphabetic}]]");
                    for (String ar : arrs) {
                        if (ar.isEmpty()) continue;
                        ar = ar.toLowerCase();
                        if (!m1.containsKey(ar)) {
                            m1.put(ar, n);
                            m2.put(ar, 1);
                            n++;
                        } else {
                            m2.put(ar, m2.get(ar) + 1);
                        }
                    }
                }
                String[] str = new String[n];
                m1.forEach((k, v) -> str[v] = k);
                sort(str);
                for (String s : str) {
                    writer.println(s + " " + m2.get(s));
                }
            }
        }
    }
}