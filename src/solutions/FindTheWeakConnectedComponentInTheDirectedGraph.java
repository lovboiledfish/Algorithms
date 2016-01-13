package solutions;

import solutions.utils.DirectedGraphNode;

import java.util.*;

/**
 * Created by PPlovboiledfish on 1/12/16.
 */
public class FindTheWeakConnectedComponentInTheDirectedGraph {
    /**
     * @param nodes a array of Directed graph node
     * @return a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        if (nodes == null || nodes.isEmpty())
            return new ArrayList<>();
        Map<Integer, Integer> ids = new HashMap<>();
        for (DirectedGraphNode node : nodes)
            ids.put(node.label, node.label);
        for (DirectedGraphNode node : nodes)
            for (DirectedGraphNode neighbor : node.neighbors)
                _union(ids, node.label, neighbor.label);
        Map<Integer, List<Integer>> classifiedGraphs = new HashMap<>();
        for (int label : ids.keySet()) {
            int set = _find(ids, label);
            if (!classifiedGraphs.containsKey(set))
                classifiedGraphs.put(set, new ArrayList<>());
            classifiedGraphs.get(set).add(label);
        }
        List<List<Integer>> graphs = new ArrayList<>();
        for (List<Integer> graph : classifiedGraphs.values()) {
            Collections.sort(graph);
            graphs.add(graph);
        }
        return graphs;
    }

    private int _find(Map<Integer, Integer> ids, int label) {
        while (ids.get(label) != label)
            label = ids.get(label);
        return label;
    }

    private void _union(Map<Integer, Integer> ids, int label1, int label2) {
        label1 = _find(ids, label1);
        label2 = _find(ids, label2);
        ids.put(Math.max(label1, label2), Math.min(label1, label2));
    }
}
