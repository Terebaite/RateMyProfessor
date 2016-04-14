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
	
	public Database() {
        this.students = new ArrayList<Student>();
        this.professors = new ArrayList<Professor>();
		try {
			this.users = loadUsersFromFile("users.dat");
		} catch (Exception e) {
			System.out.println("INFO: No users registered.");
		}
        try {
            this.ratings = loadRatingsFromFile("ratings.dat");
        } catch (Exception e) {
            System.out.println("INFO: No ratings in the system.");
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
        }
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


    // Public methods to create users
	public void registerStudent(String email, String name, String password) throws Exception {
        int id = generateId();
        Student student = new Student(email, name, password, id);
		students.add(student);
		users.add(student);
	}
	
	public void registerProfessor(String email, String name, String password) {
        int id = generateId();
        Professor professor = new Professor(email, name, password, id);
		professors.add(professor);
		users.add(professor);
	}

	public void rateProfessor(Student student, Professor professor, int score) {
		ratings.add(new Rating(student, professor, score));
	}
	
	public float getAverage(Professor professor) {
		int totalScore = 0;
		int scoreCount = 0;
		for (Rating rating : ratings) {
			if(rating.getProfessor().equals(professor)) {
				totalScore += rating.getScore();
				scoreCount++;
			}
		}
		return (float) totalScore / scoreCount;
	}
	
	//We will go with this if we go with the subject class
	
//	public void rateProfessor(Student student, Professor professor, Subject subject, int score) throws Exception {
		// TODO: Refactor this after rebuilding the SubjectProfessor class
//		if (!professor.getSubject().equals(subject)) {
//			throw new Exception("Subject not applicable for this professor");
//		}
		
//		ratings.add(new Rating(student, professor, subject, score));
//	}
//	
//	public float getAverage(Professor professor) {
//		int totalScore = 0;
//		int scoreCount = 0;
//		for (Rating rating : ratings) {
//			if (rating.getProfessor().equals(professor)) {
//				totalScore += rating.getScore();
//				scoreCount++;
//			}
//		}
//		return (float) totalScore / scoreCount;
//	}
	

	public void saveUsersToFile(String filename) throws Exception {
		FileOutputStream file = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		oos.writeObject(getUsers());
		oos.close();
		file.close();
	}

    public void saveRatingsToFile(String filename) throws Exception {
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(file);
        oos.writeObject(getRatings());
        oos.close();
        file.close();
    }

	public ArrayList<User> loadUsersFromFile(String filename) throws Exception {
		FileInputStream file = new FileInputStream(filename);
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

    public ArrayList<Rating> loadRatingsFromFile(String filename) throws Exception {
        return new ArrayList<Rating>();
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
	
	//method that give us a new id
	public int generateId() {
		return getUsers().size() + 1;
	}

    public void saveRatingsToFile() {
        System.out.println("Saving ratings");
    }

}
