package aPI;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLEncoder;

import org.apache.http.client.HttpClient;
public class Wiki {

	public void Wiki(String str) {
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
		String query = arr[0];
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
				
				
			}
		
		
	}
   catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

}
