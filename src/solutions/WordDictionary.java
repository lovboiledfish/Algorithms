package solutions;

/**
 * Created by feiyi.zhan on 2/10/16.
 */
public class WordDictionary {
	class TrieNode {
		boolean isWord;
		TrieNode[] children = new TrieNode[26];
	}

	private TrieNode _root = new TrieNode();

	// Adds a word into the data structure.
	public void addWord(String word) {
		TrieNode ptr = _root;
		for (char c : word.toCharArray()) {
			int childIdx = c - 'a';
			if (ptr.children[childIdx] == null) {
				ptr.children[childIdx] = new TrieNode();
			}
			ptr = ptr.children[childIdx];
		}
		ptr.isWord = true;
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return _search(_root, word, 0);
	}

	private boolean _search(TrieNode cur, String word, int ptr) {
		if (ptr == word.length()) {
			return cur.isWord;
		}
		int childIdx = word.charAt(ptr) - 'a';
		if (childIdx < 0) {
			for (TrieNode child : cur.children) {
				if (child != null && _search(child, word, ptr + 1)) {
					return true;
				}
			}
			return false;
		} else {
			if (cur.children[childIdx] == null) {
				return false;
			}
			return _search(cur.children[childIdx], word, ptr + 1);
		}
	}
}
