package markup;

public interface AbstractTextElement {
    void toMarkdown(StringBuilder sb);

    void toHtml(StringBuilder sb);
}

/*
        AbstractTextElement
        /               \
      Text            Paragraph
                     /    |     \
              Emphasis  Strong  Strikeout
 */