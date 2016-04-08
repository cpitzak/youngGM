package model;

import java.util.ArrayList;
import java.util.List;

import interfaces.PieceLibrary;

public class MoveGenerator {

	public static List<Move> getPossibleMoves(Integer piece, int from, Integer[] board) {
		List<Move> moves = new ArrayList<Move>();
		if (piece == PieceLibrary.WHITE_BISHOP) {
			moves = getSliderMoves(piece, from, board, true, PieceLibrary.BISHOP_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_BISHOP) {
			moves = getSliderMoves(piece, from, board, false, PieceLibrary.BISHOP_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_ROOK) {
			moves = getSliderMoves(piece, from, board, true, PieceLibrary.ROOK_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_ROOK) {
			moves = getSliderMoves(piece, from, board, false, PieceLibrary.ROOK_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_QUEEN) {
			moves = getSliderMoves(piece, from, board, true, PieceLibrary.QUEEN_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_QUEEN) {
			moves = getSliderMoves(piece, from, board, false, PieceLibrary.QUEEN_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_KNIGHT) {
			moves = getSingleMoves(piece, from, board, true, PieceLibrary.KNIGHT_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_KNIGHT) {
			moves = getSingleMoves(piece, from, board, false, PieceLibrary.KNIGHT_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_KING) {
			moves = getSingleMoves(piece, from, board, true, PieceLibrary.KING_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_KING) {
			moves = getSingleMoves(piece, from, board, false, PieceLibrary.KING_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_PAWN) {
			for (int i = 0; i < PieceLibrary.PAWN_MOVE_DELTA.length; i++) {
				int to = from + PieceLibrary.PAWN_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
				}
			}
		} else if (piece == PieceLibrary.BLACK_PAWN) {
			for (int i = 0; i < PieceLibrary.PAWN_MOVE_DELTA.length; i++) {
				int to = from - PieceLibrary.PAWN_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
				}
			}
		} else {
			throw new IllegalArgumentException("ERROR: tried to find possible moves with an undefined piece");
		}
		return moves;
	}

	private static List<Move> getSliderMoves(Integer piece, int from, Integer[] board, boolean isWhite, int[] delta) {
		List<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < delta.length; i++) {
			int to = from + delta[i];
			while ((to & 0x88) == 0
					&& (!isWhite && !PieceLibrary.isBlack(board[to]) || isWhite && !PieceLibrary.isWhite(board[to]))) {
				moves.add(new Move(from, to, piece));
				if (isAttack(piece, to, board)) {
					break;
				}
				to += delta[i];
			}
		}
		return moves;
	}

	private static List<Move> getSingleMoves(Integer piece, int from, Integer[] board, boolean isWhite, int[] delta) {
		List<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < delta.length; i++) {
			int to = from + delta[i];
			if ((to & 0x88) == 0
					&& (!isWhite && !PieceLibrary.isBlack(board[to]) || isWhite && !PieceLibrary.isWhite(board[to]))) {
				moves.add(new Move(from, to, piece));
			}
		}
		return moves;
	}

	private static boolean isAttack(Integer piece, Integer to, Integer[] board) {
		return PieceLibrary.isWhite(piece) && PieceLibrary.isBlack(board[to])
				|| PieceLibrary.isBlack(piece) && PieceLibrary.isWhite(board[to]);
	}

}
