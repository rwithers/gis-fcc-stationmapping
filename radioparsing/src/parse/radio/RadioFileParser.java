package parse.radio;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RadioFileParser {
	
	final private static Pattern lineSeparator = Pattern.compile("\\R");
	final private static Pattern pipe = Pattern.compile("[|]");
	
	final private String NA_VALUE = "-";

	public RadioFileParser(){
	}
	
	public List<Station> parse(String resource) throws FileNotFoundException{
		List<Station> stationList = new ArrayList<Station>();

		File file = new File(resource);
		
		@SuppressWarnings("resource")
		Scanner fileScanner = new Scanner(file).useDelimiter(lineSeparator);
		
		while (fileScanner.hasNext()) {
			String lineEntry = fileScanner.next();

			Scanner lineEntryScanner = new Scanner(lineEntry);
			lineEntryScanner.useDelimiter(pipe);
			
			String callsign = null;
			String frequency = null;
			String service = null;
			String channel = null;
			String antennaType = null;
			String hoursOfOperation = null;
			String stationClass = null;
			String internationalStationClass=null;
			String fmStatus = null;
			String city = null;
			String state = null;
			String country = null;
			String company = null;
			
			String dms_latitude_direction = null;
			String dms_latitude_degrees = null;
			String dms_latitude_minutes = null;
			String dms_latitude_seconds = null;
			
			String dms_longitude_direction = null;
			String dms_longitude_degrees = null;
			String dms_longitude_minutes = null;
			String dms_longitude_seconds = null;
			
			int position = 0;
			while (lineEntryScanner.hasNext()) {
				position++;
				String value = lineEntryScanner.next();
				value = value.trim();
				if(NA_VALUE.equals(value)) value = null;
				
				switch (position) {
				case 1:
					callsign=value;
					break;
				case 2:
					frequency=value;
					break;
				case 3:
					service=value;
					break;
				case 4:
					channel=value;
					break;
				case 5:
					antennaType=value;
					break;
				case 6:
					hoursOfOperation=value;
					break;
				case 7:
					stationClass=value;
					break;
				case 8:
					internationalStationClass=value;
					break;
				case 9:
					fmStatus=value;
					break;
				case 10:
					city=value;
					break;
				case 11:
					state=value;
					break;
				case 12:
					country=value;
					break;
				case 19:
					dms_latitude_direction=value;
					break;
				case 20:
					dms_latitude_degrees=value;
					break;
				case 21:
					dms_latitude_minutes=value;
					break;
				case 22:
					dms_latitude_seconds=value;
					break;
				case 23:
					dms_longitude_direction=value;
					break;
				case 24:
					dms_longitude_degrees=value;
					break;
				case 25:
					dms_longitude_minutes=value;
					break;
				case 26:
					dms_longitude_seconds=value;
					break;
				case 27:
					company=value;
					break;
				default:
					break;
				}
			}

			int lat_direction= dms_latitude_direction.equals("N") ? 1 : -1;
			int lat_degree = Integer.parseInt(dms_latitude_degrees);
			int lat_minute = Integer.parseInt(dms_latitude_minutes);
			BigDecimal lat_second = new BigDecimal(dms_latitude_seconds);
			
			BigDecimal latitude = dmsToDecimal(lat_direction, lat_degree, lat_minute, lat_second);
			
			int lon_direction= dms_longitude_direction.equals("E") ? 1 : -1;
			int lon_degree = Integer.parseInt(dms_longitude_degrees);
			int lon_minute = Integer.parseInt(dms_longitude_minutes);
			BigDecimal lon_second = new BigDecimal(dms_longitude_seconds);
			
			BigDecimal longitude = dmsToDecimal(lon_direction, lon_degree, lon_minute, lon_second);
			
			Station station = new Station(callsign, frequency, service, channel, antennaType, hoursOfOperation, stationClass, internationalStationClass, fmStatus, city, state, country, latitude, longitude, company);
			stationList.add(station);
			
			lineEntryScanner.close();
		}
		
		fileScanner.close();
		return stationList;
	}
	
	public static BigDecimal dmsToDecimal(int direction, int degree, int minutes, BigDecimal seconds){
		double result = direction * (degree + minutes / 60.0d + seconds.doubleValue() / 3600);
		return new BigDecimal(result).setScale(6, RoundingMode.HALF_UP);
	}
}
