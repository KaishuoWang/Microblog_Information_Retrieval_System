package Step1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Preprocessing {
    final private String read_file_path = "source_files/Trec_microblog11.txt"; // path to raw messages
    private StopWords stopWords;
    private Stemmer stemmer;
    private Hashtable<String, String> processedMessages;
    private Set<String> tokens;

    public Preprocessing() {
        stopWords = new StopWords();
        stemmer = new Stemmer();
        processedMessages = new Hashtable<>();
        tokens = new HashSet<>();

        read_and_process();
    }

    /***
     * Read raw messages from path and remove URLs, punctuations, numbers, non alphabets, and stop words.
     */
    private void read_and_process() {
        String words;
        try {
            BufferedReader in = new BufferedReader(new FileReader(read_file_path));
            String str;

            while ((str = in.readLine()) != null) {
                String processedStr = "";
                words = str.split("\t")[1];

                //remove URL
                words = words.replaceAll("http://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", " ").replaceAll("www.[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", " ");
                // Filtering out punctuation tokens
                words = words.replaceAll("$", "").replaceAll("|", "").replaceAll("<<", "").replaceAll("\\+",
                        "").replaceAll("<", "").replaceAll("#", "").replaceAll("@", "");
                //remove all numbers, punctuations, and any non alphabets
                words = words.toLowerCase().replaceAll("\\d+", "").replaceAll("\\pP", "").replaceAll("[^A-Za-z ]", "").trim();
                List<String> wordsList = Arrays.asList(words.split(" "));

                for (String each : wordsList) {
                    if (!stopWords.isStopWords(each) && !each.equals("") && !each.equals(" ")) {
                        stemmer.add(each.toCharArray(), each.length());
                        stemmer.stem();
                        String result = stemmer.toString();
                        processedStr += result;
                        processedStr += " ";
                        tokens.add(result);
                    }
                }
                if (processedStr.length() != 0) {
                    processedMessages.put(str.split("\t")[0], processedStr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Return processed messages
     * @return
     */
    public Hashtable<String, String> getProcessedMessages() {
        return processedMessages;
    }

    /***
     * Return tokens
     * @return
     */
    public Set<String> getTokens() {
        return tokens;
    }
}
