package markup;

import java.util.List;

public class Strikeout extends AbstractUnit {
    public Strikeout(List<Unit> included) {
        super(included, "~", "s");
    }
}
