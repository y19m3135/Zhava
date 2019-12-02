package markup;

import java.util.List;

public abstract class AbstractUnit implements Unit {
    protected String mark;
    protected String HTMLmark;
    protected List<Unit> included;
    
    protected AbstractUnit(List<Unit> included, String mark, String HTMLmark) {
        this.included = included;
        this.mark = mark;
        this.HTMLmark = HTMLmark;
    }
    
    @Override
    public void toMarkdown(StringBuilder out) {
        out.append(this.mark);
        for (Unit unit : included) {
            unit.toMarkdown(out);
        }
        out.append(this.mark);
    }
    @Override
    public void toHtml(StringBuilder out) {
        out.append("<" + this.HTMLmark + ">");
        for (Unit unit : included) {
            unit.toHtml(out);
        }
        out.append("</" + this.HTMLmark + ">");
    }
}
