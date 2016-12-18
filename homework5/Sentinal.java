package homework5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    
    private PhraseHash posHash, negHash;

    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    
    Sentinal (String posFile, String negFile) throws FileNotFoundException {
    	posHash = new PhraseHash();
    	negHash = new PhraseHash();
    	loadSentimentFile(posFile, true);
    	loadSentimentFile(negFile, false);
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    public void loadSentiment (String phrase, boolean positive) {
        if (positive) {
        	posHash.put(phrase);
        } else {
        	negHash.put(phrase);
        }
    }
    
    public void loadSentimentFile (String filename, boolean positive) throws FileNotFoundException {
    	File file = new File(filename);
    	Scanner scanner = new Scanner(file);
    	while (scanner.hasNextLine()) {
    		loadSentiment(scanner.nextLine(), positive);
    	}
    }
    
    public String sentinalyze (String filename) throws FileNotFoundException {
    	File file = new File(filename);
    	Scanner scanner = new Scanner(file);
    	
    	int overallSentiment = 0;
    	while (scanner.hasNextLine()) {
    		String[] line = scanner.nextLine().split("\\s+");
        	overallSentiment += sentimentCount(posHash, line);
        	overallSentiment -= sentimentCount(negHash, line);
    	}
    	if (overallSentiment == 0) {
    		return "neutral";
    	} else if (overallSentiment > 0) {
    		return "positive";
    	} else {
    		return "negative";
    	}
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private static int sentimentCount (PhraseHash current, String[] line) {
    	int count = 0;
    	
    	int longest = current.longestLength();
    	for (int i = 0; i < line.length; i++) {
    		for (int j = 1; j < longest + 1 && i + j - 1 < line.length; j++) {
    			String toCheck = String.join(" ", Arrays.copyOfRange(line, i, i + j));
    			if (current.get(toCheck) != null) { count++; }
    		}
    	}
    	return count;
    }
    
}

