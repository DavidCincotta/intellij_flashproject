package lsrhs.java;

import java.util.Arrays;

// sorter that "cheats" by using the built in Java sort method for arrays

public class CheatSorter extends SimpleSorterAbstract {
	public int[] sort(int[] a) {
		Arrays.sort(a);
		return a;
	}
}
