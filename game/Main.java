package game;
import java.util.function.Predicate;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {

        final Game game = new Game(false, new HumanPlayer(), new Drawer());
        final Predicate<String> IS_INTEGER = s -> (            //u know, we don't need no floats round here
                (
                        (s.startsWith("+") || s.startsWith("-"))
                                && s.substring(1).chars().allMatch(Character::isDigit)
                ) || s.chars().allMatch(Character::isDigit)
        );
        myScanner in = new myScanner(System.in);
        int n = 0, m = 0, k = 0;
        String sn, sm, sk;
        while (n < 1 && m < 1 && k < 1) {
            System.out.print("m,n,k: ");
            sm = in.nextToken();
            sn = in.nextToken();
            sk = in.nextToken();
            if (IS_INTEGER.test(sm) && IS_INTEGER.test(sn) && IS_INTEGER.test(sk)) {
                m = Integer.parseInt(sm);
                n = Integer.parseInt(sn);
                k = Integer.parseInt(sk);
            }
        }
        int result;
        do {
            result = game.play(new NMKBoard(m,n,k));
            System.out.println("Game result: " + result + "\n^D to exit\n");
        } while (result != 0);
    }
}
