package ChessPieces;

import Movements.*;
import java.util.ArrayList;

public class Pawn extends ChessPiece{
	public static String name = "Pawn";
	public static int materialWorth = 1;

	//Keep track of whether or not the Pawn moved Two Units Up last turn.
	private boolean movedTwoUnitsUp;
	
	public Pawn(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new AdvanceMovement());
		getMovements().add(new LeftRightCaptureMovement());
		getMovements().add(new TwoUnitsUp());
		getMovements().add(new EnPassantMovement());

		setUpgrades();
	}
	
	public Pawn(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth() { return materialWorth; }
	public String getName() { return name; }

	public boolean getMovedTwoUnitsUp() { return movedTwoUnitsUp; }
	public void setMovedTwoUnitsUp(boolean movedTwoUnitsUp) { this.movedTwoUnitsUp = movedTwoUnitsUp; }

	private void setUpgrades(){
		ArrayList<Movement> pawnUpgrades = getAvailableUpgrades();

		pawnUpgrades.add(new SquareMovement());
		pawnUpgrades.add(new DiagonalMovement());
		pawnUpgrades.add(new OrthogonalMovement());
		pawnUpgrades.add(new LMovement());
		pawnUpgrades.add(new HopCaptureMovement());
		pawnUpgrades.add(new RangeCaptureMovement());
		pawnUpgrades.add(new TeleportationMovement());
		pawnUpgrades.add(new RestrictedTeleportationMovement());
	}

}
