package droneSimulationGUI;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.control.ListView;

public class DroneArena {

	private int xMax, yMax;
	private Drone d;
	private Random randomGenerator = new Random();
	public ArrayList<Drone> drones;
	
	/**
     * DroneArena constructor
     * @param width        X size
     * @param height       Y size
     */
	public DroneArena(int width, int height) {
		xMax = width;
		yMax = height;
		drones = new ArrayList<Drone>();
	}
	
	/**
     * Creates a new drone
     */
	public void addDrone(MyCanvas mc, ListView<Drone> Objects) {
		Random randomGenerator;
		randomGenerator = new Random();
		int newX;
		int newY;
		
		do {
			newX = randomGenerator.nextInt(xMax - 40) - 1;
			newY = randomGenerator.nextInt(yMax - 40) - 1;
		} while (getDroneAt(newX, newY) != null);
		{
			d = new Drone(newX, newY, DirectionGUI.randomDirection());  //Places drone in random x and y coordinates and in random direction
			drones.add(d);
			mc.drawDrone(this);
			DroneAnimation.fillList(Objects);
		}
	}
	
	/**
     * Returns the X value of the drone
     */
	public int getXSize() {
		return xMax;
	}
	
	/**
     * Returns the Y value of the drone
     */
	public int getYSize() {
		return yMax;
	}
	
	/**
     * Checks to see if the drone can move in this direction
     */
	public boolean canMoveHere(int x, int y) {
		if (x < 0 || x >= xMax - 40 || y < 0 || y >= yMax - 40) {
			return false;
		}
		
		for (Drone d : drones) {
			if(getDroneAt(x, y) != null) {
				return false;
			} else {
				return true;
			}
		}
		return (Boolean) null;
	}
	
	/**
     * Search the arraylist of the drones to check if there is a drone at x,y
     * Returns null if there is not
     * Returns drone if there is
     */
	public Drone getDroneAt(int x, int y) {
		for(Drone d : drones) {
			if(d.isHere(x, y)) {
				return d;
			}
		}
		return null;
	}
	
	/**
     * Moves all the drones at the same time
     */
	public void moveAllDrones(MyCanvas mc) {
		for (Drone d: drones) {
			d.tryToMove(this);
		}
		mc.updateCanvas(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
