package rateMyProfessor;
import java.util.ArrayList;
import java.util.Scanner;

public class RateMyProfessor {
	
	static Database db = new Database();
    static Scanner input = new Scanner(System.in);
    static User me = null;
    // "me" is the User that is using the application. Before they login we don't know which user is using it, therefore it is null.

	public static void main(String[] args) throws Exception {
		
		welcome();
        // regenerateSeedData(); // Only uncomment this if you want to clear all the data you've created and start from scratch
		// one time function to make sure we have a data to work with. 
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
							// The user is a Student
							// TODO
							// Make a method that lists the actions (methods) that a student can take
                            
							// Create those empty methods
						} else {
                            System.out.println("Welcome, prof");
                            endApplication();
							// The user is not a Student, therefore, it is a Professor
							// TODO
							// Make a method that lists the actions (methods) that a professor can take
							// Create those empty methods
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
		case 1: listProfessors();
				break;
		case 2: endApplication();
				break;
		default: showStudentMenu();
				 break;
		//default is what happens in any case that is not considered before
		}
		
	}
	
	private static void listProfessors() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose a professor you want to rate:");
		ArrayList<Professor> professors = db.getProfessors(); 
		for (Professor professor : professors ) {
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
			listProfessors();
		}
		else {
			db.createRating(professorId, rating);
			System.out.println("Thank you for rating, here is the average rating for the selected professor: ");
			System.out.println(db.getAverage(professorId));
			endApplication();
		}
		
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
		db.saveUsersToFile("users.dat");
		db.saveRatingsToFile("ratings.dat");
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
            db.saveUsersToFile("users.dat");
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
            db.saveUsersToFile("users.dat");
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
    }

}
