package Step3;

import Step2.Indexing;
import Step1.Stemmer;

import java.util.*;

public class Retrieval {
    private Indexing indexing;
    private Hashtable<String, Hashtable<String, Double>> tf_table; // token, docID, tf
    private Hashtable<String, Double> idf_table; // token, idf
    private Hashtable<String, String> processedMessages; // id, message
    private String query;
    private Hashtable<String, Double> doc_length; // docID, length
    private HashMap<String, Double> scores; // docID, cosSim score
    private Hashtable<String, Double> query_tf_idf; // token, tf-idf
    private Double query_length;

    public Retrieval() {
        indexing = new Indexing();
        tf_table = indexing.get_tf_table();
        idf_table = indexing.get_idf_table();
        processedMessages = indexing.getProcessedMessages();
        doc_length = new Hashtable<>();
        scores = new HashMap<>();
    }

    /***
     * Sompare query and each document.
     * @param query given query
     */
    public void run(String query) {
        this.query = query.toLowerCase();

        Stemmer stemmer = new Stemmer();
        stemmer.add(this.query.toCharArray(), this.query.length());
        stemmer.stem();
        this.query = stemmer.toString();
        calculate_doc_length();

        query_tf_idf = calculate_tf_idf_query();
        query_length = calculate_query_length();

        calculate_cosineSim();

        scores = sortScores(scores);
    }

    /***
     * Rank cosine similarity scores from highest to lowest.
     * @param hm cosine similarity scores
     * @return sorted HashMap
     */
    private HashMap<String, Double> sortScores(HashMap<String, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                if ((o1.getValue()).compareTo(o2.getValue()) <= -1){
                    return 1;
                } else if ((o1.getValue()).compareTo(o2.getValue()) >= 1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /***
     * Calculate tf-idf values for each term in the given query.
     * @return
     */
    private Hashtable<String, Double> calculate_tf_idf_query() {
        double max = 0.0;
        Hashtable<String, Double> query_tf_idf = new Hashtable<>();
        List<String> queryWord = Arrays.asList(query.split(" "));
        for (String eachWord : queryWord) {
            if (!query_tf_idf.containsKey(eachWord)) {
                double tf = Collections.frequency(queryWord, eachWord);
                if (tf > max) {
                    max = tf;
                }
                query_tf_idf.put(eachWord, tf);
            }
        }
        for (Map.Entry<String, Double> each : query_tf_idf.entrySet()) {
            double idf = 0.0;
            if (idf_table.containsKey(each.getKey())) {
                idf = idf_table.get(each.getKey());
            }
            query_tf_idf.replace(each.getKey(), each.getValue(), (each.getValue() / max) * idf);
        }
        return query_tf_idf;
    }

    /***
     * Calculate cosine similarity for query with each document.
     */
    private void calculate_cosineSim() {
        for (Map.Entry<String, String> eachDoc : processedMessages.entrySet()) {
            double score_tmp = 0.0;
            List<String> messageWord = Arrays.asList(eachDoc.getValue().split(" "));
            for (String eachWord : messageWord) {
                double tf = 0.0;
                if (tf_table.get(eachWord).containsKey(eachDoc.getKey())) {
                    tf = tf_table.get(eachWord).get(eachDoc.getKey());
                }
                double term_tf_idf = tf * idf_table.get(eachWord);
                double query_term_tf_idf = 0.0;
                if (query_tf_idf.containsKey(eachWord)) {
                    query_term_tf_idf = query_tf_idf.get(eachWord);
                }
                score_tmp += (term_tf_idf * query_term_tf_idf);
            }
            score_tmp /= (doc_length.get(eachDoc.getKey()) * query_length);
            scores.put(eachDoc.getKey(), score_tmp);
        }
    }

    /***
     * Calculate the length for the given query.
     * @return
     */
    private Double calculate_query_length() {
        double result = 0.0;
        for (Map.Entry<String, Double> each : query_tf_idf.entrySet()) {
            result += Math.pow(each.getValue(), 2.0);
        }
        result = Math.sqrt(result);
        return result;
    }

    /***
     * Calculate length for each document.
     */
    private void calculate_doc_length() {
        for (Map.Entry<String, String> eachDoc : processedMessages.entrySet()) {
            double length = 0.0;
            List<String> doc_words = Arrays.asList(eachDoc.getValue().split(" "));
            for (String eachWord : doc_words) {
                length += Math.pow(tf_table.get(eachWord).get(eachDoc.getKey()) * idf_table.get(eachWord), 2.0);
            }
            length = Math.sqrt(length);
            doc_length.put(eachDoc.getKey(), length);
        }
    }

    /***
     * Return ranked cosine similarity scores.
     * @return
     */
    public HashMap<String, Double> getScores() {
        return scores;
    }
}
