package droneSimulationGUI;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
* The DroneAnimation class is responsible for the operations of the buttons in the canvas and 
* the movements of the drones
*
* @author  Marcus Ho
* @since   2020-12-10 
*/

public class DroneAnimation extends Application{
	private int xCanvasSize = 600, yCanvasSize = 500;  //constant for the canvas size
	private MyCanvas mc;                               //constant to draw the arena
	private static DroneArena myArena;                 //constant for the object in the drone arena
	private static AnimationTimer timer;               //constant for the timer of the animation
	
	/**
     * Outputs the details of the drones as a list
     */

	public static void fillList(ListView<Drone> ListDrone) {
		ListDrone.getItems().clear();
		for(Drone d: myArena.drones) {
			ListDrone.getItems().add(d);
		}
	};
	
	/**
     * Method that's starts the simulation
     */

	public void start(Stage stagePrimary) throws Exception {
		stagePrimary.setTitle("MH's Drone Animation");
		
		Group root = new Group();
		
		Canvas canvas = new Canvas( xCanvasSize, yCanvasSize );
		
		root.getChildren().add( canvas );
		
		mc = new MyCanvas(canvas.getGraphicsContext2D(), xCanvasSize, yCanvasSize);
		
		myArena = new DroneArena(xCanvasSize, yCanvasSize);
		
		mc.fillCanvas(xCanvasSize, yCanvasSize);
		
		timer = new AnimationTimer() {
			public void handle(long now) {
				myArena.moveAllDrones(mc);
			}
		};
		
		ListView<Drone> Objects = new ListView<>();
		VBox vbList = new VBox();
		
		vbList.getChildren().addAll(Objects);
		vbList.setAlignment(Pos.CENTER);
		vbList.setPadding(new Insets(0, 0, 0, 50));
		
		/**
	     * Button to add drone
	     */
		Button btnAddDrone = new Button("Add Drone");
		btnAddDrone.setOnAction(e -> {
			myArena.addDrone(mc, Objects);
		});
		
		btnAddDrone.setMinSize(120, 55);
		btnAddDrone.setMaxSize(120, 55);
		
		/**
	     * Button to make drone/drones move
	     */
		Button btnStart = new Button("Start");
		btnStart.setOnAction(e -> timer.start());
		btnStart.setMinSize(120, 55);
		btnStart.setMaxSize(120, 55);
		
		/**
	     * Button to stop drone/drones from moving
	     */

		Button btnStop = new Button("Stop");
		btnStop.setOnAction(e -> timer.stop());
		btnStop.setMinSize(120, 55);
		btnStop.setMaxSize(120, 55);
		
		HBox hbButtons = new HBox(20);
		hbButtons.setAlignment(Pos.CENTER);
		hbButtons.setPadding(new Insets(0, 0, 50, 0));
		
		hbButtons.getChildren().addAll(btnAddDrone, btnStart, btnStop);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(root);
		borderPane.setBottom(hbButtons);
		borderPane.setLeft(vbList);
		
		Scene scene = new Scene(borderPane, 1200, 700);
		stagePrimary.setScene( scene );
		stagePrimary.show();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
