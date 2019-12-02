package markup;

import java.util.List;

public class Emphasis extends MarkUp implements InParagraph {
    public Emphasis(List<InParagraph> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "*");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "em");
    }
}
