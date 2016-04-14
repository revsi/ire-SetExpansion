/**
 * 
 */
package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.util.SerializationUtils;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.core.io.ClassPathResource;


import aPI.searchAPIs;
import parser.Seed;
import ranker.W2vtrain;
import ranker.seed;
import util.Log;
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
		// TODO Auto-generated method stub
		//BasicConfigurator.configure();
		//Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the input file.");
        String inputFilePath = "input"; // Provide input file path containing set of related words
        /*System.out.println("Please enter the output file.");
        String outputFileName = scanner.nextLine();			// Provide output file path which will contain the expanded set
        System.out.println("Please enter the search API you want to use .");
		String API = scanner.nextLine();
		System.out.println("Please enter number of results you want.");
		int noOfResults = scanner.nextInt();
		
		System.out.println(API);
		BufferedReader reader = null;
		FileWriter writer = null;*/
		String line = "honda bmw chevrolet ford";
		ArrayList<String> seedList = new ArrayList<String>();

		try {
			new W2vtrain().word2VecTraining();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};								// Training the Word2Vec Model

		WordVectors vec = null;
		try {
			vec = WordVectorSerializer.loadTxtVectors(new File("vec.ser"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String arr[];
		arr = line.split(" ");
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

		//seed.get(index);
		
	/*	try {
			reader = new BufferedReader(new FileReader(inputFilePath));
			writer = new FileWriter(outputFileName);
			while ((line = reader.readLine()) != null && !line.equals("")) {
				Log.log.info("======= "+line+" =======");
				searchAPIs sp = new searchAPIs();
				sp.apiType(API,line,noOfResults);
				seedList.addAll(Arrays.asList(line.toLowerCase().split(" ")));	
				try {							
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reader.close();
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

/*		Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the input file.");
        String inputFilePath = scanner.nextLine(); // Provide input file path containing set of related words
        
		BufferedReader reader = null;
		String line,linet="";
		ArrayList<String> seedList = new ArrayList<String>();
		seedList.add("apple");
		seedList.add("mango");
		seedList.add("orange");
		
		try {
			reader = new BufferedReader(new FileReader(inputFilePath));
			while ((line = reader.readLine()) != null) {
				try {
					linet += line;
		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(linet);
			reader.close();
			WrapperExtraction we = new WrapperExtraction();
			Wrapper wp = new Wrapper();
			wp.wrap(linet, seedList);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
*/
	}

}