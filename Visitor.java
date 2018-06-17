package project2;

import java.io.File;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Visitor extends User {

	private String username;
	private String password;
	private int status;
	private long id;
	private File file;

	public Visitor(String username, String password, int status, long id) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
		this.id = id;
	}

	public void actions(int a) {
		MySQL mysql = new MySQL();
		Login login = new Login();
		System.out.println("***Visitor's Menu***");
		System.out.println(
				"Press 1 to view customers list\nPress 2 to view pizza shops\nPress 3 to view Burger shops\nPress 4 to view Coffee shops\nPress 5 to message\nPress 6 to view messages\nPress 7 to exit to login");
		try {
			Scanner in = new Scanner(System.in);

			int choice = 1;
			while (choice != 0) {
				int n = in.nextInt();

				switch (n) {

				case 1:
					mysql.selectCustomers();
					System.out.println("-----------------------");
					actions(a);

					break;

				case 2:
					mysql.selectPizzaShops();
					System.out.println("-----------------------");
					actions(a);

					break;

				case 3:
					mysql.selectBurgerShops();
					System.out.println("-----------------------");
					actions(a);
					break;
				case 4:
					mysql.selectCoffeeShops();
					System.out.println("-----------------------");
					actions(a);
					break;
				case 5:
					System.out.println("***The List of Users and ID's***");
					try {
						
						inputMessage(a);
						actions(a);
					} catch (NoSuchElementException e) {
						e.printStackTrace();

					}

					break;
				case 6:
					mysql.selectMessages();
					actions(a);
					break;
				case 7:
					try {
						login.loginCheck();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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

	public void inputMessage(int a) {
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		FileWrite file = new FileWrite();
		MySQL mysql = new MySQL();
		Scanner in = new Scanner(System.in);
		
		hmap=mysql.selectUsers();

		System.out.println("Give the receiver id:");
		
		int b = in.nextInt();
		if (hmap.containsKey(b)) {
			System.out.print("Write your message here:");
			in.useDelimiter("\\n");

			String d = in.next();

			mysql.createMessage(a, hmap.get(b), d);

			System.out.println("Message sent succesfully");
			System.out.println("........................");
			file.saveToFile(mysql.getLastMessage(a, hmap.get(b)));
		} else {
			System.out.println("***There is no such user!!***");
			System.out.println("-----------------------------");

		}
	}

	public void updateMessage() {
		Scanner sc = new Scanner(System.in);

		MySQL mysql = new MySQL();
		System.out.print("Enter then number of the message you want to update:");
		int a = sc.nextInt();
		System.out.print("Enter edited message:");

		while (sc.hasNext()) {
			sc.useDelimiter("\\n");

			String b = sc.next();
			mysql.updateMessage(a, b);
			break;
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
