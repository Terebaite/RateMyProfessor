package Final.v2;

import java.util.ArrayList;
import java.util.List;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Database {
	
	private List<Student> students;
	private List<Professor> professors;
	private List<Rating> ratings;
	
	public Database() {
		students = new ArrayList<Student>();
		professors = new ArrayList<Professor>();
		ratings = new ArrayList<Rating>();
	}
	
	public void registerStudent(Student student) {
		students.add(student);
	}
	
	public void registerProfessor(Professor professor) {
		professors.add(professor);
	}
	
	public Student loginStudent(String email, String password) throws Exception {
		for (Student student: students) {
			if (student.getEmail() == email && student.getPassword().equals(password)) {
				return student;
			}
		}
		throw new Exception("Username or password incorrect!");
	}
	
	public void rateProfessor(Student student, Professor professor, Subject subject, int score) throws Exception {
		if (!professor.getSubject().equals(subject)) {
			throw new Exception("Subject not applicable for this professor");
		}
		
		ratings.add(new Rating(student, professor, subject, score));
	}
	
	public float getAverage(Professor professor) {
		int totalScore = 0;
		int scoreCount = 0;
		for (Rating rating : ratings) {
			if (rating.getProfessor().equals(professor)) {
				totalScore += rating.getScore();
				scoreCount++;
			}
		}
		return (float) totalScore / scoreCount;
	}
	
    // steps for working with files:
	// Create file
	// Write each user to file
	// Flush and close the file
	public void saveUsersToFile(String filename) throws Exception{
		// Creates a new File object with either the existing users.txt or a new one. 
	    //note:it will exists after the first time i run my program 
		FileOutputStream file = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		
		// Creates a new PrintWriter object that will allow us to write and save stuff
		// into our users.txt file
		// java.io.PrintWriter output = new java.io.PrintWriter(file);
		
		try {
			// for each student of the class Student in the ArrayList students
			for (Student student : students) {
				// write the student to users.txt
				oos.writeObject(student);
			}
			// for each professor of the class Professor in the Array list professors 
			for (Professor professor : professors) {
				//write the professor to users.txt
				oos.writeObject(professor);
			}
		
		} catch (Exception e) {
			// printStackTrace prints to System.out the last operations the computer
			// tried to execute before the Exception happened
			e.printStackTrace();
		} finally {
			// Whatever is part of the finally block will always be executed
			oos.close();
		}
	}
			

	
	public ArrayList<User> loadUsersFromFile(String filename) throws Exception {
		FileInputStream file = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(file);
		ArrayList<User> users = new ArrayList<User>();
		while(true) {
			try {
				Object user = ois.readObject();
				users.add((User) user);
			} catch (EOFException e) {
				// EOF means End Of File
				// TODO make this prettier - EOFe are ugly
				ois.close();
				return users;
			}
		}
	}

}
