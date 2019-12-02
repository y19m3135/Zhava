package markup;

public interface Unit {
    void toMarkdown(StringBuilder out);
    void toHtml(StringBuilder out);
}
