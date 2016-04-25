package rateMyProfessor;

import java.io.Serializable;

public class Rating implements Serializable {

	private static final long serialVersionUID = 2369266468797958000L;
	private int studentId;
	private int professorId;
	private int subjectId;
	private int score;

	public Rating(int studentId, int professorId, int subjectId, int score) {
		this.studentId = studentId;
		this.professorId = professorId;
		this.subjectId = subjectId;
		this.score = score;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
