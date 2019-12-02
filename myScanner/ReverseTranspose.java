import java.util.*;

public class ReverseTranspose {
    public static void main(String[] args) {
        myScanner sc = new myScanner(System.in);
        List<List<Integer>> data = new ArrayList<>();
        int i = 0;
        boolean prevHadNumber = false; boolean negative = false; int num=0; char unit=0;
        /*while (sc.hasNextInt()) {
            if (!sc.hasNextIntInLine()) {
                i = 0;
            } else {
                if (data.size() <= i) data.add(new ArrayList<Integer>());
                data.get(i).add(sc.nextInt());
                i++;
            }
        }*/
        while (unit != (char) -1) {
            unit = 0;
            while (unit != '\n' && unit != (char) -1) {
                unit = sc.nextChar();

                if ((unit == '\n' || unit == (char) -1) && !prevHadNumber) break;                   //oh no
                prevHadNumber = true;                                                               //don't like how it looks
                if (unit == '-') negative = true;
                else if (Character.isDigit(unit)) num = num * 10 + Character.getNumericValue(unit); //oh no
                else {
                    if (data.size() <= i) data.add(new ArrayList<Integer>());
                    data.get(i).add(negative ? -num : num);
                    num = 0;
                    negative = false;
                    i++;
                }
            }
            prevHadNumber = false;
            i = 0;
         }
        for (List<Integer> list : data) {
            for (Integer j : list) {
                System.out.print(String.valueOf(j) + " ");
            }
            System.out.println();
        }
    }
}
