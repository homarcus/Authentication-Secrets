/**
 * 
 */
package droneSimulationGUI;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Drone {
	protected int x;
	protected int y;
	protected int droneID;
	protected Image droneImage;
	private static int count = 0;
	protected DirectionGUI move = DirectionGUI.South;

	/**
	 * @param args
	 */
	
	
	public Drone(int a, int b, DirectionGUI c) {
		x = a;
		y = b;
		move = c;
		droneID = count++;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public DirectionGUI getDirection() {
		return move;
	}
	
	public boolean tryToMove(DroneArena myArena) {
		int newX = this.x;
		int newY = this.y;
		
		if(this.move == DirectionGUI.North) {
			newY++;
		} else if (this.move == DirectionGUI.East){
			newX--;
		} else if (this.move == DirectionGUI.South) {
			newY--;
		} else if (this.move == DirectionGUI.West) {
			newX++;
		}
		
		if (myArena.canMoveHere(newX, newY)) {
			this.x = newX;
			this.y = newY;
			return true;
		} else {
			this.move = DirectionGUI.randomDirection();
			return false;
		}
	}
	
	public boolean isHere(int sx, int sy) {
		return sx == x && sy == y;
	}
	
	public boolean isTouching(int sx, int sy) {
		int droneRad = 20;
		if ((x - sx < droneRad && x - sx > - droneRad) && (y - sy < droneRad && y - sy > -droneRad)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setXY(int nx, int ny) {
		x = nx;
		y = ny;
	}
	
	public String toString() {
		return "Drone " + droneID + " is at " + x + ", " + y + " is moving in direction of " + move + "\n";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
