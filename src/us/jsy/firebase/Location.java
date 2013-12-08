package us.jsy.firebase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Location {
	double latitude;
	double longitude;
	
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
}
