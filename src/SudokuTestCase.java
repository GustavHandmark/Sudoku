import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuTestCase {
	SudokuModel sm;

	@Before
	public void setUp() throws Exception {
		sm = new SudokuModel();
	}

	@After
	public void tearDown() throws Exception {
		sm = null;
	}

	/**
	 * Test solve an empty sudoku
	 */
	@Test
	public final void testSolveEmptySudoku() {
		assertTrue("After: Invalid solution", !sm.solveSudoku());
	}

	/**
	 * Test if solution is correct
	 */
	@Test
	public void testUnsovableSudoku() {
		sm.setValue(5, 0, 0);
		sm.setValue(5, 0, 1);
		assertTrue("After: Invalid Sudoku was solved - row", !sm.solveSudoku());

		sm.setValue(5, 0, 0);
		sm.setValue(5, 1, 0);
		assertTrue("After: Invalid Sudoku was solved - column", !sm.solveSudoku());

		sm.setValue(5, 0, 0);
		sm.setValue(5, 1, 1);
		assertTrue("After: Invalid Sudoku was solved - region", !sm.solveSudoku());
	}

	@Test
	public void testUnsovableSudokuAndRemove() {
		sm.setValue(1, 0, 0);
		sm.setValue(2, 0, 1);
		sm.setValue(3, 0, 2);
		sm.setValue(4, 1, 0);
		sm.setValue(5, 1, 1);
		sm.setValue(6, 1, 2);

		sm.setValue(7, 3, 3);
		assertTrue("After: Incorrect Sudoku solveable", !sm.solveSudoku());

		sm.setValue(0, 3, 3);
		assertTrue("After: Not solveable", sm.solveSudoku());

	}

	/**
	 * Test solve an empty sudoku
	 */
	@Test
	public final void testClear() {
		sm.clearMatrix();

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++)
				assertTrue("After: Invalid solution", sm.getValue(r, c) == 0);
		}
	}

}
