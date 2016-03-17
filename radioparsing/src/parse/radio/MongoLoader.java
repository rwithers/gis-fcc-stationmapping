package parse.radio;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoLoader {
	
	private static MongoDatabase db = null;
	
	public void load(String collectionName, List<String> jsonList){
		List<Document> documents = new ArrayList<Document>();
		for(String json : jsonList){
			documents.add(Document.parse(json));
		}
		getDb().getCollection(collectionName).insertMany(documents);
		System.out.println(documents.size() + " documents inserted into " +  collectionName);
	}
	
	public void load(String collectionName, String json){
		MongoDatabase db = getDb();
		Document document = Document.parse(json);
		db.getCollection(collectionName).insertOne(document);
	}
	
	private synchronized MongoDatabase getDb(){
		if(db == null){
			MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/test"));
			db = mongoClient.getDatabase("test");
		}
		return db;
	}

	public void drop(String collectionName) {
		getDb().getCollection(collectionName).drop();
	}
}
