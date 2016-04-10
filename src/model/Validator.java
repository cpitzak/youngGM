package model;

import java.util.List;

import interfaces.PieceLibrary;

public class Validator {

	public static boolean isValidMove(Move move, Board board) {
		boolean isValid = false;
		List<Move> moves = MoveGenerator.getPossibleMoves(move.getPiece(), move.getFrom(), board);
		for (Move m : moves) {
			if (m instanceof CastleMove && move instanceof CastleMove) {
				CastleMove castleMove = (CastleMove) move;
				if (canCastle(castleMove, board)) {
					isValid = true;
					break;
				}
			} else if (m instanceof EnPassantMove) {

			} else if (m.getTo() == move.getTo()) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}

	public static boolean canCastle(int from, int to, int piece, Board board) {
		Integer[] intBoard = board.getBoard();
		boolean canCastle = false;
		if (piece == PieceLibrary.WHITE_KING) {
			if (board.isWhiteCanCastleKingSide() && from == Board.E1 && to == Board.G1 && intBoard[Board.E1] != null
					&& intBoard[Board.E1] == PieceLibrary.WHITE_KING && intBoard[Board.H1] != null
					&& intBoard[Board.H1] == PieceLibrary.WHITE_ROOK && intBoard[Board.F1] == null
					&& intBoard[Board.G1] == null) {
				canCastle = true;
			} else if (board.isWhiteCanCastleQueenSide() && from == Board.E1 && to == Board.C1
					&& intBoard[Board.E1] != null && intBoard[Board.E1] == PieceLibrary.WHITE_KING
					&& intBoard[Board.A1] != null && intBoard[Board.A1] == PieceLibrary.WHITE_ROOK
					&& intBoard[Board.B1] == null && intBoard[Board.C1] == null && intBoard[Board.D1] == null) {
				canCastle = true;
			}
		} else if (piece == PieceLibrary.BLACK_KING) {
			if (board.isBlackCanCastleKingSide() && from == Board.E8 && to == Board.G8 && intBoard[Board.E8] != null
					&& intBoard[Board.E8] == PieceLibrary.BLACK_KING && intBoard[Board.H8] != null
					&& intBoard[Board.H8] == PieceLibrary.BLACK_ROOK && intBoard[Board.F8] == null
					&& intBoard[Board.G8] == null) {
				canCastle = true;
			} else if (board.isBlackCanCastleQueenSide() && from == Board.E8 && to == Board.C8
					&& intBoard[Board.E8] != null && intBoard[Board.E8] == PieceLibrary.BLACK_KING
					&& intBoard[Board.A8] != null && intBoard[Board.A8] == PieceLibrary.BLACK_ROOK
					&& intBoard[Board.B8] == null && intBoard[Board.C8] == null && intBoard[Board.D8] == null) {
				canCastle = true;
			}
		}
		return canCastle;
	}

	public static boolean canCastle(CastleMove move, Board board) {
		Integer[] intBoard = board.getBoard();
		boolean canCastle = false;
		if (move.isKingSideWhite() && board.isWhiteCanCastleKingSide() && intBoard[Board.E1] != null
				&& intBoard[Board.E1] == PieceLibrary.WHITE_KING && intBoard[Board.H1] != null
				&& intBoard[Board.H1] == PieceLibrary.WHITE_ROOK && intBoard[Board.F1] == null
				&& intBoard[Board.G1] == null) {
			canCastle = true;
		} else if (move.isKingSideBlack() && board.isBlackCanCastleKingSide() && intBoard[Board.E8] != null
				&& intBoard[Board.E8] == PieceLibrary.BLACK_KING && intBoard[Board.H8] != null
				&& intBoard[Board.H8] == PieceLibrary.BLACK_ROOK && intBoard[Board.F8] == null
				&& intBoard[Board.G8] == null) {
			canCastle = true;
		} else if (move.isQueenSideWhite() && board.isWhiteCanCastleQueenSide() && intBoard[Board.E1] != null
				&& intBoard[Board.E1] == PieceLibrary.WHITE_KING && intBoard[Board.A1] != null
				&& intBoard[Board.A1] == PieceLibrary.WHITE_ROOK && intBoard[Board.B1] == null
				&& intBoard[Board.C1] == null && intBoard[Board.D1] == null) {
			canCastle = true;
		} else if (move.isQueenSideBlack() && board.isBlackCanCastleQueenSide() && intBoard[Board.E8] != null
				&& intBoard[Board.E8] == PieceLibrary.BLACK_KING && intBoard[Board.A8] != null
				&& intBoard[Board.A8] == PieceLibrary.BLACK_ROOK && intBoard[Board.B8] == null
				&& intBoard[Board.C8] == null && intBoard[Board.D8] == null) {
			canCastle = true;
		}
		return canCastle;
	}

}
