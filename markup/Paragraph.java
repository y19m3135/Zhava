package markup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Paragraph implements AbstractTextElement {
    protected List<AbstractTextElement> elements;

    public Paragraph(List<AbstractTextElement> from) {
        elements = new ArrayList<>(from.size());
        elements.addAll(from);
    }

    //AbstractTextElement
    @Override
    public void toMarkdown(StringBuilder sb) {
        for (AbstractTextElement el : elements) {
            el.toMarkdown(sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb) {
        for (AbstractTextElement el : elements) {
            el.toHtml(sb);
        }
    }
}
