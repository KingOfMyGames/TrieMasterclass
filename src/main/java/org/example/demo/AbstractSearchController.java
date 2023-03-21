package org.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchController {

    protected final List<String> words = new ArrayList<>();
    protected final Trie trie = new Trie();

    public AbstractSearchController(String filename) {
        try (InputStream inputStream = FruitController.class.getResourceAsStream("/textFiles/" + filename)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            // Read the lines from the file into an ArrayList
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
                trie.insert(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    protected List<String> timedSearchWordList(String input) {
        int concurrentUsers = 1000;
        long totTime = 0;
        for (int i = 0; i < concurrentUsers; i++) {
            long start = System.nanoTime();
            searchWord(input);
            long end = System.nanoTime();
            totTime += (end - start);
        }
        System.out.println("Avarage list search takes " + totTime / concurrentUsers + "ns");

        long totTimeTrie = 0;
        for (int i = 0; i < concurrentUsers; i++) {
            long start = System.nanoTime();
            searchTrie(input);
            long end = System.nanoTime();
            totTimeTrie += (end - start);
        }
        System.out.println("Avarage trie search takes " + totTimeTrie / concurrentUsers + "ns");
        System.out.println("Diff " + (totTime - totTimeTrie) / concurrentUsers + "ns");

        return searchTrie(input);
    }

    private List<String> searchWord(String input) {
        List<String> filteredWords = new ArrayList<>();
        for (String word : words) {
            if(word.startsWith(input)) {
                filteredWords.add(word);
                if (filteredWords.size() == 10) {
                    return filteredWords;
                }
            }
        }
        return filteredWords;
    }

    private List<String> searchTrie(String input) {
        return trie.findAllWordsStartingWith(input, 10);
    }
}
