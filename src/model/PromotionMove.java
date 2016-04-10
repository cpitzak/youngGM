package model;

public class PromotionMove extends Move {
	
	private Integer promotionPiece;
	
	public PromotionMove(int from, int to, int piece, Integer promotionPiece) {
		super(from, to, piece);
		this.promotionPiece = promotionPiece;
	}
	
	public Integer getPromotionPiece() {
		return promotionPiece;
	}

}
