import com.sun.xml.internal.bind.v2.TODO;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.Arrays;

// main program/test harness
public class SimpleSortMain {

	// **************** UTILITY METHODS FOR BUILDING AND TESTING ARRAY SORTING
	public static int randomInt(int max) {
			return (int) (Math.random() * max + 1);
	}
	
	public static Integer[] randomArray(int size) {
		Integer[] a = new Integer[size];
		for (int i = 0; i < size; i++) {
			a[i] = randomInt(2 * size);//size
		}
		return a;
	}
	
	public static void printArray(Integer[] a) {
		for (int n : a) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(Integer a[]) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i+1]) {
				return false;
			}
		}
		return true;
	}

	// Runs a basic test on a given sorter with a given array (if sorter is null it
	// just prints the array and says whether it's already sorted)
	public static void testSort(String label, Integer a[], SimpleSorterAbstract sorter) {
		System.out.println("\n" + label);
		//printArray(a);
		System.out.println("Is sorted? " + isSorted(a));
		if (sorter != null) {
			System.out.println("Testing sorter " + sorter.getClass().getName());
			a = sorter.sort(a);
			//printArray(a);
			System.out.println("Is sorted? " + isSorted(a));
		}
	}
	
	// main test harness
	public static void main(String[] args) {
	    int n = 10000000;
		Integer[] a1 = {0, 3, 7, 7, 9};
		Integer[] a2 = randomArray(n);
		Integer[] a3 = randomArray(n);
        Integer[] a4 = randomArray(n);
        Integer[] a5 = randomArray(n);
        Integer[] a6 = randomArray(n);
		
		SimpleSorterAbstract sorter1 = new CheatSorter();
		SimpleSorterAbstract sorter3 = new MergeSort(a4); //Θ(n·lg(n)) time
        SimpleSorterAbstract sorter4 = new HeapSort();

		testSort("Static array", a1, null);
		testSort("CheatSorter", a2, sorter1);
		testSort("MergeSort", a4, sorter3);
        testSort("HeapSort", a5, sorter4);
    }




}
