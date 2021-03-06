package edu.westga.cs1302.weather;
	
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/**
 * The main class for the weather project
 * 
 * @author Alex DeCesare
 * @version 16-July-2020
 */

public class Main extends Application {
	
	private static final String VIEW_EVENT_PANE_FXML = "view/EventPane.fxml";
	private static final String THE_TITLE = "Alex DeCesare's Project Four";
	private static final String THE_IO_EXCEPTION_TEXT = "Problem opening the view pane file";
	private static final String THE_NUMBER_FORMAT_EXCEPTION_TEXT = "Cannot find the view pane file";
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(THE_TITLE);
		
		try {
			
			URL theURL = super.getClass().getResource(VIEW_EVENT_PANE_FXML);
			
			AnchorPane thePane = FXMLLoader.load(theURL);
			
			Scene theScene = new Scene(thePane);

			primaryStage.setScene(theScene);
		
			primaryStage.show();
		} catch (IOException theException) {
			Alert message = new Alert(AlertType.ERROR);
			message.setContentText(THE_IO_EXCEPTION_TEXT);
			message.showAndWait();
			
		} catch (NullPointerException theException) {
			Alert message = new Alert(AlertType.ERROR);
			message.setContentText(THE_NUMBER_FORMAT_EXCEPTION_TEXT);
			message.showAndWait();
		}
	
	}
	
	/**
	 * The main method for the weather project
	 * 
	 * @author Alex DeCesare
	 * @version 16-July-2020
	 * 
	 * @param args the arguments
	 */
	
	public static void main(String[] args) {
		launch(args);
	}
}
