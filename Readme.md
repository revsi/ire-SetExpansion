=======================================   Set - Expansion  ================================

     Objective :
     The objective of this project is to find related words given a few words of the set as input
     
     Running Details :
     The main class of the project is Main.java from which you enter into the code . 
     You can ask your query untill the user types "exit".
     Mainly two arguments are passed : 
     1) query : example   -   java ruby python
     2) number of results : example - 10
     
     Once the progam is run there are two major things it does :
     1. Training the word2vec model i.e the wikipedia dump we have used.
     2. After we have trained the data using he word2vec model, a file is created which maps each word with its cosine angle.
        So, to perform set expansion we make use of the similarity function of the word2vec model.
        > First we search the given seeds on a search engine like google, bing, yandex, faroo etc and retrieve the top results.
        > Now we find the similarity between the current seed words and the words in retrieved webpages.
        > Then we include the word which has the most similarity with the given seed words i.e., we choose the top 'n' words which are closest to the input words. This would be then new seed word enrty in seed list.
        > Continue the process till you get the desired number of seeds you want.
        > The output will be displayed on your terminal. We also use file to store the result.
        
        Link to github repository : https://github.com/revsi/ire-SetExpansion
     
--------------------------------------------------------------------------------------------------------------------------------
