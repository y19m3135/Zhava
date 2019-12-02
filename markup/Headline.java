package markup;

import java.util.List;

public class Headline extends MarkUp implements MainText {

    public Headline(List<InParagraph> list) {
        super(list);
    }

    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    public void toHtml(StringBuilder result) {
        toHtml(result, "");
    }
}
