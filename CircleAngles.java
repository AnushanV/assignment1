/* Name: Anushan Vimalathasan SID: 100658452
 * Name: Ming (Ken) Zhou SID: 100658450
 * Date: March 6, 2019
 * This program displays a circle with three points that are connected
 * to make a triangle. The points can be moved and the triangle will change 
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CircleAngles extends Application {
	
	@Override 
	public void start(Stage primaryStage) {
		
		//Draw main circle
		Circle circle = new Circle(260, 260, 230);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);   
		
		//Generate random angle to place points on
		double[] coords = new double[3];
		for (int i = 0; i < coords.length; i++){
			coords[i] = Math.random() * 360 + 1;
			coords[i] = Math.toRadians(coords[i]);
		}
		
		//Draw points
		Circle[] points = new Circle[3];
		double[] x = new double[3];
		double[] y = new double[3];
		for (int i = 0; i < coords.length; i++){
			
			//place point depending on angle
			x[i] = 230*Math.cos(coords[i]) + 260;
			y[i] = 230*Math.sin(coords[i])+ 260;
			
			//create point
			points[i] = new Circle(x[i], y[i], 5);
			points[i].setStroke(Color.BLACK);
			points[i].setFill(Color.RED);
		}
		
		//Connect points with lines
		Line line1 = new Line(x[0], y[0], x[1], y[1]);
		Line line2 = new Line(x[0], y[0], x[2], y[2]);
		Line line3 = new Line(x[2], y[2], x[1], y[1]);
		
		//Text to display angle
		int[] angs = new int[3];
		getAngle(x, y, angs);
		Text ang1 = new Text(x[0]+10, y[0], ""+ angs[0]);
		Text ang2 = new Text(x[1]+10, y[1], ""+ angs[1]);
		Text ang3 = new Text(x[2]+10, y[2], ""+ angs[2]);
		
		/*
		//Text to label points and lines (for testing)
		Text t1 = new Text((x[0]+x[1])/2, (y[0]+y[1])/2, "a");
		Text t2 = new Text((x[0]+x[2])/2, (y[0]+y[2])/2, "b");
		Text t3 = new Text((x[2]+x[1])/2, (y[2]+y[1])/2, "c");
		
		Text t4 = new Text(x[0], y[0], "A");
		Text t5 = new Text(x[1], y[1], "B");
		Text t6 = new Text(x[2], y[2], "C");
		*/
		
		//Enable each point to be dragged
		for (int i = 0; i < points.length; i++){
			final int j = i; //to use in event
			points[j].setOnMouseDragged(e -> {
				
				//Get the angle of the mouse from center of the circle
				double ang = Math.atan(1/((260-e.getY())/(e.getX()-260)));
				//adjust angle depending on quadrant
				if (e.getY() <= 260){
					ang = Math.PI/2 - ang; 
				}
				else{
					ang = 3*Math.PI/2 - ang;
				}
				
				//Set new point coordinates
				x[j] = 230*Math.cos(ang) + 260;
				y[j] = 260 - 230*Math.sin(ang);
				points[j].setCenterX(x[j]);
				points[j].setCenterY(y[j]);
				
				//Reconnect lines
				line1.setStartX(x[0]);
				line1.setEndX(x[1]);
				line1.setStartY(y[0]);
				line1.setEndY(y[1]);
				
				line2.setStartX(x[0]);
				line2.setEndX(x[2]);
				line2.setStartY(y[0]);
				line2.setEndY(y[2]);
				
				line3.setStartX(x[2]);
				line3.setEndX(x[1]);
				line3.setStartY(y[2]);
				line3.setEndY(y[1]);
				
				//calculate all angles in the triangle
				getAngle(x, y, angs);
				
				//Display new angles
				ang1.setText(""+ angs[0]);
				ang1.setY(y[0]+10);
				
				ang2.setText(""+ angs[1]);
				ang2.setY(y[1]+10);
				
				ang3.setText(""+ angs[2]);
				ang3.setY(y[2]+10);
				
				//Formatting the angle text
				if (x[0] > 260){
					ang1.setX(x[0]+10);
				}
				else{
					ang1.setX(x[0]-20);
				}
				
				if(x[1] > 260){
					ang2.setX(x[1]+10);
				}
				else{
					ang2.setX(x[1]-20);
				}
				
				if(x[2] > 260){
					ang3.setX(x[2]+10);
				}
				else{
					ang3.setX(x[2]-20);
				}
				
			});
		}
		
		//Display everything
		Pane pane = new Pane();
		pane.getChildren().add(circle);   
		for (int i = 0; i < points.length; i++){
			pane.getChildren().add(points[i]);
		}
		pane.getChildren().add(line1);
		pane.getChildren().add(line2);
		pane.getChildren().add(line3);
		
		pane.getChildren().add(ang1);
		pane.getChildren().add(ang2);
		pane.getChildren().add(ang3);
		
		/*
		// Display for testing
		pane.getChildren().add(t1);
		pane.getChildren().add(t2);
		pane.getChildren().add(t3);
		pane.getChildren().add(t4);
		pane.getChildren().add(t5);
		pane.getChildren().add(t6);
		*/
		
		Scene scene = new Scene(pane, 520, 520);
		primaryStage.setTitle("Assignment 1 Q3"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	}
	
	/** This program calculates all the angles in the triangle
	 *  using cosine law
	 * double[] x - Array containing x coordinates of points
	 * double[] y - Array containing y coordinates of points
	 * int[] angs - Array containing all angles in degrees
	 */
	public void getAngle(double[] x, double[] y, int[] angs){
		
		//Calculate side lengths
		double ax = x[0]-x[1];
		double ay = y[0]-y[1];
		double b = Math.sqrt(ax*ax + ay*ay);
		
		double bx = x[0]-x[2];
		double by = y[0]-y[2];
		double a = Math.sqrt(bx*bx + by*by);
		
		double cx = x[1]-x[2];
		double cy = y[1]-y[2];
		double c = Math.sqrt(cx*cx + cy*cy);
		
		//Calculate angles using cosine law
		angs[1] = (int) Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
		angs[2] = (int) Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
		angs[0] = (int) Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b))); 
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}