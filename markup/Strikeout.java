package markup;

import java.util.List;

public class Strikeout extends MarkUp implements InParagraph {
    public Strikeout(List<InParagraph> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "~");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "s");
    }
}
