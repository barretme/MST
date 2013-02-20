/* Filename: Pair.java */

public class Pair<U, V> implements Comparable<Pair<U, V>> {
	private U first;
	private V second;
	
	public Pair(U first, V second) {
		if (first.equals(second) && second.equals(first)) {
			this.first = (U) second;
			this.second = (V) first;
		}
		else {
			this.first = first;
			this.second = second;
		}
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
		else return false;
	}

	public int compareTo(Pair<U, V> o) {
		int value = ((Comparable<U>)first).compareTo(o.first);
		if (value != 0)
			return value;
		return ((Comparable<V>)second).compareTo(o.second);
	}
	
	public U getFirst() {
		return first;
	}
	
	public V getSecond() {
		return second;
	}
	
	public Pair<U, V> getPair() {
		Pair<U, V> newPair = new Pair<U, V>(first, second);
		return newPair;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.first + " " + this.second);
		return result.toString();
	}

}
