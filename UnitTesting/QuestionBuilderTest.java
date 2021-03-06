package UnitTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import src.Question;
import src.QuestionBuilder;

import java.util.HashMap;

/*
 * Class: questionBuilderTest
 * Description:	-	Tests the QuestionBuilder class using the "Questions" folder,
 * 					and comparing it to the file "test" using the equals method in
 * 					Question
 * Authors: Robin MacDonald
 */
class questionBuilderTest {

	@Test
	void buildQuestionsTest() {
		HashMap<String, Question> q;
		Question expected = new Question();
		String dirName = "Questions";	
		QuestionBuilder qb  = new QuestionBuilder(dirName);
		
		expected.addBranch(0);
		expected.addBranch(0);
		expected.setQuestionPriority(0);
		expected.setQuestion("How would you rate your customer experience?");
		expected.setInputRange(5);
		expected.addAnswer("1) Absolute Rubbish");
		expected.addAnswer("2) Regular Rubbish");
		expected.addAnswer("3) Adequate");
		expected.addAnswer("4) Hey, That's Pretty Good!");
		expected.addAnswer("5) Unforgettable");
		
		qb.buildQuestions();
		
		q = qb.getBuiltQuestions();
		assertTrue(q.get("test.txt").getQuestion().equals(expected.getQuestion()));
	}

}
