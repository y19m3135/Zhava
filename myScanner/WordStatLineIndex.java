import java.io.*;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

public class WordStatLineIndex {
    public static void main (String[] args) {
        SortedMap<String,ArrayList<Integer>> words = new TreeMap<>();  //now stores {word, int{count, line, index, line, index...}}
//         SortedMap<String,ArrayList<Integer>> words = new TreeMap<>();
        String word = "";
        int indexInLine = 1;
        int lineIndex = 1;
        int[] tmp;
        try {
            myScanner inputFile = new myScanner(new File(args[0]));
            PrintStream outputFile = new PrintStream(new File(args[1]));

            while (inputFile.hasNextWord()) {
                if (!inputFile.hasNextWordInLine()) {
//                    inputFile.nextLine();
                    lineIndex++;
                    indexInLine = 1;
                } else {
                    word = inputFile.nextWord().toLowerCase();
                    if (words.containsKey(word)) {
                        words.get(word).add(lineIndex);
                        words.get(word).add(indexInLine);
                    } else {
                        words.put(word, new ArrayList<Integer>(List.of(lineIndex, indexInLine)));
                    }
                    indexInLine++;
                }
            }

            try {
/*                 for  (char schar = 0; schar != (char) -1; schar = inputFile.nextChar()) {
//                     if (Character.isLetter(schar)       //don't want to use regex and regex groups for that. am i right?
//                             || Character.getType(schar) == Character.DASH_PUNCTUATION
//                             || schar == '\''
//                             || schar == '\u055a'     //armenian apostrophe
//                             || schar == '\uff07') {  //fullwidth. every apostrophe i could find. there's also no unicode catergory for apostrophes only.
//                         word += String.valueOf(Character.toLowerCase(schar));
//                     } else if (schar == '\n') {
//                         indexInLine = 0;
//                         lineIndex++;
//                     } else {
//                         if (!word.isEmpty()) {
//                             if (words.containsKey(word)) {
//                                 if (words.get(word)[0] * 2 + 1 == words.get(word).length) {
//                                     tmp = new int[words.get(word)[0] * 4 + 1];       //i'm still going to copy when out of space, but not every time. Aaaand i'm not sure there will be that many repeating words for this fix to make sense
//                                     System.arraycopy(words.get(word), 0, tmp, 0, words.get(word).length);
//                                     tmp[words.get(word)[0] * 2 + 1] = lineIndex;
//                                     tmp[words.get(word)[0] * 2 + 2] = indexInLine;
//                                     tmp[0] += 1;
//                                     words.replace(word, tmp);
//                                 } else {
//                                     words.get(word)[words.get(word)[0] * 2 + 1] = lineIndex;
//                                     words.get(word)[words.get(word)[0] * 2 + 2] = indexInLine;
//                                     words.get(word)[0] += 1;
//                                 }
//                             } else {
//                                 words.put(word, new int[]{1, lineIndex, indexInLine});
//                             }
//                             word = "";
//                             indexInLine++;
//                         }
//                         if (schar == '\n') {
//                             indexInLine = 1;
//                             lineIndex++;
//                         }
//                     }
//                 }
*/
                for (Map.Entry<String, ArrayList<Integer>> entry : words.entrySet()) {
                    StringBuilder ou = new StringBuilder();
                    ou.append(entry.getKey())
                        .append(" ")
                        .append(entry.getValue().size()/2);
                    for (int i = 0; i < entry.getValue().size(); i += 2) {
                        ou.append(" ").append(entry.getValue().get(i))
                            .append(":").append(entry.getValue().get(i+1));
                    }
                    outputFile.println(ou.toString());
                }
            } finally {
                //inputFile.close();
                outputFile.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
