package markup;

import java.util.List;

public class Emphasis extends Paragraph {

    public Emphasis(List<AbstractTextElement> from) {
        super(from);
    }

    @Override
    public void toMarkdown(StringBuilder sb){
        sb.append('*');
        super.toMarkdown(sb);
        sb.append('*');
    }

    @Override
    public void toHtml(StringBuilder sb){
        sb.append("<em>");
        super.toHtml(sb);
        sb.append("</em>");
    }
}
