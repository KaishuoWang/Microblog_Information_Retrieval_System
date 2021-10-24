package Step2;

import Step1.Preprocessing;

import java.util.*;

public class Indexing {
    private Preprocessing preprocessing;
    private Hashtable<String, String> processedMessages; // docID, message
    private Hashtable<String, Hashtable<String, Double>> tf_table; // <token, <docID, tf>>
    private Hashtable<String, Double> idf_table; // <token, idf>
    private Double numDoc; // total number of documents

    public Indexing() {
        preprocessing = new Preprocessing();
        processedMessages = preprocessing.getProcessedMessages();
        numDoc = Double.valueOf(processedMessages.size());
        tf_table = new Hashtable<>();
        idf_table = new Hashtable<>();

        calculate_tf();
        calculate_idf();
    }

    /***
     * Calculate tf using 1 + log(tf)
     */
    private void calculate_tf() {
        for (Map.Entry<String, String> each : processedMessages.entrySet()) {
            List<String> messageWord = Arrays.asList(each.getValue().split(" "));
            for (String eachWord : messageWord) {
                if (messageWord.contains(eachWord)) {
                    double tf = 1 + Math.log10(Collections.frequency(messageWord, eachWord));
                    if (tf_table.containsKey(eachWord)) {
                        tf_table.get(eachWord).put(each.getKey(), tf);
                    } else {
                        Hashtable<String, Double> tmp = new Hashtable<>();
                        tmp.put(each.getKey(), tf);
                        tf_table.put(eachWord, tmp);
                    }
                }
            }
        }
    }

    /***
     * Calculate idf using log(N/df)
     */
    private void calculate_idf() {
        for (Map.Entry<String, Hashtable<String, Double>> each_token : tf_table.entrySet()) {
            idf_table.put(each_token.getKey(), Math.log10(numDoc / each_token.getValue().size()));
        }
    }

    /***
     * Return tf table
     * @return
     */
    public Hashtable<String, Hashtable<String, Double>> get_tf_table() {
        return tf_table;
    }

    /***
     * Return idf table
     * @return
     */
    public Hashtable<String, Double> get_idf_table() {
        return idf_table;
    }

    /***
     * Return processed messages.
     * @return
     */
    public Hashtable<String, String> getProcessedMessages() {
        return processedMessages;
    }
}
