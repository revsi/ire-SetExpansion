/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import util.Log;
/**
 * @author rajat
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String inputFilePath=args[0];						// Provide input file path containing set of related words
		String outputFileName=args[1];						// Provide output file path which will contain the expanded set
		int noOfResults = Integer.parseInt(args[2]);
		
		BufferedReader reader = null;
		FileWriter writer = null;
		String line;
		
		ArrayList<String> seedList = new ArrayList<String>();
		
		try {
			reader = new BufferedReader(new FileReader(inputFilePath));
			writer = new FileWriter(outputFileName);
			while ((line = reader.readLine()) != null && !line.equals("")) {
				Log.log.info("======= "+line+" =======");
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

	}

}
