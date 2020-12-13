package droneSimulationGUI;

import java.util.Random;

public enum DirectionGUI {
	
	//The four possible direction
		North, East, South, West;

		//Create random direction
		public static DirectionGUI randomDirection() {
			return values()[new Random().nextInt(values().length)];
		}

		//Returns next direction
		public static DirectionGUI nextDirection(DirectionGUI c) {
			return values()[(c.ordinal() + 1) % values().length];
		}

}
