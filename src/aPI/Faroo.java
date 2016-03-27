package aPI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.Log;

public class Faroo {

	public void Faroo(String str) {
		// TODO Auto-generated constructor stub
		int starting=1,records = 10;
		Set<String> result=new HashSet<String>();
		Set<String> finalresult=new HashSet<String>();
		URL url;
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.iiit.ac.in", 8080));   // remove if not under a proxy
		HttpURLConnection conn=null;
		BufferedReader br=null;
		String FarooKey = "0hab7LSvVHwzv-aqvNyzljNfZoo_";
		String arr[];
		arr = str.split(" ");
		String searchTerm = arr[0];
		for(int i=1;i< arr.length;i++)
		{
			searchTerm = searchTerm + " " + arr[i];
		}
		String query = searchTerm;
		try{
			while(starting<records){
				String start=Integer.toString(starting);
				//query = query.replaceAll(" ","%20");
				url=new URL("http://www.faroo.com/api?q="+query+"&start="+start+"&length=10&l=en&src=web&i=false&f=json&key="+FarooKey);
				Log.log.info("======= "+url+" =======");
				conn=(HttpURLConnection) url.openConnection(proxy);
				conn.setRequestMethod("GET");
				br=new BufferedReader(new InputStreamReader((conn.getInputStream())));
				StringBuilder sb=new StringBuilder();
				String line;
				while((line=br.readLine())!=null)				
					sb.append(line);
				result = jsonParser(sb.toString());				// Obtain all the URLs
				if(result.size()==0) break;
				for(String local:result)
					{
					finalresult.add(local);
					System.out.println(local);
					}
				starting=finalresult.size();
			}
		}catch(Exception E){
			System.out.println("Error Retreiving Results");
		}
		
		
	}
	public Set<String> jsonParser(String s) throws JSONException{
		Set<String> output=new HashSet<String>();
		JSONObject obj=new JSONObject(s);
		JSONArray arr=obj.getJSONArray("results");					// parse the json for all the urls
		for(int i=0;i<arr.length();i++){
			String geturl=arr.getJSONObject(i).getString("url");
			output.add(geturl);
		}
		return output;
	}

}
