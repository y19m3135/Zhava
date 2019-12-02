package markup;

import java.util.List;

public class Strong extends Paragraph {

    public Strong(List<AbstractTextElement> from) {
        super(from);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("__");
        super.toMarkdown(sb);
        sb.append("__");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<strong>");
        super.toHtml(sb);
        sb.append("</strong>");
    }
}
