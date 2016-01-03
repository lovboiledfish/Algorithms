package solutions;

import java.util.*;

/**
 * Created by PPlovboiledfish on 1/2/16.
 */
public class WordSearchII {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        char childrenCnt;
        boolean isWord;
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0) return new ArrayList<>();

        TrieNode trie = _buildTrie(words);
        Set<String> found = new HashSet<>();
        char[][] color = new char[board.length][board[0].length];
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j)
                _find(i, j, moves, color, board, trie, "", found);
        }
        return new ArrayList<>(found);
    }

    private void _find(int startRow, int startCol, int[][] moves, char[][] color, char[][] board,
                       TrieNode node, String word, Set<String> found) {
        if (node.childrenCnt == 0) return;
        color[startRow][startCol] = 1;
        for (int i = 0; i < 26; ++i)
            if (node.children[i] != null && board[startRow][startCol] == 'a' + i) {
                word += board[startRow][startCol];
                node = node.children[i];
                if (node.isWord) found.add(word);
                for (int[] move : moves) {
                    int nextRow = startRow + move[0], nextCol = startCol + move[1];
                    if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length
                            && color[nextRow][nextCol] == 0) {
                        _find(nextRow, nextCol, moves, color, board, node, word, found);
                    }
                }
                break;
            }
        color[startRow][startCol] = 0;
    }

    private TrieNode _buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode ptr = root;
            for (int i = 0; i < word.length(); ++i) {
                int ahead = word.charAt(i) - 'a';
                if (ptr.children[ahead] == null) {
                    ptr.children[ahead] = new TrieNode();
                    ++ptr.childrenCnt;
                }
                ptr = ptr.children[ahead];
                if (i == word.length() - 1)
                    ptr.isWord = true;
            }
        }
        return root;
    }

    static public class Test {
        static private WordSearchII _solution = new WordSearchII();

        static public void test(char[][] board, String[] word) {
            _solution.findWords(board, word).forEach(System.out::println);
        }

        static public void randomTest() {
            test(new char[][]{{'a'}}, new String[]{"a"});
            test(new char[][]{{'a', 'a'}}, new String[]{"aaa"});
            char[][] board = {
                    "oaan".toCharArray(),
                    "etae".toCharArray(),
                    "ihkr".toCharArray(),
                    "iflv".toCharArray()
            };
            String[] word = {"oath","pea","eat","rain"};
            test(board, word);
        }
    }
}
