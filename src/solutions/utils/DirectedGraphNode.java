package solutions.utils;

import java.util.ArrayList;

/**
 * Created by PPlovboiledfish on 1/12/16.
 */
public class DirectedGraphNode {
    public int label;
    public ArrayList<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}
