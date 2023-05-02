package Movements;

import ChessGameClasses.Board;
import java.util.ArrayList;

public class AdvanceMovement implements Movement{
	//posRow and posCol are the row and column that the chess piece is currently in.z

	public static final String movementName = "Advance Movement";
	public static final int movementCost = 100;

	@Override
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean moved, Board board){
		//Create an empty ArrayList<ArrayList<Character>> to store the possible moves
		ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
		
		//Determine the ally and enemy symbols depending on the color
		ArrayList<Character> pieceSymbols = getSymbols(color);
		Character allyKingSymbol = pieceSymbols.get(0);
		Character allyPieceSymbol = pieceSymbols.get(1);
		Character enemyKingSymbol = pieceSymbols.get(2);
		Character enemyPieceSymbol = pieceSymbols.get(3);
		
		//Logic
		int currentRow = posRow;
		if (color.equals("White")) currentRow--; //White always starts from the bottom
		else currentRow++;
		
		int currentColumn = posCol;
		
		//If the position is not out of range
		if (currentRow >= 0 && currentRow < board.getPositionBoard().size() &&
				currentColumn >= 0 && currentColumn < board.getPositionBoard().get(currentRow).size())
		{
			Character currentCharacter = board.getPositionBoard().get(currentRow).get(currentColumn);
			
			//If position is occupied by ally or enemy, can't move there.
			if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol) ||
					currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol))
				result.get(currentRow).set(currentColumn, invalidMoveSymbol);
			
			//If position is empty, can move there
			else
				result.get(currentRow).set(currentColumn, moveSymbol); 
		}
		
		//To mark itself
		result.get(posRow).set(posCol, currentPositionSymbol); 
		return result;
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
