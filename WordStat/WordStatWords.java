import java.io.*; 
import java.util.ArrayList; 
import java.util.Collections;

public class WordStatWords {
    public static void main (String[] args) {
        List<Integer> count = new ArrayList<Integer>(); 
        List<String> words  = new ArrayList<String>(); 
        String word = ""; 

        try {
            BufferedReader inputFile = new BufferedReader(   //TODO shoulda change this
                new InputStreamReader(                       //LOOOOOOOOOOOOOOOOOOOOOOOOOOOONG
                    new FileInputStream(                     //filereader.
                        new File(args[0])                    //there has to be somehting better for my character-at-once reading
                    ), "utf-8"
                )
            );
            PrintStream outputFile = new PrintStream(new File(args[1]));
            
            try {
                for  (char schar = 0; schar != (char) -1; schar = (char) inputFile.read()) {   
                    if (Character.isLetter(schar)       //don't want to use regex and regex groups for that. am i right?
                            || Character.getType(schar) == Character.DASH_PUNCTUATION
                            || schar == '\''
                            || schar == '\u055a'     //armenian apostrophe
                            || schar == '\uff07') {  //fullwidth. every apostrophe i could find. there's also no unicode catergory for apostrophes only.
                        word += String.valueOf(Character.toLowerCase(schar));
                    } else {
                        if (!word.isEmpty()) {
                            if (words.contains(word)) {
                                    count.set(                                                                                                                                                                                          //MEMORI USAGE OPTEMEZASHIN'
                                    words.indexOf(word),        
                                    count.get(words.indexOf(word)) + 1
                                );
                            } else {
                                words.add(word);
                                Collections.sort(words);                //TODO an 'easy-to-write' solution. Ma-aybe it's not optimized enough.
                                count.add(words.indexOf(word), 1);      //however, that doesn't affect time usage much. 
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
                inputFile.close();   //does the test clean all the 'test\d\d?\.(in|out)' files? well, hope i don't need to do that
                outputFile.close();  //well, i think, this'd be bad only if there'd already be read-only files named like that. A-a-and that's not me who's going to make these.
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}
