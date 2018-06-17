package project2;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Shop extends Visitor {

	private String name;

	public Shop(String username, String password, int status, long id, String name) {
		super(username, password, status, id);
		this.name = name;
	}

	public void actions(int a) {
		MySQL mysql = new MySQL();
		Login login = new Login();
		System.out.println(
				"To view Users press 1\nTo view messages press 2\nFor message press 3\nTo edit message press 4\nTo go back to login press 5");
		try {
			Scanner in = new Scanner(System.in);

			int choice = 1;
			while (choice != 0) {
				int n = in.nextInt();

				switch (n) {

				case 1:
					mysql.selectUsers();
					actions(a);

					break;

				case 2:
					mysql.selectMessages();
					actions(a);
					break;
				case 3:
					System.out.println("***The List of Users and ID's***");
					inputMessage(a);
					actions(a);
					break;
				case 4:
					mysql.selectMessages();
					updateMessage();
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

			in.close();
		} catch (InputMismatchException e) {
			System.out.println("><Invalid option><");
			System.out.println("------------------");
			System.out.println("Please choose again: ");
			actions(a);
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Shop [name=" + name + "]";
	}

}
