package Movements;

import ChessGameClasses.Board;
import java.util.ArrayList;

public class TeleportationMovement implements Movement{
	//posRow and posCol are the row and column that the chess piece is currently in.

	public static final String movementName = "Teleportation";
	public static final int movementCost = 1500;

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
		for (int row = 0; row < board.getPositionBoard().size(); row++){
			for (int column = 0; column < board.getPositionBoard().get(row).size(); column++){
				Character currentCharacter = board.getPositionBoard().get(row).get(column);
				
				//If position is occupied by ally or by enemy, can't move there
				if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol) ||
						currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol))
					result.get(row).set(column, invalidMoveSymbol);
				
				//If position is empty, can move there
				else
					result.get(row).set(column, moveSymbol); 
			}
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
