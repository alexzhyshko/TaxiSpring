package io.github.zhyshko.application.dto.request;

public class OrderCreateRequest {

	private String departureLongitude;
	private String departureLatitude;
	private String destinationLongitude;
	private String destinationLatitude;
	private int numberOfPassengers;
	private String carCategory;
	
	
	public String getDepartureLongitude() {
		return departureLongitude;
	}
	public String getDepartureLatitude() {
		return departureLatitude;
	}
	public String getDestinationLongitude() {
		return destinationLongitude;
	}
	public String getDestinationLatitude() {
		return destinationLatitude;
	}
	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public String getCarCategory() {
		return carCategory;
	}
	
	
	
	
}
