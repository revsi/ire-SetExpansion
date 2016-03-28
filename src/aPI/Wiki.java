package aPI;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLEncoder;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class Wiki {

	public void Wiki(String str) throws IOException {
		// TODO Auto-generated constructor stub
		String url = "https://en.wikipedia.org/w/";
		HttpClient client;
		String urlQuery = url + "api.php?action=query&format=xml";
		String xmlOutput;
		int i = 0;
		int count;
		String urlOffset = "&gsrlimit=50&gsroffset=";
		String offset = "";
		String arr[];
		arr = str.split(" ");
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.iiit.ac.in", 8080));
		String searchTerm = arr[0];
		for(int j=1;j< arr.length;j++)
		{
			searchTerm = searchTerm + " " + arr[j];
		}
		System.out.println(searchTerm);
		String query = searchTerm;
try {
			
			query = URLEncoder.encode(query, "UTF-8");
			urlQuery += "&generator=search&gsrsearch="
					+ query
					+ "&gsrprop=snippet&prop=info&inprop=url";
			for (i = 0; i < 100; i += 50) {

				offset = urlQuery + urlOffset + i;
				System.out.println(offset);
			//	xmlOutput = HttpQueries.sendGetQuery(offset, client);
				//count = extractURLs(xmlOutput);
		//		if (count <= 50)
			//		break;
				
				
				// I am adding my part
				System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
				System.getProperties().put("http.proxyPort", "8080");
				Document doc = Jsoup.connect(offset).userAgent("Mozilla/5.0").get();
				//System.out.println(doc.toString());
				
				//Pasrsing the page
				String wikiDoc = doc.toString();
				int index = 0;
				while(index != -1){
					if(wikiDoc.indexOf("title=\"", index) == -1)
						break;
					
					System.out.println("Title : " + wikiDoc.substring((index = wikiDoc.indexOf("title=\"", index) + 7),(index =  wikiDoc.indexOf("\"", (index + 1)) + 1) - 1));
					System.out.println("URL : " + wikiDoc.substring((index = wikiDoc.indexOf("fullurl=\"", index) + 9) ,(index = wikiDoc.indexOf("\"", (index + 1)) + 1) - 1));
					Document doc1 =null;
					try {
						doc1 = Jsoup.connect(wikiDoc.substring((index = wikiDoc.indexOf("fullurl=\"", index) + 9) ,(index = wikiDoc.indexOf("\"", (index + 1)) + 1) - 1)).timeout(1000000).userAgent("Mozilla/5.0").get();
					} catch(Exception e) {
						continue;
						
					}
					System.out.println(doc1);					
					}
				
			}
		
		
	}
   catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

}
