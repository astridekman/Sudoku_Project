package Sudoku;


/**
 * Soduko solving class, backend
 *
 * @author Astrid and Emma
 *
 */
 
public class SudokuBoard {
    public int[][] board;
   
    public SudokuBoard() {
        board = new int[9][9];
    }
   
    /**
     * Returns the value of the position at index (row, column)
     * on the Sudoku board
     *
     * @param row the row  
     * @param col the column
     * @return the int value at the position (row, column)
     */
    public int getValue(int row, int col) {
        //GUI ska kunna hämta värdet på en specifik plats
        return board[row][col];
    }
   
    /**
     * Sets the desired value on the desired position
     *
     * @param row the row of the desired position
     * @param col the column of the desired position
     * @param value the value you want to put in the desired position
     */
    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }
   
    /**
     * Solves the Sudoku if possible
     *
     * @return returns true if the sudoku is solvable, otherwise it returns false
     */
    public boolean solve(int row, int col) {
        if(row == 9) {
            return true;
        }
       
        if(board[row][col] == 0) {
            for(int value = 1; value <= 9; value++) {
                board[row][col] = value;
                if(allowedValue(row, col)) {
                    if(col != 8) {
                        if(solve(row, col+1)) {
                            return true;
                        }
                    }
                    else {
                        if(solve(row+1, 0)) {
                            return true;
                        }
                       
                    }
                }
            }
            board[row][col] = 0;
            return false;
        }
        else {
            if(allowedValue(row, col)) {
                if(col!= 8) {
                    if(solve(row, col+1)) {
                        return true;
                    }
                }
                else {
                    if(solve(row + 1, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
   
    private boolean allowedValue(int row, int col) {
       
        //Kolla om värdet på indexet row,col finns i raden
        for(int i = 0; i < 9; i++) {
            if(i != col) {
                if(board[row][i] == board[row][col]) {
                    return false;
                }
            }
           
        }
       
        //Kolla om värdet på indexet row,col finns i kolumnen
        for(int i = 0; i < 9; i++) {
            if(i != row) {
                if(board[i][col] == board[row][col]) {
                    return false;
                }
            }  
        }
       
        //Kolla om värdet på indexet row,col finns i boxen
        int newRow = row % 3 ;
        int newCol = col % 3 ;
       
        int startrow = row - newRow;
        int startcol = col - newCol;
       
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i != newRow && j != newCol) {
                    if(board[startrow + i][startcol + j] == board[row][col]) {
                        return false;
                    }
                }
            }
        }  
        return true;
    }
   
    /**
     * Checks if the user has put in values that don't break the rules
     *
     * @return true if the values the user has put in are ok, otherwise false
     */
    public boolean checkBoard() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(!allowedValue(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }
}