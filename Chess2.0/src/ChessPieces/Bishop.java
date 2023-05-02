package ChessPieces;

import Movements.*;
import java.util.ArrayList;

public class Bishop extends ChessPiece{
	public static String name = "Bishop";
	public static int materialWorth = 3;

	public Bishop(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new DiagonalMovement());

		//setUpgrades();
	}
	
	public Bishop(ChessPiece source)
	{
		super(source);
	}
	
	public int getMaterialWorth() { return materialWorth; }
	public String getName() { return name; }

	private void setUpgrades(){
		ArrayList<Movement> bishopUpgrades = getAvailableUpgrades();

		bishopUpgrades.add(new SquareMovement());
		bishopUpgrades.add(new OrthogonalMovement());
		bishopUpgrades.add(new LMovement());
		bishopUpgrades.add(new HopCaptureMovement());
		bishopUpgrades.add(new RangeCaptureMovement());
		bishopUpgrades.add(new TeleportationMovement());
		bishopUpgrades.add(new RestrictedTeleportationMovement());

	}

}
