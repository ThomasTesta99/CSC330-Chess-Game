package Items;

import ChessGameClasses.Board;
import ChessPieces.ChessPiece;
import Players.Player;

import java.util.ArrayList;

public interface Item {
    public void use(ArrayList<ChessPiece> opponentChessPieces);
    public String getItemName();
    public int getItemCost();
}
