package reverse.src.dfsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exampleee {
    public static void main(String[] args) throws IOException {
        List<String> list = List.of("a", "b", "c");// неизменяемый
        List<String> arraylist = new ArrayList<>(List.of("a", "b", "c"));
        arraylist.add("hello");
        System.out.print(arraylist);
    }
}
