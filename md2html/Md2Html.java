package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import markup.*;

public class Md2Html {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(
                new InputStreamReader(
                        new FileInputStream(new File(args[0])),
                        StandardCharsets.UTF_8
                )
        );
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
        );
        md2html solver = new md2html();
        solver.solve(in, out);
        out.close();
        in.close();
    }

    static class md2html {
        Map<String, String> keys = new HashMap<>();
        Stack<String> tags = new Stack<>();
        Stack<String> texts = new Stack<>();
        int pos = 0;
        int h = 0;
        char tip = '@';
        char tip2 = '@';
        MainText gg = new Headline(null);
        StringBuilder thisLine = new StringBuilder();
        public void solve(Scanner in, BufferedWriter out) throws IOException {
            keys.put("*", "em");
            keys.put("_", "em");
            keys.put("**", "strong");
            keys.put("__", "strong");
            keys.put("--", "s");
            keys.put("++", "u");
            keys.put("`", "code");
            while(!in.isEmpty()) {
                StringBuilder next = new StringBuilder(in.nextLine());
                if(next.length() < 1) {
                    continue;
                }
                if(next.charAt(0) == '\n') {
                    stack_isEmpty(out);
                    continue;
                }
                if(pos > 0) {
                    thisLine.append('\n');
                }
                int start_pos = 0;
                if(pos == 0 && next.charAt(0) == '#') {
                    for(int i = 0; i < next.length(); ++i) {
                        if(next.charAt(i) == '#') {
                            h++;
                            pos++;
                        } else break;
                    }
                    start_pos = 1;
                    if(next.charAt(pos) != ' ') {
                        pos = 0;
                        h = 0;
                        start_pos = 0;
                    }
                    if(start_pos != 0) {
                        start_pos = h + 1;
                        out.write("<h" + h + ">");
                    }
                }
                if(pos == 0) {
                    out.write("<p>");
                }
                for(int i = start_pos; i < next.length(); ++i) {
                    i = next_i(i, next);
                }
            }
            stack_isEmpty(out);
        }

        int next_i(int i, StringBuilder next) {
            if(next.charAt(i) == '\\') {
                i++;
                if(i < next.length()) {
                    thisLine.append(next.charAt(i));
                }
                return i;
            }
            if(next.charAt(i) == '*' || next.charAt(i) == '_' || next.charAt(i) == '`' || (next.charAt(i) == '-' && next.charAt(i + 1) == '-') || (next.charAt(i) == '+' && next.charAt(i + 1) == '+')) {
                if(tip == next.charAt(i)) {
                    StringBuilder tag = new StringBuilder();
                    tag.append(tip);
                    String key = keys.get(tag.toString());
                    if(tip2 != '@') {
                        if(i + 1 < next.length() && tip2 != next.charAt(i)) {
                            StringBuilder thisLine_new = new StringBuilder();
                            thisLine_new.append(tip);
                            thisLine_new.append('<' + key + '>');
                            thisLine_new.append(thisLine);
                            thisLine_new.append("</" + key + '>');
                            thisLine = thisLine_new;
                        } else {
                            tag.append(tip);
                            key = keys.get(tag.toString());
                            StringBuilder thisLine_new = new StringBuilder();
                            thisLine_new.append('<' + key + '>');
                            thisLine_new.append(thisLine);
                            thisLine_new.append("</" + key + '>');
                            thisLine = thisLine_new;
                            i++;
                        }
                    } else {
                        StringBuilder thisLine_new = new StringBuilder();
                        thisLine_new.append('<' + key + '>');
                        thisLine_new.append(thisLine);
                        thisLine_new.append("</" + key + '>');
                        thisLine = thisLine_new;
                    }
                    tip = '@';
                    tip2 = '@';
                    if(!tags.empty()) {
                        StringBuilder text = new StringBuilder(texts.peek());
                        String tag2 = tags.peek();
                        if(tag2.length() > 1) {
                            tip = tag2.charAt(0);
                            tip2 = tag2.charAt(1);
                        } else {
                            if(tag2.length() != 1){
                                tip = '@';
                            } else {
                                tip = tag2.charAt(0);
                            }
                        }
                        text.append(thisLine);
                        thisLine = text;
                        tags.pop();
                        texts.pop();
                    }
                } else {
                    if (thisLine.length() > 0) {
                        StringBuilder tag = new StringBuilder();
                        if(tip != '@')  tag.append(tip);
                        if(tip2 != '@') tag.append(tip);
                        tip = '@';
                        tip2 = '@';
                        tags.add(tag.toString());
                        texts.add(thisLine.toString());
                        thisLine = new StringBuilder();
                    }
                    if(tip != '@') {
                        StringBuilder tag = new StringBuilder();
                        if(tip != '@')  tag.append(tip);
                        if(tip2 != '@') tag.append(tip);
                        tip = '@';
                        tip2 = '@';
                        tags.add(tag.toString());
                        texts.add(thisLine.toString());
                        thisLine = new StringBuilder();
                    }
                    tip = next.charAt(i);
                    tip2 = '@';
                    if(i + 1 < next.length() && next.charAt(i + 1) == tip) {
                        tip2 = next.charAt(i);
                        i++;
                    }
                }
            } else {
                if(next.charAt(i) == '<') {
                    thisLine.append("&lt;");
                } else if(next.charAt(i) == '>') {
                    thisLine.append("&gt;");
                } else if(next.charAt(i) == '&') {
                    thisLine.append("&amp;");
                } else {
                    thisLine.append(next.charAt(i));
                    pos++;
                }
            }
            return i;
        }

        void stack_isEmpty(BufferedWriter out) throws IOException{
            if(!tags.empty()) {
                if(tip != '@') {
                    StringBuilder thisLine_new = new StringBuilder();
                    thisLine_new.append(tip);
                    thisLine_new.append(thisLine);
                    thisLine = thisLine_new;
                }
                if(tip2 != '@') {
                    StringBuilder thisLine_new = new StringBuilder();
                    thisLine_new.append(tip);
                    thisLine_new.append(thisLine);
                    thisLine = thisLine_new;
                }
                while(!tags.empty()) {
                    String tag = tags.peek();
                    StringBuilder thisLine_new = new StringBuilder();
                    if(tag.length() > 0) {
                        thisLine_new.append(tag);
                    }
                    thisLine_new.append(texts.peek());
                    if(tag.length() > 1) {
                        tip = tag.charAt(0);
                        tip2 = tag.charAt(1);
                    } else {
                        if(tag.length() != 1){
                            tip = '@';
                        } else {
                            tip = tag.charAt(0);
                        }
                    }
                    thisLine_new.append(thisLine);
                    thisLine = thisLine_new;
                    tags.pop();
                    texts.pop();
                }
            }
            if(thisLine.length() > 1) {
                if(h > 0) {
                    out.write(thisLine.toString());
                    out.write("</h" + h + ">");
                } else {
                    out.write(thisLine.toString());
                    out.write("</p>");
                }
                out.write('\n');
            }
            thisLine = new StringBuilder();
            pos = 0;
            h = 0;
        }
    }
}
