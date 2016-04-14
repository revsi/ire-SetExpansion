package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ListUtil {

	public static int getOverLap(Iterable<String> mylist, Iterable<String> seedList) {
		int count =0;
		for(String seed : seedList){
			for(String item : mylist){
				if(item.toLowerCase().contains(seed)){
					count++;
					break;
				}
			}
		}
		//System.out.println(count);
		return count;
	}

	public static double getOverLapWithoutStopWords(Iterable<String> list1, Iterable<String> list2) {
		double count =0;
		//System.out.println("List1 : "+ list1);
		//System.out.println("List2 : "+ list2);
		for(String w1 : list1){
			if(isValidWord(w1)){
				for(String w2 : list2){
					if(isValidWord(w2)){
						//if(w2.contains(w1) || w1.contains(w2)){ count++;break;}
						count+=compareStrings(w1, w2);
					}
				}
			}
		}
		return count;
	}
	
	public static double compareStrings(final String s1, final String s2)
    {
        double retval = 0.0;
        final int n = s1.length();
        final int m = s2.length();
        if (0 == n)
        {
            retval = m;
        }
        else if (0 == m)
        {
            retval = n;
        }
        else
        {
            retval = 1.0 - (compare(s1, n, s2, m) / (Math.max(n, m)));
        }
        return retval;
    }
	
	 private static double compare(final String s1, final int n, 
             final String s2, final int m)
			{
			int matrix[][] = new int[n + 1][m + 1];
			for (int i = 0; i <= n; i++)
			{
			matrix[i][0] = i;
			}
			for (int i = 0; i <= m; i++)
			{
			matrix[0][i] = i;
			}
			
			for (int i = 1; i <= n; i++)
			{
			int s1i = s1.codePointAt(i - 1);
			for (int j = 1; j <= m; j++)
			{
			  int s2j = s2.codePointAt(j - 1);
			  final int cost = s1i == s2j ? 0 : 1;
			  matrix[i][j] = min3(matrix[i - 1][j] + 1, 
			                      matrix[i][j - 1] + 1, 
			                      matrix[i - 1][j - 1] + cost);
			}
			}
			return matrix[n][m];
			}

	
	    private static int min3(final int a, final int b, final int c)
	    {
	        return Math.min(Math.min(a, b), c);
	    }
	    
	public static boolean isValidWord(String smallWord) {
		//Matcher matcher = nonWordPattern.matcher(smallWord);
		//if(matcher.find() || stopWords.contains(smallWord) || smallWord.length()<2 ){
		
		HashSet<String> stopWords = new HashSet<String>();

		BufferedReader reader = null;
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
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=0;
		if(smallWord.length()<=2 || stopWords.contains(smallWord)){
			//System.out.println("This is Invalid Concept (stopword or not-word) : " + word);
			return false;
		}
		
		for(i=0; i<smallWord.length(); i++){
			if(!Character.isAlphabetic(smallWord.charAt(i))){
				return false;
			}
		}
		
		return true;
	}


}