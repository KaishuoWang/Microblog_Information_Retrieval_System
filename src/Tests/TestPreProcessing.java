package Tests;

import Step1.Preprocessing;

import java.util.HashSet;
import java.util.Set;


public class TestPreProcessing {
    public static void main(String[] args) {
        Preprocessing preprocessing = new Preprocessing();
        Set<String> tokens = preprocessing.getTokens();

        Set<String> test = new HashSet<>();

        System.out.println(tokens.size());
    }
}
