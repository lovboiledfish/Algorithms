package solutions;

import java.util.*;

/**
 * Created by PPlovboiledfish on 1/2/16.
 */
public class WordSearchII {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0) return new ArrayList<>();

        TrieNode trie = _buildTrie(words);
        List<String> found = new ArrayList<>();
        char[][] visited = new char[board.length][board[0].length];
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j)
                _find(i, j, moves, visited, board, trie, "", found);
        }
        return found;
    }

    private void _find(int startRow, int startCol, int[][] moves, char[][] visited, char[][] board,
                       TrieNode node, String word, List<String> found) {
        visited[startRow][startCol] = 1;
        int i = board[startRow][startCol] - 'a';
        if (node.children[i] != null) {
            word += board[startRow][startCol];
            node = node.children[i];
            if (node.isWord) {
                node.isWord = false;
                found.add(word);
            }
            for (int[] move : moves) {
                int nextRow = startRow + move[0], nextCol = startCol + move[1];
                if (nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length
                        && visited[nextRow][nextCol] == 0) {
                    _find(nextRow, nextCol, moves, visited, board, node, word, found);
                }
            }
        }
        visited[startRow][startCol] = 0;
    }

    private TrieNode _buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode ptr = root;
            for (int i = 0; i < word.length(); ++i) {
                int ahead = word.charAt(i) - 'a';
                if (ptr.children[ahead] == null) ptr.children[ahead] = new TrieNode();
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
            String[] word = {"oath", "pea", "eat", "rain"};
            test(board, word);
        }
    }
}
