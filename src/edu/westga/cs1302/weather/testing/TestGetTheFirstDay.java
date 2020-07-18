package edu.westga.cs1302.weather.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sun.glass.ui.Application;

import edu.westga.cs1302.weather.model.WeatherForADay;
import edu.westga.cs1302.weather.model.WeatherManager;

class TestGetTheFirstDay {
	
	@Test
	public void shouldThrowAnExceptionForEmptyManager() {
		
		WeatherManager theManager = new WeatherManager();
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			theManager.getTheFirstDay();
		});
		
	}
	
	@Test
	public void shouldReturnTheFirstWeatherForManagerWithOneWeather() {
		
		WeatherManager theManager = new WeatherManager();
		
		WeatherForADay theFirstWeather = new WeatherForADay("USC00091640", "CARROLLTON GA US", "2020-08-01", 92, 68);
		
		theManager.addWeatherDay(theFirstWeather);
		
		assertEquals("The day: 2020-08-01 at the station: USC00091640 at the location: CARROLLTON GA US with the maximum temperature of: 92.0 with the minimum temperature of: 68.0", theManager.getTheFirstDay().toString());
	}
	
	@Test
	public void shouldReturnTheFirstWeatherForManagerWithMultipleWeathers() {
		
		WeatherManager theManager = new WeatherManager();
		
		WeatherForADay theFirstWeather = new WeatherForADay("USC00091640", "CARROLLTON GA US", "2020-08-01", 92, 68);
		WeatherForADay theSecondWeather = new WeatherForADay("USC00091640", "CARROLLTON GA US", "2020-08-02", 80, 48);
		WeatherForADay theThirdWeather = new WeatherForADay("USC00091640", "CARROLLTON GA US", "2020-08-03", 90, 70);
		
		theManager.addWeatherDay(theFirstWeather);
		theManager.addWeatherDay(theSecondWeather);
		theManager.addWeatherDay(theThirdWeather);
		
		assertEquals("The day: 2020-08-01 at the station: USC00091640 at the location: CARROLLTON GA US with the maximum temperature of: 92.0 with the minimum temperature of: 68.0", theManager.getTheFirstDay().toString());
	}

}