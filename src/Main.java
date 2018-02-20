
public class Main {

	// Currently used for manual testing
	public static void main(String[] args) {
		SudokuModel sm = new SudokuModel();
		sm.setValue(3, 1, 1);
		sm.setValue(5, 8, 8);
		sm.setValue(9, 7, 8);
		sm.setValue(4, 3, 5);
		sm.setValue(8, 6, 3);
		sm.setValue(2, 2, 5);
		sm.setValue(5, 1, 8);

		
		// testar reglerna
		sm.setValue(4, 3, 2);
		sm.setValue(5, 8, 5);
		sm.setValue(4, 4, 5);
		System.out.println(sm.checkRules(8, 7, 5));

		System.out.println(sm.getValue(8, 8));
		sm.printMatrix();

	}
}
