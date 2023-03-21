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
        TrieNode current = root;
        for (char letter : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(letter, x -> new TrieNode());
        }
        current.setEndOfWord(true);
    }

    public List<String> findAllWordsStartingWith(String prefix, int maxWords) {
        List<String> result = new ArrayList<>();
        TrieNode current = findNodeOfWord(prefix);
        if (current != null) {
            addAllWordsInNodeToList(current, prefix, result, maxWords);
        }
        return result;
    }

    private TrieNode findNodeOfWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            TrieNode node = current.getChildren().get(c);
            if (node == null) {
                return null;
            }
            current = node;
        }
        return current;
    }

    private void addAllWordsInNodeToList(TrieNode node, String currentNodeWord, List<String> result, int maxWords) {
        if (node.isEndOfWord()) {
            result.add(currentNodeWord);
            if (result.size() == maxWords) {
                return;
            }
        }
        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            char c = entry.getKey();
            TrieNode child = entry.getValue();
            addAllWordsInNodeToList(child, currentNodeWord + c, result, maxWords);
            if (result.size() == maxWords) {
                return;
            }
        }
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
