package model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import interfaces.PieceLibrary;
import model.move.Move;
import model.move.MoveGenerator;

public class PieceInfoTest {
	
	private Board board = new Board();
	
	@Before
	public void setup() {
		board.clear();
	}
	
	@Test
	public void bishopMoves() {
		int location = 68;
		List<Move> possibleMoves = MoveGenerator.getPossibleMoves(PieceLibrary.WHITE_BISHOP, location, board);
		assertThat(possibleMoves.size(), is(13));

		assertThat(possibleMoves.get(0).getFrom(), is(location));
		assertThat(possibleMoves.get(0).getTo(), is(83));

		assertThat(possibleMoves.get(1).getFrom(), is(location));
		assertThat(possibleMoves.get(1).getTo(), is(98));

		assertThat(possibleMoves.get(2).getFrom(), is(location));
		assertThat(possibleMoves.get(2).getTo(), is(113));

		assertThat(possibleMoves.get(3).getFrom(), is(location));
		assertThat(possibleMoves.get(3).getTo(), is(85));

		assertThat(possibleMoves.get(4).getFrom(), is(location));
		assertThat(possibleMoves.get(4).getTo(), is(102));

		assertThat(possibleMoves.get(5).getFrom(), is(location));
		assertThat(possibleMoves.get(5).getTo(), is(119));

		assertThat(possibleMoves.get(6).getFrom(), is(location));
		assertThat(possibleMoves.get(6).getTo(), is(53));

		assertThat(possibleMoves.get(7).getFrom(), is(location));
		assertThat(possibleMoves.get(7).getTo(), is(38));

		assertThat(possibleMoves.get(8).getFrom(), is(location));
		assertThat(possibleMoves.get(8).getTo(), is(23));

		assertThat(possibleMoves.get(9).getFrom(), is(location));
		assertThat(possibleMoves.get(9).getTo(), is(51));

		assertThat(possibleMoves.get(10).getFrom(), is(location));
		assertThat(possibleMoves.get(10).getTo(), is(34));

		assertThat(possibleMoves.get(11).getFrom(), is(location));
		assertThat(possibleMoves.get(11).getTo(), is(17));

		assertThat(possibleMoves.get(12).getFrom(), is(location));
		assertThat(possibleMoves.get(12).getTo(), is(0));
	}

	@Test
	public void pawnMoves() {
		int location = 20;
		List<Move> possibleMoves = MoveGenerator.getPossibleMoves(PieceLibrary.WHITE_PAWN, location, board);
		assertThat(possibleMoves.size(), is(2));

		assertThat(possibleMoves.get(0).getFrom(), is(location));
		assertThat(possibleMoves.get(0).getTo(), is(36));

		assertThat(possibleMoves.get(1).getFrom(), is(location));
		assertThat(possibleMoves.get(1).getTo(), is(52));
	}

	@Test
	public void knightMoves() {
		int location = 67;
		List<Move> possibleMoves = MoveGenerator.getPossibleMoves(PieceLibrary.WHITE_KNIGHT, location, board);
		assertThat(possibleMoves.size(), is(8));

		assertThat(possibleMoves.get(0).getFrom(), is(location));
		assertThat(possibleMoves.get(0).getTo(), is(98));

		assertThat(possibleMoves.get(1).getFrom(), is(location));
		assertThat(possibleMoves.get(1).getTo(), is(100));

		assertThat(possibleMoves.get(2).getFrom(), is(location));
		assertThat(possibleMoves.get(2).getTo(), is(85));

		assertThat(possibleMoves.get(3).getFrom(), is(location));
		assertThat(possibleMoves.get(3).getTo(), is(53));

		assertThat(possibleMoves.get(4).getFrom(), is(location));
		assertThat(possibleMoves.get(4).getTo(), is(36));

		assertThat(possibleMoves.get(5).getFrom(), is(location));
		assertThat(possibleMoves.get(5).getTo(), is(34));

		assertThat(possibleMoves.get(6).getFrom(), is(location));
		assertThat(possibleMoves.get(6).getTo(), is(49));

		assertThat(possibleMoves.get(7).getFrom(), is(location));
		assertThat(possibleMoves.get(7).getTo(), is(81));
	}

	@Test
	public void kingMoves() {
		int location = 67;
		List<Move> possibleMoves = MoveGenerator.getPossibleMoves(PieceLibrary.WHITE_KING, location, board);
		assertThat(possibleMoves.size(), is(8));

		assertThat(possibleMoves.get(0).getFrom(), is(location));
		assertThat(possibleMoves.get(0).getTo(), is(66));

		assertThat(possibleMoves.get(1).getFrom(), is(location));
		assertThat(possibleMoves.get(1).getTo(), is(82));

		assertThat(possibleMoves.get(2).getFrom(), is(location));
		assertThat(possibleMoves.get(2).getTo(), is(83));

		assertThat(possibleMoves.get(3).getFrom(), is(location));
		assertThat(possibleMoves.get(3).getTo(), is(84));

		assertThat(possibleMoves.get(4).getFrom(), is(location));
		assertThat(possibleMoves.get(4).getTo(), is(68));

		assertThat(possibleMoves.get(5).getFrom(), is(location));
		assertThat(possibleMoves.get(5).getTo(), is(52));

		assertThat(possibleMoves.get(6).getFrom(), is(location));
		assertThat(possibleMoves.get(6).getTo(), is(51));

		assertThat(possibleMoves.get(7).getFrom(), is(location));
		assertThat(possibleMoves.get(7).getTo(), is(50));
	}

	@Test
	public void rookMoves() {
		int location = 67;
		List<Move> possibleMoves = MoveGenerator.getPossibleMoves(PieceLibrary.WHITE_ROOK, location, board);
		assertThat(possibleMoves.size(), is(14));

		assertThat(possibleMoves.get(0).getFrom(), is(location));
		assertThat(possibleMoves.get(0).getTo(), is(66));

		assertThat(possibleMoves.get(1).getFrom(), is(location));
		assertThat(possibleMoves.get(1).getTo(), is(65));

		assertThat(possibleMoves.get(2).getFrom(), is(location));
		assertThat(possibleMoves.get(2).getTo(), is(64));

		assertThat(possibleMoves.get(3).getFrom(), is(location));
		assertThat(possibleMoves.get(3).getTo(), is(83));

		assertThat(possibleMoves.get(4).getFrom(), is(location));
		assertThat(possibleMoves.get(4).getTo(), is(99));

		assertThat(possibleMoves.get(5).getFrom(), is(location));
		assertThat(possibleMoves.get(5).getTo(), is(115));

		assertThat(possibleMoves.get(6).getFrom(), is(location));
		assertThat(possibleMoves.get(6).getTo(), is(68));

		assertThat(possibleMoves.get(7).getFrom(), is(location));
		assertThat(possibleMoves.get(7).getTo(), is(69));

		assertThat(possibleMoves.get(8).getFrom(), is(location));
		assertThat(possibleMoves.get(8).getTo(), is(70));

		assertThat(possibleMoves.get(9).getFrom(), is(location));
		assertThat(possibleMoves.get(9).getTo(), is(71));

		assertThat(possibleMoves.get(10).getFrom(), is(location));
		assertThat(possibleMoves.get(10).getTo(), is(51));

		assertThat(possibleMoves.get(11).getFrom(), is(location));
		assertThat(possibleMoves.get(11).getTo(), is(35));

		assertThat(possibleMoves.get(12).getFrom(), is(location));
		assertThat(possibleMoves.get(12).getTo(), is(19));

		assertThat(possibleMoves.get(13).getFrom(), is(location));
		assertThat(possibleMoves.get(13).getTo(), is(3));
	}

	@Test
	public void queenMoves() {
		int location = 67;
		List<Move> possibleMoves = MoveGenerator.getPossibleMoves(PieceLibrary.WHITE_QUEEN, location, board);
		assertThat(possibleMoves.size(), is(27));

		assertThat(possibleMoves.get(0).getFrom(), is(location));
		assertThat(possibleMoves.get(0).getTo(), is(66));

		assertThat(possibleMoves.get(1).getFrom(), is(location));
		assertThat(possibleMoves.get(1).getTo(), is(65));

		assertThat(possibleMoves.get(2).getFrom(), is(location));
		assertThat(possibleMoves.get(2).getTo(), is(64));

		assertThat(possibleMoves.get(3).getFrom(), is(location));
		assertThat(possibleMoves.get(3).getTo(), is(82));

		assertThat(possibleMoves.get(4).getFrom(), is(location));
		assertThat(possibleMoves.get(4).getTo(), is(97));

		assertThat(possibleMoves.get(5).getFrom(), is(location));
		assertThat(possibleMoves.get(5).getTo(), is(112));

		assertThat(possibleMoves.get(6).getFrom(), is(location));
		assertThat(possibleMoves.get(6).getTo(), is(83));

		assertThat(possibleMoves.get(7).getFrom(), is(location));
		assertThat(possibleMoves.get(7).getTo(), is(99));

		assertThat(possibleMoves.get(8).getFrom(), is(location));
		assertThat(possibleMoves.get(8).getTo(), is(115));

		assertThat(possibleMoves.get(9).getFrom(), is(location));
		assertThat(possibleMoves.get(9).getTo(), is(84));

		assertThat(possibleMoves.get(10).getFrom(), is(location));
		assertThat(possibleMoves.get(10).getTo(), is(101));

		assertThat(possibleMoves.get(11).getFrom(), is(location));
		assertThat(possibleMoves.get(11).getTo(), is(118));

		assertThat(possibleMoves.get(12).getFrom(), is(location));
		assertThat(possibleMoves.get(12).getTo(), is(68));

		assertThat(possibleMoves.get(13).getFrom(), is(location));
		assertThat(possibleMoves.get(13).getTo(), is(69));

		assertThat(possibleMoves.get(14).getFrom(), is(location));
		assertThat(possibleMoves.get(14).getTo(), is(70));

		assertThat(possibleMoves.get(15).getFrom(), is(location));
		assertThat(possibleMoves.get(15).getTo(), is(71));

		assertThat(possibleMoves.get(16).getFrom(), is(location));
		assertThat(possibleMoves.get(16).getTo(), is(52));

		assertThat(possibleMoves.get(17).getFrom(), is(location));
		assertThat(possibleMoves.get(17).getTo(), is(37));

		assertThat(possibleMoves.get(18).getFrom(), is(location));
		assertThat(possibleMoves.get(18).getTo(), is(22));

		assertThat(possibleMoves.get(19).getFrom(), is(location));
		assertThat(possibleMoves.get(19).getTo(), is(7));

		assertThat(possibleMoves.get(20).getFrom(), is(location));
		assertThat(possibleMoves.get(20).getTo(), is(51));

		assertThat(possibleMoves.get(21).getFrom(), is(location));
		assertThat(possibleMoves.get(21).getTo(), is(35));

		assertThat(possibleMoves.get(22).getFrom(), is(location));
		assertThat(possibleMoves.get(22).getTo(), is(19));

		assertThat(possibleMoves.get(23).getFrom(), is(location));
		assertThat(possibleMoves.get(23).getTo(), is(3));

		assertThat(possibleMoves.get(24).getFrom(), is(location));
		assertThat(possibleMoves.get(24).getTo(), is(50));

		assertThat(possibleMoves.get(25).getFrom(), is(location));
		assertThat(possibleMoves.get(25).getTo(), is(33));

		assertThat(possibleMoves.get(26).getFrom(), is(location));
		assertThat(possibleMoves.get(26).getTo(), is(16));
	}

}
