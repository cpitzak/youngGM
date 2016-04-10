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
		board.print();
	}
	
	public boolean makeMove(String move) {
		boolean result = board.makeMove(move);
		board.print();
		return result;
	}
	
	public boolean isSquareEmpty(String algebraicSquare) {
		return board.isSquareEmpty(algebraicSquare);
	}
	
	public boolean isWhiteTurn() {
		return board.isWhitesTurn();
	}
	
}
