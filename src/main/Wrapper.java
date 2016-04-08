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
		
		for(int i=0;i<256;i++)
			traverse(root.child[i],st+(char)(i), side, seedSize);
		
		if(st.length() !=5)
			return;
		if(side == 0)							//Left side wrapper
		{
			int size = root.isEnd.keySet().size();
			if( size <1)
				return;

			Set keys = root.wrapNum.keySet();
			int fl = 0;
			for(Object key:keys)
			{
				if(wrapSelectLeft.get(key) == null)
				{
					fl = 1;
					wrapSelectLeft.put(key, st);
				}
			}
/*			if(fl == 1)
				wrappersLeft.addElement(st);
*/		}		
		else
		{
			int size = root.isEnd.keySet().size();
			if( size <1)
				return;
			Set keys = root.wrapNum.keySet();
			int fl=0;

			for(Object key:keys)
			{
				if(wrapSelectRight.get(key) == null)
				{
					fl = 1;
					wrapSelectRight.put(key, st);
				}
			}
/*			if(fl == 1)
				wrappersRight.addElement(st);
*/
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
			System.out.println(seedList.get(i));
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
					String st1="",st2="",st3="";
					
					for(int k=j-1;k>=st;k--){
						st1 = st1 + htmlText.charAt(k);
						st3 = htmlText.charAt(k) + st3;
					}
					for(int k=j+seedLen;k<=end;k++)
						st2 = st2 + htmlText.charAt(k);
//					System.out.println(st2);
//					System.out.println(st3);
					
//					System.out.println("");
					trie.insert(trieLeft, st1, i, wrapNum);
					trie.insert(trieRight, st2, i, wrapNum);
					wrapNum++;
				}
			}
		}
		traverse(trieRight, "", 1, 3);
		traverse(trieLeft, "", 0, 3);

		Set keys = wrapSelectLeft.keySet();

		for(Object key:keys)
		{
			if(wrapSelectRight.get(key) != null)
			{
				wrappersLeft.add(wrapSelectLeft.get(key));
				wrappersRight.add(wrapSelectRight.get(key));
			}
		}
		
		for(int i=0; i< wrappersRight.size();i++)
		{	
			for(int j=i+1; j< wrappersRight.size();j++)
			{
				if((String)(wrappersRight.get(i)) == (String)(wrappersRight.get(j)) && (String)(wrappersLeft.get(i)) == (String)(wrappersLeft.get(j)))
				{
					wrappersLeft.remove(j);
					wrappersRight.remove(j);
					j--;
				}
			}
		}
		for(int i=0; i< wrappersRight.size();i++)
			System.out.println(wrappersRight.get(i));

		for(int i=0; i< wrappersLeft.size();i++)
		{	String st1 = wrappersLeft.get(i).toString();
			String st3 = "";
			for(int j=0;j<st1.length();j++)
				st3 = st1.charAt(j) + st3;
			wrappersLeft.insertElementAt(st3, i);
			wrappersLeft.remove(i+1);
		}
		for(int i=0; i< wrappersLeft.size();i++)
			System.out.println(wrappersLeft.get(i));
		
		WrapperExtraction we = new WrapperExtraction();
		
		Vector extractions = new Vector();
		for(int i=0;i<wrappersLeft.size();i++)
		{
			String LHS = wrappersLeft.get(i).toString();
			String RHS = wrappersRight.get(i).toString();
			Vector v_LHS = we.KMP(htmlText, LHS, 0);
			Vector v_RHS = we.KMP(htmlText, RHS, 1);
			System.out.println(v_LHS);
			System.out.println(v_RHS);
			
			int x=0;
			int y=0;
			
			while(x<v_LHS.size() && y < v_RHS.size())
			{
				if((int)v_LHS.get(x) > (int)v_RHS.get(y))
					y++;
				else if(x<v_LHS.size()-1 && (int)v_LHS.get(x+1) <(int)v_RHS.get(y))
					x++;
				else
				{
					extractions.add(htmlText.substring((int)v_LHS.get(x),(int)v_RHS.get(y)));
					x++;
					y++;
				}
			}
		}
		
		for(int i=0;i<extractions.size();i++)
			System.out.println(extractions.get(i));

	}
}
