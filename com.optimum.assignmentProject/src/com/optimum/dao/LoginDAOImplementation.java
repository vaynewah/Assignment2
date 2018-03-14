package com.optimum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.optimum.controller.LoginController;
import com.optimum.model.Login;
import com.optimum.model.User;
import com.optimum.util.DBUtil;
import com.optimum.util.Email;

public class LoginDAOImplementation implements LoginDAO{
	private static Connection conn = DBUtil.getConnection();
	
	public LoginDAOImplementation() {
		conn=DBUtil.getConnection();
	}
	//	[ADMIN]	REGISTERING NEW USER	---	START
	public static void addUser() {
		try {
			String query = "INSERT into USER(name, nric, dateOfBirth, email, mobile, password, role, firstLogin, status, attempts) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, User.getName());
			preparedStatement.setString(2, User.getNRIC());
			preparedStatement.setString(3, User.getDateOfBirth());
			preparedStatement.setString(4, User.getEmail());
			preparedStatement.setString(5, User.getMobile());
			preparedStatement.setString(6, User.getPassword());
			preparedStatement.setString(7, "user");
			preparedStatement.setString(8, "T");
			preparedStatement.setString(9, "UNLOCK");
			preparedStatement.setInt(10, 0);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			System.out.println("--- USER CREATED ---");
			Email.main();
			Login.adminPage();
		}
		catch (MySQLIntegrityConstraintViolationException e1) {
	        System.out.println("A User, with the same Email Address, has already been created!");
	        Login.duplicateEntry();
	    }
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//	[ADMIN]	REGISTERING NEW USER	---	END
	//	[ADMIN]	View List of Users	---	START
	public static void viewUserList() {
		try {
			String query = "SELECT name , email, mobile, status FROM user WHERE serialNumber>1";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
	        ResultSet rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	User.setName(rs.getString("name"));
	        	User.setEmail(rs.getString("email"));
	        	User.setMobile(rs.getString("mobile"));
	        	User.setStatus(rs.getString("status"));
	    		System.out.println(User.getName() +"\t\t " + User.getEmail() +"\t" + User.getMobile() +"\t" + User.getStatus());
	        }
	        rs.close();
	        preparedStatement.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("No Users Created");
		}
	}
	//	[ADMIN] View List of Users	---	END
	//	[ADMIN] Change the Status of the User	---	START
	public static void changeStatus() {
		try {
			String query = "SELECT * FROM USER where email='"+User.getEmail()+"'";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
	        ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
	        	String roleDB = rs.getString("role");
				String statusDB = rs.getString("status");
				//	If the User Status is "LOCK", the ADMIN will change the Status to "UNLOCK"	---	START
				if(roleDB.equals("user") && statusDB.equals("LOCK")) {
					String updateQuery = "UPDATE USER set status=?, attempts=? where email='"+User.getEmail()+"'";
					PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
					updateStatement.setString(1, "UNLOCK");
					updateStatement.setInt(2, 0);
					updateStatement.executeUpdate();
					updateStatement.close();
					System.out.println("ACCOUNT UNLOCKED");
					Login.userListPage();
				}
				//	If the User Status is "LOCK", the ADMIN will change the Status to "UNLOCK"	---	END
				//	If the User Status is "UNLOCK", the ADMIN will change the Status to "LOCK"	---	START
				else if(roleDB.equals("user") && statusDB.equals("UNLOCK")) {
					String updateQuery = "UPDATE USER set status=?, attempts=? where email='"+User.getEmail()+"'";
					PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
					updateStatement.setString(1, "LOCK");
					updateStatement.setInt(2, 3);
					updateStatement.executeUpdate();
					updateStatement.close();
					System.out.println("ACCOUNT LOCKED");
					Login.userListPage();
				}
				//	If the User Status is "UNLOCK", the ADMIN will change the Status to "LOCK"	---	END
				//	Cannot Change the Status if the account is an ADMIN account	---	START
				else {
					System.out.println("Cannot change status of Admin User!");
					Login.changePage();
				}
				//	Cannot Change the Status if the account is an ADMIN account	---	END
			}
			else {
				System.out.println("User does not Exist!");
				Login.changePage();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("");
		}
	}
	//	[ADMIN] Change the Status of the User	---	END
	//	Logging In	---	START
	public static void userLogin() {
		try {
			String query = "SELECT * FROM USER where email = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setString(1,User.getEmail());
	        ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	        	String passwordDB = rs.getString("password");
	        	int attempts = rs.getInt("attempts");
	        	String roleDB = rs.getString("role");
	        	User.setAttempts(attempts);
	        	User.setRole(roleDB);
	        	//	If the account belongs to an Admin, redirect to AdminPage	---	START
	        	if(roleDB.equals("admin") && passwordDB.equals(User.getPassword())) {
	        		Login.adminPage();
	        	}
	        	//	If the account belongs to an Admin, redirect to AdminPage	---	END
	        	//	If the account belongs to a User with lesser than 3 failed login attempts, redirect to User Login Page	---	START
	        	else if(roleDB.equals("user") && passwordDB.equals(User.getPassword()) && attempts<3) 
	        	{
	        		String updateQuery = "UPDATE USER SET attempts =? WHERE email='"+User.getEmail()+"'";
	    			PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	        		updateStatement.setInt(1, 0);
	    			updateStatement.executeUpdate();
	        		//	If it is the User's first time logging in	---	START
			        String firstLogin = rs.getString("firstLogin");
			        if(firstLogin.equals("T")){
			        	String retrievedName = rs.getString("name");
			        	String retrievedPassword = rs.getString("password");
			        	User.setName(retrievedName);
			        	User.setTempPassword(retrievedPassword);
			        	Login.firstLoginPage();
			        	Login.securityQuestionPage();
			        }
	        		//	If it is the User's first time logging in	---	END
	        		//	Redirect to User Login Page	---	START
			        else {
			        	String retrievedName = rs.getString("name");
			        	String retrievedPassword = rs.getString("password");
			        	User.setName(retrievedName);
			        	Login.userPage();
			        	}
	        		//	Redirect to User Login Page	---	END
	        	}
	        	//	If the account belongs to a User with lesser than 3 failed login attempts, redirect to User Login Page	---	END
	        	//	If the account belongs to a User with 3 or more failed login attempts, notifies User that account is LOCKED	---	START
	        	else if(roleDB.equals("user") && passwordDB.equals(User.getPassword()) && attempts>=3) {
	        		System.out.println("ACCOUNT LOCKED	-	PLEASE CONTACT YOUR ADMINISTRATOR");
	        		Login.frontPage();
	        	}
	        	//	If the account belongs to a User with 3 or more failed login attempts, notifies User that account is LOCKED	---	END
	        	//	Redirects to userPenalty class	---	START
	        else {
	        	System.out.println("Incorrect Login Credentials");
	        	if(roleDB.equals("user")) {
	        		userPenalty();
	        		}
	        	else {
	    			Login.loginPage();
	        	}
	        }
	        	//	Redirects to userPenalty class	---	END
	        }
	        else {
	        	System.out.println("Incorrect Login ID ");
	        	Login.invalidLogin();
	        }
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//	Logging In	---	END
	//	User first Login, sets SecurityQuestion, SecurityAnswer and New Password	---	START
	public static void updateUser() {
		try {
			String query = "UPDATE USER SET password=?, securityQuestion=?, securityAnswer=?, firstLogin=?, status=? WHERE email ='"+User.getEmail()+"'";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, User.getPassword());
			preparedStatement.setString(2, User.getSecurityQuestion());
			preparedStatement.setString(3, User.getSecurityAnswer());
			preparedStatement.setString(4, "F");
			preparedStatement.setString(5, "UNLOCK");
			preparedStatement.executeUpdate();
			preparedStatement.close();
			System.out.println("User Updated - Back to Front Page");
			Login.frontPage();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update Fail");
		}
	}
	//	User first Login, sets SecurityQuestion, SecurityAnswer and New Password	---	END
	//	Incorrect Login Credentials, increase Failed Login Attempts by 1	---	START
	public static void userPenalty() {
		try {
			int attempts = User.getAttempts();
			attempts+=1;
			//	If it is the User's THIRD failed login attempt, the Account is LOCKed	---	START
			if(attempts==3) {
				String query = "UPDATE USER SET status=?, attempts=? where email='"+User.getEmail()+"'";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, "LOCK");
				preparedStatement.setInt(2, attempts);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				System.out.println("ACCOUNT LOCKED	-	PLEASE CONTACT YOUR ADMINISTRATOR");
				System.out.println("Redirected to Front Page");
				Login.frontPage();
			}
			//	If it is the User's THIRD failed login attempt, the Account is LOCKed	---	END
			if(attempts<3) {
				String query = "UPDATE USER SET attempts=? where email='"+User.getEmail()+"'";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, attempts);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				Login.invalidLogin();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Penalty Fail");
		}
	}
	//	Incorrect Login Credentials, increase Failed Login Attempts by 1	---	END
	//	Forget Password	---	START
	public static void forgetPassword() {
		try {
			String query ="SELECT * from USER where email='"+User.getEmail()+"'";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
	        ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				String statusDB = rs.getString("status");
				String firstLoginDB = rs.getString("firstLogin");
				//	If the Account has not Logged into the System before, no Security Question/Answer has been set	---	START
				if(firstLoginDB.equals("T")) {
					System.out.println("You have not Logged Into the System yet, password is the same as written in the Email.");
					System.out.println("--- REDIRECTING TO FRONT PAGE ---");
					Login.frontPage();
				}
				//	If the Account has not Logged into the System before, no Security Question/Answer has been set	---	END
				//	If the Account status is Locked, the User cannot select Forget Password	---	START
				if(statusDB.equals("LOCK")) {
					System.out.println("ACCOUNT LOCKED	-	PLEASE CONTACT YOUR ADMINISTRATOR");
					System.out.println("--- REDIRECTING TO FRONT PAGE ---");
					Login.frontPage();
				}
				//	If the Account status is Locked, the User cannot select Forget Password	---	END
				//	If the Account status is Unlocked, the User can select Forget Password	---	START
				if(statusDB.equals("UNLOCK")) {
				String securityQuestionDB = rs.getString("securityQuestion");
				String securityAnswerDB = rs.getString("securityAnswer");
				User.setSecurityQuestion(securityQuestionDB);
				User.setSecurityAnswer(securityAnswerDB);
				Login.forgetPasswordSecurityQuestionPage();
				}
				//	If the Account status is Unlocked, the User can select Forget Password	---	END
			}
			else {
				Login.invalidForgetLogin();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Penalty Fail");
		}
	}
	//	Forget Password	---	END
	//	Password Change	---	START
	public static void resetPassword() {
		try {
			String query = "UPDATE USER SET password=? where email='"+User.getEmail()+"'";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, User.getPassword());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			System.out.println("Password Reset Complete - Please Set New Security Question");
			Login.securityQuestionPage();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update Fail");
		}
	}
	//	Password Change	---	END
}
