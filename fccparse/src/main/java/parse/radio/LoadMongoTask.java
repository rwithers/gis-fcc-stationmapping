package parse.radio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class LoadMongoTask {

	public static void main(String[] args) throws FileNotFoundException {
		RadioFileParser radioFileParser = new RadioFileParser();
		MongoLoader mongoLoader = new MongoLoader();
		
		//FM
		List<Station> fmStations = radioFileParser.parse("files/fmshort.txt");
		
		Gson gson = new Gson();
		List<String> jsonFMStations = new ArrayList<String>();

		for (Station station : fmStations) {
			jsonFMStations.add(gson.toJson(station));
		}
		
		mongoLoader.drop("fm");
		mongoLoader.load("fm", jsonFMStations);
		
		//AM
		List<Station> amStations = radioFileParser.parse("files/amshort.txt");
		
		List<String> jsonAMStations = new ArrayList<String>();

		for (Station station : amStations) {
			jsonAMStations.add(gson.toJson(station));
		}
		
		mongoLoader.drop("am");
		mongoLoader.load("am", jsonAMStations);
	}
}
