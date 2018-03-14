package com.optimum.controller;

import java.util.Scanner;

import com.optimum.dao.LoginDAO;
import com.optimum.dao.LoginDAOImplementation;
import com.optimum.model.InputCheck;
import com.optimum.model.Login;
import com.optimum.model.User;

public class LoginController {
	private LoginDAO refLoginDAO;
	private LoginDAOImplementation refLoginDAOImplementation;
	static Scanner sc = new Scanner(System.in);

	//	Main Login Page	---	START
	public void chooseOption(int option) {
		if(option==1) {
			Login.loginPage();
		}
		if(option==2) {
			Login.forgetPasswordPage();
		}
		if(!(option==1 || option==2)) {
			System.out.println("Please Select a Valid Option");
			Login.frontPage();
		}
	}
	//	Main Login Page	---	END
	//	Logout Page	---	START
	public void logoutOption(String option) {
		if(option.equals("y") || option.equals("Y")) {
			Login.logoutPage();
		}
		if(option.equals("n") || option.equals("N")) {
			Login.userPage();
		}
		if(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
			System.out.print("Please Select a Valid Option (Y/N) : ");
			logoutOption(sc.next());
		}
	}
	//	Logout Page	---	END

	//	[ADMIN] Admin Page	---	START
	public void chooseAdminOption(int option) {
		if(option==1) {
			Login.registerPage();
		}
		if(option==2) {
			Login.userListPage();
		}
		if(option==3) {
			Login.logoutPage();
		}
		
		if(!(option==1 || option==2 || option==3)) {
			System.out.println("Please Select a Valid Option");
			Login.adminPage();
		}
	}
	//	[ADMIN] Admin Page	---	END
	
	//	[ADMIN] Yes/No (ADMIN)	---	START
	public void yesNoOption(String option) {
		if(option.equals("y") || option.equals("Y")) {
			LoginDAOImplementation.addUser();
			//	Link to Database to Store Data
		}
		if(option.equals("n") || option.equals("N")) {
			System.out.println("Would you like to : ");
			System.out.println("1. Register New User");
			System.out.println("2. Go Back to Previous Page");
			System.out.println("Option : ");
			int choice = 0;
			boolean success=false;
			while(!success) {
				if(sc.hasNextInt()) {
					choice = sc.nextInt();
					registrationOrPreviousPage(choice);
					success=true;
				}
				else{
					System.out.println("Please Select a Valid Option");
					System.out.println("1. Register New User");
					System.out.println("2. Go Back to Previous Page");
					System.out.println("Option : ");
					sc.next();
				}
			}
		}
		if(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
			System.out.print("Please Select a Valid Option (Y/N) : ");
			yesNoOption(sc.next());
		}
	}
	//	[ADMIN] Yes/No (ADMIN)	---	END
	//	[ADMIN] Yes/No (If there's duplicate entry in the Database)	---	START
	public void yesNoDuplicate(String option) {
		if(option.equals("y") || option.equals("Y")) {
			Login.registerPage();
		}
		if(option.equals("n") || option.equals("N")) {
			Login.adminPage();
		}
		if(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
			System.out.print("Please Select a Valid Option (Y/N) : ");
			yesNoDuplicate(sc.next());
		}
	}
	//	[ADMIN] Yes/No (If there's duplicate entry in the Database)	---	END
	//	[ADMIN] UserList (Whether the Admin wish to Unlock/Lock an Account or go back to Admin Page)	---	START
	public static void yesNoUserList(String option) {
		if(option.equals("y") || option.equals("Y")) {
			System.out.print("Enter Email of User : ");
			InputCheck.emailCheck();
			LoginDAOImplementation.changeStatus();
		}
		if(option.equals("n") || option.equals("N")) {
			Login.adminPage();
		}
		if(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
			System.out.print("Please Select a Valid Option (Y/N) : ");
			yesNoUserList(sc.next());
		}
	}
	//	[ADMIN] UserList (Whether the Admin wish to Unlock/Lock an Account or go back to Admin Page)	---	END
	//	Yes/No (USER)	---	START
	public void yesNoOptionFirstLogin(String option) {
		if(option.equals("y") || option.equals("Y")) {
			LoginDAOImplementation.updateUser();
			//	Link to Database to Store Data
		}
		if(option.equals("n") || option.equals("N")) {
			System.out.println("Would you like to : ");
			System.out.println("1. Redo Security Question");
			System.out.println("2. Logout");
			System.out.print("Option : ");
			int choice = 0;
			boolean success=false;
			while(!success) {
				if(sc.hasNextInt()) {
					choice = sc.nextInt();
					firstLoginOrLogout(choice);
					success=true;
				}
				else{
					System.out.println("Would you like to : ");
					System.out.println("1. Redo Security Question");
					System.out.println("2. Logout");
					System.out.print("Option : ");		
					sc.next();
				}
			}
		}
		if(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
			System.out.print("Please Select a Valid Option (Y/N) : ");
			yesNoOptionFirstLogin(sc.next());
		}
	}
	//	Yes/No (USER)	---	END
	//	SecurityQuestion Selection	---	START
	public void securitySelection(String option) {
		if(option.equals("a") || option.equals("A")) {
			User.setSecurityQuestion("A. What is your Favourite Colour?");
		}
		if(option.equals("b") || option.equals("B")) {
			User.setSecurityQuestion("B. What is your Favourite Drink?");
		}
		if(option.equals("c") || option.equals("C")) {
			User.setSecurityQuestion("C. What is your Favourite Day of the Week?");	
		}
		if(!(option.equals("a") || option.equals("A") || option.equals("b") || option.equals("B") || option.equals("c") || option.equals("C"))) {
			System.out.print("Please Select a Valid Option : ");
			securitySelection(sc.next());
		}
	}
	//	SecurityQuestion Selection	---	END
	//	Registration or Previous Page (After Admin key in all the registration details and choose to Continue or Not)	---	START
	public void registrationOrPreviousPage(int option) {
		if(option==1) {
			InputCheck.clearInput();
			Login.registerPage();
		}
		if(option==2) {
			Login.adminPage();
		}
		if(!(option==1 || option==2)) {
			System.out.print("Please Select a Valid Option");
			registrationOrPreviousPage(sc.nextInt());
		}
	}
	//	Registration or Previous Page (After Admin key in all the registration details and choose to Continue or Not)	---	END
	//	First Login, Security Question (Whether User wish to register Security Question or Not)	---	START
	public void firstLoginOrLogout(int option) {
		if(option==1) {
			Login.securityQuestionPage();
		}
		if(option==2) {
			Login.logoutPage();
		}
	}
	//	First Login, Security Question (Whether User wish to register Security Question or Not)	---	END
	//	Yes/No (Security Question Answer)	---	START
	public static void yesNoSecurity(String option) {
		if(option.equals("y") || option.equals("Y")) {
			Login.forgetPasswordPage();
		}
		if(option.equals("n") || option.equals("N")) {
			Login.frontPage();
		}
		if(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
			System.out.print("Please Select a Valid Option (Y/N) : ");
			yesNoSecurity(sc.next());
		}
	}
	//	Yes/No (Security Question Anwer)	---	END
	//	InvalidLogin (Whether the User wish to Try Logging In again or Not, after a failed Login Attempt)	---	START
	public static void yesNoInvalidLogin(String option) {
		if(option.equals("y") || option.equals("Y")) {
			Login.loginPage();
		}
		if(option.equals("n") || option.equals("N")) {
			Login.frontPage();
		}
		if(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
			System.out.print("Please Select a Valid Option (Y/N) : ");
			yesNoInvalidLogin(sc.next());
		}
	}
	//	InvalidLogin (Whether the User wish to Try Logging In again or Not, after a failed Login Attempt)	---	END
	//	InvalidPasswordLogin (User click Forget Password but key in a Login ID that does not Exist)	---	START
	public static void yesNoForgetInvalidLogin(String option) {
		if(option.equals("y") || option.equals("Y")) {
			Login.forgetPasswordPage();;
		}
		if(option.equals("n") || option.equals("N")) {
			Login.frontPage();
		}
		if(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
			System.out.print("Please Select a Valid Option (Y/N) : ");
			yesNoForgetInvalidLogin(sc.next());
		}
	}
	//	InvalidPasswordLogin (User click Forget Password but key in a Login ID that does not Exist)	---	END
}
