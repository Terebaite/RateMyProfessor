package Final.RateMyProfessor.Final.v2;// i read about it and it says that it can be because i have too much stuff. lets delete Viktorija>final. and Stuff>

import java.util.ArrayList;
import java.util.Scanner;

public class RateMyProfessor {

public static void main(String[] args) throws Exception {
		
		Database db = new Database();
		
//		Student viktorija = new Student(1, "Viktorija", "123", 111); 
//		db.registerStudent(viktorija);
//
//		
//		Student alice = new Student(2, "Alice", "123", 222); 
//		db.registerStudent(alice);
//		
//		
//		Student tom = new Student(3, "Tom", "lkasjdi", 333); 
//		db.registerStudent(tom);	
		
		// try to save all my users to users.txt
//		try {
//			db.saveUsersToFile("users.txt");
//			System.out.println("yay");
//		} catch (Exception e) {
//			// if it fails, print oops
//			System.out.println("Oops");
//		}
		//just to see if it works. Will delete it later 
		ArrayList<User> my_users = db.loadUsersFromFile("users.txt");
		// for each user of the class User in the ArrayList my_users
		for ( User user : my_users) {
			// Get the user name and print it
			System.out.println(user.getName());
		}
		
//		Subject maths = new Subject(1, "Maths");
//		
//		Professor bob = new Professor(1, "Bob", "asdj1309", maths);
//		db.registerProfessor(bob);
//		
//		//
//		
//		try {
//			db.rateProfessor(viktorija, bob, maths, 4);
//			db.rateProfessor(alice, bob, maths, 5);
//			db.rateProfessor(max, bob, maths, 1);
//			
//			System.out.print(db.getAverage(bob));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		// TODO Implement login functionality
		// User (upper case U), instance of the User class
		// user (lower case u), human being using the program
		
//		Scanner input = new Scanner(System.in);
//		int count = 0;
//		
//		do {
//			System.out.println("Enter your email: ");
//			String useremail = input.nextLine();
//			System.out.println("Enter your password: ");
//			String password = input.nextLine();
//			if (password.equals(password) && useremail.equals(useremail)) {
//				System.out.println("You are logged in");
//				break;
//			} else {
//				count++;
//				if (count == 3) {
//					System.out.println("You blocked your account");
//				}
//			}
//			
//		} while(count < 3);
		
	
		
		// Ask the user for their email
		// Ask the user for their password
		// Get all users with loadUsersFromFile
		// Find the User with the email the user entered
		// Compare if the User password is the same as the password the user entered
		// if it is, print "success"
		// if it isn't try again (use the loop logic of the login exercise)

	}

}
