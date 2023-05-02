package ChessPieces;

import Movements.*;
import java.util.ArrayList;

public class Knight extends ChessPiece{
	public static String name = "Knight";
	public static int materialWorth = 3;
	
	public Knight(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new LMovement());

		setUpgrades();
	}
	
	public Knight(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth() { return materialWorth; }
	public String getName() { return name; }

	private void setUpgrades(){
		ArrayList<Movement> knightUpgrades = getAvailableUpgrades();

		knightUpgrades.add(new SquareMovement());
		knightUpgrades.add(new DiagonalMovement());
		knightUpgrades.add(new OrthogonalMovement());
		knightUpgrades.add(new LMovement());
		knightUpgrades.add(new HopCaptureMovement());
		knightUpgrades.add(new RangeCaptureMovement());
		knightUpgrades.add(new TeleportationMovement());
		knightUpgrades.add(new RestrictedTeleportationMovement());
		knightUpgrades.add(new LeftRightCaptureMovement());
	}

}
