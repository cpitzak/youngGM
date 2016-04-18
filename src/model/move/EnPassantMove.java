package model.move;

public class EnPassantMove extends Move {
	
	// the square that will be cleared
	private int targetSquare;

	public EnPassantMove(int from, int to, int piece, int targetSquare, Integer pieceCaptured) {
		super(from, to, piece);
		this.targetSquare = targetSquare;
		this.setPieceCaptured(pieceCaptured);
	}
	
	public int getTargetSquare() {
		return targetSquare;
	}

}
