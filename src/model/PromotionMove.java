package model;

public class PromotionMove extends Move {
	
	private int promotionPiece;
	
	public PromotionMove(int from, int to, int piece, int promotionPiece) {
		super(from, to, piece);
		this.promotionPiece = promotionPiece;
	}
	
	public int getPromotionPiece() {
		return promotionPiece;
	}

}
