package Movements;

import ChessGameClasses.Board;
import java.util.ArrayList;

public class LeftRightCaptureMovement implements Movement{
	//posRow and posCol are the row and column that the chess piece is currently in.

	public static final String movementName = "Left Right Capture";
	public static final int movementCost = 200;

	@Override
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean moved, Board board){
		//Create an empty ArrayList<ArrayList<Character>> to store the possible moves
		ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
		
		//Logic
		final int range = 1;
		int currentRow = posRow;
		int currentColumn = posCol;
		
		if (color.equals("White")) currentRow -= range; //Assuming White is on the bottom
		else currentRow += range;
		
		//Left side
		checkPosition(color, board, result, currentRow, currentColumn - 1);
		
		//Right side
		checkPosition(color, board, result, currentRow, currentColumn + 1);
		
		//To mark itself
		result.get(posRow).set(posCol, currentPositionSymbol); 
		return result;
	}
	
	public void checkPosition(String color, Board board, ArrayList<ArrayList<Character>> result, int row, int column){
		//Determine the ally and enemy symbols depending on the color
		ArrayList<Character> pieceSymbols = getSymbols(color);
		Character enemyKingSymbol = pieceSymbols.get(2);
		Character enemyPieceSymbol = pieceSymbols.get(3);
		
		int currentRow = row;
		int currentColumn = column;
		
		//If the position is not out of range
		if (currentRow >= 0 && currentRow < board.getPositionBoard().size() &&
				currentColumn >= 0 && currentColumn < board.getPositionBoard().get(currentRow).size()){
			Character currentCharacter = board.getPositionBoard().get(currentRow).get(currentColumn);
			
			//If position is occupied by enemy, it can capture. Otherwise it can't move nor capture there.
			if (currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol))
				result.get(currentRow).set(currentColumn, moveAndCaptureSymbol); 
			else
				result.get(currentRow).set(currentColumn, invalidMoveSymbol); 
		}

	}

	@Override
	public String getMovementName() {
		return movementName;
	}

	@Override
	public int getMovementCost() {
		return movementCost;
	}
}
