import java.io.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//import java.util.Scanner;

public class ReverseTranspose {

    public static int[] stringToIntArray(String string) {
        if (string.isEmpty()) {
            return new int[0];
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(-1);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                queue.add(i);
            }
        }
        int[] array = new int[queue.size()];
        queue.add(string.length());
        int i = 0;
        while (queue.size() > 1) {
            array[i++] = Integer.parseInt(string.substring(queue.remove() + 1, queue.peek()));
        }
        return array;
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> matrix = new ArrayList<>();
        String line;
        int[] nums;
        int col = 0;
        Scanner scanner = new Scanner(System.in);
        Scanner stringer;
        List<Integer> borders;

        while (scanner.hasNextLine()) {
            nums = stringToIntArray(scanner.nextLine());
            for (int i = 0; i < nums.length; i++) {
                if (matrix.size() <= i) {
                    matrix.add(new ArrayList<>());
                }
                matrix.get(i).add(nums[i]);
            }
        }

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out), 4096)) {
            for (List<Integer> list : matrix) {
                for (int i : list) {
                    out.write(i + " ");
                }
                out.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
