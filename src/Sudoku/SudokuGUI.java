package Sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class SudokuGUI {
	JPanel[][] board;
	JTextField[][] textFields;
	JFrame frame;
	JPanel topPanel;
	JPanel boardPanel;
	JPanel buttonPanel;
	
	public SudokuGUI() {
		board = new JPanel[9][9];
		textFields = new JTextField[9][9];
		SwingUtilities.invokeLater(() -> createWindow());
	}

	private void createWindow() {
		frame = createFrame();
		
		topPanel = createTopPanel();
		
		boardPanel = createBoardPanel();
		addToBoardPanel(boardPanel);
		
		buttonPanel = createButtonPanel();
	

		
		topPanel.add(boardPanel);
        topPanel.add(buttonPanel);
		frame.add(topPanel);
		frame.setVisible(true);
		
	}
	
	public JFrame createFrame() {
		JFrame frame = new JFrame("Sudoku");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(410, 480);
		frame.setResizable(false);
		
		return frame;
	}
	
	public JPanel createTopPanel() {
		JPanel topPanel = new JPanel();
		
		return topPanel;
	}
	
	public JPanel createBoardPanel() {

		JPanel boardPanel = new JPanel();
		
		GridLayout layOut = new GridLayout(9, 9);
		boardPanel.setLayout(layOut);
		
		Color color = new Color(240,248,255);
		layOut.setHgap(6);
		boardPanel.setBackground(color);

		
		return boardPanel;
	}
	
	public void addToBoardPanel(JPanel boardPanel) {
		Color color = new Color(255,255,255);
		
		int passedRows = 0;
		boolean newRow = false;

		
		for(int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				board[row][col] = new JPanel();
				
				if (col%3 == 0 && newRow == false) {
					color = changeColor(color);					
				}
				
				JPanel subPanel = new JPanel();
				subPanel.setBackground(color);
				subPanel.setPreferredSize(new Dimension(70,70));
		        subPanel.setOpaque(true);		
				
				boardPanel.setPreferredSize(new Dimension(410,410));
			    boardPanel.setOpaque(true);
			        

		        
		        JTextField textField = new JTextField(2);
		       
		        textField.setBackground(color);
		        Font font1 = new Font("SansSerif", Font.BOLD, 12);
		        textField.setFont(font1);
		        textField.setSize(70,70);
		        
		        Border border = BorderFactory.createLineBorder(color, 5);
		        textField.setBorder(border);
		        
		        textField.addKeyListener(new KeyAdapter() {
		            public void keyPressed(KeyEvent ke) {
		               String value = textField.getText();
		               int l = value.length();
		               if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && value.length() < 1) {
		            	   textField.setEditable(true);
		                  //label.setText("");
		               } else {
		            	   textField.setEditable(false);
		                  //label.setText("* Enter only numeric digits(0-9)");
		               }
		            }
		         });
		        
		        subPanel.add(textField);
		        
		        textFields[row][col] = textField;
				board[row][col].add(subPanel);
				newRow = false;
				
			}
			
			passedRows++;
			
			if (passedRows == 3 || passedRows == 6) {
				color = changeColor(color);
			}
			newRow = true;
			
			}

		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				boardPanel.add(board[i][j]);
			}			
		}
	}
	
	public JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");
        
        solveButton.addActionListener(event -> {		
			solveButtonPressed();
	});
        
        clearButton.addActionListener(event -> {		
			clearButtonPressed();
    	});
        
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        
        return buttonPanel;
	}
	
	public Color changeColor(Color color) {
		int red = color.getRed();;
		int green = color.getGreen();
		int blue = color.getBlue();
	
		if (red == 255 && green == 165 && blue == 0) {
			color = new Color(255,255,255);
		}
		
		else {
			color = new Color(255,165,0);
		}
		
		return color;

	}
	
	public void solveButtonPressed() {
		SudokuBoard sudokuBoard = new SudokuBoard();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				JTextField textField = textFields[i][j];
				String str = textField.getText();
				if (!str.isEmpty()) {
					int value = Integer.parseInt(textField.getText());
					
					sudokuBoard.setValue(i, j, value);
				}
				
			}
		}
		
		sudokuBoard.solve(0, 0);
		
		int[][] solvedBoard = sudokuBoard.plan;
		frame.invalidate();
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				JPanel subPanel = board[i][j];
				subPanel.remove(0);
				
				Integer intVal = solvedBoard[i][j];
				String val = intVal.toString();
				
				JLabel label = new JLabel(val);
				
				Font font1 = new Font("SansSerif", Font.BOLD, 12);
		        label.setFont(font1);
		        label.setSize(70,70);
		        
		        subPanel.add(label);
				
				
			}
		}
		
		frame.validate();
		
	}
	
	
	
	public void clearButtonPressed() {
	}
}
