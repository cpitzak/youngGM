package model;

import org.apache.log4j.Logger;

import interfaces.UCICommands;

public class UCICommandRunner {
	
	final static Logger logger = Logger.getLogger(UCICommandRunner.class);
	
	public static void runPosition(Board board, String command) {
		String[] tokens = command.split("\\s", 2);
		if (tokens[0].equalsIgnoreCase(UCICommands.FEN)) {
			String[] temp = tokens[1].split(UCICommands.MOVES);
			board.importFEN(temp[0]);
			if (temp.length > 1) {
				String[] moves = temp[1].split("\\s");
			}
		} else if (tokens[0].equalsIgnoreCase(UCICommands.START_POS)){
			logger.warn(UCICommands.GO + " is not yet implemented");
			String[] temp = tokens[1].split(UCICommands.MOVES);
			String[] moves = temp[1].split("\\s");
		}
	}

}
