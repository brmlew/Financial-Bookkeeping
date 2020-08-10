package com.sandbag;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author ZeAus
 *
 */



public class MyPersonalInformation {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public static List<PersonalInfo> search(String firstName, String lastName) {
		List<PersonalInfo> results = new ArrayList<PersonalInfo>();
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			String query = "SELECT A.*, B.*, C.* from PrimaryInfo A INNER JOIN Address B ON A.Id = B.Id "
					+ "INNER JOIN LoginInfo C ON A.Id = C.Id ";
			
			if (firstName != null && lastName != null) {
				query += "WHERE UPPER(firstName) like '%" + firstName.toUpperCase() + "%' OR "
						+ "UPPER(lastName) like '%" + lastName.toUpperCase() + "%'";
			} 
			else if (firstName != null) {
				
				query += "WHERE UPPER(firstName) like '%" + firstName.toUpperCase() + "%'";
				
			}
			else if (lastName != null) {
				query += "WHERE UPPER(lastName) like '%" + lastName.toUpperCase() + "%'";
				
			}
			
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				
				PersonalInfo info = createPersonalInformation(rs);
				results.add(info);
				
			}
			rs.close();
			statement.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return results;
	}
	
	public static int validateLogin(String username, String password) {
		int id = 0;
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT ID from LoginInfo WHERE Username = '" + username + "' AND Password = '"
					+ password + "'");
			if (rs.next()) {
				id = rs.getInt(1);
			}
			statement.close();
			rs.close();
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		return id;
		
	}
	
	public static boolean saveLoginInformation(String username, String password) {
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			statement.executeUpdate("UPDATE LoginInfo SET Password = '" + password + "' WHERE Username = '" + username + "';");
			statement.close();
			con.close();
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static boolean savePersonalInformation(PersonalInfo personalInfo) {
		if (personalInfo.getId() == 0) {
			return insertPersonalInformation(personalInfo);
		}
		else {
			return updatePersonalInformation(personalInfo);
		}
		
	}
	
	public static boolean insertPersonalInformation(PersonalInfo personalInfo) {
		try {
			Connection con = createConnection();
			int tempId = 0;
			Statement statement = con.createStatement();
			statement.executeUpdate("INSERT INTO PrimaryInfo (FirstName, LastName, Email, Phone) "
					+ "VALUES ('" + personalInfo.getFirstName() + "', '" + personalInfo.getLastname() + "', '"
					+ personalInfo.getEmail() + "', '" + personalInfo.getPhone() + "');", Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				tempId = rs.getInt(1);
				rs.close();
			}
			statement.executeUpdate("INSERT INTO Address (Id, StreetNumber, StreetName, City, Country, PostalCode) "
					+ "VALUES (" + tempId + ", " + personalInfo.getAddress().getStreetNumber() + ", '"
					+ personalInfo.getAddress().getStreetName() + "', '" + personalInfo.getAddress().getCity() + "', '"
					+ personalInfo.getAddress().getCountry() + "', '" + personalInfo.getAddress().getPostalCode() + "');");
			statement.close();
			String query = "INSERT INTO LoginInfo(Id, Username, Password) VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, tempId);
			pstmt.setString(2, personalInfo.getLoginInfo().getUserName());
			pstmt.setString(3, personalInfo.getLoginInfo().getPassword());
			//pstmt.setTimestamp(4, new java.sql.Timestamp(personalInfo.getLoginInfo().getLastLogin().getTime()));
			pstmt.execute();
			pstmt.close();
			con.close();
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static boolean updatePersonalInformation(PersonalInfo personalInfo) {
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			statement.executeUpdate("UPDATE PrimaryInfo SET FirstName = '" + personalInfo.getFirstName()
					+ "', LastName = '" + personalInfo.getLastname() + "', Email = '" 
					+ personalInfo.getEmail() + "', Phone = '" + personalInfo.getPhone() + "' WHERE Id = " + personalInfo.getId() + ";");
			
			statement.executeUpdate("UPDATE Address SET StreetNumber = " + personalInfo.getAddress().getStreetNumber()
					+ ", StreetName = '" + personalInfo.getAddress().getStreetName() + "', City = '" 
					+ personalInfo.getAddress().getCity() + "', Country = '" + personalInfo.getAddress().getCountry()
					+ "', PostalCode = '" + personalInfo.getAddress().getPostalCode() + "' WHERE Id = " + personalInfo.getId() + ";");
			statement.close();
			con.close();
			return true;
			
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	private static PersonalInfo createPersonalInformation(ResultSet rs) {
		Address address = new Address();
		LoginInfo loginInfo = new LoginInfo();
		PersonalInfo personalInfo = new PersonalInfo();
		try {
			personalInfo.setId(rs.getInt(1));
			personalInfo.setFirstName(rs.getString("FirstName"));
			personalInfo.setLastName(rs.getString("LastName"));
			personalInfo.setEmail(rs.getString("Email"));
			personalInfo.setPhone(rs.getString("Phone"));
			address.setStreetNumber(rs.getInt("StreetNumber")); 
			address.setStreetName(rs.getString("StreetName"));
			address.setCity(rs.getString("City"));
			address.setCountry(rs.getString("Country"));
			address.setPostalCode(rs.getString("PostalCode"));
			loginInfo.setUserName(rs.getString("Username"));
			loginInfo.setPassword(rs.getString("Password"));
			if (rs.getDate("LastLogin") != null) {
				loginInfo.setLastLogin(new java.util.Date(rs.getDate("LastLogin").getTime()));
			}
			personalInfo.setAddress(address);
			personalInfo.setLoginInfo(loginInfo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return personalInfo;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static PersonalInfo getPersonalInformation(int id) {
		
		PersonalInfo personalInfo = new PersonalInfo();
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			ResultSet rs=statement.executeQuery("SELECT A.*, B.*, C.* from PrimaryInfo A FULL OUTER JOIN Address B ON B.Id = A.Id "
					+ "FULL OUTER JOIN LoginInfo C ON C.Id = A.Id "
					+ "WHERE A.Id = " + id); 
			if (rs.next()) {
				personalInfo = createPersonalInformation(rs);
			}
			statement.close();
			rs.close();
			con.close();
			return personalInfo;
			
		} catch(Exception e) {System.out.println(e);}
		return personalInfo;
	}
	
	public static Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			String url = "jdbc:sqlserver://localhost\\sqlexpress;"
					+ "allowMultipleQueries=true;"
					+ "database=MyPOC;"
					+ "user=Brandon;"
					+ "password=testUser";
			con = DriverManager.getConnection(url);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	public static List<Account> getAccounts(String name, int id) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			String query = "SELECT * FROM Accounts WHERE ID=" + id + " AND accountName='" + name + "' "
					+ "ORDER BY createDate DESC;";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				float bal = rs.getFloat("balance");
				Account account = new Account(rs.getInt("Id"), rs.getString("accountName"), String.format("%.2f", bal), rs.getString("asOfDate"));
				accounts.add(account);
			}
			rs.close();
			statement.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return accounts;
	}
	
	public static boolean addAccount(Account account) {
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		    Date parsedDate = dateFormat.parse(account.asOfDate);
		    java.sql.Date timestamp = new java.sql.Date(parsedDate.getTime());
		    System.out.println(parsedDate.toString());
		    System.out.println(timestamp.toString());
		    float bal = Float.parseFloat(account.getBalance());
			statement.executeUpdate("INSERT INTO Accounts (Id, accountName, balance , asOfDate) "
					+ "VALUES (" + account.id + ", '" + account.name + "', " + bal + ", '" + timestamp + "');");
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static List<Account> getAccountSummary(int id) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			String query = "SELECT * FROM (SELECT *, RANK() OVER (PARTITION BY accountName ORDER BY createDate DESC) RNK FROM Accounts) t " + 
					"WHERE ID=" + id + " AND RNK=1 ORDER BY createDate DESC;";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				float bal = rs.getFloat("balance");
				Account account = new Account(rs.getInt("Id"), rs.getString("accountName"), String.format("%.2f", bal), rs.getString("asOfDate"));
				accounts.add(account);
			}
			rs.close();
			statement.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return accounts;
	}
	
	public static List<Expense> getExpenses(int id) {
		List<Expense> expenses = new ArrayList<Expense>();
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			String query = "SELECT * FROM Expenses WHERE ID=" + id + " ORDER BY txnDate DESC;";
			ResultSet rs = statement.executeQuery(query);
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			while (rs.next()) {
				float txnAmount = rs.getFloat("txnAmount");
				Date date = rs.getDate("txnDate");
				Expense expense = new Expense(rs.getInt("Id"), dateFormat.format(date), String.format("%.2f", txnAmount), rs.getString("txnType"), rs.getString("notes"));
				expenses.add(expense);
			}
			rs.close();
			statement.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return expenses;
	}
	
	public static boolean addExpense(Expense expense) {
		try {
			Connection con = createConnection();
			Statement statement = con.createStatement();
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		    Date parsedDate = dateFormat.parse(expense.getTransactionDate());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			statement.executeUpdate("INSERT INTO Expenses (Id, txnDate, txnAmount, txnType, notes) "
					+ "VALUES (" + expense.id + ", '" + timestamp + "', " + Float.parseFloat(expense.getTransactionAmount()) + ", '" + expense.getTransactionType() + "', '" + expense.getNotes() +"');");
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static void main(String[] args) {
		try {
			/*
			Date date = new Date();
			Address address = new Address(100, "street", "city", "country", "code");
			LoginInfo loginInfo = new LoginInfo("user6", "pass", date);
			PersonalInfo info = new PersonalInfo(1, "test", "lastname", "EMAIL", "PHONE", address, loginInfo);
			insertPersonalInformation(info);
			*/
			List<Account> accounts = getAccountSummary(21);
			System.out.println(accounts.size());
			
		} catch(Exception e) {System.out.println(e);}

	}

}
