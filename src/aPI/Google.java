package aPI;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.Wrapper;

public class Google {
	//public static final String GOOGLE_SEARCH_URL = "http://www.google.com/search";
	public void Google(String line) throws IOException
	{
		System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
		System.getProperties().put("http.proxyPort", "8080");
		String GOOGLE_SEARCH_URL = "http://www.google.com/search";
		String arr[];
		ArrayList<String> seedList = new ArrayList<String>();
		arr = line.split(" ");
		String searchTerm = arr[0];
		for(int i=1;i< arr.length;i++)
		{
			searchTerm = searchTerm + "+" + arr[i];
			seedList.add(arr[i]);
		}
		
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
       // System.out.println(doc.html());
         
        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly
        Elements results = doc.select("h3.r > a");
 
        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            System.out.println("Text::" + linkText + ", URL::#" + linkHref.substring(7, linkHref.indexOf("&"))+"#");
            if(linkHref.substring(7, linkHref.indexOf("&")).contains("http://") || 
            		linkHref.substring(7, linkHref.indexOf("&")).contains("https://" ))
            {
            	Document doc1 = Jsoup.connect(linkHref.substring(7, linkHref.indexOf("&"))).userAgent("Mozilla/5.0").get(); // HTML source text for all URLS
               // System.out.println(doc1);
            	Wrapper wp = new Wrapper();
            	wp.wrap(doc1.text(),seedList);
            	
                
            }
            
        }
		
		
	}

}
