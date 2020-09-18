import java.util.*;

public class UniqueWordCounter {

    public Integer countWordsInSentence(String sentence){

        // Check for  empty or null sentence
        if(sentence == null || sentence.equals("")) {
            return 0;
        }

        sentence = preprocessSentence(sentence);

        List<String> splittedWords = Arrays.asList(sentence.split(" "));

        Set<String> uniqueWords = new HashSet<>(splittedWords);

        return uniqueWords.size();

    }

    public String preprocessSentence(String sentence){
        // All Lower case
        // Remove punctuations
        return removePunctuations(sentence).toLowerCase();


    }

    public String removePunctuations(String sentence){
        return sentence.replaceAll("\\p{P}", "");
    }



}
