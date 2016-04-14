package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;


import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.util.SerializationUtils;
import org.jsoup.Jsoup;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import util.Porterstemmer;
import aPI.SearchProvider;
import aPI.searchAPIs;
import parser.ListFinderHTML;





public class Seed {
	


	private static TokenizerFactory tokenizer =  new DefaultTokenizerFactory();;


	public static ArrayList<String> expandSeed(ArrayList<String> seedList, int noOfResults, String searchEngine, WordVectors vec) throws Exception {
		searchAPIs sp = new searchAPIs();
		StringBuilder tempSeed = new StringBuilder();
		for(String seed : seedList){
			tempSeed.append(seed).append(" ");
		}

		ListFinderHTML listFinder = new ListFinderHTML();		//Find patterns from web pages

		ArrayList<String> listTokens = new ArrayList<>();
		
		HashMap<String, Double> distance = new HashMap<>();

		double d = 0.0;

		System.out.println("listing urls");			
		ArrayList<String> listpages = sp.apiType(searchEngine, seedList, noOfResults);
		// Query the given seed list in the required search engine
		System.out.println(listpages.size());
		for(int j=0; j<listpages.size(); j++){
			
			String text = "";
			String html = "";
			html = SearchProvider.getHtml(listpages.get(j));
			text = Jsoup.parse(html).text().toLowerCase();
			HashSet<String> tokens = split(text);
			listFinder.SetHTML(text);
			ArrayList<String> webList = new ArrayList<String>();
			while ((webList = listFinder.getNextList()) != null) {
				if (webList.size() > 0) {
					if (ListUtil.getOverLap(webList, seedList) >= 2) {
						listTokens.addAll(webList);
					}
				}
			}
			System.out.println("==+++++++++++++++++++++++++++++++====================="+words);
			for (String word : tokens) {
				d = 0;
				if (isValidWord(word) && !seedList.contains(word)
						&& !distance.containsKey(word)) {
					for (String seedWord : seedList) {
						d += vec.similarity(word, seedWord);
					}
				}
				if (d > 0.0) {
					if (listTokens.contains(word)) {
						d = d + 5; // Add an additional weight
					}
					distance.put(word, d);
				}
			}

			listTokens.clear();
		}

		Set<Entry<String, Double>> set = distance.entrySet();
		List<Entry<String, Double>> list = new ArrayList<>(set);
		Collections.sort(list, new Comparator<Entry<String, Double>>() {
			@Override
			public int compare(Entry<String, Double> a, Entry<String, Double> b) {
				if (a.getValue() > b.getValue()) {
					return -1;
				} else
					return 1;
			}
		});
		
		ArrayList<String> retValue = new ArrayList<>();
		for (int i = 0; i < noOfResults && i < list.size(); i++) {
			System.out.println(list.get(i));
			retValue.add(list.get(i).getKey());
		}
		System.out.println("=================NEW SEED " +retValue+"===============");
		//System.out.println("New Seed :  "+tempSeed.toString());
	return seedList;
	}

	
	public static String getStemmedWord(String word){
	    Porterstemmer s = new Porterstemmer();
		s.add(word.toLowerCase().toCharArray(), word.length());				// Perform stemming on the given word
		s.stem();
		return s.toString();
	}
	
	public static HashSet<String> split(String text){
		
		HashSet<String> tokens = new HashSet<>();
		
		StringTokenizer tokenize = new StringTokenizer(text, " |\\?|\\.|/|:|\\+|%|=|&|\n|\\$|,|_|;|\\(|\\)|\\{|\\}|\\[|\\]|&|%");
		
		while(tokenize.hasMoreTokens()){
			tokens.add(tokenize.nextToken());
		}
		return tokens;
	}

	public static boolean isValidWord(String smallWord) {
		//Matcher matcher = nonWordPattern.matcher(smallWord);
		HashSet<String> stopWords = new HashSet<String>();

		BufferedReader reader=null;

		
		try {
			reader = new BufferedReader(new FileReader("stopwords"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stopWords.addAll( Arrays.asList(reader.readLine().split(",")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				// Read file containing stop words
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//if(matcher.find() || stopWords.contains(smallWord) || smallWord.length()<2 ){
		if(smallWord.length()<=2 || stopWords.contains(smallWord)){								 // Consider words of length greater than 2 and are not stop words					
			//System.out.println("This is Invalid Concept (stopword or not-word) : " + word);
			return false;
		}
		return true;
	}
	

	
	/*public static String getNewSeed(String oldSeedString, String pageHtml,ArrayList<String> seedList, WordVectors vec){
		double weightage= 0.0;
		String newSeed = "";
		Tokenizer tf = tokenizer.create(pageHtml);   // tokenize the overall HTML into set of words
		while(tf.hasMoreTokens()){
			String htmlToken = tf.nextToken().toLowerCase();    // convert each word to lower case
			if(isValidWord(htmlToken)){					// Check for stop word
				htmlToken=getStemmedWord(htmlToken);		// Perform Stemming
				if (!seedList.contains(htmlToken)){				// Check if word is present in current seed list
					double temp = vec.similarity(oldSeedString, htmlToken);
					if (weightage< temp ){
						newSeed = htmlToken;					// finding a word which is not already in the seed list and has the highest similarity
						weightage = temp;
					}
				}
			}
		}
		return newSeed;										// return the new seed
	}*/
}