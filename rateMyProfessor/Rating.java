package rateMyProfessor;

import java.io.Serializable;

public class Rating implements Serializable {
	
	private int studentId;
	private int professorId;
//	private Subject subject;
	private int score;
	
	public Rating(int studentId, int professorId, int score) {
		this.studentId = studentId;
		this.professorId = professorId;
//		this.subject = subject;
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
//	public Subject getSubject() {
//		return subject;
//	}
//	public void setSubject(Subject subject) {
//		this.subject = subject;
//	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	

}
