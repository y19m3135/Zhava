package md2html;

import java.util.ArrayList;
import java.util.List;

public class MarkupElement implements AbstractElement {
    private final String tag;
    private List<AbstractElement> subElements = new ArrayList<>();

    public MarkupElement(String tag) {
        this.tag = tag;
    }

    public void addSubElements(List<AbstractElement> subElements) {
        this.subElements.addAll(subElements);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append('<').append(tag).append('>');
        for (AbstractElement el : subElements) {
            el.toHtml(sb);
        }
        sb.append("</").append(tag).append('>');
    }
}
