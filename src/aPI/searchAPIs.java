package aPI;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class searchAPIs
{
	public ArrayList<String> apiType(String api,ArrayList<String> seedList,int noOfResults) throws IOException, JSONException
	{
		
		api = api.toUpperCase();
		switch (api) {
		case "BING":
			Bing b = new Bing();
		    return b.Bin(seedList,noOfResults);
			
		case "GOOGLE":
			Google gog = new Google();
			return gog.Google(seedList,noOfResults);
			
		case "TWITTER":
			Twitter twit = new Twitter();
			twit.Twit(seedList,noOfResults);
			break;
		case "YANDEX":
			Yandex yan = new Yandex();
			return yan.yandex(seedList,noOfResults);
			
		case "FAROO":
			Faroo fr = new Faroo();
			fr.Faroo(seedList,noOfResults);
		      break;
		case "WIKI":
			Wiki wk = new Wiki();
			wk.Wiki(seedList,noOfResults);
			 break;
		default :
			System.out.println("Sorry , you have entered a wrong API !!!!");
			break;
				
	}
		return seedList;

 }
}