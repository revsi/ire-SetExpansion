package main;
import java.util.*;

public class Wrapper {
	
	static Vector wrappersLeft = new Vector();
	static Vector wrappersRight = new Vector();
	static Hashtable wrapSelectLeft = new Hashtable();
	static Hashtable wrapSelectRight = new Hashtable();
	
	
	//Identifying wrappers by traversing the trie
	public static void traverse(trie.TrieNode root, String st, int side, int seedSize)
	{
		if(root == null)
			return;
		for(int i=0;i<26;i++)
			traverse(root.child[i],st+(char)('a'+i), side, seedSize);
		
		if(st.length() <=5)
			return;
		else if(side == 0)							//Left side wrapper
		{
			int size = root.isEnd.keySet().size();
			if( size != seedSize)
				return;
			Set keys = root.wrapNum.keySet();
			for(Object key:keys)
			{
				if(wrapSelectLeft.get(key) == null)
				{
					wrapSelectLeft.put(keys, 1);
					wrappersLeft.addElement(st);
				}
			}			
		}
		else
		{
			int size = root.isEnd.keySet().size();
			if( size != seedSize)
				return;
			Set keys = root.wrapNum.keySet();
			for(Object key:keys)
			{
				if(wrapSelectLeft.get(key) == null)
				{
					wrapSelectRight.put(keys, 1);
					wrappersRight.addElement(st);
				}
			}			
		}
	}
	
	public static void wrap(String htmlText, ArrayList<String> seedList)
	{
		int noOfSeeds = seedList.size();
		int htmlLen = htmlText.length();
		int fl;
		trie.TrieNode trieLeft = trie.newn();
		trie.TrieNode trieRight = trie.newn();
		int wrapNum = 1;
		for(int i=0;i<noOfSeeds;i++)
		{
			int seedLen = seedList.get(i).length();
			
			for(int j=0;j<htmlLen-seedLen+1;j++)
			{
				fl = 0;
				for(int k=0;k<seedLen;k++)
				{
					if(htmlText.charAt(j+k) != seedList.get(i).charAt(k))
					{
						fl = 1;
						break;
					}
				}
				if(fl==0)
				{
					int st, end;
					if(j >= 50)
						st = j-50;
					else
						st = 0;
					if(j+seedLen+50 < htmlLen)
						end = j+seedLen+50;
					else
						end = htmlLen;
					String st1="",st2="";
					
					for(int k=j-1;k>=st;k--)
						st1 = st1 + htmlText.charAt(k);
					
					for(int k=j+seedLen+1;k<=end;k++)
						st2 = st2 + htmlText.charAt(k);

					trie.insert(trieLeft, st1, i, wrapNum);
					trie.insert(trieRight, st2, i, wrapNum);
					
				}
			}
		}
	}
}
