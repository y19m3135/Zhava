package markup;

import java.util.List;

public class MarkUp {
    List<InParagraph> list;
    MarkUp(List<InParagraph> list) {
        this.list = list;
    }

    protected void toMarkdown(StringBuilder text , String string) {
        if(!string.isEmpty()) text.append(string);
        for(InParagraph elem : list) {
            elem.toMarkdown(text);
        }
        if(!string.isEmpty()) text.append(string);
    }

    protected void toHtml(StringBuilder text , String string) {
        if(!string.isEmpty()) text.append("<" + string + ">");
        for(InParagraph elem : list) {
            elem.toHtml(text);
        }
        if(!string.isEmpty()) text.append("</" + string + ">");
    }
}
