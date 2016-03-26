/**
 * 
 */
package main;
import aPI.Twitter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
		
		Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the input file.");
        String inputFilePath = scanner.nextLine(); // Provide input file path containing set of related words
        System.out.println("Please enter the output file.");
        String outputFileName = scanner.nextLine();			// Provide output file path which will contain the expanded set
		System.out.println("Please enter number of results you want.");
		int noOfResults = scanner.nextInt();
		BufferedReader reader = null;
		FileWriter writer = null;
		String line;
		ArrayList<String> seedList = new ArrayList<String>();
		
		try {
			reader = new BufferedReader(new FileReader(inputFilePath));
			writer = new FileWriter(outputFileName);
			while ((line = reader.readLine()) != null && !line.equals("")) {
				Log.log.info("======= "+line+" =======");
				Twitter twit = new Twitter();

			    twit.Twit(line);
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
