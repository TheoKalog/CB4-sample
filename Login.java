package project2;

import java.util.Scanner;
import java.sql.SQLException;

public class Login {

	public boolean loginAccess() throws ClassNotFoundException, SQLException {
		boolean booleanFlag = true;

		MySQL mysql = new MySQL();

		Scanner in = new Scanner(System.in);

		System.out.print("Enter username: ");
		String n1 = in.next();

		System.out.print("Enter password: ");
		String n2 = in.next();

		if (mysql.access2(n1, n2)) {
			booleanFlag = true;
		} else {
			booleanFlag = false;
		}

		return booleanFlag;
	}

	public void loginCheck() throws ClassNotFoundException, SQLException {
		System.out.println("**Login**");

		if (loginAccess()) {

		} else {
			System.out.println("*Try again*");
			loginCheck();
		}
	}

}
