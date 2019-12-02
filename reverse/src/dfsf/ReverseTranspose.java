package reverse.src.dfsf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReverseTranspose {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder num = new StringBuilder();
        String nums;
        List<String> numb = new ArrayList<>();
        while (true) {
            nums = in.readLine();
            if(nums == null) break;
            nums += " ";
            int k = 0;
            for (int i = 0; i < nums.length(); i++) {
                char ch = nums.charAt(i);
                if (Character.isWhitespace(ch)) {
                    if(num.length() == 0) continue;
                    if (k >= numb.size()) {
                        numb.add(num.toString());
                    } else {
                        numb.set(k, numb.get(k) + " " + num.toString());
                    }
                    k++;
                    num.setLength(0);
                } else {
                    num.append(ch);
                }
            }
        }
        for (String s : numb) {
            System.out.println(s);
        }
    }
}