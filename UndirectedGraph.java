/* UndirectedGraph.java */

import java.util.*;

public final class UndirectedGraph<T> implements Iterable<T>{
	private final Map<T, Set<T>> graph = new HashMap<T, Set<T>>();

	public boolean addNode(T node) {
	 if (graph.containsKey(node))
	  return false;

	 graph.put(node, new HashSet<T>());
	 return true;
	}

	public void addEdge(T start, T dest) {
	 if (!graph.containsKey(start) || !graph.containsKey(dest))
	  throw new Exception("No such nodes in the graph.");
	 
	 graph.get(start).add(dest);
	 graph.get(dest).add(start);
	}

	public void removeEdge(T start, T dest) {
	 if (!graph.containsKey(start) || !graph.containsKey(dest))
	  throw new Exception("No such nodes in the graph.");

	 graph.get(start).remove(dest);
	 graph.get(dest).remove(start);
	}

	public boolean isEdgeExists(T start, T end) {
	 if (!graph.containsKey(start) || !graph.containsKey(end))
	  throw new Exception("No such nodes in the graph.");

	 return graph.get(start).contains(end);
	}

	public Set<T> getNeighbors(T node) {
	 Set<T> neighbors = graph.get(node);
	 if (neighbors == null)
	  throw new Exception("No such node in the graph.");

	 return Collections.unmodifiableSet(neighbors);
	}

	public Iterator<T> iterator() {
	 return graph.keySet().iterator();
	}
	public Iterable<T> getNodes() {
	 return graph.keySet();
	}
	public int size() {
	 return graph.size();
	}
	public boolean isEmpty() {
	 return graph.isEmpty();
	}
}
