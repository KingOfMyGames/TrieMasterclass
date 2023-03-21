package org.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchController {

    protected final List<String> words = new ArrayList<>();
    // protected final Trie trie = new Trie();

    public AbstractSearchController(String filename) {
        try (InputStream inputStream = FruitController.class.getResourceAsStream("/textFiles/" + filename)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word);
                // trie.insert(word);
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
        System.out.println("Avarage search takes " + totTime / concurrentUsers + "ns, or about " + totTime / concurrentUsers / 1000000 + "ms");

        return searchWord(input);
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

    // TODO once you implement the Trie class, use this instead of searchWord() in timedSearch()
    // private List<String> searchTrie(String input) {
    //     return trie.findAllWordsStartingWith(input, 10);
    // }
}
