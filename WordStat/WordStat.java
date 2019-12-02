import java.io.*; import java.util.ArrayList;

public class WordStat {
    public static void main (String[] args) {
        ArrayList<Integer> count = new ArrayList<Integer>(); 
        ArrayList<String> words  = new ArrayList<String>(); 
        String word = ""; Character schar;

        try {
            BufferedReader inputFile = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(
                        new File(args[0])
                    ), "utf-8"
                )
            );
            PrintStream outputFile = new PrintStream(new File(args[1]));
            
            try {
                for (int sint = 0; sint != -1; sint = inputFile.read()) {
                    schar = (char) sint;             //don't want to use regex and regex groups for that. am i right?
                    if (Character.isLetter(schar) 
                            || Character.getType(schar) == Character.DASH_PUNCTUATION
                            || schar == '\''
                            || schar == '\u055a'     //armenian apostrophe
                            || schar == '\uff07') {  //fullwidth. every apostrophe i could find. there's also no unicode catergory for apostrophes only.
                        word += String.valueOf(Character.toLowerCase(schar));
                    } else {
                        if (!word.isEmpty()) {
                            if (words.contains(word)) {
                                count.set(
                                    words.indexOf(word), 
                                    count.get(words.indexOf(word)) + 1
                                );
                            } else {
                                words.add(word);
                                count.add(1);
                            }
                            word = "";
                        } 
                    }
                }
                for (int i = 0; i < words.size(); i++) {
                    outputFile.println(words.get(i) 
                        + " " 
                        + count.get(i).toString());
                }
            } finally {
                inputFile.close();   //does the test clean all the 'test\d\d?\.(in|out)' files?
                outputFile.close();  //well, i think, it'd be bad only if there'd already be read-only files named like that. A-a-and that's not me who's going to make them.
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}
// private class IJustWantedToUsePythonlikeDictionaryAndIHaveNoIdeaWhatToSearchInDocumentationFor {
//     private String[] keys; private int[] count;
//     public static int get_
// } WellIRealizedIJustHadToUseTwoListsForThatAndNoReasonToTryToDealWithNewClass
