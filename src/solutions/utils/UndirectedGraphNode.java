package solutions.utils;

import java.util.ArrayList;

/**
 * Created by PPlovboiledfish on 1/12/16.
 */
public class UndirectedGraphNode {
    public int label;
    public ArrayList<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
