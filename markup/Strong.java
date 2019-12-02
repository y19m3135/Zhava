package markup;

import java.util.List;

public class Strong extends MarkUp implements InParagraph {
    public Strong(List<InParagraph> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "__");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "strong");
    }
}
