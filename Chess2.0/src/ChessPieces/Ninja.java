package ChessPieces;

import Movements.*;
import java.util.ArrayList;

public class Ninja extends ChessPiece{
	public static String name = "Ninja";
	public static int materialWorth = 7;
	
	public Ninja(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new RestrictedTeleportationMovement());

		setUpgrades();
	}
	
	public Ninja(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth() { return materialWorth; }
	public String getName() { return name; }

	private void setUpgrades(){
		ArrayList<Movement> ninjaUpgrades = getAvailableUpgrades();

		ninjaUpgrades.add(new SquareMovement());
		ninjaUpgrades.add(new DiagonalMovement());
		ninjaUpgrades.add(new OrthogonalMovement());
		ninjaUpgrades.add(new LMovement());
		ninjaUpgrades.add(new HopCaptureMovement());
		ninjaUpgrades.add(new RangeCaptureMovement());
		ninjaUpgrades.add(new TeleportationMovement());
		ninjaUpgrades.add(new LeftRightCaptureMovement());

	}

}
