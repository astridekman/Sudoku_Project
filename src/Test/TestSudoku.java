package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Sudoku.SudokuBoard;

class TestSudoku {

	SudokuBoard sudokuBoard;

	@BeforeEach
	void setUp() throws Exception {
		sudokuBoard = new SudokuBoard();
	}

	@AfterEach
	void tearDown() throws Exception {
		sudokuBoard = null;
	}

	@Test
	void testEmpty() {
		assertTrue(sudokuBoard.solve(0, 0), "Solution of empty soduko should return True");
		
	}
	
	@Test
	void testFig1() {

		int[] rows1 = {2, 3, 6, 7};
		int[] columns1 = {0, 4, 1, 6};
		setValues(rows1, columns1, 1);
		

		int[] rows2 = {0, 2, 3, 5};
		int[] columns2 = {8, 2, 3, 7};
		setValues(rows2, columns2, 2);
		
		int[] rows3 = {7};
		int[] columns3 = {4};
		setValues(rows3, columns3, 3);
		
		int[] rows4 = {6, 8};
		int[] columns4 = {0, 6};
		setValues(rows4, columns4, 4);
		
		int[] rows5 = {1, 2, 4};
		int[] columns5 = {8, 3, 1};
		setValues(rows5, columns5, 5);
		
		int[] rows6 = {0, 4, 5, 6, 7};
		int[] columns6 = {7, 6, 0, 3, 1};
		setValues(rows6, columns6, 6);
		
		//No sevens
		
		int[] rows8 = {0, 5, 6, 7};
		int[] columns8 = {2, 8, 5, 0};
		setValues(rows8, columns8, 8);
		
		int[] rows9 = {0, 3};
		int[] columns9 = {5, 7};
		setValues(rows9, columns9, 9);
		
		assertTrue(sudokuBoard.solve(0, 0), "There should be a solution.");
		
		int[][] correctSolution = {{5, 4, 8, 1, 7, 9, 3, 6, 2}, {3, 7, 6, 8, 2, 4, 9, 1, 5}, {1, 9, 2, 5, 6, 3, 8, 7, 4}, 
								   {7, 8, 4, 2, 1, 6, 5, 9, 3}, {2, 5, 9, 3, 8, 7, 6, 4, 1}, {6, 3, 1, 9, 4, 5, 7, 2, 8},
								   {4, 1, 5, 6, 9, 8, 2, 3, 7}, {8, 6, 7, 4, 3, 2, 1, 5, 9}, {9, 2, 3, 7, 5, 1, 4, 8, 6}
		};
		
		

		
		assertArrayEquals(correctSolution, sudokuBoard.plan);
		
		
		
	}
	
	void setValues(int[] rows, int[] columns, int value) {
		int length = rows.length;
		
		for (int i = 0; i < length; i++) {
					sudokuBoard.setValue(rows[i], columns[i], value);
				}
	}
	

	
	@Test
	void testNotSolvable1() {
		//Set two equal values on the same row
		int[] rows = {1, 1};
		int[] columns = {2, 3};
		
		setValues(rows, columns, 1);
		assertFalse(sudokuBoard.solve(0, 0), "There is no solution.");
		
	}
	
	@Test
	void testNotSolvable2() {
		//Set two equal values on the same col
		int[] rows = {1, 7};
		int[] columns = {5, 5};
		
		setValues(rows, columns, 3);
		assertFalse(sudokuBoard.solve(0, 0), "There is no solution.");
		
	}
	
	@Test
	void testNotSolvable3() {
		//Set two equal values on the same subsquare
		int[] rows = {0, 2};
		int[] columns = {0, 2};
		
		setValues(rows, columns, 2);
		assertFalse(sudokuBoard.solve(0, 0), "There is no solution.");
		
	}
	

}
