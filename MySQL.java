package project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class MySQL {

	private static final String USERNAME = "root";
	private static final String PASS = "";
	private static final String MYSQLURL = "jdbc:mysql://localhost/project1";
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement stm = null;
	Customer customer;

	public boolean access2(String username, String password) {
		boolean booleanFlag = true;

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Login.idUser,Login.Username,Login.Password,Users.Name,Users.Status,Users.typeid\n"
					+ " FROM  Login inner join Users ON Login.idUser=Users.idUser \n" + " ;";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {
				rs.getString(2);
				rs.getString(3);
				rs.getInt(5);
				rs.getInt(6);

				if (rs.getString(2).equals(username) && rs.getString(3).equals(password)) {
					booleanFlag = true;
					if (rs.getInt(5) == 1) {
						User visitor1 = new Visitor(rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(1));
						int k = rs.getInt(1);
						visitor1.actions(k);
					} else if (rs.getInt(5) == 2 && rs.getInt(6) == 2) {
						User visitor1 = new Customer(rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(1),
								rs.getString(4));
						int k = rs.getInt(1);
						visitor1.actions(k);
					} else if (rs.getInt(5) == 2 && rs.getInt(6) == 3) {
						User visitor1 = new Shop(rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(1),
								rs.getString(4));
						int k = rs.getInt(1);
						visitor1.actions(k);
					} else if (rs.getInt(5) == 3) {
						User visitor1 = new Moderator(rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(1),
								rs.getString(4));
						int k = rs.getInt(1);
						visitor1.actions(k);
					} else if (rs.getInt(5) == 4) {
						User visitor1 = new Admin(rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(1),
								rs.getString(4));
						visitor1.adminActions();
					}

					break;
				} else {
					booleanFlag = false;

				}
			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return booleanFlag;

	}

	public    HashMap<Integer, Integer>  selectUsers() {
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Name ,idUser\n" + "FROM Users;";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			System.out.format("%-30s %7s%n", "Username", "ID");
			System.out.format("%-30s %12s%n", "------------", "------------");
			
		     
		 

		      int i=1;
			while (rs.next()) {
				hmap.put(i, rs.getInt(2));
				
				while(i<hmap.size())
					System.out.println(i);
				System.out.format("%-30s %7s%n", rs.getString(1), i);

				i++;
			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return hmap;
	}

	public void selectMessages() {
		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Messages.MessageId,Users1.Name as Sender,  Users2.Name as Receiver ,Messages.DateOfSubmission,Messages.MessageData\n"
					+ "\n" + "\n" + "FROM Messages\n"
					+ " INNER JOIN Users as Users1 ON Users1.idUser=Messages.SenderId \n"
					+ "INNER JOIN Users as Users2 ON Users2.idUser=Messages.ReceiverId;  ";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {
				System.out.println("Message number: " + rs.getInt(1));

				System.out.println("This Message was sent at: " + rs.getString(4));
				System.out.println("From: " + rs.getString(2));
				System.out.println("To: " + rs.getString(3));
				System.out.println("Data of the message: " + rs.getString(5));
				System.out.println("--------------------");

			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public boolean selectShop(String shop) {
		boolean booleanFlag = true;

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Name \n" + "FROM Users\n" + "WHERE Name LIKE '%Pizza%';";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {

				if (rs.getString(1).equals(shop)) {
					booleanFlag = true;

					break;
				} else {
					booleanFlag = false;

				}
			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return booleanFlag;

	}

	public void selectCustomers() {

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Name \n" + "FROM Users\n" + "WHERE typeid=2;";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {
				System.out.println(rs.getString(1));

			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void createUser( int a,int b, String c, int d, int e, int f, String y, String z) {

		Connection con = null;
		PreparedStatement stm = null;
		PreparedStatement stm2 = null;

		String sqlselect;
		String sqlselect2;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "INSERT INTO Users VALUES (?,?,?,?,?);";
			sqlselect2 = "INSERT INTO Login VALUES (?,?,?) ;";

			
			stm = con.prepareStatement(sqlselect);
			stm.setInt(1, a);
			stm.setInt(2, b);
			stm.setString(3, c);
			stm.setInt(4, d);
			stm.setInt(5, e);
			stm2 = con.prepareStatement(sqlselect2);

			stm2.setInt(1, f);
			stm2.setString(2, y);
			stm2.setString(3, z);

			stm.executeUpdate();

			stm2.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("****ERROR****");
			System.out.println("This user already exists");

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e1) {
					
				}
			}
		}
	}

	public Messages getLastMessage(int a, int b) {

		Messages message = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stm = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);

			sqlselect = "SELECT  max(Messages.DateOfSubmission) as maximum,Users1.Name,Users2.Name,Messages.MessageData\n"
					+ "FROM Messages INNER JOIN Users as Users1 ON Messages.SenderId=Users1.idUser\n"
					+ "INNER JOIN Users as Users2 ON Messages.ReceiverId=Users2.idUser \n"
					+ "WHERE Messages.SenderId=? AND Messages.ReceiverId=?\n"
					+ "GROUP BY Messages.SenderId,Messages.ReceiverId,Messages.MessageData\n"
					+ "ORDER BY maximum DESC;";
			stm = con.prepareStatement(sqlselect);

			stm.setInt(1, a);
			stm.setInt(2, b);

			rs = stm.executeQuery();
			rs.next();

			message = new Messages(rs.getTimestamp(1), rs.getString(2), rs.getString(3), rs.getString(4));

			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return message;

	}

	public void createMessage(int a, int b, String d) {

		Connection con = null;
		PreparedStatement stm = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "INSERT INTO Messages (SenderId,ReceiverId,MessageData)\n" + "VALUES (?, ?, ?);";
			stm = con.prepareStatement(sqlselect);
			stm.setInt(1, a);
			stm.setInt(2, b);
			stm.setString(3, d);

			stm.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void deleteUser(String a) {

		Connection con = null;
		PreparedStatement stm = null;
		PreparedStatement stm2 = null;

		String sqlselect;
		String sqlselect2;


		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "DELETE FROM Users " + "WHERE Name=?; ";
			sqlselect2 = "DELETE l FROM Login l INNER JOIN Users u ON l.idUser=u.idUser WHERE u.Name=?; ;";

			stm = con.prepareStatement(sqlselect);
			stm2 = con.prepareStatement(sqlselect2);

			stm.setString(1, a);
			stm2.setString(1, a);

			stm2.executeUpdate();

			stm.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void updateUser(int a,int d, String e) {

		Connection con = null;
		PreparedStatement stm = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "UPDATE Users\n" + "SET Status=?,typeid=?\n" + "WHERE Name=?;";
			stm = con.prepareStatement(sqlselect);

			stm.setInt(1, a);
			stm.setInt(2, d);
			stm.setString(3, e);

			stm.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void updateMessage(int b, String a) {

		Connection con = null;
		PreparedStatement stm = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "UPDATE Messages\n" + "SET MessageData=?\n" + "WHERE MessageId=?;";
			stm = con.prepareStatement(sqlselect);

			stm.setInt(2, b);
			stm.setString(1, a);
			// stm.setInt(2, b);

			stm.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void deleteMessage(int a) {

		Connection con = null;
		PreparedStatement stm = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "DELETE FROM Messages\n" + "WHERE MessageId=?;";
			stm = con.prepareStatement(sqlselect);

			stm.setInt(1, a);

			stm.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void selectProductsPizza1(String a) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stm = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Users.Name,Products.Name\n" + " FROM Shop_Products \n"
					+ " INNER JOIN Shops ON Shops.idShops=Shop_Products.idShop \n"
					+ " INNER JOIN Products ON Shop_Products.idProduct=Products.idProducts\n"
					+ " INNER JOIN Users ON Shops.id=Users.idUser\n" + " WHERE Users.Name=?";
			stm = con.prepareStatement(sqlselect);
			stm.setString(1, a);
			rs = stm.executeQuery(sqlselect);
			while (rs.next()) {
				System.out.println(rs.getString(2));

			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void selectProductsPizza2() {

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Users.Name,Products.Name\n" + " FROM Shop_Products \n"
					+ " INNER JOIN Shops ON Shops.idShops=Shop_Products.idShop \n"
					+ " INNER JOIN Products ON Shop_Products.idProduct=Products.idProducts\n"
					+ " INNER JOIN Users ON Shops.id=Users.idUser\n" + " WHERE Users.Name=\"Pizza2\";";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {
				System.out.println(rs.getString(2));

			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void selectPizzaShops() {

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Users.Name\n" + "FROM Users INNER JOIN Shops ON Users.idUser=Shops.id\n"
					+ "WHERE Shops.type='Pizza';";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {
				System.out.println(rs.getString(1));

			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void selectBurgerShops() {

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Users.Name\n" + "FROM Users INNER JOIN Shops ON Users.idUser=Shops.id\n"
					+ "WHERE Shops.type='Burger';";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {
				System.out.println(rs.getString(1));

			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void selectCoffeeShops() {

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Users.Name\n" + "FROM Users INNER JOIN Shops ON Users.idUser=Shops.id\n"
					+ "WHERE Shops.type='Coffee';";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {
				System.out.println(rs.getString(1));

			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public boolean accessShop() {
		boolean booleanFlag = true;

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Status  FROM Users";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {

				rs.getInt(1);

				if (rs.getInt(1) == 2) {
					booleanFlag = true;
					break;
				} else {
					booleanFlag = false;
				}
			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return booleanFlag;

	}

	public boolean accessAdmin() {
		boolean booleanFlag = true;

		Connection con = null;
		ResultSet rs = null;
		String sqlselect;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
			sqlselect = "SELECT Status  FROM Users";
			Statement statement = con.createStatement();
			rs = statement.executeQuery(sqlselect);
			while (rs.next()) {

				rs.getInt(1);

				if (rs.getInt(1) == 3) {
					// do something
					booleanFlag = true;
					break;
				} else {
					booleanFlag = false;
				}
			}
			rs.close();

		} catch (SQLException ex) {
			Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return booleanFlag;

	}

}
