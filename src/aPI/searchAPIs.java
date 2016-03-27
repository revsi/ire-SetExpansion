package aPI;

import java.io.IOException;

import org.json.JSONException;

public class searchAPIs
{
	public void apiType(String api,String line,int noOfResults) throws IOException, JSONException
	{
		
		switch (api) {
		case "BING":
			Bing b = new Bing();
		    b.Bin(line);
			break;
		case "GOOGLE":
			Google gog = new Google();
			gog.Google(line);
			break;
		case "TWITTER":
			Twitter twit = new Twitter();
			twit.Twit(line);
			break;
		case "YANDEX":
			Yandex yan = new Yandex();
			yan.yandex(line);
			 break;
		case "FAROO":
			Faroo fr = new Faroo();
			fr.Faroo(line);
		      break;
		case "WIKI":
			 break;
		default :
			System.out.println("Sorry , you have entered a wrong API !!!!");
			break;
		
		
		
	}

 }
}