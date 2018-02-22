import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

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
		sm.setValue(5, 0, 1);
		sm.setValue(5, 0, 8);
		assertTrue("After: Incorrect Sudoku solveable", !sm.solveSudoku());

		sm.clearMatrix();
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++)
				assertTrue("After: Not all cells cleared", sm.getValue(r, c) == 0);
		}
		assertTrue("After: Sudoku not solved", sm.solveSudoku());

	}

	@Test
	public final void testSolveSudoku() {
		sm.setValue(8, 0, 2);
		sm.setValue(1, 2, 0);
		sm.setValue(2, 2, 2);
		sm.setValue(1, 3, 4);
		sm.setValue(9, 0, 5);
		sm.setValue(5, 2, 3);
		sm.setValue(6, 0, 7);
		sm.setValue(2, 0, 8);
		sm.setValue(5, 1, 8);
		sm.setValue(5, 4, 1);
		sm.setValue(6, 5, 0);
		sm.setValue(2, 3, 3);
		sm.setValue(9, 3, 7);
		sm.setValue(6, 4, 6);
		sm.setValue(2, 5, 7);
		sm.setValue(8, 5, 8);
		sm.setValue(4, 6, 0);
		sm.setValue(1, 6, 1);
		sm.setValue(8, 7, 0);
		sm.setValue(6, 7, 1);
		sm.setValue(6, 6, 3);
		sm.setValue(8, 6, 5);
		sm.setValue(3, 7, 4);
		sm.setValue(1, 7, 6);
		sm.setValue(4, 8, 6);

		assertTrue("After: Sudoku failed to solve", sm.solveSudoku());

	}

	@Test
	public final void testIncorrectInData() {
		try {
			sm.setValue(-1, 4, 4);
			fail("Should Exception");
		} catch (Exception e) {
			// successful test
		}
		
		

	}

}
