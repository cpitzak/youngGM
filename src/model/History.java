package model;

import java.util.Stack;

import model.move.Move;

public class History {

	private Stack<State> states = new Stack<State>();

	public void record(Move move, Board board) {
		State state = new State(board.isWhitesTurn(), board.isWhiteCanCastleKingSide(),
				board.isWhiteCanCastleQueenSide(), board.isBlackCanCastleKingSide(), board.isBlackCanCastleQueenSide(),
				board.getEnPassantTargetSquare(), board.getHalfMoveClock(), board.getFullMoveClock(), move);
		states.push(state);
	}
	
	public State pop() {
		if (states.empty()) {
			return null;
		}
		return states.pop();
	}
	
	public State peek() {
		if (states.empty()) {
			return null;
		}
		return states.peek();
	}
	
	public int size() {
		return states.size();
	}
	
}
