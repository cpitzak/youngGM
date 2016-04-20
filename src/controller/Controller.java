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
	
	public String makeMove(String move) {
		String result = board.makeMove(move);
		System.out.println("------------------------------");
		board.print();
		return result;
	}
	
	public boolean isSquareEmpty(String algebraicSquare) {
		return board.isSquareEmpty(algebraicSquare);
	}
	
	public boolean isWhiteTurn() {
		return board.isWhitesTurn();
	}
	
	public boolean undoMove() {
		boolean result = board.undoMove();
		System.out.println("------------------------------");
		board.print();
		return result;
	}
	
}
