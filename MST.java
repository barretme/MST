/* MST.java */

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MST {
	private TreeMap<Pair<String, String>, Integer> oldMap = 
			new TreeMap<Pair<String, String>, Integer>();
	private TreeMap<Pair<String, String>, Integer> newMap = 
			new TreeMap<Pair<String, String>, Integer>();
	private TreeSet<String> vertexSet = new TreeSet<String>();
	
	// Constructor
	public MST(TreeMap<Pair<String, String>, Integer> p, TreeSet<String> set) {
		this.oldMap.putAll(p);
		this.vertexSet.add(oldMap.firstKey().getFirst());
		prim(set);
	}
	
	// Sort map and return as a SortedSet
	public static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> 
		entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e1.getValue().compareTo(e2.getValue());
                    // Special fix to preserve items with equal values
                    return res != 0 ? res : 1; 
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
	
	// Use Prim's algorithm to find MST undirected graph
	private void prim(TreeSet <String> set) {
		while (vertexSet.size() < set.size()) {
			for (Entry<Pair<String, String>, Integer> entry  : 
				entriesSortedByValues(oldMap)) {
				String firstWord = entry.getKey().getFirst();
				String secondWord = entry.getKey().getSecond();
				if (vertexSet.contains(firstWord) ^ 
						vertexSet.contains(secondWord)) {
					if (vertexSet.contains(firstWord))
						vertexSet.add(secondWord);
					else if (vertexSet.contains(secondWord))
						vertexSet.add(firstWord);
					newMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
	}
	
	// Add up the sum of all the edges in the MST
	public int addEdges() {
		int total = 0;
		for (Integer edgeValues : newMap.values()) {
			total += edgeValues;
		}
		return total;
	}
	
	// Return the size of the oldMap
	public int size() {
		return oldMap.size();
	}
	
	// Add a string to the vertexSet
	public void add(String s) {
		vertexSet.add(s);
	}
	
	public boolean contains(Object o) {
		if (vertexSet.contains(o)) 
			return true;
		else
			return false;
		
	}
	
	public void print() {
		for (Entry<Pair<String, String>, Integer> entry : 
			entriesSortedByValues(oldMap) ) {
//			System.out.println("(" + entry.getKey().getFirst() + ", " + 
//					entry.getKey().getSecond() + ") " + entry.getValue());
			String s1 = entry.getKey().getFirst();
			String s2 = entry.getKey().getSecond();
			if (s1.compareTo(s2) < 0)
				System.out.println(s1 + " " + s2 + " " + entry.getValue());
			if (s1.compareTo(s2) > 0)
				System.out.println(s2 + " " + s1 + " " + entry.getValue());
		}
	}
}
