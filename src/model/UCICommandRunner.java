package model;

public class UCICommandRunner {
	
	public static void runPosition(Board board, String command) {
		String[] tokens = command.split("\\s", 2);
		if (tokens[0].equalsIgnoreCase("fen")) {
			String[] temp = tokens[1].split("moves");
			board.importFEN(temp[0]);
			if (temp.length > 1) {
				String[] moves = temp[1].split("\\s");
			}
		} else if (tokens[0].equalsIgnoreCase("startpos")){
			String[] temp = tokens[1].split("moves");
			String[] moves = temp[1].split("\\s");
		}
	}

}
