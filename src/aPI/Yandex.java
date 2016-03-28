package aPI;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class Yandex
{
	public void yandex(String str)
		{
		System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
		System.getProperties().put("http.proxyPort", "8080");
		int starting = 0,records = 10 ;
		String YandexKey = "03.373869876%3A821c7fc2f1a23d592005fda98376f0d1";
		Set<String> result=new HashSet<String>();
		Set<String> finalresult=new HashSet<String>();
		URL url;
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.iiit.ac.in", 8080));  // remove if not under a proxy
		HttpURLConnection conn=null;
		BufferedReader br = null;
		String arr[];
		arr = str.split(" ");
		String searchTerm = arr[0];
		for(int i=1;i< arr.length;i++)
		{
			searchTerm = searchTerm + " " + arr[i];
		}
		String query = searchTerm;
		
		System.out.println(query);
		query = query.replaceAll(" ","%20");
		try{
			while(starting < records)
			{
				//System.out.println("inside");
				String start=Integer.toString(starting);
				url=new URL("https://xmlsearch.yandex.com/xmlsearch?l10n=en&user=uid-lygh3cov&key="+YandexKey+"&query="+query+"&page="+start);
				System.out.println(url);
				conn=(HttpURLConnection) url.openConnection(proxy);
				conn.setRequestMethod("GET");
				conn.disconnect();
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
			
				Document doc = db.parse(url.openConnection(proxy).getInputStream());
			    
				NodeList nList = doc.getElementsByTagName("group");
				System.out.println(nList.getLength()); 
		
				for (int temp = 0; temp < nList.getLength(); temp++)
				{
					Node nNode = nList.item(temp);
					 
					if (nNode.getNodeType() == Node.ELEMENT_NODE) 
					{
						
						Element eElement = (Element) nNode;
						 String resp=eElement.getElementsByTagName("url").item(0).getTextContent();// obtain all the urls for a given query
						 System.out.println(resp);
						 finalresult.add(resp);
					}
				}
				starting+=10;
			}
		}catch(Exception E){
			System.out.println("Error");
		}
}
}
