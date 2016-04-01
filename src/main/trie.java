package main;
import java.util.*;

public class trie {
	
	static class TrieNode
	{
		TrieNode[] child;
		Hashtable isEnd;
		Hashtable wrapNum;
	}
	
	public static TrieNode newn()
	{
		TrieNode newnode = new TrieNode();
		for(int i=0;i<26;i++)
		{
			newnode.child[i] = null;
		}
		newnode.isEnd = new Hashtable();
		newnode.wrapNum = new Hashtable();
		return newnode;
	}
	public static void insert(TrieNode root,String key, int seedNum, int wrapNum)
	{
		int len = key.length();
		int index;

		for(int i=0;i<len;i++)
		{
			index = (int)(key.charAt(i) - 'a');
		
			if(root.child[index] == null)
			{
				root.child[index] = newn();
			}
			root.isEnd.put(seedNum, 1);
			root.wrapNum.put(wrapNum, 1);
			root = root.child[index];
		}
		root.wrapNum.put(wrapNum, 1);
		root.isEnd.put(seedNum, 1);
	} 
}
