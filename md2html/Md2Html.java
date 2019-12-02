package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Md2Html {

    private static final Map<String, String> tags = new HashMap<>();
    private static final Map<Character, String> charSwap = new HashMap<>();

    static {
        tags.put("*", "em");
        tags.put("**", "strong");
        tags.put("_", "em");
        tags.put("__", "strong");
        tags.put("--", "s");
        tags.put("`", "code");
        tags.put("++", "u");

        charSwap.put('<', "&lt;");
        charSwap.put('>', "&gt;");
        charSwap.put('&', "&amp;");
    }

    private static MarkupElement parseParagraph(String raw) { //Определяем, является ли заголовком и циферку после h
        int i = 0;
        while (i < raw.length() && raw.charAt(i) == '#') {
            i++;
        }
        i = i < raw.length() && raw.charAt(i) == ' ' && i > 0 ? i + 1 : 0; //После '#' должен быть пробел, если нет, то это параграф

        MarkupElement paragraph = new MarkupElement(i > 0 ? "h" + (i - 1) : "p"); //Если заголовок, ставим h с циферью, иначе это параграф
        List<AbstractElement> subElements = new ArrayList<>(); //Лист для подэлементов параграфа

        parseMarkup(raw, i, "", subElements); //Здесь у нас заполнится лист
        paragraph.addSubElements(subElements); //А тут мы затолкаем его в параграф-объект

        return paragraph;
    }

    private static boolean checkTag(String raw, int at, String tag) { //Проверяем, есть ли тэг на индексе at
        return !tag.isEmpty() && ( //Если пустой тэг, то нечего и проверять
                raw.charAt(at) == tag.charAt(0) && ( //Не пустой => есть первый символ, сравним его
                        tag.length() == 1 || at + 1 < raw.length() && raw.charAt(at + 1) == tag.charAt(1) //Если при этом размер тэга один (иначе 2), то всё хорошо
                )   // иначе следующий символ должен совпасть со вторым символом тэга
        );
    }

    private static int parseMarkup(String raw, int from, String tag, List<AbstractElement> subElements) { //Функция рекурсивно парсит разметочные элементы внутри
                            //разметочного элемента, чей тэг знает, который начинается со from, не включая тэг, забивает лист его подэлементов, вернёт индекс, следующий за окончанием его закрывающего тэга

        StringBuilder text = new StringBuilder(); //Сюда будем собирать все текстовые элементы
        int i = from;

        boolean hasJumped; //Если мы наткнёмся на разметочный элемент, то функция "перескочит" его из-за рекурсивного вызова
        while (i < raw.length()) {
            hasJumped = false;
            for (String mdTag : tags.keySet()) { //Цикл по всем имеющимся markdown-тэгам
                if (i > from && raw.charAt(i) == '\\') { //В случае символа экранирования просто добавим следующий
                    i += 1;
                    break;
                }

                if (checkTag(raw, i, mdTag)) { //Если на индексе есть тэг
                    if (!mdTag.equals(tag) && i + mdTag.length() < raw.length() //Он не является закрывающим
                        && !Character.isWhitespace(raw.charAt(i + mdTag.length()))) { //После него не стоит пробел
                        hasJumped = true;
                        List<AbstractElement> deeperSubElements = new ArrayList<>();
                        subElements.add(new Text(text.toString()));
                        text = new StringBuilder();

                        i = parseMarkup(raw, i + mdTag.length(), mdTag, deeperSubElements);
                        if (checkTag(raw, i - mdTag.length(), mdTag)) { //Если в конце элемента, который мы распарсили стоит тэг, то он был закрывающим, добавляем его как новый элемент
                            MarkupElement el = new MarkupElement(tags.get(mdTag));
                            el.addSubElements(deeperSubElements);
                            subElements.add(el);
                        } else { //Иначе добавляем его подэлементы в текущий уровень
                            subElements.addAll(deeperSubElements);
                        }
                        break;
                    } else if (mdTag.equals(tag) && i - mdTag.length() >= from //Если он закрывающий и в нём есть хотя бы один символ
                            && !Character.isWhitespace(raw.charAt(i - 1))) { //И перед ним не стоит пробел
                        subElements.add(new Text(text.toString())); //Возвращаем следующий за элементом индекс
                        return i + mdTag.length();
                    }
                }
            }

            if (!hasJumped) {
                text.append(charSwap.getOrDefault(raw.charAt(i), Character.toString(raw.charAt(i)))); //Если это спецсимвол, то мы добавим строку из charSwap, иначе текущий символ
                i++;
            }
        }
        subElements.add(new Text(text.toString()));

        return i;
    }

    private static List<String> split(String raw) { //Почти string.split["\n\n"]
        List<String> splitted = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < raw.length(); i++) {
            if (raw.charAt(i) == '\n' && i + 1 < raw.length() && raw.charAt(i + 1) == '\n') {
                if (!sb.toString().isEmpty()) {
                    splitted.add(sb.toString());
                }
                sb = new StringBuilder();
                i++;

            } else {
                sb.append(raw.charAt(i));
            }
        }
        if (!sb.toString().isEmpty()) {
            splitted.add(sb.toString());
        }
        return splitted;
    }

    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8), 4096);
             BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8), 4096)) {
            final StringBuilder raw = new StringBuilder();
            final StringBuilder out = new StringBuilder();

            br.lines().forEach(line -> raw.append(line).append('\n'));
            split(raw.toString()).stream().map(par -> {
                int l = 0, r = par.length() - 1;
                while (l < r && par.charAt(l) == '\n') {
                    l++;
                }
                while (r > l - 1 && par.charAt(r) == '\n') {
                    r--;
                }
                return parseParagraph(par.substring(l, r + 1));
            }).forEach(par -> {
                par.toHtml(out);
                out.append('\n');
            });
            bw.write(out.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
