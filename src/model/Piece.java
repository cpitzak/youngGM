package model;

import interfaces.PieceLibrary;

import java.util.ArrayList;
import java.util.List;

public class Piece {

	private final int piece;
	private int location;

	public Piece(int piece, int location) {
		this.piece = piece;
		this.location = location;
	}

	public int getPiece() {
		return piece;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public List<Move> getPossibleMoves() {
		List<Move> moves = new ArrayList<Move>();
		if (piece == PieceLibrary.WHITE_BISHOP || piece == PieceLibrary.BLACK_BISHOP) {
			// i = 0 is up left, etc... as mentioned in PieceLibrary.BISHOP_MOVE_DELTA
			for (int i = 0; i < PieceLibrary.BISHOP_MOVE_DELTA.length; i++) {
				int to = location + PieceLibrary.BISHOP_MOVE_DELTA[i];
				while ((to & 0x88) == 0) {
					moves.add(new Move(location, to, piece));
					to += PieceLibrary.BISHOP_MOVE_DELTA[i];
				}
			}
		} else if (piece == PieceLibrary.WHITE_PAWN || piece == PieceLibrary.BLACK_PAWN) {
			for (int i = 0; i < PieceLibrary.PAWN_MOVE_DELTA.length; i++) {
				int to = location + PieceLibrary.PAWN_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(location, to, piece));
				}
			}
		} else if (piece == PieceLibrary.WHITE_KNIGHT || piece == PieceLibrary.BLACK_KNIGHT) {
			for (int i = 0; i < PieceLibrary.KNIGHT_MOVE_DELTA.length; i++) {
				int to = location + PieceLibrary.KNIGHT_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(location, to, piece));
				}
			}
		} else if (piece == PieceLibrary.WHITE_KING || piece == PieceLibrary.BLACK_KING) {
			for (int i = 0; i < PieceLibrary.KING_MOVE_DELTA.length; i++) {
				int to = location + PieceLibrary.KING_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(location, to, piece));
				}
			}
		} else if (piece == PieceLibrary.WHITE_ROOK || piece == PieceLibrary.BLACK_ROOK) {
			for (int i = 0; i < PieceLibrary.ROOK_MOVE_DELTA.length; i++) {
				int from = location;
				int to = from + PieceLibrary.ROOK_MOVE_DELTA[i];
				while ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
					to += PieceLibrary.ROOK_MOVE_DELTA[i];
				}
			}
		} else if (piece == PieceLibrary.WHITE_QUEEN || piece == PieceLibrary.BLACK_QUEEN) {
			for (int i = 0; i < PieceLibrary.QUEEN_MOVE_DELTA.length; i++) {
				int to = location + PieceLibrary.QUEEN_MOVE_DELTA[i];
				while ((to & 0x88) == 0) {
					moves.add(new Move(location, to, piece));
					to += PieceLibrary.QUEEN_MOVE_DELTA[i];
				}
			}
		} else {
			throw new IllegalArgumentException("ERROR: tried to find possible moves with an undefined piece");
		}
		return moves;
	}

}
