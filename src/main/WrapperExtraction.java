package main;
import java.util.*;

public class WrapperExtraction {
	
	static public Vector KMP(String text, String wrapper)
	{
		Vector ans = new Vector();
		
		int M = wrapper.length();
		int N = text.length();
		int len = 0;
		int[] lps = new int[len+1];
		
		int i=1;
		
		while(i<M)
		{
			if(wrapper.charAt(i) == wrapper.charAt(len))
			{
				len++;
				lps[i] = len;
				i++;
			}
			else
			{
				if(len != 0)
					len = lps[len-1];
				else
				{
					lps[i]=0;
					i++;
				}
			}
		}
		i=0;
		int j=0;
		while(i<N)
		{
			if(wrapper.charAt(j) == text.charAt(i))
			{
				i++;
				j++;
			}
			if(j==M)
			{
				ans.addElement(i);
				j = lps[j-1];
			}
			else if(i<N && wrapper.charAt(j) != wrapper.charAt(i) )
			{
				if(j!=0)
					j = lps[j-1];
				else
					i++;
			}
		}
		
		return ans;
		
	}
}