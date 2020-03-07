package Sudoku;


public class SudokuBoard {
    public int[][] plan;
 
    public SudokuBoard() {
        plan = new int[9][9];
    }
 
    /**
     * Returns the value of that position.
     *
     * @param row
     *            The row where the it's located
     * @param col
     *            The column where the it's located
     * @return the integer value.
     */
    public int getValue(int row, int col) {
 
        return plan[row][col];
    }
 
    /**
     * Sets the value of that position.
     *
     * @param row
     *            The row where the value should be inserted.
     * @param col
     *            The column where the value should be inserted.
     * @param value
     *            The value you want to set.
     */
    public void setValue(int row, int col, int value) {
 
        plan[row][col] = value;
 
    }
 
    /**
     * Prints the board.
     */
    public void print() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
 
                System.out.print(plan[row][col] + " ");
            }
            System.out.print("\n");
        }
 
    }
 
    /**
     * Solves the Sudoku if possible.
     *
     * @return true if the sudoku can be solved, else returns false.
     */
    public boolean solve(int row, int col) {
        if (row == 9) {
            return true;
        }
 
        if (plan[row][col] == 0) {
            for (int value = 1; value <= 9; value++) {
                plan[row][col] = value;
                if (allowed(row, col)) {
                    if (col != 8) {
                        if (solve(row, col + 1)) {
                            return true;
                        }
                    } else {
                        if (solve(row + 1, 0)) {
                            return true;
                        }
                    }
                }
            }
            plan[row][col] = 0;
            return false;
 
        } else {
 
            if (allowed(row, col)) {
                if (col == 8) {
                    if (solve(row + 1, 0)) {
                        return true;
                    }
 
                } else {
                    if (solve(row, col + 1)) {
                        return true;
                    }
                }
            }
        }
 
        return false;
    }
 
    /**
     * Checks if the number at position (row,col) breaks any rules towards the
     * other numbers in the suduko matrix.
     *
     * @return true if the number can be placed at (row,col).
     */
    private boolean allowed(int row, int col) {
 
        // kontrollerar raden
        for (int i = 0; i <= 8; i++) {
            if (i != col) {
                if (plan[row][i] == plan[row][col])
                    return false;
            }
        }
 
        // kontrollerar kollumnen
        for (int i = 0; i <= 8; i++) {
            if (i != row) {
                if (plan[i][col] == plan[row][col])
                    return false;
            }
        }
 
        // kontrollerar boxen
        int rowbox = row % 3;
        int colbox = col % 3;
 
        int startrow = row - rowbox;
        int startcol = col - colbox;
 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(row == startrow + i && col == startcol + j)
                        && plan[row][col] == plan[startrow + i][startcol + j]) {
                    return false;
                }
            }
        }
 
        return true;
    }
 
    /**
     * Checks if the sudoku is legit so far.
     *
     * @return true if the sudoku doesn't break any rules. Else returns false,
     */
    public boolean checkboard() {
 
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (plan[row][col] != 0) {
                    if (!allowed(row, col)) {
                        return false;
                    }
 
                }
 
            }
        }
 
        return true;
 
    }
 
}

