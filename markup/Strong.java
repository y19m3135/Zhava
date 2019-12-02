package markup;

import java.util.List;

public class Strong extends AbstractUnit {
    public Strong(List<Unit> included) {
        super(included, "__", "strong");
    }
}
