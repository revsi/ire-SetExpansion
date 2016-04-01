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
		File gModel = new File("/Developer/Vector Models/GoogleNews-vectors-negative300.bin.gz");
		Word2Vec vec = (Word2Vec) WordVectorSerializer.loadGoogleModel(gModel, true);
		//seed.get(index);
		ArrayList<String> expandedset = new ArrayList<String>();
		
		
		return expandedset;
	}

}
