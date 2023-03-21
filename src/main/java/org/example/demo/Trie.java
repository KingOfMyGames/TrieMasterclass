package org.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void insert(String word) {
        // TODO implement
        // Hint: word.toCharArray() allows you to easily loop over all letters
    }

    public List<String> findAllWordsStartingWith(String prefix, int maxWords) {
        List<String> result = new ArrayList<>();
        TrieNode nodeRepresentingPrefix = findNodeOfWord(prefix);
        if (nodeRepresentingPrefix != null) {
            addAllWordsInNodeToList(nodeRepresentingPrefix, prefix, result, maxWords);
        }
        return result;
    }

    private TrieNode findNodeOfWord(String word) {
        // TODO implement
        return null;
    }

    // Hint: This method should be a recursive method. Make sure to stop when you have found maxWords
    private void addAllWordsInNodeToList(TrieNode node, String currentNodeWord, List<String> result, int maxWords) {
        // TODO implement
    }

    static class TrieNode {
        private final Map<Character, TrieNode> children;
        private boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }

        public void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }
    }

    /**
     * below methods should only be used for the tests.
     * Normally those would get a nice @TestOnly annotation
     **/
    public TrieNode getRootTestOnly() {
        return root;
    }

    public TrieNode findNodeOfWordTestOnly(String word) {
        return findNodeOfWord(word);
    }

    public void addAllWordsInNodeToListTestOnly(TrieNode node, String currentNodeWord, List<String> result, int maxWords) {
        addAllWordsInNodeToList(node, currentNodeWord, result, maxWords);
    }
}
