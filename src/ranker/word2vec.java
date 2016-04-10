package ranker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

public class word2vec {

	public word2vec() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<String> run(ArrayList seed, ArrayList newseeds) throws IOException{
		File gModel = new File("~/GoogleNews-vectors-negative300.bin.gz");
		Word2Vec vec = (Word2Vec) WordVectorSerializer.loadGoogleModel(gModel, true);
		//seed.get(index);
		ArrayList<String> expandedset = new ArrayList<String>();
		
		for (int i = 0; i < seed.size(); i++) {
			for (int j = 0; j < newseeds.size(); j++){
				/*if (contain.get(i).contains(code.trim())) {
		            System.out.println(contain.get(i));
		        }	*/
			}
	    }
		
		
		return expandedset;
	}

	
	public double compare(String w1, String w2, Word2Vec vec){
		double cosSim = vec.similarity(w1, w2);
	    //System.out.println(cosSim);
		return cosSim;
	
	}
	
	
}
