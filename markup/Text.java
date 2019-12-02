package markup;

import java.util.List;

public class Text implements Unit {
    private String inp;
    
    public Text(String inp) {
        this.inp = inp;
    }
    
    @Override
    public void toMarkdown(StringBuilder out) {
        out.append(this.inp);
    }
    @Override
    public void toHtml(StringBuilder out) {
        out.append(this.inp);
    }
}
