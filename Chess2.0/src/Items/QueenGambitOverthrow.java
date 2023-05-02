package Items;

import ChessGameClasses.Board;
import ChessPieces.ChessPiece;
import ChessPieces.Queen;
import Players.Player;

import java.util.ArrayList;

public class QueenGambitOverthrow extends DefinedItem{

    public QueenGambitOverthrow() {
        super("Queen Gambit Overthrow", 10);
    }

    @Override
    public void use(ArrayList<ChessPiece> opponentChessPieces) {
        if(isUsed()){
            return;
        }else{
            for(ChessPiece potentialQueen : opponentChessPieces){
                if(potentialQueen instanceof Queen){
                    potentialQueen.chessPieceGetsCaptured();
                }
            }
        }
    }
}
