package aPI;
import twitter4j.*;
import twitter4j.api.SearchResource;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Twitter {

	public void Twit(ArrayList<String> seed, int noOfRes) 
	{
		System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
		System.getProperties().put("http.proxyPort", "8080");
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("ZvyHY7wciiK7WMHSvCLow8755")
		  .setOAuthConsumerSecret("rirtzaACfiINS6nOaQajckqelWnDrqEMIQ3Hz4XTSaiFd8K5xD")
		  .setOAuthAccessToken("3066359394-v3ExCcaKVxksplTxk3EmANIylScClNdZrjVsnq3")
		  .setOAuthAccessTokenSecret("6dMcJElEZyQmq1JfvDm4Vw2DxI9Q4mdQhQPXUmFsOqW0P");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
        Scanner scanner = new Scanner(System.in);
      //  System.out.println("Please enter the search term.");
       // String searchTerm = scanner.nextLine();
		twitter4j.Twitter twitter = tf.getInstance();
		String searchTerm="";
		for(int i=1;i< seed.size();i++)
		{
			searchTerm = searchTerm + " " + seed.get(i);
			
		}
	//	searchTerm = "honda chevrolet maruti suzuki";
		//System.out.println(searchTerm);
		try {
		Query query = new Query(searchTerm);
		QueryResult result;
		int count=0;
		
		do {
		result = ((SearchResource) twitter).search(query);
		List<Status> tweets = result.getTweets();
		System.out.println(result);

		for (Status tweet : tweets) {
			count++;
			String s = tweet.getUser().getScreenName();
			if (!s.toLowerCase().contains(searchTerm.toLowerCase()))
				System.out.println("@" + s + " - " + tweet.getText());
		}
		} while ((query = result.nextQuery()) != null && count <= 10);
		
		System.exit(0);
		} catch (TwitterException te) {
		te.printStackTrace();
		System.out.println("Failed to search tweets: " + te.getMessage());
		System.exit(-1);
		}
	}

	
		
	}

