package edu.westga.cs1302.weather.io;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.westga.cs1302.weather.errormessages.ErrorMessages;
import edu.westga.cs1302.weather.model.WeatherForADay;
import edu.westga.cs1302.weather.model.WeatherManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This reads the weather file and gets the weather data
 * 
 * @author Alex DeCesare
 * @version 17-July-2020
 */

public class ReadWeatherFile {
	
	private static final String THE_FILE_NOT_FOUND_WARNING = "The file was not found";
	
	private String theFileName;
	private File theFile;
	private Scanner inFile;
	private WeatherManager theWeatherManager;
	
	/**
	 * The constructor for the read weather file class
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public ReadWeatherFile() {

		this.theWeatherManager = new WeatherManager();

	}
	
	/**
	 * Sets the file name
	 * 
	 * @precondition fileNameToSet != null && fileNameToSet.isEmpty() == false
	 * @postcondition theFileName == fileNameToSet
	 * 
	 * @param fileNameToSet the file name to set
	 */
	
	public void setFileName(String fileNameToSet) {
		
		if (fileNameToSet == null) {
			throw new IllegalArgumentException(ErrorMessages.CANNOT_SET_A_NULL_FILE);
		}
		if (fileNameToSet.isEmpty()) {
			throw new IllegalArgumentException(ErrorMessages.CANNOT_SET_AN_EMPTY_FILE);
		}
		
		this.theFileName = fileNameToSet;
	}
	
	/**
	 * Gets the file name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the file name
	 */
	
	public String getFileName() {
		
		return this.theFileName;
		
	}
	
	/**
	 * Sets the file
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public void setTheFile() {
		try {
			this.theFile = new File(this.theFileName);
			this.inFile = new Scanner(this.theFile);
		} catch (FileNotFoundException theFileNotFoundException) {
			Alert theAlert = new Alert(AlertType.WARNING);
			theAlert.setContentText(THE_FILE_NOT_FOUND_WARNING);
			theAlert.showAndWait();
		}
	}
	
	/**
	 * Gets the weather manager
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the weather manager
	 */
	
	public WeatherManager getTheWeatherManager() {
		return this.theWeatherManager;
	}
	
	/**
	 * Gets the in file scanner
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the in file scanner
	 */
	
	public Scanner getInFileScanner() {
		return this.inFile;
	}
	
	/**
	 * Reads the weather data in the file
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	
	public void readFile() {
		
		this.theWeatherManager = new WeatherManager();
			
		this.getInFileScanner().nextLine();
			
		while (this.getInFileScanner().hasNextLine()) {
			try {
				this.addFileLineToWeather(this.getInFileScanner().nextLine());
			} catch (NullPointerException theNullPointerException) {
				Alert theAlert = new Alert(AlertType.ERROR);
				theAlert.setContentText(ErrorMessages.ALERT_THERE_WAS_AN_ERROR_READING_THE_FILE);
				theAlert.showAndWait();
			} catch (IllegalArgumentException theIllegalArgumentException) {
				Alert theAlert = new Alert(AlertType.ERROR);
				theAlert.setContentText(theIllegalArgumentException.getMessage());
				theAlert.showAndWait();
			}
		}
	}
	
	/**
	 * Gets the file name without extension
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the file name
	 */
	
	public String getFileNameNoExtension() {
		
		String fullFileName = this.theFile.getName();
		
		int indexOfExtension = fullFileName.lastIndexOf(".");
		
		if (indexOfExtension > 0) {
			fullFileName = fullFileName.substring(0, indexOfExtension);
		}
		
		return fullFileName;
		
	}
	
	private void addFileLineToWeather(String theWeatherLine) {

		String[] theFileLine = this.parseTheFileLine(theWeatherLine);
		
		WeatherForADay addTheWeather = new WeatherForADay(theFileLine[0], theFileLine[1], theFileLine[2], this.parseIntegers(theFileLine[3]), this.parseIntegers(theFileLine[4]));
		
		this.getTheWeatherManager().addWeatherDay(addTheWeather);
		
	}
	
	private String[] parseTheFileLine(String theLineToParse) {
		String[] splitString = theLineToParse.split("\",");
		
		for (int counter = 0; counter < splitString.length; counter++) {
			splitString[counter] = splitString[counter].replace("\"", "");
		}
		
		return splitString;
	}
	
	private double parseIntegers(String stringToParse) {
		
		try {
			return Double.parseDouble(stringToParse);
		} catch (NumberFormatException theNumberFormatException) {
			Alert theAlert = new Alert(AlertType.ERROR);
			theAlert.setContentText(ErrorMessages.ALERT_THERE_WAS_AN_ERROR_PARSING_THE_TEMPERATURE);
			theAlert.showAndWait();
			return 0;
		}
		
	}

}
