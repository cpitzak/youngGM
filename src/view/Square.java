package view;

import javax.swing.JButton;

public class Square {
	private int row;
	private int col;
	private JButton button;

	public Square(int row, int col, JButton button) {
		this.row = row;
		this.col = col;
		this.button = button;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public JButton getButton() {
		return button;
	}

}
