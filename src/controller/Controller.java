package controller;

import model.Board;
import view.ChessGUI;

public class Controller {
	
	private Board board;
	private ChessGUI view;
	
	public void addModel(Board board) {
		this.board = board;
	}
	
	public void addView(ChessGUI view) {
		this.view = view;
	}
	
	public void resetBoard() {
		board.initBoard();
	}
	
	public void makeMove(String move) {
		board.makeMove(move);
		board.print();
	}

}
