package Tests;

import Step2.Indexing;

import java.util.Hashtable;
import java.util.Map;

public class TestIndexing {
    public static void main(String[] args) {
        Indexing indexing = new Indexing();

        Hashtable<String, Double> idf_table = indexing.get_idf_table();

        int counter = 0;
        for (Map.Entry<String, Double> each : idf_table.entrySet()) {
            counter += 1;
            if (counter <= 200) {
                System.out.println(each.getKey() + "  ");
            } else {
                break;
            }
        }
    }
}
