package markup;

import java.util.List;

public class Paragraph extends MarkUp implements MainText {
    public Paragraph(List<InParagraph> list) {
        super(list);
    }

    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    public void toHtml(StringBuilder result) {
        toHtml(result, "");
    }
}
