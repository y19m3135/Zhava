package markup;

import java.util.List;

public class Paragraph extends AbstractUnit {
    public Paragraph(List<Unit> included) {
        super(included, "", "");
    }
    
    @Override
    public void toHtml(StringBuilder out) {
        for (Unit unit : this.included) {
            unit.toHtml(out);
        }
    }
}
