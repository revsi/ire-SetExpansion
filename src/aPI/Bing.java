package aPI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Bing {

	public void Bin(String line) throws IOException, JSONException 
	{
		System.getProperties().put("http.proxyHost", "proxy.iiit.ac.in");
		System.getProperties().put("http.proxyPort", "8080");
		URL url = null;
		HttpURLConnection conn = null;
		BufferedReader br = null;
		String arr[];
		arr = line.split(" ");
		String searchTerm = arr[0];
		for(int i=1;i< arr.length;i++)
		{
			searchTerm = searchTerm + " " + arr[i];
		}
		
		String query = searchTerm;
		query = query.replaceAll(" ", "%20");
		String accountKey = "rLSasvRW9cvlU5fG9hoSGjJG2M1eIjR+Ld27nFC9Pj8=";
		byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
		String accountKeyEnc = new String(accountKeyBytes);
		try {
			url = new URL("https://api.datamarket.azure.com/Data.ashx/Bing/Search/v1/Web?Query=%27" + query + "%27&$top="+"10"+"&$format=json");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
		conn.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
		br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		StringBuilder sb = new StringBuilder();
		String output;
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}
		conn.disconnect();
		JSONObject obj;
		obj = new JSONObject(sb.toString());
		JSONArray arr1 = obj.getJSONObject("d").getJSONArray("results");
		for (int i = 0; i < arr1.length(); i++){
			String post_id = arr1.getJSONObject(i).getString("Url");
			System.out.println(post_id);
			Document doc =null;
			try {
				doc = Jsoup.connect(post_id).timeout(1000000).userAgent("Mozilla/5.0").get();
			} catch(Exception e) {
				continue;
				
			}
			System.out.println(doc);
			 
			//String description=arr1.getJSONObject(i).getString("Description");		// Obtain all the urls for the given search query
			//@SuppressWarnings("unused")
			//String title=arr1.getJSONObject(i).getString("Title");
			//System.out.println(description);
			//Document doc1 = Jsoup.connect(post_id).userAgent("Mozilla/5.0").get(); // HTML source text for all URLS
			/*myfinder.SetHTML(post_id);
			LogUtil.log.info("aaa "+title+" "+ post_id+" "+description);
			WebPage page = new WebPage(title, post_id,description);
			listPages.add(page);*/
		}

	}
}