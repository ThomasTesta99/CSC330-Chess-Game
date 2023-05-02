package Movements;

import ChessGameClasses.Board;
import ChessPieces.ChessPiece;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class TwoUnitsUp implements Movement{

    public static final String movementName = "Two Units Up";
    public static final int movementCost = 0;

    @Override
    public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean moved, Board board) {
        ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
        ArrayList<Character> pieceSymbols = getSymbols(color);
        Character allyKingSymbol = pieceSymbols.get(0);
        Character allyPieceSymbol = pieceSymbols.get(1);
        Character enemyKingSymbol = pieceSymbols.get(2);
        Character enemyPieceSymbol = pieceSymbols.get(3);

        if(moved){
            return result;
        }

        int col = posCol, row;
        int direction = 0;

        if(color.compareTo("White") == 0){
            direction = -2;
        }else{
            direction = 2;
        }

        row = posRow + direction;
        Character currentCharacter = board.getPositionBoard().get(row).get(col);
        if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol) ||
                currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol)){
            result.get(row).set(col, invalidMoveSymbol);
        }

        //If position is empty, can move there
        else{
            result.get(row).set(col, moveSymbol);
        }

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
