package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.Board;

/**
 * Code from:
 * http://stackoverflow.com/questions/21142686/making-a-robust-resizable-swing-
 * chess-gui
 * 
 * Modified by Clint
 */
public class ChessGUI implements Observer, ActionListener {

	private Controller controller;
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Image[][] chessPieceImages = new Image[2][6];
	private JPanel chessBoard;
	private final JLabel message = new JLabel("Chess Champ is ready to play!");
	private static final String COLS = "ABCDEFGH";
	public static final int KING = 0, QUEEN = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
	public static final int[] STARTING_ROW = { ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK };
	public static final int BLACK = 0, WHITE = 1;
	private static final String PAWN_BLACK = "PAWN_BLACK";
	private static final String QUEEN_BLACK = "QUEEN_BLACK";
	private static final String KING_BLACK = "KING_BLACK";
	private static final String ROOK_BLACK = "ROOK_BLACK";
	private static final String KNIGHT_BLACK = "KNIGHT_BLACK";
	private static final String BISHOP_BLACK = "BISHOP_BLACK";

	private static final String PAWN_WHITE = "PAWN_WHITE";
	private static final String QUEEN_WHITE = "QUEEN_WHITE";
	private static final String KING_WHITE = "KING_WHITE";
	private static final String ROOK_WHITE = "ROOK_WHITE";
	private static final String KNIGHT_WHITE = "KNIGHT_WHITE";
	private static final String BISHOP_WHITE = "BISHOP_WHITE";
	
	private static final String WHITE_STRING = "WHITE";
	private static final String BLACK_STRING = "BLACK";

	private JButton fromPieceButton;
	private ImageIcon transparentIcon;

	public ChessGUI() {
		initializeGui();
	}

	public final void initializeGui() {
		transparentIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		transparentIcon.setDescription("");
		// create the images for the chess pieces
		createImages();

		// set up the main GUI
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		Action newGameAction = new AbstractAction("New") {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.resetBoard();
				setupNewGame();
			}
		};
		tools.add(newGameAction);
		tools.add(new JButton("Save")); // TODO - add functionality!
		tools.add(new JButton("Restore")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(new JButton("Resign")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(message);

		gui.add(new JLabel("?"), BorderLayout.LINE_START);

		chessBoard = new JPanel(new GridLayout(0, 9)) {

			/**
			 * Override the preferred size to return the largest it can, in a
			 * square shape. Must (must, must) be added to a GridBagLayout as
			 * the only component (it uses the parent as a guide to size) with
			 * no GridBagConstaint (so it is centered).
			 */
			@Override
			public final Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				Dimension prefSize = null;
				Component c = getParent();
				if (c == null) {
					prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
				} else if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
					prefSize = c.getSize();
				} else {
					prefSize = d;
				}
				int w = (int) prefSize.getWidth();
				int h = (int) prefSize.getHeight();
				// the smaller of the two sizes
				int s = (w > h ? h : w);
				return new Dimension(s, s);
			}
		};
		chessBoard.setBorder(new CompoundBorder(new EmptyBorder(8, 8, 8, 8), new LineBorder(Color.BLACK)));
		// Set the BG to be ochre
		Color ochre = new Color(204, 119, 34);
		chessBoard.setBackground(ochre);
		JPanel boardConstrain = new JPanel(new GridBagLayout());
		boardConstrain.setBackground(ochre);
		boardConstrain.add(chessBoard);
		gui.add(boardConstrain);

		// create the chess board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
				JButton b = new JButton();
				b.addActionListener(this);
				b.setMargin(buttonMargin);
				// our chess pieces are 64x64 px in size, so we'll
				// 'fill this in' using a transparent icon..
				// ImageIcon icon = new ImageIcon(
				// new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(transparentIcon);
				if ((jj % 2 == 1 && ii % 2 == 1)
						// ) {
						|| (jj % 2 == 0 && ii % 2 == 0)) {
					b.setBackground(Color.WHITE);
				} else {
					b.setBackground(Color.BLACK);
				}
				chessBoardSquares[jj][ii] = b;
			}
		}

		/*
		 * fill the chess board
		 */
		chessBoard.add(new JLabel(""));
		// fill the top row
		for (int ii = 0; ii < 8; ii++) {
			chessBoard.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
		}
		// fill the black non-pawn piece row
		for (int ii = 0; ii < 8; ii++) {
			for (int jj = 0; jj < 8; jj++) {
				switch (jj) {
				case 0:
					chessBoard.add(new JLabel("" + (9 - (ii + 1)), SwingConstants.CENTER));
				default:
					chessBoard.add(chessBoardSquares[jj][ii]);
				}
			}
		}
	}

	public final JComponent getGui() {
		return gui;
	}

	private final void createImages() {
		try {
			// URL url = new URL("http://i.stack.imgur.com/memI0.png");
			BufferedImage bi = ImageIO.read(new File("memI0.png"));
			for (int ii = 0; ii < 2; ii++) {
				for (int jj = 0; jj < 6; jj++) {
					chessPieceImages[ii][jj] = bi.getSubimage(jj * 64, ii * 64, 64, 64);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes the icons of the initial chess board piece places
	 */
	private final void setupNewGame() {
		message.setText("Make your move!");
		for (int row = 0; row < chessBoardSquares.length; row++) {
			for (int col = 0; col < chessBoardSquares[0].length; col++) {
				chessBoardSquares[row][col].setIcon(transparentIcon);
			}
		}
		// set up the black pieces
		for (int i = 0; i < STARTING_ROW.length; i++) {
			ImageIcon blackStartingRow = new ImageIcon(chessPieceImages[BLACK][STARTING_ROW[i]]);
			blackStartingRow.setDescription(pieceNumToName(BLACK, STARTING_ROW[i]));
			chessBoardSquares[i][0].setIcon(blackStartingRow);
		}
		for (int i = 0; i < STARTING_ROW.length; i++) {
			ImageIcon blackPawnIcon = new ImageIcon(chessPieceImages[BLACK][PAWN]);
			blackPawnIcon.setDescription(PAWN_BLACK);
			chessBoardSquares[i][1].setIcon(blackPawnIcon);
		}
		// set up the white pieces
		for (int i = 0; i < STARTING_ROW.length; i++) {
			ImageIcon whitePawn = new ImageIcon(chessPieceImages[WHITE][PAWN]);
			whitePawn.setDescription(PAWN_WHITE);
			chessBoardSquares[i][6].setIcon(whitePawn);
		}
		for (int i = 0; i < STARTING_ROW.length; i++) {
			ImageIcon whiteStartingRow = new ImageIcon(chessPieceImages[WHITE][STARTING_ROW[i]]);
			whiteStartingRow.setDescription(pieceNumToName(WHITE, STARTING_ROW[i]));
			chessBoardSquares[i][7].setIcon(whiteStartingRow);
		}
	}

	private String pieceNumToName(int color, int piece) {
		if (piece == QUEEN) {
			return color == 1 ? QUEEN_WHITE : QUEEN_BLACK;
		} else if (piece == KING) {
			return color == 1 ? KING_WHITE : KING_BLACK;
		} else if (piece == ROOK) {
			return color == 1 ? ROOK_WHITE : ROOK_BLACK;
		} else if (piece == KNIGHT) {
			return color == 1 ? KNIGHT_WHITE : KNIGHT_BLACK;
		} else if (piece == BISHOP) {
			return color == 1 ? BISHOP_WHITE : BISHOP_BLACK;
		}
		throw new IllegalStateException("error in pieceNumToName in GUI");
	}

	public static void main(String[] args) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				Board model = new Board();
				ChessGUI view = new ChessGUI();
				model.addObserver(view);
				Controller controller = new Controller();
				controller.addModel(model);
				controller.addView(view);
				view.addController(controller);

				JFrame f = new JFrame("ChessChamp");
				f.add(view.getGui());
				// Ensures JVM closes after frame(s) closed and
				// all non-daemon threads are finished
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// See http://stackoverflow.com/a/7143398/418556 for demo.
				f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				f.pack();
				// ensures the minimum size is enforced.
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		// Swing GUIs should be created and updated on the EDT
		// http://docs.oracle.com/javase/tutorial/uiswing/concurrency
		SwingUtilities.invokeLater(r);
	}

	public void addController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			System.out.println("Receieved command: " + arg);
		}
	}

	public boolean isValidMove(int row, int col, JButton toPiece) {
		if (fromPieceButton == null) {
			return false;
		}
		ImageIcon icon = (ImageIcon) toPiece.getIcon();
		String toDescription = icon.getDescription();
		ImageIcon fromIcon = (ImageIcon) fromPieceButton.getIcon();
		String fromDescription = fromIcon.getDescription();
		if ((toDescription.contains(WHITE_STRING) && fromDescription.contains(WHITE_STRING)) ||
				(toDescription.contains(BLACK_STRING) && fromDescription.contains(BLACK_STRING))) {
			return false;
		}
		return true;
	}

	private void setFromPiece(JButton button) {
		if (fromPieceButton == null) {
			this.fromPieceButton = button;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("button pressed");
		if (e.getSource() instanceof JButton) {
			boolean didSelect = false;
			for (int row = 0; row < chessBoardSquares.length; row++) {
				for (int col = 0; col < chessBoardSquares[0].length; col++) {
					JButton button = chessBoardSquares[row][col];
					if (button == e.getSource()) {
						System.out.println("row: " + row + ", col: " + col);
						didSelect = makeMove(button, row, col);
						break;
					}
				}
				if (didSelect) {
					break;
				}
			}
		}
	}

	private boolean makeMove(JButton toPieceButton, int row, int col) {
		boolean didSelect = false;
		ImageIcon icon = (ImageIcon) toPieceButton.getIcon();
		boolean isValidMove = isValidMove(row, col, toPieceButton);
		if (fromPieceButton != null) {
			if (fromPieceButton == toPieceButton) {
				fromPieceButton = null;
				didSelect = true;
			} else if (isValidMove){
				toPieceButton.setIcon(fromPieceButton.getIcon());
				fromPieceButton.setIcon(transparentIcon);
				fromPieceButton = null;
				didSelect = true;
			} else {
				fromPieceButton = null;
			}
		} else {
			String description = icon.getDescription();
			if (!description.isEmpty()) {
				if (description.equals(PAWN_BLACK)) {
					System.out.println(PAWN_BLACK);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(QUEEN_BLACK)) {
					System.out.println(QUEEN_BLACK);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(KING_BLACK)) {
					System.out.println(KING_BLACK);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(ROOK_BLACK)) {
					System.out.println(ROOK_BLACK);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(BISHOP_BLACK)) {
					System.out.println(BISHOP_BLACK);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(KNIGHT_BLACK)) {
					System.out.println(KNIGHT_BLACK);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(PAWN_WHITE)) {
					System.out.println(PAWN_WHITE);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(QUEEN_WHITE)) {
					System.out.println(QUEEN_WHITE);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(KING_WHITE)) {
					System.out.println(KING_WHITE);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(ROOK_WHITE)) {
					System.out.println(ROOK_WHITE);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(BISHOP_WHITE)) {
					System.out.println(BISHOP_WHITE);
					setFromPiece(toPieceButton);
					didSelect = true;
				} else if (description.equals(KNIGHT_WHITE)) {
					System.out.println(KNIGHT_WHITE);
					setFromPiece(toPieceButton);
					didSelect = true;
				}
			}
		}
		return didSelect;
	}

}
