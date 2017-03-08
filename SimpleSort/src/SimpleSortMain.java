package lsrhs.java;

// main program/test harness
public class SimpleSortMain {

	// **************** UTILITY METHODS FOR BUILDING AND TESTING ARRAY SORTING
	public static int randomInt(int max) {
			return (int) (Math.random() * max + 1);
	}
	
	public static int[] randomArray(int size) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = randomInt(2 * size);
		}
		return a;
	}
	
	public static void printArray(int[] a) {
		for (int n : a) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(int a[]) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i+1]) {
				return false;
			}
		}
		return true;
	}

	// Runs a basic test on a given sorter with a given array (if sorter is null it
	// just prints the array and says whether it's already sorted)
	public static void testSort(String label, int a[], SimpleSorterAbstract sorter) {
		System.out.println("\n" + label);
		printArray(a);
		System.out.println("Is sorted? " + isSorted(a));
		if (sorter != null) {
			System.out.println("Testing sorter " + sorter.getClass().getName());
			a = sorter.sort(a);
			printArray(a);
			System.out.println("Is sorted? " + isSorted(a));

		}
	}
	
	// main test harness
	public static void main(String[] args) {
		int[] a1 = {0, 3, 7, 7, 9};
		int[] a2 = randomArray(10);
		int[] a3 = randomArray(10);
		
		SimpleSorterAbstract sorter1 = new CheatSorter();
		SimpleSorterAbstract sorter2 = new MySorter();
		testSort("Static array", a1, null);
		testSort("CheatSorter", a2, sorter1);
		testSort("MySorter", a3, sorter2);
	}

}
