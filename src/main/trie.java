package main;

public class trie {
	
	static class TrieNode
	{
		TrieNode[] child;
		boolean isEnd;
	}
	
	public static TrieNode newn()
	{
		TrieNode newnode = new TrieNode();
		for(int i=0;i<26;i++)
		{
			newnode.child[i] = null;
		}
		newnode.isEnd = false;
		return newnode;
			
	}
	public static void insert(TrieNode root,String key)
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
			root = root.child[index];
		}
		root.isEnd = true;
	} 
}
