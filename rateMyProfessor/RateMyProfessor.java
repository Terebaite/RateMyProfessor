package rateMyProfessor;
import java.util.ArrayList;
import java.util.Scanner;

public class RateMyProfessor {
	
	static Database db = new Database();
    static Scanner input = new Scanner(System.in);
    static User me = null;
    // "me" is the User that is using the application. Before they login we don't know which user is using it, therefore it is null.

	public static void main(String[] args) throws Exception {
		
		if(db.isEmpty()) {
			// regenerateSeedData() creates a new Database with some data
			regenerateSeedData();
		}
		welcome();
	}

	private static void welcome() throws Exception {
        Scanner input = new Scanner(System.in);
		System.out.println("Welcome please choose an option");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. Exit");

		int option = input.nextInt();
		
		switch(option) {
			case 1: registerStudent();
					break;
			case 2: me = doLogin();
					if(me.getId() > 0) {
						if (me instanceof Student) {
                            System.out.println("Welcome, student");
                            showStudentMenu();
						} else {
                            System.out.println("Welcome, prof");
                            showProfessorMenu();
						}
					} else {
						endApplication();
					}
					break;
			case 3: endApplication();
					break;
			default: welcome(); 
					 break;
		}
	}
	
	private static void showStudentMenu() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose from the following options:");
		System.out.println("1. Rate a professor");
		System.out.println("2. Exit the program");
		int option = input.nextInt();
		
		switch(option) {
		case 1: listSubjects();
				break;
		case 2: endApplication();
				break;
		default: showStudentMenu();
				 break;
		//default is what happens in any case that is not considered before
		}
		
	}
	
	private static void showProfessorMenu() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose from the following options:");
		System.out.println("1. See my average rating");
		System.out.println("2. Exit the program");
		int option = input.nextInt();
		
		switch(option) {
		case 1: showProfessorAverage();
				break;
		case 2: endApplication();
				break;
		default: showProfessorMenu();
				break;
		}
	}
	

	private static void showProfessorAverage() throws Exception {
		System.out.println("Your average rating for the course is: ");
		System.out.println(((Professor) me).getAverage());
		endApplication();
	}

	private static void listProfessors(int subjectId) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose a professor you want to rate:");
		Subject subject = db.getSubjectById(subjectId);
		if (subject.getId() > 0) {
			for (Professor professor : subject.getProfessors() ) {
				System.out.printf("%s. %s\n", professor.getId(), professor.getName());
			}
			int professorId = input.nextInt();
			System.out.println("Choose from the following rating options:");
			System.out.println("1 - bad");
			System.out.println("2 - poor");
			System.out.println("3 - fair");
			System.out.println("4 - good");
			System.out.println("5 - very good");
			int rating = input.nextInt();
			if (rating > 5) {
				listProfessors(subjectId);
			}
			else {
				db.createRating(me.getId(), professorId, subjectId, rating);
				System.out.println("Thank you for rating, here is the average rating for the selected professor: ");
				Professor professor = db.getProfessorById(professorId);
				System.out.println(professor.getAverage());
				showStudentMenu();
			}
		}
	}
	
	private static void listSubjects() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose a subject");
		ArrayList<Subject> subjects = db.getSubjects();
		for (Subject subject : subjects) {
			System.out.printf("%s. %s\n", subject.getId(), subject.getName());
		}
		int subjectId = input.nextInt();
		listProfessors(subjectId);
	}

	private static void registerStudent() throws Exception {
        Scanner input = new Scanner(System.in);
		System.out.println("Email: ");
		String email = input.nextLine();
		System.out.println("Name: ");
		String name = input.nextLine();
		System.out.println("Password: ");
		String password = input.nextLine();
		System.out.println("Repeat your password: ");
		String repeatPassword = input.nextLine();

		if (password.equals(repeatPassword)) {
			db.registerStudent(email, name, password);
		}
		else {
			registerStudent();
		}

        System.out.println("Thank you for registering, please login now");
        welcome();
	}

	private static User doLogin() {
		// We create an empty user(User) variable so we ensure the return type of the method
		User user = null;
		System.out.println("Please, enter your email:");
		String email = input.nextLine();
		System.out.println("Please, enter your password:");
		String password = input.nextLine();
		try {
			user = db.userLogin(email, password);
		} catch (Exception e) {
			System.out.println("Wrong username or password.");
			doLogin();
		}
		return user;
	}

	private static void endApplication() throws Exception {
		db.saveUsersToFile();
		db.saveRatingsToFile();
        System.out.println("Thank you for using RateMyProfessor.");
        input.close();
        System.exit(0);
        // End of the application
	}

    /**
     * Generates the default files for users(students, professors) and ratings
     * DO NOT USE unless your database got corrupted or you want to start from scratch
     */
    private static void regenerateSeedData() throws Exception {
        System.out.println("Regenerating seed data...\n");

        db = new Database(true);

        // Students
		db.registerStudent("viktorija@email.com", "Viktorija", "123");
		db.registerStudent("alice@email.com", "Alice", "123");
		db.registerStudent("tom@email.com", "Tom", "lkasjdi");

        try {
            System.out.println("Saving students...");
            db.saveUsersToFile();
            System.out.println("Students Saved\n");
        } catch (Exception e) {
            System.out.println("Error attempting to save students");
            e.printStackTrace();
        }

        // Print Students
        System.out.println("Registered Students:");
        for (Student student : db.getStudents()) {
            System.out.println(student.getEmail());
        }

        // Professors
        db.registerProfessor("albus@hogwarts.co.uk", "Albus Dumbledore", "sherbertlemon");
        db.registerProfessor("feeny@hotmail.com", "George Feeny", "coreytopangashawn");
        db.registerProfessor("deadpoet@gmail.com", "John Keating", "captain");

        try {
            System.out.println("\n\nSaving professors...");
            db.saveUsersToFile();
            System.out.println("Professors Saved\n");
        } catch (Exception e) {
            System.out.println("Error attempting to save professors");
            e.printStackTrace();
        }

        // Print Professors
        System.out.println("\nRegistered Professors:");
        for (Professor professor : db.getProfessors()) {
            System.out.println(professor.getEmail());
        }


        // Print Users
        System.out.println("\nAll Users:");
        // for each user of the class User in the List my_users
        for ( User user : db.getUsers()) {
            // Get the user name and print it
            System.out.printf("%s: %s\n", user.getId(), user.getName());
        }
        
        // Subjects
        
        db.registerSubject("EU Internet Law");
		db.registerSubject("Introduction to programming and distributed systems");
		db.registerSubject("Strategic and Tactical Tools for E-Business");

        try {
            System.out.println("Saving subjects...");
            db.saveSubjectsToFile();
            System.out.println("Subjects Saved\n");
        } catch (Exception e) {
            System.out.println("Error attempting to save subjects");
            e.printStackTrace();
        }

        // Print Subjects
        System.out.println("Registered Subjects:");
        for (Subject subject : db.getSubjects()) {
            System.out.println(subject.getName());
        }
        
        // SubjectProfessors
        // We know that users 4, 5, 6 are Professors and that subjects 1, 2, 3 exist
        db.associateSubjectAndProfessor(4, 1);
		db.associateSubjectAndProfessor(5, 2);
		db.associateSubjectAndProfessor(6, 3);
		db.associateSubjectAndProfessor(6, 2);
		
        try {
            System.out.println("Saving subjectProfessors...");
            db.saveSubjectProfessorsToFile();
            System.out.println("SubjectProfessors Saved\n");
        } catch (Exception e) {
            System.out.println("Error attempting to save subjectProfessors");
            e.printStackTrace();
        }

        // Print Subjects
        System.out.println("Registered Subject and Professor associations:");
        for (SubjectProfessor subjectProfessor : db.getSubjectProfessors()) {
            System.out.println(subjectProfessor.getId());
        }
        
    }    
}
