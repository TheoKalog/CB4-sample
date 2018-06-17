package project2;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Customer extends Visitor {

	private String name;

	public Customer(String username, String password, int status, long id, String name) {
		super(username, password, status, id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void actions(int a) {
		Login login = new Login();
		MySQL mysql = new MySQL();
		System.out.println("***Customer's Menu***");
		System.out.println(
				"For order press 1\nFor message press 2\nTo view messages press 3\nTo edit message press 4\nTo go back to Login press 5");
		try {
			Scanner in = new Scanner(System.in);

			int choice = 1;
			while (choice != 0) {
				int n = in.nextInt();

				switch (n) {

				case 1:
					System.out.println(
							"For pizza press 1\nFor Burger press 2\nFor Coffee press 3\nTo go back to choices press 4");
					
				default:
					System.out.println("><Invalid option><");
					System.out.println("------------------");
					System.out.println("Please choose again: ");
					actions(a);
					try {
						order();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 2:
					System.out.println("***The List of Users and ID's***");
					inputMessage(a);
					actions(a);

					break;
				case 3:
					mysql.selectMessages();
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
				/*default:
					System.out.println("><Invalid option><");
					System.out.println("------------------");
					System.out.println("Please choose again: ");
					actions(a);*/
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

	void order() throws ClassNotFoundException, SQLException {
		MySQL mysql = new MySQL();
		Scanner in = new Scanner(System.in);

		int choice = 1;
		while (choice != 0) {
			int n = in.nextInt();

			switch (n) {
			case 0:
				choice = 0;
				break;

			case 1:

				orderPizza();
				break;

			case 2:
				System.out.println("From which shop would you like to order burger?");
				mysql.selectBurgerShops();
				break;

			case 3:
				System.out.println("From which shop would you like to order?");
				mysql.selectCoffeeShops();
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid option");
			}
		}
		
		in.close();

	}

	public void orderPizza() {
		Scanner in = new Scanner(System.in);

		MySQL mysql = new MySQL();
		System.out.println("From which shop would you like to order pizza?");
		mysql.selectPizzaShops();
		System.out.print("Enter your choice: ");

		String k = in.next();
		if (mysql.selectShop(k)) {
			System.out.println("What would you like to order?");

		} else {
			System.out.println("Please select again: ");
			try {
				orderPizza();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			in.close();

		}

	}

	public void orderBurger() {
		Scanner in = new Scanner(System.in);

		MySQL mysql = new MySQL();
		System.out.println("From which shop would you like to order burger?");
		mysql.selectPizzaShops();
		System.out.print("Enter your choice: ");

		String k = in.next();
		if (mysql.selectShop(k)) {
			System.out.println("ok");

		} else {
			System.out.println("Please select again: ");
			try {
				orderPizza();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			in.close();

		}

	}

}
