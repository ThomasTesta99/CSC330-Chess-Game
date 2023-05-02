package Movements;

import ChessGameClasses.Board;
import java.util.ArrayList;

public class LMovement implements Movement{
	//posRow and posCol are the row and column that the chess piece is currently in.

	public static final String movementName = "L-Movement";
	public static final int movementCost = 500;

	@Override
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color,boolean moved, Board board){
		//Create an empty ArrayList<ArrayList<Character>> to store the possible moves
		ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
		
		//Logic
		//First row
		checkPosition(posRow, posCol, color, board, result, -2, -1);
		checkPosition(posRow, posCol, color, board, result, -2, 1); 
		
		//Second row
		checkPosition(posRow, posCol, color, board, result, -1, -2);
		checkPosition(posRow, posCol, color, board, result, -1, 2);
		
		//Third row
		checkPosition(posRow, posCol, color, board, result, 1, -2);
		checkPosition(posRow, posCol, color, board, result, 1, 2);
		
		//Fourth Row
		checkPosition(posRow, posCol, color, board, result, 2, -1);
		checkPosition(posRow, posCol, color, board, result, 2, 1);
		
		//To mark itself
		result.get(posRow).set(posCol, currentPositionSymbol); 
		return result;
	}
	public void checkPosition(int posRow, int posCol, String color, Board board, ArrayList<ArrayList<Character>> result, int rowShift, int columnShift) {
		//Determine the ally and enemy symbols depending on the color
		ArrayList<Character> pieceSymbols = getSymbols(color);
		Character allyKingSymbol = pieceSymbols.get(0);
		Character allyPieceSymbol = pieceSymbols.get(1);
		Character enemyKingSymbol = pieceSymbols.get(2);
		Character enemyPieceSymbol = pieceSymbols.get(3);
		
		int currentRow = posRow + rowShift;
		int currentColumn = posCol + columnShift;
		
		//If the position is not out of range
		if (currentRow >= 0 && currentRow < board.getPositionBoard().size() &&
				currentColumn >= 0 && currentColumn < board.getPositionBoard().get(currentRow).size()){
			Character currentCharacter = board.getPositionBoard().get(currentRow).get(currentColumn);
			
			//If position is occupied by ally, can't move there.
			if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol))
				result.get(currentRow).set(currentColumn, invalidMoveSymbol);
			
			//If position is occupied by enemy, can move&capture.
			else if (currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol))
				result.get(currentRow).set(currentColumn, moveAndCaptureSymbol); 
			
			//If position is empty, can move there
			else
				result.get(currentRow).set(currentColumn, moveSymbol); 
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
