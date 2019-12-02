package md2html;

public class Text implements AbstractElement {
    private String text;

    public Text(String text){
        this.text = text;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(text);
    }
}