import jdk.nashorn.internal.runtime.ECMAException;

public class SSSMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("Random");
        for (int i = 0; i < 5; i++) {
            SSSData d1 = new SSSData(1000);
            //d1.print();
            d1.randomize();
            //d1.print();
            //System.out.println("Linear: " + lookUpLinear(d1, 200));
            //d1.printHits();
            //d1.resetHitCount();
            System.out.println("Binary: " + binarySearch(d1, 40));
            d1.printHits();
        }
        System.out.println("Sorted");
        for (int i = 0; i < 5; i++) {
            SSSData d2 = new SSSData(1000);
            //d2.print();
            d2.sort();
            //d2.print();
            //System.out.println("Linear: " + lookUpLinear(d2, 200));
            //d2.printHits();
            //d2.resetHitCount();
            System.out.println("Binary: " + binarySearch(d2, 40));
            d2.printHits();
        }
    }
	public static int lookUpLinear(SSSData object, int value){
        int i = 0;
        try {
            while (object.get(i) != value) {
                i++;
            }
        }catch (Exception e){
            return -1;
        }
        return i;
    }
    public static int binarySearch(SSSData object, int value){
        int low = 0;
        int high = object.getSize()-1;
        while(high>=low){
            int middle = (int)(((double)low+(double)high)/2);
            int num = object.get(middle);
            if(num == value){
                return middle;
            }
            if (num<value){
                low = middle + 1;
            }
            if( num>value){
                high = middle-1;
            }
        }
        return -1;
    }

}
