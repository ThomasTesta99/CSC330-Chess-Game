package Movements;

import ChessGameClasses.Board;
import java.util.ArrayList;

public class RestrictedTeleportationMovement implements Movement{
	//posRow and posCol are the row and column that the chess piece is currently in.

	public static final String movementName = "Restricted Teleportation";
	public static final int movementCost = 1200;

	@Override
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean moved, Board board){
		//Create an empty ArrayList<ArrayList<Character>> to store the possible moves
		ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
		
		
		//Logic
		
		//Up
		restrictedTeleportInDirection(posRow, posCol, color, board, result, -1, 0);
		
		//Down
		restrictedTeleportInDirection(posRow, posCol, color, board, result, 1, 0);
		
		//Left
		restrictedTeleportInDirection(posRow, posCol, color, board, result, 0, -1);
		
		//Right
		restrictedTeleportInDirection(posRow, posCol, color, board, result, 0, 1);
		
		//To mark itself
		result.get(posRow).set(posCol, currentPositionSymbol); 
		return result;
	}
	
	public void restrictedTeleportInDirection(int posRow, int posCol, String color, Board board, ArrayList<ArrayList<Character>> result, int directionRow, int directionColumn) {
		//Determine the ally and enemy symbols depending on the color
		ArrayList<Character> pieceSymbols = getSymbols(color);
		Character allyKingSymbol = pieceSymbols.get(0);
		Character allyPieceSymbol = pieceSymbols.get(1);
		Character enemyKingSymbol = pieceSymbols.get(2);
		Character enemyPieceSymbol = pieceSymbols.get(3);
		
		final int restriction = 3;
		
		//Move in specified direction
		int currentRow = posRow + directionRow;
		int currentColumn = posCol + directionColumn;
		
		for (int i = 0; i < restriction; i++){
			//If position is within board range
			if (currentRow >= 0 && currentRow < board.getPositionBoard().size() &&
					currentColumn >= 0 && currentColumn < board.getPositionBoard().get(currentRow).size()){
				Character currentCharacter = board.getPositionBoard().get(currentRow).get(currentColumn);
				
				//If position is occupied by ally, can't move there
				if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol))
					result.get(currentRow).set(currentColumn, invalidMoveSymbol);
				
				//If position is occupied by enemy, can move&capture
				else if (currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol))
					result.get(currentRow).set(currentColumn, moveAndCaptureSymbol); 
				
				//If position is empty, can move there
				else
					result.get(currentRow).set(currentColumn, moveSymbol); 
			}
			
			//Move in specified direction
			currentRow += directionRow;
			currentColumn += directionColumn;
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
