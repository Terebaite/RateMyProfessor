package rateMyProfessor;

import java.io.Serializable;

public class SubjectProfessor implements Serializable {

	private int id;
	private int professorId;
	private int subjectId;

	public SubjectProfessor(int id, int professorId, int subjectId) {
		this.id = id;
		this.professorId = professorId;
		this.subjectId = subjectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	// runs through the ArrayList and checks if any Professor's id is the same
	// as this.ProfessorId.
	public Professor getProfessor() {
		Professor professor = null;
		for (Professor item : RateMyProfessor.db.getProfessors()) {
			if (item.getId() == this.getProfessorId()) {
				professor = item;
			}
		}
		return professor;
	}

	public Subject getSubject() {
		Subject subject = null;
		for (Subject item : RateMyProfessor.db.getSubjects()) {
			if (item.getId() == this.getSubjectId()) {
				subject = item;
			}
		}
		return subject;
	}

}
