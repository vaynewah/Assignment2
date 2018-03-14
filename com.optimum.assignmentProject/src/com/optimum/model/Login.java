package com.optimum.model;

import java.sql.Ref;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.optimum.controller.LoginController;
import com.optimum.dao.LoginDAOImplementation;

public class Login {
	static LoginController refLoginController = new LoginController();
	static Scanner sc = new Scanner(System.in);
	static String inputEmail;
	static String inputPassword;
	static String securityQuestion, securityAnswer;
	
//	Basic Pages	---	START
	//	Display FrontPage	---	START
	public static void frontPage() {
		System.out.println("--- FRONT PAGE ---");
		System.out.println("1. Login ");
		System.out.println("2. Forget Password ");
		System.out.print("Choose an Option : " );		
		int choice = 0;
		boolean success=false;
		while(!success) {
			if(sc.hasNextInt()) {
				choice = sc.nextInt();
				refLoginController.chooseOption(choice);
				success=true;
			}
			else{
				System.out.println("Please Select a Valid Option");
				System.out.println("--- FRONT PAGE ---");
				System.out.println("1. Login ");
				System.out.println("2. Forget Password ");
				System.out.print("Choose an Option : " );		
				sc.next();
			}
		}
	}
	//	Display FrontPage		---	END
	//	Display LoginPage	---	START
	public static void loginPage() {
		System.out.println("--- LOGIN PAGE ---");
		System.out.print("Login ID : ");
		inputEmail = sc.next();
		System.out.print("Password : ");
		inputPassword = sc.next();
		User.setEmail(inputEmail);
		User.setPassword(inputPassword);
		LoginDAOImplementation.userLogin();
	}
	//	Display LoginPage	---	END
	//	Display LogoutPage	---	START
	public static void logoutPage() {
		System.out.println("Logging Out	-	Thanks for Visiting!");
		System.out.println("");
		InputCheck.clearInput();
		Login.loginPage();
	}
	//	Display LogoutPage	---	END
//	Basic Pages	---	END
	
//	[ADMIN] Function Pages	---	START
	//	Display AdminPage	---	START
	public static void adminPage() {
		System.out.println("--- ADMIN PAGE ---");
		System.out.println("Welcome Back Admin!");
		System.out.println("");
		System.out.println("What would you like to do today?");
		System.out.println("1. Register New User");
		System.out.println("2. View UserList");
		System.out.println("3. Logout");
		System.out.print("Option : ");
		int choice = 0;
		boolean success=false;
		while(!success) {
			if(sc.hasNextInt()) {
				choice = sc.nextInt();
				refLoginController.chooseAdminOption(choice);
				success=true;
			}
			else{
				System.out.println("Please Select a Valid Option");
				System.out.println("--- ADMIN PAGE ---");
				System.out.println("Welcome Back Admin!");
				System.out.println("");
				System.out.println("What would you like to do today?");
				System.out.println("1. Register New User");
				System.out.println("2. View UserList");
				System.out.println("3. Logout");
				System.out.print("Option : ");	
				sc.next();
			}
		}
	}
	//	Display AdminPage	---	END	
	//	Display RegisterPage	---	START
	public static void registerPage() {
		System.out.println("--- REGISTER PAGE ---");
		System.out.print("Enter Name : ");
		InputCheck.nameCheck();
		System.out.print("Enter NRIC : ");
		InputCheck.nricCheck();
		System.out.print("Enter DOB (DD/MM/YYYY): ");
		InputCheck.dateOfBirthCheck();
		System.out.print("Enter Email : ");
		InputCheck.emailCheck();
		System.out.print("Enter Mobile : ");
		InputCheck.mobileCheck();
		InputCheck.generateTempPassword();
		System.out.println();
		System.out.println("REGISTRATION DATA");
		System.out.println("Name : " + User.getName());
		System.out.println("NRIC : " + User.getNRIC());
		System.out.println("Date of Birth : " + User.getDateOfBirth());
		System.out.println("Email : " + User.getEmail());
		System.out.println("Mobile : " + User.getMobile());
		System.out.print("Do you want to Carry On? (Y/N) : ");
		refLoginController.yesNoOption(sc.next());
		}
	//	Display RegisterPage	---	END
	//	Display User Lists Page	---	START
	public static void userListPage() {
		System.out.println("--- USER LIST ---");
		System.out.println("NAME\t\t EMAIL\t\t\t MOBILE\t\t STATUS");
		LoginDAOImplementation.viewUserList();
		System.out.print("Would you like to change the Status? (Y/N) : ");
		refLoginController.yesNoUserList(sc.next());
	}
	//	Display User Lists Page	---	END
	//	Display Option to Change Status	---	START
	public static void changePage() {
		System.out.print("Would you like to change the Status? (Y/N) : ");
		refLoginController.yesNoUserList(sc.next());
	}
	//	Display Option to Change Status	---	END
	//	Display Duplicate Entry Page	---	START
	public static void duplicateEntry() {
		System.out.print("Would you like to Register a New User? (Y/N) :");
		refLoginController.yesNoDuplicate(sc.next());
	}
	//	Display Duplicate Entry Page	---	END
	//
//	[ADMIN] Function Pages	---	END
	
//	[USER] Function Pages	---	START
	//	Display UserPage	---	START
	public static void userPage() {
		System.out.println("--- USER PAGE ---");
		System.out.println("Welcome Back " +User.getName()+"!");
		System.out.println("");
		System.out.print("Would you like to Logout? (Y/N)");
		refLoginController.logoutOption(sc.next());
	}
	//	Display UserPage	---	END
	//	Display User First LoginPage	---	START
	public static void firstLoginPage() {
		System.out.println("--- USER PAGE ---");
		System.out.println("Welcome " + User.getName() + "!");
		System.out.print("Enter Old Password : ");
		InputCheck.checkTempPassword();
		InputCheck.passwordComparison();
	}
	//	Display User First LoginPage	---	END
	//	Display SecurityQuestionPage	---	START
	public static void securityQuestionPage() {
		System.out.println("Security Question : ");
		System.out.println("A. What is your Favourite Colour?");
		System.out.println("B. What is your Favourite Drink?");
		System.out.println("C. What is your Favourite Day of the Week?");
		System.out.print("Select A Question : ");
		refLoginController.securitySelection(sc.next());
		System.out.print("Type in Answer : ");
		InputCheck.securityAnswer();
		System.out.print("Do you want to Carry On? (Y/N) : ");
		refLoginController.yesNoOptionFirstLogin(sc.next());
	}
	//	Display SecurityQuestionPage	---	END
	//	Display Forget Password Page	---	START
	public static void forgetPasswordPage() {
		System.out.println("--- FORGET PASSWORD ---");
		System.out.print("Login ID : ");
		inputEmail = sc.next();
		User.setEmail(inputEmail);
		LoginDAOImplementation.forgetPassword();
	}
	//	Display Forget Password Page	---	END
	//	Display Security Question for Forget Password Page	---	START
	public static void forgetPasswordSecurityQuestionPage() {
		System.out.println(User.getSecurityQuestion().replaceFirst("\\w*. ", "Question : "));
		System.out.print("Answer : ");
		InputCheck.compareAnswer();
		LoginDAOImplementation.resetPassword();
	}
	//	Display Security Question for Forget Password Page	---	END
	//	Display Invalid Login Page	-	When Login ID does not exist in the Database	---	START
	public static void invalidLogin() {
    	System.out.print("Would you like to Try Again? (Y/N) : " );
    	refLoginController.yesNoInvalidLogin(sc.next());
	}
	//	Display Invalid Login Page	-	When Login ID does not exist in the Database	---	END	
	//	InvalidPasswordLogin (User click Forget Password but key in a Login ID that does not Exist)	---	START
	public static void invalidForgetLogin() {
		System.out.println("Login ID does not Exist	-	Would you like to try again? (Y/N) : ");
    	refLoginController.yesNoForgetInvalidLogin(sc.next());
	}
	//	InvalidPasswordLogin (User click Forget Password but key in a Login ID that does not Exist)	---	END
//	[USER] Function Pages	---	END
}
