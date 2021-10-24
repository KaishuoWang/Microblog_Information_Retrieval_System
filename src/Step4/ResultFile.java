package Step4;

import Step3.Retrieval;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultFile {
    final private String write_file_path = "Results.txt"; // path to Result.txt
    final private String test_query_path = "source_files/topics_MB1-49.txt"; // path to test queries
    private Retrieval retrieval;

    public  ResultFile() {
        retrieval = new Retrieval();
        get_test_queries();
    }

    /***
     * Read each test query from file and call step3 to compare them with each document.
     * @return
     */
    private ArrayList<String> get_test_queries() {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(test_query_path));
            String str;

            while ((str = in.readLine()) != null) {
                if (str.split(" ")[0].equals("<num>")) {
                    String topic_id = str.replaceAll("<num>", "").replaceAll("</num>", "").replaceAll("Number:", "").trim();
                    str = in.readLine();
                    String title = str.replaceAll("<title>", "").replaceAll("</title>", "").trim();

                    int counter = 0;
                    retrieval.run(title);
                    for (Map.Entry<String, Double> eachScore : retrieval.getScores().entrySet()) {
                        counter += 1;
                        if (counter <= 1000) {
                            DecimalFormat df = new DecimalFormat("0.000");
                            write_to_file(topic_id + " Q0 " + eachScore.getKey() + " " + counter + " " + String.valueOf(df.format(eachScore.getValue())) + " myRun\n");
                        } else {
                            break;
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     * Write top 1000 results to Result.txt
     * @param content
     */
    private void write_to_file(String content) {
        try {
            File file = new File(write_file_path);
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter out = new BufferedWriter(fr);
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
