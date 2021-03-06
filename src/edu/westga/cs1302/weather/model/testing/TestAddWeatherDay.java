package edu.westga.cs1302.weather.model.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.weather.model.WeatherForADay;
import edu.westga.cs1302.weather.model.WeatherManager;

class TestAddWeatherDay {

	@Test
	public void shouldNotAddNullWeatherForDay() {
		
		WeatherManager theManager = new WeatherManager();

		assertThrows(IllegalArgumentException.class, () -> {
			
			theManager.addWeatherDay(null);
			
		});
	}
	
	@Test
	public void shouldAddWeatherDayToEmptyManager() {
		
		WeatherManager theManager = new WeatherManager();
		WeatherForADay theDaysWeather = new WeatherForADay("USC00091640", "CARROLLTON, GA US", "2020-07-01", 92, 68);
		
		theManager.addWeatherDay(theDaysWeather);
		
		assertEquals("The Sorted Array List: " + System.lineSeparator() 
			+ "The day: 2020-07-01 at the station: USC00091640 at the location: CARROLLTON, GA US with the maximum temperature of: 92.0 with the minimum temperature of: 68.0" + System.lineSeparator() 
			+ "The Unsorted HashMap" + System.lineSeparator()
			+ "2020-07-01=The day: 2020-07-01 at the station: USC00091640 at the location: CARROLLTON, GA US with the maximum temperature of: 92.0 with the minimum temperature of: 68.0"
			+ System.lineSeparator(), theManager.toString());
	}
	
	@Test
	public void shouldAddMultipleWeatherDays() {
		
		WeatherManager theManager = new WeatherManager();
		WeatherForADay theFirstDaysWeather = new WeatherForADay("USC00091640", "CARROLLTON, GA US", "2020-07-01", 92, 68);
		WeatherForADay theSecondDaysWeather = new WeatherForADay("USC00091640", "CARROLLTON, GA US", "2020-07-02", 90, 71);
		WeatherForADay theThirdDaysWeather = new WeatherForADay("USC00091640", "CARROLLTON, GA US", "2020-07-03", 85, 40);
		
		theManager.addWeatherDay(theFirstDaysWeather);
		theManager.addWeatherDay(theSecondDaysWeather);
		theManager.addWeatherDay(theThirdDaysWeather);
		theManager.getTheSortedWeather();
		
		assertEquals("The Sorted Array List: " + System.lineSeparator() 
			+ "The day: 2020-07-01 at the station: USC00091640 at the location: CARROLLTON, GA US with the maximum temperature of: 92.0 with the minimum temperature of: 68.0" 
				+ System.lineSeparator() + "The day: 2020-07-02 at the station: USC00091640 at the location: CARROLLTON, GA US with the maximum temperature of: 90.0 with the minimum temperature of: 71.0" 
			    + System.lineSeparator() + "The day: 2020-07-03 at the station: USC00091640 at the location: CARROLLTON, GA US with the maximum temperature of: 85.0 with the minimum temperature of: 40.0"
				+ System.lineSeparator() + "The Unsorted HashMap"
			    + System.lineSeparator() + "2020-07-03=The day: 2020-07-03 at the station: USC00091640 at the location: CARROLLTON, GA US with the maximum temperature of: 85.0 with the minimum temperature of: 40.0" 
				+ System.lineSeparator() + "2020-07-01=The day: 2020-07-01 at the station: USC00091640 at the location: CARROLLTON, GA US with the maximum temperature of: 92.0 with the minimum temperature of: 68.0"
				+ System.lineSeparator() + "2020-07-02=The day: 2020-07-02 at the station: USC00091640 at the location: CARROLLTON, GA US with the maximum temperature of: 90.0 with the minimum temperature of: 71.0" 
				+ System.lineSeparator(), theManager.toString());
	}

}
