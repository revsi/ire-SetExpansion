package ranker;

import java.io.File;
import java.util.ArrayList;

import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.util.SerializationUtils;





public class seed {

	private TokenizerFactory tokenizer =  new DefaultTokenizerFactory();;


	public String getNewSeed(String oldSeedString, String pageHtml,ArrayList<String> seedList){
		Word2Vec vec = SerializationUtils.readObject(new File("vec2.ser"));
		double weightage= 0.0;
		String newSeed = "";
		Tokenizer tf = tokenizer.create(pageHtml);   // tokenize the overall HTML into set of words
		while(tf.hasMoreTokens()){
			String htmlToken = tf.nextToken().toLowerCase();    // convert each word to lower case
					// Check if word is present in current seed list
					double temp = vec.similarity(oldSeedString, htmlToken);
					if (weightage < temp ){
						newSeed = htmlToken;					// finding a word which is not already in the seed list and has the highest similarity
						weightage = temp;
					}
		}
		return newSeed;	
	}
	
}