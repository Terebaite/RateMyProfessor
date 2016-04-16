package rateMyProfessor;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Database {
	
	private ArrayList<Student> students;
	private ArrayList<Professor> professors;
	private ArrayList<Rating> ratings;
	private ArrayList<User> users;
    private ArrayList<Subject> subjects;
    private ArrayList<SubjectProfessor> subjectProfessors;
    
    public static final String USERS_FILENAME = "users.dat";
    public static final String RATINGS_FILENAME = "ratings.dat";
    public static final String SUBJECTS_FILENAME = "subjects.dat";
    public static final String SUBJECTPROFESSORS_FILENAME = "subjectprofessors.dat";
    
   
	public Database() {
        this.students = new ArrayList<Student>();
        this.professors = new ArrayList<Professor>();
		try {
			this.users = loadUsersFromFile();
		} catch (Exception e) {
			System.out.println("INFO: No users registered.");
		}
        try {
            this.ratings = loadRatingsFromFile();
        } catch (Exception e) {
            System.out.println("INFO: No ratings in the system.");
        }
        try {
        	this.subjects = loadSubjectsFromFile();
        } catch (Exception e) {
        	System.out.println("INFO: no subjects registered");
        }
        try {
        	this.subjectProfessors = loadSubjectProfessorsFromFile();
	    } catch (Exception e) {
	    	System.out.println("INFO: no relationship between subjects and professors registered");
	    }
	}	

	/**
     * Creates a new empty database
     * @param reset true to create a new empty database
     */
    public Database(Boolean reset) {
        if(reset) {
            this.students = new ArrayList<Student>();
            this.professors = new ArrayList<Professor>();
            this.users = new ArrayList<User>();
            this.ratings = new ArrayList<Rating>();
            this.subjects = new ArrayList<Subject>();
            this.subjectProfessors = new ArrayList<SubjectProfessor>();
        }
    }
    
    public boolean isEmpty() {
    	return !(this.getUsers().size() > 0 || this.getRatings().size() > 0 || this.getSubjects().size() > 0);
    }

    // Public methods to access the database lists
	public ArrayList<User> getUsers() {
		return this.users;
	}
	public ArrayList<Student> getStudents() {
        return this.students;
    }
    public ArrayList<Professor> getProfessors() {
        return this.professors;
    }
    public ArrayList<Rating> getRatings() {
        return this.ratings;
    }
    public ArrayList<Subject> getSubjects() {
    	return this.subjects;
    }
    
    public Subject getSubjectById(int subjectId) {
    	Subject subject = null;
    	for (Subject item : this.getSubjects()) {
    		if (item.getId() == subjectId) {
    			subject = item;
    		}
    	}
    	return subject;
    }
    
    public Professor getProfessorById(int professorId) {
    	Professor professor = null;
    	for(Professor item : this.getProfessors()) {
    		if (item.getId() == professorId) {
    			professor = item;
    		}
    	}
    	return professor;
    }
    
    public ArrayList<SubjectProfessor> getSubjectProfessors() {
    	return this.subjectProfessors;
    }
    // Public methods to create users
	public void registerStudent(String email, String name, String password) throws Exception {
        int id = generateId("User");
        Student student = new Student(email, name, password, id);
		students.add(student);
		users.add(student);
	}
	
	public void registerProfessor(String email, String name, String password) {
        int id = generateId("User");
        Professor professor = new Professor(email, name, password, id);
		professors.add(professor);
		users.add(professor);
	}
	
	public void registerSubject(String name) {
		int id = generateId("Subject");
		Subject subject = new Subject(id, name);
		subjects.add(subject);
	}
	
	public void associateSubjectAndProfessor(int subjectId, int professorId) {
		int id = generateId("SubjectProfessor");
		SubjectProfessor subjectProfessor = new SubjectProfessor(id, subjectId, professorId);
		subjectProfessors.add(subjectProfessor);
	}

	public void saveUsersToFile() throws Exception {
		FileOutputStream file = new FileOutputStream(USERS_FILENAME);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		oos.writeObject(getUsers());
		oos.close();
		file.close();
	}

    public void saveRatingsToFile() throws Exception {
        FileOutputStream file = new FileOutputStream(RATINGS_FILENAME);
        ObjectOutputStream oos = new ObjectOutputStream(file);
        oos.writeObject(getRatings());
        oos.close();
        file.close();
    }
    
    public void saveSubjectsToFile() throws Exception {
    	FileOutputStream file = new FileOutputStream(SUBJECTS_FILENAME);
    	ObjectOutputStream oos = new ObjectOutputStream(file);
    	oos.writeObject(getSubjects());
    	oos.close();
    	file.close();
    }

    public void saveSubjectProfessorsToFile() throws Exception {
    	FileOutputStream file = new FileOutputStream(SUBJECTPROFESSORS_FILENAME);
    	ObjectOutputStream oos = new ObjectOutputStream(file);
    	oos.writeObject(getSubjectProfessors());
    	oos.close();
    	file.close();
    }
    	
	public ArrayList<User> loadUsersFromFile() throws Exception {
		FileInputStream file = new FileInputStream(USERS_FILENAME);
		ObjectInputStream ois = new ObjectInputStream(file);
		Object users = ois.readObject();
        ArrayList<User> user_list = (ArrayList<User>) users;
		ois.close();
        for(User user : user_list) {
            if(user instanceof Student) {
                students.add((Student) user);
            } else if(user instanceof Professor) {
                professors.add((Professor) user);
            }
        }
		return user_list;
	}

    public ArrayList<Rating> loadRatingsFromFile() throws Exception {
    	FileInputStream file = new FileInputStream(RATINGS_FILENAME);
		ObjectInputStream ois = new ObjectInputStream(file);
		Object ratings = ois.readObject();
        ArrayList<Rating> ratings_list = (ArrayList<Rating>) ratings;
		ois.close();
		return ratings_list;
    }
    
    private ArrayList<Subject> loadSubjectsFromFile() throws Exception{
    	FileInputStream file = new FileInputStream(SUBJECTS_FILENAME);
    	ObjectInputStream ois = new ObjectInputStream(file);
    	Object subjects = ois.readObject();
    	ArrayList<Subject> subjects_list = (ArrayList<Subject>) subjects; //type casting 
    	ois.close();
    	return subjects_list;
	}
    
    private ArrayList<SubjectProfessor> loadSubjectProfessorsFromFile() throws Exception{
    	FileInputStream file = new FileInputStream(SUBJECTPROFESSORS_FILENAME);
    	ObjectInputStream ois = new ObjectInputStream(file);
    	Object subjectProfessors = ois.readObject();
    	ArrayList<SubjectProfessor> subjectProfessors_list = (ArrayList<SubjectProfessor>) subjectProfessors; //type casting 
    	ois.close();
    	return subjectProfessors_list;
	}
    
	// the method userLogin will return a User
	public User userLogin(String email, String password) throws Exception {
		// we create a new empty(null) User called loggedInUser
		User loggedInUser = null;
		for (User user : getUsers()) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				loggedInUser = user;
			}
		}
		if(!loggedInUser.getEmail().equals("")) {
			return loggedInUser;
		} else {
			throw new Exception("Wrong email or password");
		}
		
	}
	
	//method that gives us a new id
	public int generateId(String klass) {
		int id = 0;
		switch(klass) {
			case "User": id = getUsers().size() + 1;
						 break;
			case "Subject": id = getSubjects().size() + 1;
						 	break;
			case "SubjectProfessor": id = getSubjectProfessors().size() + 1;
									 break;
		}
		return id;
	}

	public void createRating(int studentId, int professorId, int subjectId, int score) {
		Rating rating = new Rating(studentId, professorId, subjectId, score); 
		ratings.add(rating); //add the newly created rating to the ratings ArrayList
	}

}
