package model;

public class CastleMove extends Move {

	private Move rookMove;

	public CastleMove(int from, int to, int piece, Move rookMove) {
		super(from, to, piece);
		this.rookMove = rookMove;
	}
	
	public Move getRookMove() {
		return rookMove;
	}

}
