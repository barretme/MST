/* Main.java */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		TreeMap<Pair<String, String>, Integer> pairMap = 
				new TreeMap<Pair<String, String>, Integer>();
		TreeSet<String> vertexSet = new TreeSet<String>();
		// Create a scanner object to read words from file
		Scanner in = new Scanner(new File("mobysmall.txt"));
		// delim scanner to punctuation and spaces 
		in.useDelimiter("[\\p{Punct}\\p{Space}\\p{Digit}]+");
		String s1 = in.next().toLowerCase();
		String s2 = "";
		
		// Scan through strings of file
		while (in.hasNext()) {
			// Save next word into s2
			s2 = in.next().toLowerCase();
			if (s2 != "") {
				vertexSet.add(s2);
				// Create Edge containing two adjacent words
				Pair<String, String> wordPair = new Pair<String, String>(s1, s2);
				s1 = s2;
				// If TreeMap does not contain wordEdge, enter conditional
				if (pairMap.containsKey(wordPair)) {
					// If wordEdge exists, get its value
					Integer tempCount = pairMap.get(wordPair);
					// Put in wordEdge and add 1 to its current value 
					pairMap.put(wordPair, tempCount + 1);
				}
				else {
					// Put word pair and initial count of 1 into TreeMap
					pairMap.put(wordPair, 1);	
				}
			}
		} // End while loop reading file
		
		MST mst = new MST(pairMap, vertexSet);
		mst.print();
		System.out.println("Expected answer = 894");
		System.out.println("Determined answer = " + mst.addEdges());
		
		in.close();
	}
}
