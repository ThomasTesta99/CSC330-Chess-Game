package ChessPieces;

import Movements.CastleMovement;
import Movements.SquareMovement;

public class King extends ChessPiece{
	public static final String name = "King";
	public static final int materialWorth = 10;

	public King(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new SquareMovement());
		getMovements().add(new CastleMovement());
	}
	
	public King(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth() { return materialWorth; }
	public String getName() { return name; }
	
	public void Castle(){
		
	}
}
