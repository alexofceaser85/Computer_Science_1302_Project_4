package edu.westga.cs1302.weather.chart.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.weather.chart.ChartData;
import edu.westga.cs1302.weather.model.WeatherForADay;
import edu.westga.cs1302.weather.model.WeatherManager;

class TestGetMedianTemperature {

	@Test
	public void shouldNotGetMedianTemperatureOfEmptyManager() {
		
		WeatherManager theManager = new WeatherManager();
		ChartData theChartData = new ChartData(theManager);
		
		assertThrows(NullPointerException.class, () -> {
			assertEquals(null, theChartData.getTheMedianTemperature());
		});
	}
	
	@Test
	public void shouldGetMedianTemperatureOfManagerWithOneDay() {
		
		WeatherManager theManager = new WeatherManager();
		
		WeatherForADay theFirstWeather = new WeatherForADay("USC00091640", "CARROLLTON GA US", "2020-08-01", 92.5, 68.5);
		
		theManager.addWeatherDay(theFirstWeather);
		
		ChartData theChartData = new ChartData(theManager);
		
		assertEquals(80.5, theChartData.getTheMedianTemperature());
		
	}
	
	@Test
	public void shouldGetMedianTemperatureOfManagerWithMultipleDays() {
		
		WeatherManager theManager = new WeatherManager();
		
		WeatherForADay theFirstWeather = new WeatherForADay("USC00091640", "CARROLLTON GA US", "2020-08-01", 92, 68);
		WeatherForADay theSecondWeather = new WeatherForADay("USC00091640", "CARROLLTON GA US", "2020-08-02", 91, 67);
		WeatherForADay theThirdWeather = new WeatherForADay("USC00091640", "CARROLLTON GA US", "2020-08-03", 90, 66);
		
		theManager.addWeatherDay(theFirstWeather);
		theManager.addWeatherDay(theSecondWeather);
		theManager.addWeatherDay(theThirdWeather);
		
		ChartData theChartData = new ChartData(theManager);
		
		assertEquals(79, theChartData.getTheMedianTemperature());
		
	}

}
