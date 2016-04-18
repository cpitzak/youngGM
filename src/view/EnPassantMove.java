package view;

public class EnPassantMove extends Move {
	
	private Square targetSquare;

	public EnPassantMove(Square from, Square to, Square targetSquare) {
		super(from, to);
		this.targetSquare = targetSquare;
	}

	public Square getTargetSquare() {
		return targetSquare;
	}
	
}
