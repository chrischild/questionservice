/**
 * AppBuilder.java
 *
 */
package org.cannibalcoding.app;

import org.cannibalcoding.dao.QuestionsAnswersDao;
import org.cannibalcoding.entities.QuestionsAnswers;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * @author Chris
 *
 */
public class QuestionAppBinder extends AbstractBinder {

    @Override
    protected void configure() {
	bind(QuestionsAnswersDao.class).to(QuestionsAnswersDao.class);
	bind(QuestionsAnswers.class).to(QuestionsAnswers.class);
    }

}
