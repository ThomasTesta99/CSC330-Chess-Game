package Movements;

import ChessGameClasses.Board;
import java.util.ArrayList;

public class OrthogonalNonCaptureMovement implements Movement{
	//posRow and posCol are the row and column that the chess piece is currently in.

	public static final String movementName = "Orthogonal Non-Capture Movement";
	public static final int movementCost = 0;

	@Override
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean moved, Board board){
		//Create an empty ArrayList<ArrayList<Character>> to store the possible moves
		ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
		
		//Logic
		
		//Move Up
		moveInDirection(posRow, posCol, color, board, result, -1, 0);
		
		//Move Down
		moveInDirection(posRow, posCol, color, board, result, 1, 0);
		
		//Move Left
		moveInDirection(posRow, posCol, color, board, result, 0, -1);
		
		//Move Right
		moveInDirection(posRow, posCol, color, board, result, 0, 1);
		
		
		//The piece can't move onto itself
		result.get(posRow).set(posCol, currentPositionSymbol); 
		
		return result;
	}
	
	public void moveInDirection(int posRow, int posCol, String color, Board board, ArrayList<ArrayList<Character>> result, int directionRow, int directionColumn) {
		//Determine the ally and enemy symbols depending on the color
		ArrayList<Character> pieceSymbols = getSymbols(color);
		Character allyKingSymbol = pieceSymbols.get(0);
		Character allyPieceSymbol = pieceSymbols.get(1);
		Character enemyKingSymbol = pieceSymbols.get(2);
		Character enemyPieceSymbol = pieceSymbols.get(3);
				
		//Move in specified direction
		int currentRow = posRow + directionRow;
		int currentColumn = posCol + directionColumn;
		
		//If the position is not out of range
		while (currentRow >= 0 && currentRow < board.getPositionBoard().size() &&
				currentColumn >= 0 && currentColumn < board.getPositionBoard().get(currentRow).size()){
			Character currentCharacter = board.getPositionBoard().get(currentRow).get(currentColumn);
			//If position is occupied by ally or enemy, it is an invalid move and is blocked from moving further
			if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol) ||
					currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol)){
				result.get(currentRow).set(currentColumn, invalidMoveSymbol);
				break;
			}
			
			//If position is empty, can move there
			else
				result.get(currentRow).set(currentColumn, moveSymbol); 
			
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
