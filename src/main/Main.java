/**
 * 
 */
package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;

import parser.Seed;
import ranker.W2vtrain;
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

	    try {
			//vec = WordVectorSerializer.loadTxtVectors(new File("dep.words"));
			
			vec = WordVectorSerializer.loadTxtVectors(new File("glove_data_200.txt"));

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Collection<String> similar = vec.wordsNearest("india", 10);
		System.out.println("Similar words to 'india' : " + similar);
	    
        String y = "exit";
        while(true )
        {
            System.out.println("Please enter your Query. Type EXIT to end the program");
    		String query = scanner.nextLine();

        	if(query.toLowerCase().equals(y))
        		 System.exit(0);
        	else
        	{
        		ArrayList<String> seedList = new ArrayList<String>();

        		try {
        			new W2vtrain().word2VecTraining();
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		};								// Training the Word2Vec Model

        		String arr[];
        		arr = query.split(" ");
        		for(int i=0;i<arr.length;i++)
        		{
        			seedList.add(arr[i]);
        		}
        		
        		       
                
        		String searchEngine = "bing";
        		int noofresults = 10;
        		
        		System.out.println(seedList);
        		
        		try {
        			ArrayList<String> list = Seed.expandSeed(seedList,noofresults,searchEngine, vec);
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		
        	}
        }
		
	}

}