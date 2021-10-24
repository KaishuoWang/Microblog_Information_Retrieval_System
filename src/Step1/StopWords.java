package Step1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StopWords {
    final private String file_path = "source_files/StopWords.txt"; // path to stopWords.txt
    private ArrayList<String> stopWords;

    public StopWords() {
        stopWords = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file_path));
            String str;

            while ((str = in.readLine()) != null) {
                stopWords.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Check if the input word is one of stop words.
     * @param input
     * @return
     */
    public boolean isStopWords(String input) {
        return stopWords.contains(input);
    }
}
