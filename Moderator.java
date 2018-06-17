package project2;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Moderator extends Customer {

	public Moderator(String username, String password, int status, long id, String name) {
		super(username, password, status, id, name);
		// TODO Auto-generated constructor stub
	}

	public void actions(int a) {
		Login login = new Login();
		MySQL mysql = new MySQL();
		System.out.println("***Moderator's Menu***");
		System.out.println(
				"For message press 1\nTo view messages press 2\nTo edit message press 3\nTo delete message press 4\nTo go back to Login press 5");
		try {
		Scanner in = new Scanner(System.in);

		int choice = 1;
		while (choice != 0) {
			int n = in.nextInt();

			switch (n) {
			
			case 1:
				System.out.println("***The List of Users and ID's***");
				inputMessage(a);
				actions(a);
				break;
			case 2:
				mysql.selectMessages();
				actions(a);
				break;
			case 3:
				mysql.selectMessages();
				updateMessage();
				actions(a);
				break;
			case 4:mysql.selectMessages();
				deleteConversation();
				actions(a);
				break;
			case 5:
				try {
					login.loginCheck();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("><Invalid option><");
				System.out.println("------------------");
				System.out.println("Please choose again: ");
				actions(a);
			}
		}
		
		in.close();}catch(InputMismatchException e) {
			System.out.println("><Invalid option><");
			System.out.println("------------------");
			System.out.println("Please choose again: ");
			actions(a);
		}

	}

	public void deleteConversation() {
		Scanner sc = new Scanner(System.in);

		MySQL mysql = new MySQL();
		System.out.print("Enter then number of the message you want to delete:");
		int a = sc.nextInt();

		mysql.deleteMessage(a);

	}
}
