package view;

public class CastleMove extends Move {
	
	private Move rookMove;

	public CastleMove(Square from, Square to, Move rookMove) {
		super(from, to);
		this.rookMove = rookMove;
	}
	
	public Move getRookMove() {
		return rookMove;
	}

}
