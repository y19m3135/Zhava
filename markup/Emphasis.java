package markup;

import java.util.List;

public class Emphasis extends AbstractUnit {
    public Emphasis(List<Unit> included) {
        super(included, "*", "em");
    }
}
