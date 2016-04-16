package rateMyProfessor;

import java.util.ArrayList;

public class Professor extends User {
	
	public Professor(String name, String password, String email, int id) {
		super(name, password, email, id);
	}
// this returns a list of subjects that this professor teaches. 
	public ArrayList<Subject> getSubjects() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		for (SubjectProfessor subjectProfessor : RateMyProfessor.db.getSubjectProfessors()) {
			if (subjectProfessor.getProfessorId() == this.getId()) {
				subjects.add(subjectProfessor.getSubject());
			}
		}
		return subjects;
	}
	
	public float getAverage() {
		int totalScore = 0;
		int scoreCount = 0;
		for (Rating rating : RateMyProfessor.db.getRatings()) {
			if(rating.getProfessorId() == this.getId()) {
				totalScore += rating.getScore();
				scoreCount++;
			}
		}
		if (scoreCount > 0) {
			return (float) totalScore / scoreCount;
		} else {
			return (float) 0.0;
		}
		
	}

}
