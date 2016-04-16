package rateMyProfessor;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {
	
	private static final long serialVersionUID = 3626390569555531577L;
	private int id;
	private String name;
	
	public Subject(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Professor> getProfessors() {
		ArrayList<Professor> professors = new ArrayList<Professor>();
		for (SubjectProfessor subjectProfessor : RateMyProfessor.db.getSubjectProfessors()) {
			if (subjectProfessor.getSubjectId() == this.getId()) {
				professors.add(subjectProfessor.getProfessor());
			}
		}
		return professors;
	}
	
	

}
