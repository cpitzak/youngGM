package model.move;

import interfaces.PieceLibrary;
import model.Board;

public class CastleMove extends Move {

	private Move rookMove;

	public CastleMove(int from, int to, int piece, Move rookMove) {
		super(from, to, piece);
		this.rookMove = rookMove;
	}

	public Move getRookMove() {
		return rookMove;
	}

	public boolean isKingSideWhite() {
		return piece == PieceLibrary.WHITE_KING && rookMove.getPiece() == PieceLibrary.WHITE_ROOK && from == Board.E1
				&& to == Board.G1 && rookMove.getFrom() == Board.H1 && rookMove.getTo() == Board.F1;
	}

	public boolean isKingSideBlack() {
		return piece == PieceLibrary.BLACK_KING && rookMove.getPiece() == PieceLibrary.BLACK_ROOK && from == Board.E8
				&& to == Board.G8 && rookMove.getFrom() == Board.H8 && rookMove.getTo() == Board.F8;
	}

	public boolean isQueenSideWhite() {
		return piece == PieceLibrary.WHITE_KING && rookMove.getPiece() == PieceLibrary.WHITE_ROOK && from == Board.E1
				&& to == Board.C1 && rookMove.getFrom() == Board.A1 && rookMove.getTo() == Board.D1;
	}

	public boolean isQueenSideBlack() {
		return piece == PieceLibrary.BLACK_KING && rookMove.getPiece() == PieceLibrary.BLACK_ROOK && from == Board.E8
				&& to == Board.C8 && rookMove.getFrom() == Board.A8 && rookMove.getTo() == Board.D8;
	}

}
