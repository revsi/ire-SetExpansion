package ranker;


import java.io.File;
import java.util.Collection;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EndingPreProcessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.util.SerializationUtils;

public class W2vtrain {
	private SentenceIterator iter;
	private Word2Vec vec;

	public final static String VEC_PATH = "vec.ser";

	public W2vtrain() throws Exception {
		this.iter = new LineSentenceIterator(new File("TrainingDataSet.txt"));   // Training Dataset
	}

	public void word2VecTraining() throws Exception {
		if(vec == null && !new File(VEC_PATH).exists()) {
			System.out.println("!!!!!!!!!!!!!!!!!");
			iter.setPreProcessor(new SentencePreProcessor() {				// Process the given dataset line by line
				@Override
				public String preProcess(String sentence) {
					return sentence.toLowerCase();							
				}
			});

			TokenizerFactory t = new DefaultTokenizerFactory();
			final EndingPreProcessor preProcessor = new EndingPreProcessor();
			t.setTokenPreProcessor(new TokenPreProcess() {
				@Override
				public String preProcess(String token) {
					token = token.toLowerCase();
					String base = preProcessor.preProcess(token);		// obtain the tokenized words
					base = base.replaceAll("\\d", "d");
					if (base.endsWith("ly") || base.endsWith("ing"))
						System.out.println();
					return base;
				}
			});

			int layerSize = 200;
			
			vec = new Word2Vec.Builder()
					.sampling(1e-5)
					.minWordFrequency(5)
					.batchSize(1000)
					.useAdaGrad(false)
					.layerSize(layerSize)
					.seed(42)
					.iterations(1)
					.learningRate(0.025)
					.minLearningRate(1e-2)
					.negativeSample(10)			
					.iterate(iter)
					.tokenizerFactory(t)
					.build();						// set the parameters for the word2vec model

			vec.fit();
			System.out.println("Evaluate model....");
		    double sim = vec.similarity("country", "june");
		    System.out.println("Similarity between country and june: " + sim);
		    Collection<String> similar = vec.wordsNearest("country", 10);
		    System.out.println("Similar words to 'country' : " + similar);
		    WordVectorSerializer.writeWordVectors(vec, "vec.ser");
		}else{
			System.out.println("System Already Trained !!!!! :)");			
		}
	}
}