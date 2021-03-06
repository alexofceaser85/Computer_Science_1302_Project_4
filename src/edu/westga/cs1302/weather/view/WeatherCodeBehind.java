package edu.westga.cs1302.weather.view;

import java.io.File;

import java.nio.file.Paths;
import java.util.NoSuchElementException;

import edu.westga.cs1302.weather.errormessages.ErrorMessages;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The code behind class for the weather project
 * 
 * @author Alex DeCesare
 * @version 16-July-2020
 */

public class WeatherCodeBehind extends Pane {
	
	private static final String INFORMATION_ALERT_TITLE = "Weather Parser by Alex DeCesare";
	private static final String INFORMATION_ALERT_CONTENT_TEXT = "This program parses weather data which is split by new lines for each day and double quotes for each peice of data";

    @FXML
    private HBox thePane;

    @FXML
    private MenuItem buttonOpenFile;

    @FXML
    private MenuItem saveTheFile;

    @FXML
    private MenuItem buttonExit;

    @FXML
    private MenuItem buttonAbout;

    @FXML
    private TextArea textAreaTheLocationInput;

    @FXML
    private Label labelTheOutput;

    @FXML
    private Pane theChartPane;
    
	private WeatherViewModel theWeatherViewModel;
	private File theFile;
	
	
	/**
	 * The constructor for the code behind method
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public WeatherCodeBehind() {
	
		this.theWeatherViewModel = new WeatherViewModel();
		
	}
	
	@FXML
	private void initialize() {
		
	}
	
	/**
	 * Chooses the file to parse and sets it to the projects file path
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	@FXML
	public void chooseFile() {
		
		try {
			Window stage = this.thePane.getScene().getWindow();
			
			FileChooser theFileChooser = new FileChooser();
			
			this.setFileChooserProperties(theFileChooser);
			
			this.theFile = theFileChooser.showOpenDialog(stage);
			
			this.theWeatherViewModel.parseFile(this.theFile.getAbsolutePath());
			
			this.displayOutput();
			this.displayChart();
		} catch (NullPointerException theNullPointerException) {
			Alert theAlert = new Alert(AlertType.ERROR);
			theAlert.setTitle(ErrorMessages.NO_FILE_SELECTED_TITLE);
			theAlert.setContentText(ErrorMessages.NO_FILE_SELECTED_CONTENT_TEXT);
			theAlert.showAndWait();
		} catch (ArrayIndexOutOfBoundsException theArrayOutOfBoundsException) {
			Alert theAlert = new Alert(AlertType.ERROR);
			theAlert.setTitle(ErrorMessages.ARRAY_OUT_OF_BOUNDS_FILE_SELECTED_TITLE);
			theAlert.setContentText(ErrorMessages.ARRAY_OUT_OF_BOUNDS_FILE_SELECTED_CONTENT_TEXT);
			theAlert.showAndWait();
		} catch (IllegalArgumentException theIllegalArgumentException) {
			Alert theAlert = new Alert(AlertType.ERROR);
			theAlert.setContentText(theIllegalArgumentException.getMessage());
			theAlert.showAndWait();
		} catch (NoSuchElementException theNoSuchElementException) {
			Alert theAlert = new Alert(AlertType.ERROR);
			theAlert.setContentText(ErrorMessages.CANNOT_READ_THE_FILE);
			theAlert.showAndWait();
		}
	}
	
	/**
	 * Chooses the file to save
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	@FXML
	public void saveFile() {
		
		try {
			Window stage = this.thePane.getScene().getWindow();
			
			FileChooser theFileChooser = new FileChooser();
			
			this.setFileChooserProperties(theFileChooser);
			
			File theFile = theFileChooser.showSaveDialog(stage);
		
			this.theWeatherViewModel.saveFile(theFile);
		} catch (NullPointerException theNullPointerException) {
			Alert theAlert = new Alert(AlertType.ERROR);
			theAlert.setTitle(ErrorMessages.CANNOT_SAVE_FILE_TITLE);
			theAlert.setContentText(ErrorMessages.CANNOT_SAVE_A_FILE_BEFORE_IT_IS_OPENED_CONTENT_TEXT);
			theAlert.showAndWait();
		} catch (IllegalArgumentException theIllegalArgumentException) {
			Alert theAlert = new Alert(AlertType.ERROR);
			theAlert.setContentText(theIllegalArgumentException.getMessage());
			theAlert.showAndWait();
		}
		
	}
	
	/**
	 * Exits out of the application
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public void exitTheApplication() {
		System.exit(0);
	}
	
	/**
	 * Displays the output to the gui
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public void displayOutput() {
		
		this.labelTheOutput.setText(this.theWeatherViewModel.setOutput());
		
	}
	
	/**
	 * Displays the chart to the gui
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public void displayChart() {
		
		try {
			this.theWeatherViewModel.drawTheChart(this.theChartPane);
		} catch (NullPointerException theNullPointerException) {
			Alert theAlert = new Alert(AlertType.ERROR);
			theAlert.setContentText(ErrorMessages.ALERT_CANNOT_FIND_THE_DATA_OF_A_EMPTY_MANAGER);
			theAlert.showAndWait();
		}
		
	}
	
	/**
	 * displays the locations found to the gui
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public void displayLocationOutput() {
		
		if (!this.textAreaTheLocationInput.getText().isEmpty()) {
			this.labelTheOutput.setText(this.theWeatherViewModel.setLocationOutput(this.textAreaTheLocationInput.getText()));
		}
		
	}
	
	/**
	 * Displays an about section 
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public void displayAboutTheApplication() {
		Alert theAlert = new Alert(AlertType.INFORMATION);
		theAlert.setTitle(INFORMATION_ALERT_TITLE);
		theAlert.setContentText(INFORMATION_ALERT_CONTENT_TEXT);
		theAlert.showAndWait();
	}
	
	private void setFileChooserProperties(FileChooser theFileChooser) {
		String theCurrentFilePath = Paths.get(".").toAbsolutePath().normalize().toString();
		theFileChooser.setInitialDirectory(new File(theCurrentFilePath + "/src"));
		theFileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files", "*"),
				new FileChooser.ExtensionFilter("Only .txt Files", "*.txt")
		);
	}

}