package aPI;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.ListFinderHTML;
import main.Wrapper;

public class Google {
	//public static final String GOOGLE_SEARCH_URL = "http://www.google.com/search";
	public ArrayList<String> Google(ArrayList<String> seed, int noOfRes) throws IOException
	{
		System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
		System.getProperties().put("http.proxyPort", "8080");
		String GOOGLE_SEARCH_URL = "https://www.google.com/search";
        String searchTerm=seed.get(0);
		for(int i=1;i<seed.size();i++)
		{
			searchTerm = searchTerm + "+" + seed.get(i);
			
		}
		
		int num = 10;
		System.out.println("searching for :" + searchTerm);
		String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+num;
        //without proper User-Agent, we will get 403 error
		
		System.out.println(searchURL);
		Document doc =
			    Jsoup.connect(searchURL)
			         .userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
			         .timeout(5000).get();

         
        //below will print HTML data, save it to a file and open in browser to compare
       // System.out.println(doc.html());
         
        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly
        Elements results = doc.select("h3.r > a");
        ArrayList<String> listpages = new ArrayList<String>();
        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            if(linkHref.substring(7, linkHref.indexOf("&")).contains("http://") || 
            		linkHref.substring(7, linkHref.indexOf("&")).contains("https://" ))
            {
            	listpages.add(linkHref.substring(7, linkHref.indexOf("&")));
            	//Document doc1 = Jsoup.connect(linkHref.substring(7, linkHref.indexOf("&"))).userAgent("Mozilla/5.0").get(); // HTML source text for all URLS
            	/*Document doc1 = null;
            	try {
            		  doc1 = Jsoup.connect(linkHref.substring(7, linkHref.indexOf("&"))).timeout(1000000).userAgent("Mozilla/5.0").get();
    			} catch(Exception e) {
    				continue;
    				
    			}*/
//           
           // 	System.out.println(doc1.toString());
  //          	String webDoc =  doc1.toString().replaceAll("!|@|#|\\$|%|~|`|\\^|\\*|\\(|\\)|-|_|\\+|=|\\{|\\}|\\[|\\]|;|:|'|\"|<|>|,|\\.|\\?|/", " ").replaceAll("( )+", " ").trim().toString().toLowerCase();
 //           	webDoc = webDoc.replaceAll("\n", " ").trim().replaceAll("( )+", " ");
           // 	System.out.println(webDoc);
//          
            	
//            	
            	/*ListFinderHTML extraction = new ListFinderHTML();
            	extraction.setMyHTML(doc1.toString());
            	extraction.SetHTML();
            	System.out.println("title is " + extraction.getTitle());
            	while(true)
            	{	
            		System.out.println("list is " + extraction.getNextList());	
                	System.out.println("list is " + extraction.getNextList());	
                	System.out.println("list is " +extraction.getNextList());	
                	System.out.println("list is " +extraction.getNextList());	
                	System.out.println("header is " +extraction.getHeader());	
                	System.out.println("description is " +extraction.getDescription());
            		if(extraction.getNextList() == null)
            			break;
            	
            	}*/
            }            
        }
		return listpages;
		
		
	}

}
