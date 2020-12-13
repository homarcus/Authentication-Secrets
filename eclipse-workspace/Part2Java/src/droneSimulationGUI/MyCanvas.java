package droneSimulationGUI;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MyCanvas extends Application{
	int xCanvasSize = 512;				
	int yCanvasSize = 512;
    GraphicsContext gc; 
    
    Image drone = new Image("drone.png");          //Creates image using this address

    /**
     * Constructor for MyCanvas to set up the canvas and the graphics
     * @param g
     * @param xcs
     * @param ycs
     */
    public MyCanvas(GraphicsContext g, int xcs, int ycs) {
    	gc = g;
    	xCanvasSize = xcs;
    	yCanvasSize = ycs;
    }
    
    /**
     * Returns the X value of the canvas
     */
    public int getXCanvasSize() {
    	return xCanvasSize;
    }
    
    /**
     * Returns the Y value of the canvas
     */
    public int getYCanvasSize() {
    	return yCanvasSize;
    }

    /**
     * Clears the canvas
     */
    public void clearCanvas() {
		gc.clearRect(0,  0,  xCanvasSize,  yCanvasSize);		
    }
    
	/**
     * Draws object by using its image, position and the size
     */
	public void drawIt (Image i, double x, double y, double sz) {
		gc.drawImage(i, xCanvasSize * (x - sz/2), yCanvasSize*(y - sz/2), xCanvasSize*sz, yCanvasSize*sz);
	}
	
	public void fillCanvas(int width, int height) {
		//Sets colour, size and formatting for canvas
		gc.setFill(Color.LAVENDER);
		gc.fillRect(0, 0, width, height);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, width, height);
	}
	
	public void updateCanvas(DroneArena myArena){
		//Sets formatting and clears canvas
		gc.clearRect(0, 0, xCanvasSize, yCanvasSize);
		//Fills the canvas
		fillCanvas(xCanvasSize, yCanvasSize);
		//Draws all drones
		drawDrone(myArena);
	}
	
	/**
     * Draws images based on information inside of drones, giving size and location
     */
	public void drawDrone(DroneArena myArena) {
		for (Drone d : myArena.drones) {
			gc.drawImage(drone, d.getX(), d.getY(), 25, 25);
			}
		}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
}


