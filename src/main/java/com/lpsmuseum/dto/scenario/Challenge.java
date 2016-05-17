package com.lpsmuseum.dto.scenario;

import com.lpsmuseum.entity.ChallengeDO;
import com.lpsmuseum.entity.AnswerDO;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an <b>challenge</b>, just for transfer between 
 * client-side and server-side.
 */
public class Challenge {

	/**
	 * This field represents the challenge's id.
	 */
	private Long challengeId;
	
	/**
	 * This fields represents the challenge's description.
	 */
	private String description;
	
	/**
	 * This field represents a <code>List</code> of <code>Answer</code> 
	 * associated with this <code>Challenge</code> instance.
	 */
	private List<Answer> answers;

	/**
	 * This field represents the index of the <b>correct</b> <code>Answer
	 * </code> in the <code>List<Answer> answers</code>.
 	 */
	private int correctAnswerIndex;

	/**
	 * Class constructor.
	 */
	public Challenge() {
	}

	/**
	 * Class constructor with given id.
	 * 
	 * @param challengeId the id of the challenge.
	 */
	public Challenge(Long challengeId) {
		this.challengeId = challengeId;
	}

	/**
	 * Returns the <code>id</code> of this <code>Challenge</code> instance.
	 *
	 * @return the <code>id</code> of this <code>Challenge</code> instance.
	 */
	public Long getChallengeId() {
		return challengeId;
	}

	/**
	 * Sets the <code>id</code> of this <code>Challenge</code> instance.
	 * 
	 * @param challengeId the <code>id</code> of this <code>Challenge</code> 
	 * instance.
	 */
	public void setChallengeId(Long challengeId) {
		this.challengeId = challengeId;
	}

	/**
	 * Returns the <code>description</code> of this <code>Challenge</code> 
	 * instance.
	 * 
	 * @return the <code>description</code> of this <code>Challenge</code> 
	 * instance.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the <code>description</code> of this <code>Challenge</code> 
	 * instance.
	 * 
	 * @param description  the <code>description</code> of this <code>Challenge
	 * </code> instance.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the <code>List</code> of <code>Answer</code> associated with 
	 * this <code>Challenge</code> instance.
	 * 
	 * @return the <code>List</code> of <code>Answer</code> associated with 
	 * this <code>Challenge</code> instance.
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * Sets the <code>List</code> of <code>Answer</code> associated with 
	 * this <code>Challenge</code> instance.
	 * 
	 * @param answers the <code>List</code> of <code>Answer</code> associated 
	 * with this <code>Challenge</code> instance.
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;

		for (Answer answer : answers)
			if (answer.isCorrect())
				correctAnswerIndex = answers.indexOf(answer);
	}

	/**
	 * Returns the entity representation (<code>ChallengeDO</code>) for this 
	 * <code>Challenge</code> instance.
	 * 
	 * @return the entity representation (<code>ChallengeDO</code>) for this 
	 * <code>Challenge</code> instance.
	 * @see ChallengeDO
	 * @throws Exception when <code>Answer.getEntity</code> is called.
	 */
	public ChallengeDO getEntity() throws Exception {
		ChallengeDO challengeDO = new ChallengeDO();

		List<AnswerDO> challengeItems = new ArrayList<AnswerDO>();
		for (Answer answer : answers) {
			challengeItems.add(answer.getEntity());
		}
		challengeDO.setAnswers(challengeItems);
		challengeDO.setDescription(description);
		challengeDO.setId(challengeId);

		return challengeDO;
	}

	/**
	 * Returns the data object representation for the <b>correct</b> answer 
	 * of this <code>Challenge</code> instance.
	 *
	 * @return the data object representation for the <b>correct</b> answer 
	 * of this <code>Challenge</code> instance.
	 */
	public Answer getCorrectAnswer() {
		return answers.get(correctAnswerIndex);
	}

	/**
	 * Gets the result of this challenge.  <b>Not implemented.</b>
	 */
	public void getResults() {
	}
}
