package solutions;

/**
 * Created by PPlovboiledfish on 1/12/16.
 */
public class AddAndSearchWord {
    class Trie {
        boolean isWord = false;
        Trie[] children = new Trie[26];
    }

    private Trie _trie = new Trie();

    // Adds a word into the data structure.
    public void addWord(String word) {
        Trie ptr = _trie;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (ptr.children[idx] == null)
                ptr.children[idx] = new Trie();
            ptr = ptr.children[idx];
        }
        ptr.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return _search(_trie, word);
    }

    private boolean _search(Trie trie, String word) {
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (c == '.') {
                String substr = (i == word.length() - 1) ? "" : word.substring(i + 1);
                for (Trie child : trie.children) {
                    if (child != null && _search(child, substr))
                        return true;
                }
                return false;
            } else {
                int idx = c - 'a';
                if (trie.children[idx] == null) return false;
                trie = trie.children[idx];
            }
        }
        return trie.isWord;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");