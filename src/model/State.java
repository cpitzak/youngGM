package model;

import model.move.Move;

public class State {
	
	private boolean isWhitesTurn;
	private boolean whiteCanCastleKingSide;
	private boolean whiteCanCastleQueenSide;
	private boolean blackCanCastleKingSide;
	private boolean blackCanCastleQueenSide;
	private String enPassantTargetSquare;
	private int halfMoveClock;
	private int fullMoveClock;
	private Move move;
	
	public State(boolean isWhitesTurn, boolean whiteCanCastleKingSide, boolean whiteCanCastleQueenSide,
			boolean blackCanCastleKingSide, boolean blackCanCastleQueenSide, String enPassantTargetSquare,
			int halfMoveClock, int fullMoveClock, Move move) {
		this.isWhitesTurn = isWhitesTurn;
		this.whiteCanCastleKingSide = whiteCanCastleKingSide;
		this.whiteCanCastleQueenSide = whiteCanCastleQueenSide;
		this.blackCanCastleKingSide = blackCanCastleKingSide;
		this.blackCanCastleQueenSide = blackCanCastleQueenSide;
		this.enPassantTargetSquare = enPassantTargetSquare;
		this.halfMoveClock = halfMoveClock;
		this.fullMoveClock = fullMoveClock;
		this.move = move;
	}
	
	public boolean isWhitesTurn() {
		return isWhitesTurn;
	}
	public boolean isWhiteCanCastleKingSide() {
		return whiteCanCastleKingSide;
	}
	public boolean isWhiteCanCastleQueenSide() {
		return whiteCanCastleQueenSide;
	}
	public boolean isBlackCanCastleKingSide() {
		return blackCanCastleKingSide;
	}
	public boolean isBlackCanCastleQueenSide() {
		return blackCanCastleQueenSide;
	}
	public String getEnPassantTargetSquare() {
		return enPassantTargetSquare;
	}
	public int getHalfMoveClock() {
		return halfMoveClock;
	}
	public int getFullMoveClock() {
		return fullMoveClock;
	}
	
	public Move getMove() {
		return move;
	}
	
	

}
