import java.util.Scanner; import java.util.ArrayList;
public class Reverse {
    public static void main (String[] args) {
        ArrayList<ArrayList<Integer>> nums = new ArrayList<ArrayList<Integer>>(); int i = 0; String inp;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            inp = sc.nextLine();
            nums.add(new ArrayList());
            for (String singleNum : inp.strip().split("\\s+")) {
                if (!singleNum.isBlank()) { 
                    nums.get(i).add(0, Integer.parseInt(singleNum)); 
                }
            }
            i++;
        }
        sc.close();
        for (i=nums.size()-1; i>=0; i--) {
            for (int j : nums.get(i)) {
                System.out.print(Integer.toString(j)+" ");
            }
            System.out.println();
        }
    }
}
