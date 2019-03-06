/* Name: Anushan Vimalathasan SID: 100658452
 * Name: Ming (Ken) Zhou SID: 100658450
 * Date: March 6, 2019
 * This program displays 3 random cards
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Cards extends Application {
	@Override 
	public void start(Stage primaryStage) {
		
		int numCards = 3; //number of cards to display
		
		//Create array of images and imageviews to store cards
		Image[] cardImg = new Image[numCards];
		ImageView[] cards = new ImageView[numCards];
		
		//Create 3 random cards and store them in the arrays
		for (int i = 0; i < numCards; i++){
		
			int randNum = (int) (Math.random() * 54 + 1); //get random number
			
			//create card
			cardImg[i] = new Image("/Cards/" + randNum + ".png");
			cards[i] = new ImageView(cardImg[i]);
		}
		
		HBox hbox = new HBox(5);
		
		//add all cards
		for (int i = 0; i < numCards; i++){
			hbox.getChildren().add(cards[i]);
		}
		
		Scene scene = new Scene(hbox);
	    primaryStage.setTitle("Assignment 1 Q1");
	    primaryStage.setScene(scene);
	    primaryStage.show();
		
	}
  
  	public static void main(String[] args) {
  		launch(args);
  	}

}