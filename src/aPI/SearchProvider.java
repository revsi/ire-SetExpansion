package aPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

@SuppressWarnings("deprecation")
public class SearchProvider {
	private static MongoClient mongoClient;
	private static DB db;
	private static DBCollection webCollection;
	private static DBCollection searchCollection;

	static {
		try{
			System.setProperty("https.proxyHost", "proxy.iiit.ac.in");
			System.setProperty("https.proxyPort", "8080");						// Remove these lines if not under a proxy

			mongoClient = new MongoClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db = mongoClient.getDB("BING");
		webCollection= db.getCollection("urlCollection");					// Mongo database for storing all the URLs obtained
		searchCollection = db.getCollection("searchCollection");			// Mongo database for storing all the search queries
	}


	private static String constructQuery(ArrayList<String> seedList) {
		String ret = new String();
		for(String s : seedList){
			ret = ret + " " + s;				// construct the query from the given the seed list
		}
		return ret;
	}	

	public String getPageHtml(String url){
		BasicDBObject query = new BasicDBObject();
		query.put("url", url);
		DBCursor cur = webCollection.find(query);

		if(cur.count()>0){								// get the page HTML from the given webpage
			return (String)cur.next().get("html");
		}else{
			String html = getHtml(url);
			insert(url,html);
			return html;
		}
	}

	public static String getHtml(String url){
		BufferedReader in;
		String inputLine;
		StringBuilder sb = new StringBuilder();
		try {
			in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			while ((inputLine = in.readLine()) != null){
				sb.append(inputLine).append("\n");
			}
		} catch (MalformedURLException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return Jsoup.parse(sb.toString()).text();
	}

	private void insert(String url, String html){
		BasicDBObject obj = new BasicDBObject("url",url).append("html", html);  // Insert the given URL in the database
		webCollection.insert(obj);
	}	
}