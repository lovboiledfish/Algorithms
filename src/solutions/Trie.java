package solutions;

/**
 * Created by PPlovboiledfish on 11/1/15.
 */
class TrieNode {
    // Initialize your data structure here.
    protected TrieNode[] successors;
    boolean end;

    public TrieNode() {
        successors = new TrieNode[26];
        end = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode route = root;
        for (int i = 0; i < word.length(); ++i) {
            if (route.successors[word.charAt(i) - 'a'] == null)
                route.successors[word.charAt(i) - 'a'] = new TrieNode();
            route = route.successors[word.charAt(i) - 'a'];
        }
        route.end = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode n = _trace(word);
        return (n != null) && n.end;
    }

    private TrieNode _trace(String word) {
        TrieNode ptr = root;
        for (int i = 0; i < word.length(); ++i) {
            if (ptr.successors[word.charAt(i) - 'a'] == null)
                return null;
            ptr = ptr.successors[word.charAt(i) - 'a'];
        }
        return ptr;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode start = _trace(prefix);
        return _DFS(start);
    }

    private boolean _DFS(TrieNode s) {
        if (s == null)
            return false;
        if (s.end)
            return true;
        for (TrieNode successor : s.successors) {
            if (_DFS(successor))
                return true;
        }
        return false;
    }

    static public class Test {
        static public void randomTest() {
            Trie trie = new Trie();
            trie.insert("abcdef");
            trie.insert("abcef");
            System.out.println(trie.search("abc"));
            System.out.println(trie.startsWith("abc"));
            System.out.println(trie.search("abce"));
            System.out.println(trie.search("abcef"));
        }
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
