package parse.radio;

import java.math.BigDecimal;

public class Station {
	
	final private String callsign;
	final private String frequency;
	final private String service;
	final private String channel;
	final private String antennaType;
	final private String hoursOfOperation;
	final private String stationClass;
	final private String internationalStationClass;
	final private String fmStatus;
	final private String city;
	final private String state;
	final private String country;
	
	final private BigDecimal latitude;
	final private BigDecimal longitude;
	
	final private String company;

	public Station(String callsign, String frequency, String service, String channel, String antennaType, String hoursOfOperation,
			String stationClass, String internationalStationClass, String fmStatus, String city, String state, String country, BigDecimal latitude,
			BigDecimal longitude, String company) {
		
		this.callsign = callsign;
		this.frequency = frequency;
		this.service = service;
		this.channel = channel;
		this.antennaType = antennaType;
		this.hoursOfOperation=hoursOfOperation;
		this.stationClass = stationClass;
		this.internationalStationClass=internationalStationClass;
		this.fmStatus = fmStatus;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Station [callsign=" + callsign + ", frequency=" + frequency + ", service=" + service + ", channel="
				+ channel + ", antennaType=" + antennaType + ", hoursOfOperation=" + hoursOfOperation
				+ ", stationClass=" + stationClass + ", internationalStationClass=" + internationalStationClass
				+ ", fmStatus=" + fmStatus + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", company=" + company + "]";
	}
	
	
}
