package rateMyProfessor;

public class Rating {
	
	private Student student;
	private Professor professor;
	private Subject subject;
	private int score;
	
	public Rating(Student student, Professor professor, int score) {
		this.student = student;
		this.professor = professor;
//		this.subject = subject;
		this.score = score;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
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
