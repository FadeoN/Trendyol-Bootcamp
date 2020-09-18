import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UniqueWordCounterTest {

    @Test
    public void countUniqueWords_WhenNullSentenceGiven(){

        UniqueWordCounter sut = new UniqueWordCounter();

        Integer uniqueWordCount = sut.countWordsInSentence(null);

        assertThat(uniqueWordCount).isEqualTo(0);

    }

    @Test
    public void countUniqueWords_WhenEmptySentenceGiven(){

        UniqueWordCounter sut = new UniqueWordCounter();

        Integer uniqueWordCount = sut.countWordsInSentence("");

        assertThat(uniqueWordCount).isEqualTo(0);

    }

    @Test
    public void countUniqueWords_WhenAllLowerCaseAndUniqueSentenceGiven(){

        UniqueWordCounter sut = new UniqueWordCounter();

        String sentence = "this is a test";
        Integer uniqueWordCount = sut.countWordsInSentence(sentence);

        assertThat(uniqueWordCount).isEqualTo(sentence.split(" ").length);

    }

    @Test
    public void countUniqueWords_WhenBothLowerCaseAndUpperCaseSentenceGiven(){

        UniqueWordCounter sut = new UniqueWordCounter();

        String sentence = "This Is a test";
        Integer uniqueWordCount = sut.countWordsInSentence(sentence);

        assertThat(uniqueWordCount).isEqualTo(sentence.split(" ").length);

    }


    @Test
    public void countUniqueWords_WhenDuplicateWordsInSentenceGiven(){

        UniqueWordCounter sut = new UniqueWordCounter();

        String sentence = "test test";
        Integer uniqueWordCount = sut.countWordsInSentence(sentence);

        assertThat(uniqueWordCount).isEqualTo(1);

    }

    @Test
    public void countUniqueWords_WhenPunctuationsInSentenceGiven(){

        UniqueWordCounter sut = new UniqueWordCounter();

        String sentence = "This, is a punctuation's. test: !";
        Integer uniqueWordCount = sut.countWordsInSentence(sentence);

        assertThat(uniqueWordCount).isEqualTo(5);

    }

}
