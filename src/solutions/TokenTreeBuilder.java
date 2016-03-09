package solutions;import java.util.ArrayList;
import java.util.List;

/**
 * Created by feiyi.zhan on 2/19/16.
 */
public class TokenTreeBuilder {
	class Token {
		String name;
		String type;

		public Token(String n, String t) {
			name = n;
			type = t;
		}

		public boolean isCloseToken(Token t) {
			return t != null && (name.equals(t.name) && "close".equals(t.type));
		}
	}

	class TreeNode {
		Token val;
		List<TreeNode> children = new ArrayList<TreeNode>();

		public TreeNode(Token v) {
			val = v;
		}
	}

	class Tokenizer {
		public Token getNext() {
			return null;
		}
	}

	public TreeNode buildTree(Tokenizer tokenizer) {
		TreeNode dummy = new TreeNode(null);
		_buildTree(dummy, tokenizer);
		if (dummy.children.isEmpty()) {
			return null;
		} else {
			return dummy.children.get(0);
		}
	}

	private void _buildTree(TreeNode parent, Tokenizer tokenizer) {
		Token token = tokenizer.getNext();
		while (token != null) {
			if ("inner".equals(token.type)) {
				parent.children.add(new TreeNode(token));
				return;
			} else if (!parent.val.isCloseToken(token)) {
				TreeNode newNode = new TreeNode(token);
				parent.children.add(newNode);
				_buildTree(newNode, tokenizer);
				return;
			}
			token = tokenizer.getNext();
		}
	}
}
