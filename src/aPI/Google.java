package aPI;

import java.io.IOException;
import java.util.Scanner;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Google {
	//public static final String GOOGLE_SEARCH_URL = "http://www.google.com/search";
	public void Google(String line)
	{
		System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
		System.getProperties().put("http.proxyPort", "8080");
		String GOOGLE_SEARCH_URL = "http://www.google.com/search";
		String arr[];
		arr = line.split(" ");
		String searchTerm = arr[0];
		int num = 10;
		System.out.println("searching for :" + searchTerm);
		String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+num;
        //without proper User-Agent, we will get 403 error
        Document doc = null;
		try {
			doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        //below will print HTML data, save it to a file and open in browser to compare
        //System.out.println(doc.html());
         
        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly
        Elements results = doc.select("h3.r > a");
 
        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
        }
		
		
	}

}
