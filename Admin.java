package project2;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Moderator {

	public Admin(String username, String password, int status, long id, String name) {
		super(username, password, status, id, name);
		// TODO Auto-generated constructor stub
	}

	public void adminActions() {
		Login login = new Login();
		MySQL mysql = new MySQL();
		System.out.println(
				"To view Users press 1\nTo create user press 2\nTo delete user press 3\nTo update user press 4\nTo go back to Login press 5");
		try {
			Scanner in = new Scanner(System.in);

			int choice = 1;
			while (choice != 0) {
				int n = in.nextInt();

				switch (n) {

				case 1:
					mysql.selectUsers();
					System.out.println("-----------------------");
					adminActions();
					break;

				case 2:
					mysql.selectUsers();
					System.out.println("-----------------------");
					createUser();
					adminActions();

					break;

				case 3:
					mysql.selectUsers();
					System.out.println("-----------------------");
					deleteUser();
					adminActions();

					break;
				case 4:
					mysql.selectUsers();
					System.out.println("-----------------------");
					updateUser();
					adminActions();

					break;
				case 5:
					try {
						login.loginCheck();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				default:
					System.out.println("><Invalid option><");
					System.out.println("This user already exists...");
					System.out.println("------------------");
					System.out.println("Please choose again: ");

					adminActions();
				}
			}
			if (in.hasNextLine())
				;
			int n = in.nextInt();
			in.close();
		} catch (InputMismatchException e) {
			System.out.println("><Invalid option><");
			System.out.println("------------------");
			System.out.println("Please choose again: ");
			adminActions();
		}

	}

	public void createUser() {
		Scanner sc = new Scanner(System.in);

		MySQL mysql = new MySQL();
		System.out.print("Enter the Number of the user:");
		int number = sc.nextInt();
		System.out.print("Enter the ID of the user:");
		int id = sc.nextInt();

		System.out.print("Enter the Name of the user:");
		String name = sc.next();
		System.out.print("Enter the Status of the  user:");
		int status = sc.nextInt();
		System.out.print("Enter the Type ID of the  user:");
		int typeId = sc.nextInt();
		System.out.print("Enter the username of the  user:");
		String username = sc.next();
		System.out.print("Enter the password of the  user:");
		String password = sc.next();

		mysql.createUser( number,id, name, status, typeId, id, username, password);
		mysql.selectUsers();
		System.out.println("-----------------------");
		adminActions();

	}

	public void deleteUser() {
		Scanner sc = new Scanner(System.in);

		MySQL mysql = new MySQL();

		System.out.print("Enter the Name of the user:");
		String name = sc.next();

		mysql.deleteUser(name);
		mysql.selectUsers();
		System.out.println("-----------------------");
		adminActions();

	}

	public void updateUser() {
		Scanner sc = new Scanner(System.in);

		MySQL mysql = new MySQL();
		System.out.print("Enter the Name of the user you want to update:");
		String name = sc.next();
		
		
		//System.out.print("Enter the updated Number of the user:");
		//int number = sc.nextInt();
		
		System.out.print("Enter the updatedStatus of the  user:");
		int status = sc.nextInt();
		System.out.print("Enter the update Type ID of the  user:");
		int typeId = sc.nextInt();

		mysql.updateUser(status, typeId, name);
		mysql.selectUsers();
		System.out.println("-----------------------");
		adminActions();

	}

}
