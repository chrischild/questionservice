/**
 * QuestionService.java
 *
 */
package org.cannibalcoding.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cannibalcoding.dao.QuestionsAnswersDao;
import org.cannibalcoding.dto.QuestionsAnswersDTO;
import org.cannibalcoding.entities.QuestionsAnswers;

import com.google.gson.Gson;

/**
 * @author Chris
 *
 */
@Path("questions")
public class QuestionService {

    @Inject
    QuestionsAnswersDao questionAnswerDao;

    @POST
    @Path("/question")
    @Produces(MediaType.APPLICATION_JSON)
    public QuestionsAnswersDTO question() {
	return getQuestion(false);
    }

    @POST
    @Path("/answer")
    @Produces(MediaType.APPLICATION_JSON)
    public QuestionsAnswersDTO answer(String params) {

	boolean correct = Boolean.FALSE;
	Gson gson = new Gson();
	QuestionsAnswers questionsAnswers = gson.fromJson(params, QuestionsAnswers.class);

	QuestionsAnswers correctAnswer = questionAnswerDao.getAnswer(questionsAnswers.getQuestionId(), questionsAnswers.getAnswer());

	if (correctAnswer != null) {
	    correct = Boolean.TRUE;
	}

	return getQuestion(correct);
    }

    private QuestionsAnswersDTO getQuestion(boolean correct) {
	QuestionsAnswers questionsAnswers = questionAnswerDao.getQuestion();

	List<String> answers = questionAnswerDao.getAnswers(3, questionsAnswers.getType(), questionsAnswers.getAnswer());
	answers.add((int) Math.floor(Math.random() * 4), questionsAnswers.getAnswer());
	QuestionsAnswersDTO dto = new QuestionsAnswersDTO(questionsAnswers, answers, correct);
	dto.setAnswers(answers);

	return dto;
    }

}
