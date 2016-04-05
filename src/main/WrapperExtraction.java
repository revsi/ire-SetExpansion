package main;
import java.util.*;

public class WrapperExtraction {
	
	static public Vector KMP(String text, String wrapper,int side)
	{
		Vector ans = new Vector();
		
		int M = wrapper.length();
		int N = text.length();
/*		System.out.println(M);		
		System.out.println(N);
*/		int len = 0;
		int[] lps = new int[M];
		
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
/*		for(int mmm=0;mmm<lps.length;mmm++)
			System.out.println(lps[mmm]);

		System.out.println("");
*/		i=0;
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
				if(side == 0)
					ans.addElement(i);
				else
					ans.addElement(i-j);

				j = lps[j-1];
			}
			else if(i<N && wrapper.charAt(j) != text.charAt(i) )
			{
				if(j!=0)
					j = lps[j-1];
				else
					i++;
			}
		}
		System.out.println(ans);
		return ans;
		
	}
}