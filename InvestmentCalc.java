/* Name: Anushan Vimalthasan SID: 100658452 
 * Name: Ming (Ken) Zhou SID: 100658450
 * Date: March 6, 2019
 * This program gets user input to calculate investment
 */

import java.text.ParseException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class InvestmentCalc extends Application {
	
	/** This function calculates future value of an investment
	 * String invesAmount - The investment amount as a string
	 * String years - The years as a string
	 * String interRate - The annual interest rate as a string
	 * returns the future value as a string
	 */
	public String futureValue(String invesAmount, String years, String interRate){
		
		//Convert values to double
		Double inves = Double.parseDouble(invesAmount)*1.0;
		Double monthlyRate = Double.parseDouble(interRate)/12.0/100;
		Double yrs = Double.parseDouble(years) * 1.0;
		
		/*
		//For testing
		System.out.println(inves);
		System.out.println(yrs);
		System.out.println(monthlyRate);
		*/
		
		//return future value to 2 decimal places
		return "" + Math.round((inves*(Math.pow((1 + monthlyRate), yrs*12)))*100)/100.0;
	}
	
	@Override
	public void start(Stage primaryStage) throws ParseException {
		
		//Create labels and text fields
		Label label1 = new Label("Investment Amount");
		TextField invesAmount = new TextField ();
		invesAmount.setAlignment(Pos.CENTER_RIGHT);
		
		Label label2 = new Label("Years");
		TextField years = new TextField ();
		years.setAlignment(Pos.CENTER_RIGHT);
		
		Label label3 = new Label("Annual Interest Rate");
		TextField interRate = new TextField ();
		interRate.setAlignment(Pos.CENTER_RIGHT);
		
		Label label4 = new Label("FutureValue");
		TextField label5 = new TextField();
		label5.setAlignment(Pos.CENTER_RIGHT);
		label5.setEditable(false);
		
		Button calc = new Button("Calculate");
		
		//Calculate future value and display it on button press
		calc.setOnAction(e -> label5.setText(futureValue(invesAmount.getText(), years.getText(), interRate.getText())));
		
		//Create grid pane to place nodes
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setHgap(10);
		pane.setVgap(10);
				
		//add nodes
		pane.add(label1, 0, 0);
		pane.add(label2, 0, 1);
		pane.add(label3, 0, 2);
		pane.add(label4, 0, 3);
		pane.add(invesAmount, 1, 0);
		pane.add(years, 1, 1);
		pane.add(interRate, 1, 2);
		pane.add(label5, 1, 3);
		pane.add(calc, 1, 4);
		
		//set alignment to match example
		GridPane.setHalignment(calc, HPos.RIGHT);
		GridPane.setHalignment(label5, HPos.RIGHT);
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Assignment 1 Q2");
		primaryStage.setScene(scene); 
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}