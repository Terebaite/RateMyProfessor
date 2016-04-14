package rateMyProfessor;

public class Professor extends User {
	
	public Professor(String name, String password, String email, int id) {
		super(name, password, email, id);
	}

	
//  This is not needed, if you do it like this a professor can only have one subject
//	public Subject getSubject() {
//		return subject;
//	}
//
//	public void setSubject(Subject subject) {
//		this.subject = subject;
//	}
	

}
