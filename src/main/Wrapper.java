package main;

public class Wrapper {
	
	public static void wrap(String htmlText, String[] seeds)
	{
		
		int noOfSeeds = seeds.length;
		int htmlLen = htmlText.length();
		int fl;
		trie.TrieNode trieLeft = trie.newn();
		trie.TrieNode trieRight = trie.newn();

		for(int i=0;i<noOfSeeds;i++)
		{
			int seedLen = seeds[i].length();
			
			for(int j=0;j<htmlLen-seedLen+1;j++)
			{
				fl = 0;
				for(int k=0;k<seedLen;k++)
				{
					if(htmlText.charAt(j+k) != seeds[i].charAt(k))
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

					trie.insert(trieLeft, st1);
					trie.insert(trieRight, st2);

				}
			}
		}
		
	}
}
