package solutions;

import solutions.utils.UndirectedGraphNode;

import java.util.*;

/**
 * Created by PPlovboiledfish on 1/12/16.
 */
public class FindTheConnectedComponentInTheUndirectedGraph {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        if (nodes == null || nodes.isEmpty())
            return new ArrayList<>();

        Map<Integer, List<UndirectedGraphNode>> adj = new HashMap<>();
        for (UndirectedGraphNode node : nodes)
            adj.put(node.label, node.neighbors);

        List<List<Integer>> graphs = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int node : adj.keySet()) {
            if (!visited.contains(node)) {
                List<Integer> graph = new ArrayList<>();
                _DFS(adj, visited, node, graph);
                Collections.sort(graph);
                graphs.add(graph);
            }
        }
        return graphs;
    }

    private void _DFS(Map<Integer, List<UndirectedGraphNode>> adj, Set<Integer> visited, int label, List<Integer> rec) {
        rec.add(label);
        visited.add(label);
        for (UndirectedGraphNode next : adj.get(label)) {
            if (!visited.contains(next.label))
                _DFS(adj, visited, next.label, rec);
        }
    }
}
