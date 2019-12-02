package reverse.src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    private static void readerAndWriter(final BufferedReader reader) throws IOException {
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                System.out.println();
                System.out.println("readLine() returned null that means EOF");
                return;
            }
            System.out.println("Line: " + line);
        }
    }
    public static void main(String[] args)  throws IOException{
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "utf8"));
        try {
            readerAndWriter(reader);
        } finally {
            System.out.println("Close BufferedReader");
            System.out.println("And all nested readers and streams");
            reader.close();
        }
    }
}
