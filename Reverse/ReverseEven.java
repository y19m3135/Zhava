import java.util.Scanner; import java.util.ArrayList;
public class ReverseEven {
    public static void main (String[] args) {
        ArrayList<ArrayList<Integer>> nums = new ArrayList<ArrayList<Integer>>(); 
        String inp;
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNextLine()) {          
            inp = sc.nextLine();             //wanna use "for (String inp=sc.nextLine(); sc.hasNextLine(); inp = sc.nextLine())" but have no idea how to get that work right
            nums.add(0, new ArrayList());    
            for (String singleNum : inp.strip().split("\\s+")) {    //i'd rather use python-like map(function,array), but it looks too complicated in java
                if (!singleNum.isBlank() && Integer.parseInt(singleNum) % 2 == 0) { //should i make a new variable for this int?
                    nums.get(0).add(0, Integer.parseInt(singleNum));   
                }
            }
        }
        sc.close();
        
        for (int i = 0; i < nums.size(); i++) {             //for (ArrayList line : nums) {
            for (int j : nums.get(i)) {                     // for (int j : line) { ... }}
                System.out.print(Integer.toString(j)+" ");  //looks better. wonder how to make that work. Or, maybe, something like ".deepToString" with an another separator
            }
            System.out.println();
        }
    }
}
