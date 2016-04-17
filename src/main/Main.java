/**
 * 
 */
package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import parser.Seed;
import twitter4j.JSONException;
/**
 * @author sonam
 *
 */
public class Main {

	/**
	 * 
	 
	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) {
		
		//Logger.getAnonymousLogger().setLevel(Level.OFF);

		// TODO Auto-generated method stub
		//BasicConfigurator.configure();
		Scanner scanner = new Scanner(System.in);
 
		WordVectors vec = null;

		Logger root = (Logger) LoggerFactory
		        .getLogger(Logger.ROOT_LOGGER_NAME);

		root.setLevel(Level.OFF);
		
		//LoggerContext loggerContext = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();

		//loggerContext.stop();
		
	    try {
			//vec = WordVectorSerializer.loadTxtVectors(new File("dep.words"));
			
			vec = WordVectorSerializer.loadTxtVectors(new File("wiki_model.txt"));

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	//	Collection<String> similar = vec.wordsNearest("india", 10);
	//	System.out.println("Similar words to 'india' : " + similar);
	    
        String y = "exit";
        int noofresults = 7;
        while(true )
        {
            System.out.println("Please enter your Query or Type EXIT to end the program");
    		String query = scanner.nextLine();
    		
        	if(query.toLowerCase().equals(y))
        	{
        		System.out.println("program terminated!!!");
        		System.exit(0);
        	}
        	else
        	{
        		ArrayList<String> seedList = new ArrayList<String>();
        	/*	try {
        			new W2vtrain().word2VecTraining();
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		};		*/						// Training the Word2Vec Model

        		String arr[];
        		arr = query.split(" ");
        		for(int i=0;i<arr.length;i++)
        		{
        			seedList.add(arr[i]);
        		}
        		
        		       
                
        		String searchEngine = "google";
        		     	
        		
        		try {
        			ArrayList<String> list = Seed.expandSeed(seedList,noofresults,searchEngine, vec,stopWords);
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		
        	}
        }
		
	}

}