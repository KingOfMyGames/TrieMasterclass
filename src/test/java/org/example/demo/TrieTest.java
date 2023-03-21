package org.example.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void testInsert() {
        Trie trie = buildTestTrie();
        Trie.TrieNode root = trie.getRootTestOnly();
        assertFalse(root.isEndOfWord());
        assertEquals("Expected 3 children: a, c, d", 3, root.getChildren().size());
        assertEquals("Expected 2 children: o, r", 2, root.getChildren().get('d').getChildren().size());
        Trie.TrieNode doNode = root.getChildren().get('d').getChildren().get('o');
        assertTrue(doNode.isEndOfWord());
        assertEquals("Expected 3 children: g, o, z", 3, doNode.getChildren().size());
        Trie.TrieNode dogNode = doNode.getChildren().get('g');
        assertTrue(dogNode.isEndOfWord());
        assertEquals("Expect 1 child: m", 1, dogNode.getChildren().size());
    }

    @Test
    public void testFindNodeOfWord() {
        Trie trie = buildTestTrie();
        assertNull("Expect to find null", trie.findNodeOfWordTestOnly("abracadabra"));
        Trie.TrieNode root = trie.getRootTestOnly();
        Trie.TrieNode dogNode = root.getChildren().get('d').getChildren().get('o').getChildren().get('g');
        assertEquals("Node found was not equal to expected node", trie.findNodeOfWordTestOnly("dog"), dogNode);
        assertNotEquals("Node dogm should be different from node dog", trie.findNodeOfWordTestOnly("dogm"), dogNode);
    }

    @Test
    public void addAllWordsInNodeToList() {
        Trie trie = buildTestTrie();
        Trie.TrieNode root = trie.getRootTestOnly();
        List<String> result = new ArrayList<>();
        trie.addAllWordsInNodeToListTestOnly(root, "", result, 9999);
        assertThat("root node should have all words", result,
                containsInAnyOrder("ace", "cow", "do", "dog", "dogma", "door", "dozen", "drug"));

        Trie.TrieNode dogNode = root.getChildren().get('d').getChildren().get('o').getChildren().get('g');
        List<String> result2 = new ArrayList<>();
        trie.addAllWordsInNodeToListTestOnly(dogNode, "dog", result2, 9999);
        assertThat("dog node should have both dog and dogma", result2,
                containsInAnyOrder("dog", "dogma"));

        List<String> result3 = new ArrayList<>();
        trie.addAllWordsInNodeToListTestOnly(root, "", result3, 3);
        assertEquals("maxWords should limit max words returned", 3, result3.size());
    }

    private Trie buildTestTrie() {
        Trie trie = new Trie();
        trie.insert("ace");
        trie.insert("cow");
        trie.insert("do");
        trie.insert("dog");
        trie.insert("dogma");
        trie.insert("door");
        trie.insert("dozen");
        trie.insert("drug");
        return trie;
    }
}
