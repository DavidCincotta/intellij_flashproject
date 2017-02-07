public class SSSMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			SSSData d1 = new SSSData(10);
			SSSData d2 = new SSSData(10);
			
			d1.print();
			d1.randomize();
			d1.print();
			
			d2.print();
			d2.sort();
			d2.print();			
	}
}
