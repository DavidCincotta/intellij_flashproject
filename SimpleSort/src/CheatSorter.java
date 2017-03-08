import java.util.Arrays;

// sorter that "cheats" by using the built in Java sort method for arrays

public class CheatSorter extends SimpleSorterAbstract {
	public Integer[] sort(Integer[] a) {
		long startTime = System.currentTimeMillis();
		Arrays.sort(a);
		System.out.println(System.currentTimeMillis() - startTime);

		return a;
	}
}
