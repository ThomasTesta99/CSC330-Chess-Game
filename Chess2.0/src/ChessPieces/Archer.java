package ChessPieces;

import Movements.*;

import java.util.ArrayList;

public class Archer extends ChessPiece{
	public static String name = "Archer";
	public static int materialWorth = 2;

	public Archer(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new AdvanceMovement());
		getMovements().add(new RangeCaptureMovement());

		setUpgrades();
	}
	
	public Archer(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth(){
		return materialWorth;
	}

	public String getName(){
		return name;
	}

	private void setUpgrades(){
		ArrayList<Movement> archerUpgrade = getAvailableUpgrades();

		archerUpgrade.add(new SquareMovement());
		archerUpgrade.add(new DiagonalMovement());
		archerUpgrade.add(new OrthogonalMovement());
		archerUpgrade.add(new LMovement());
		archerUpgrade.add(new HopCaptureMovement());
		archerUpgrade.add(new TeleportationMovement());
		archerUpgrade.add(new RestrictedTeleportationMovement());
		archerUpgrade.add(new LeftRightCaptureMovement());

	}
}