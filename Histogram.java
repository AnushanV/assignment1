/* Name: Anushan Vimalthasan SID: 100658452 
 * Name: Ming (Ken) Zhou SID: 100658450
 * Date: March 6, 2019
 * This program makes a histogram of characters in a file
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Histogram extends Application{
	@Override
	public void start(Stage primaryStage){
		
		// Create user input nodes
		Button view = new Button("View");
		TextField textField = new TextField();
		Label label = new Label("Filename", textField);
		label.setContentDisplay(ContentDisplay.RIGHT);
		
		HBox bottom = new HBox();
		
		// Add user input to hbox
		bottom.getChildren().add(label);
		bottom.getChildren().add(textField);
		bottom.getChildren().add(view);
		
		// Set initial frame
		Scene scene = new Scene(bottom);
		primaryStage.setTitle("assignmentq4");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		view.setOnAction(e -> {
			
			int [] occurences = new int[26];
			int max;
			Pane histogram = new Pane();
			
			// default values to 0
			for(int i = 0; i < 26; i++){
				occurences[i] = 0;
			}
			
			String url = textField.getText();
			
			// Get the character frequency statistics
			try{
				File file = new File(url);
				if(file.isFile()){
					Scanner scan = new Scanner(file);
					max = 0;
					while(scan.hasNext()){
						String line = scan.next();
						for(int i = 0; i < line.length(); i++){
							
							// Count how many of each letters there are
							if((int)(line.charAt(i)) >= 65 && (int)(line.charAt(i)) <= 90){
								occurences[(int)(line.charAt(i)) - 65]++;
								if(occurences[(int)(line.charAt(i)) - 65] > max){
									max = occurences[(int)(line.charAt(i)) - 65];
								}
							} else if ((int)(line.charAt(i)) >= 97 && (int)(line.charAt(i)) <=122){
								occurences[(int)(line.charAt(i)) - 97]++;
								if(occurences[(int)(line.charAt(i)) - 97] > max){
									max = occurences[(int)(line.charAt(i)) - 97];
								}	
							}
						}
					}
					
					// setting bar graph heights
					for(int i = 0; i < 26; i++){
						occurences[i] = 250*occurences[i]/max;
					}
					
					// Make Histogram
					for(int i = 0; i < 26; i++){
						histogram.getChildren().add(new Rectangle(10 + 15*i, 260 - occurences[i], 7.5, occurences[i]));
						histogram.getChildren().add(new Text(10 + 15*i, 280, String.valueOf(Character.toChars(65+i))));
					}
					histogram.getChildren().add(new Line(10, 260, 390, 260));
					
					// Create scene
					VBox pane = new VBox();
					pane.getChildren().add(histogram);
					pane.getChildren().add(bottom);
					Scene s = new Scene(pane);
					primaryStage.setScene(s);
					primaryStage.show();
					
				} else {
					
					// If file not found, tell user
					VBox pane = new VBox();
					pane.getChildren().add(new Text(0, 0, "File not found"));
					pane.getChildren().add(bottom);
					Scene s = new Scene(pane);
					primaryStage.setScene(s);
					primaryStage.show();
				}
			}catch (FileNotFoundException err){
				System.out.println("File Not Found");
			}
		});
		
	}
	
	public static void main(String [] args){
		launch(args);
	}
}