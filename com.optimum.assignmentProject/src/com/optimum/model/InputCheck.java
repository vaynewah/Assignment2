package com.optimum.model;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.optimum.controller.LoginController;

public class InputCheck {
	static Scanner sc = new Scanner(System.in);
	private static String name="";
	
	private static String nric;
	private static String dateOfBirth;
	private static String email;
	private static String mobile;
	private static String password, retypePassword;
	private static String tempPassword="";	
	private static String securityAnswer;
	private static Pattern pattern;
	private static Matcher matcher;

//	REGISTRATION VALIDATION	---	START
	//	Name check Validation 	---	START
	public static void nameCheck() {
		boolean nameCheck=false;
		while(!nameCheck) {
			name = sc.nextLine();
			if(!(name.equals(""))) {
				if(name.matches("([a-zA-Z]+\\s)*[a-zA-Z]*")) {
					User.setName(name);
					nameCheck=true;
				}
				else {
					System.out.println("Invalid Input	-	Please Enter a Valid Name (Alphabets Only)");
				}
			}
		}
	}
	//	Name check Validation	---	END
	//	NRIC check Validation	---	START
	public static void nricCheck() {
		boolean nricCheck=false;
		while(!nricCheck) {
			try {
				nric = sc.next();
				for(int x=0;x<=9;x++) {
					if(x==0 || x==8) {
						if(Character.isLetter(nric.charAt(x) )) { 	
							nricCheck=true;
						}
						else {
							nricCheck=false;
							System.out.println("Invalid Input	-	Please Enter a Valid NRIC");
							break;
						}
					}
					if(x>0 && x<8) {
						if(Character.isDigit(nric.charAt(x))) {
							nricCheck=true;
						}
						else {
							nricCheck=false;
							System.out.println("Invalid Input	-	Please Enter a Valid NRIC");
							break;
						}
					}
				}
				if(nricCheck==true) {
					User.setNRIC(nric);
				}
			}
			catch(StringIndexOutOfBoundsException e) {
				System.out.println("Invalid Input	-	Please Enter a Valid NRIC");
				nricCheck=false;
			}
		}
	}
	//	NRIC check Validation	---	END
	//	Date of Birth check Validation	---	START
	public static void dateOfBirthCheck() {
		boolean dateOfBirthCheck = false;
		while(!dateOfBirthCheck) {
			dateOfBirth=sc.next();
			//	Regex for Date of Birth	-	Split into 3 Groups (DD/MM/YYYY)	---	START
			String dateOfBirthRegex = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
			//	Regex for Date of Birth	-	Split into 3 Groups (DD/MM/YYYY)	---	END
			pattern = Pattern.compile(dateOfBirthRegex);
			matcher = pattern.matcher(dateOfBirth);
			if(matcher.matches()) {
				matcher.reset();
				if(matcher.find()) {
				String dd=matcher.group(1);	//	Day
				String mm=matcher.group(2);	//	Month
				int yy = Integer.parseInt(matcher.group(3));	//	Year
					if(dd.equals("31") && (mm.equals("04") || mm.equals("06") || mm.equals("09") || mm.equals("11") )){
						dateOfBirthCheck = false;
					}
					else if(mm.equals("02")) {	//	February
						if(yy%4==0) {	//	Year Divide by 4, to find which year is a Leap Year
							if(dd.equals("30") || dd.equals("31")) {	//	If it's a Leap year, no 30th or 31st Date
								dateOfBirthCheck = false;
							}
							else {
								dateOfBirthCheck = true;
							}
						}
						else {
							if(dd.equals("29") || dd.equals("30") || dd.equals("31")){	//	If it's NOT a Leap Year, no 29th, 30th or 31st Date 
								dateOfBirthCheck = false;
							}
							else {
								dateOfBirthCheck = true;
							}
						}
					}
					else {
						dateOfBirthCheck = true;
					}
				}
				else {
					dateOfBirthCheck = false;
				}
			}
			else {
				dateOfBirthCheck = false;
			}
			
//			dateOfBirthCheck = matcher.matches();
			if(dateOfBirthCheck==true) {
				User.setDateOfBirth(dateOfBirth);
//				dateOfBirthCheck=true;
			}
			else {
				System.out.print("Invalid Input		-	Please Enter a Date of Birth : ");
			}
		}
	}
	//	Date of Birth check Validation	---	END
	//	Email check Validation	---	START
	public static void emailCheck() {
		boolean emailCheck=false;
		while(!emailCheck) {
		email=sc.next();
		String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		
		pattern = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(email);
		emailCheck = matcher.matches();
		if(emailCheck==true) {
			User.setEmail(email);
//			emailCheck=true;
		}
		else {
			System.out.print("Invalid Input		-	Please Enter a Valid Email Address : ");
		}
		}
	}
	//	Email check Validation	---	END
	//	Mobile Number check Validation	---	START
	public static void mobileCheck() {
		boolean mobileCheck=false;
		while(!mobileCheck) {
			mobile = sc.next();
			//	Mobile Number must consist of 8 Digits ONLY	---	START
			if(mobile.matches("[0-9]+") && mobile.length()==8) {
				User.setMobile(mobile);
				mobileCheck = true;
			}
			//	Mobile Number must consist of 8 Digits ONLY	---	END
			else {
				System.out.print("Invalid Input		-	Please Enter a Valid Mobile Number (8 Numbers Only) : ");	
			}
		}
	}
	//	Mobile Number check Validation	---	END
	//	Generate Temporary Password	---	START
	public static void generateTempPassword() {
		//	First 4 Characters of the Temporary Password is the First 4 Numeric Value of the NRIC	---	START
		for(int x=1;x<5;x++) {
			tempPassword+=Character.toString(nric.charAt(x));
		}
		//	First 4 Characters of the Temporary Password is the First 4 Numeric Value of the NRIC	---	END
		//	Last 4 Characters of the Temporary Password is the Last 4 Numeric Value of the Mobile Number	---	START
		for(int x=4;x<8;x++) {
			tempPassword+=Character.toString(mobile.charAt(x));
		}
		//	Last 4 Characters of the Temporary Password is the Last 4 Numeric Value of the Mobile Number	---	END
		User.setPassword(tempPassword);
	}
	//	Generate Temporary Password	---	END
	
//	REGISTRATION VALIDATION	---	END
	
	//	Clear all Input		---	START
	public static void clearInput() {
		name = null;
		nric = null;
		dateOfBirth = null;
		email = null;
		mobile = null;
		tempPassword = "";
		securityAnswer = null;
	}
	//	Clear all Input		---	END
	//	Checks Temporary Password (for First Login)	---	START
	public static void checkTempPassword() {
		boolean tempPasswordCheck = false;
		String oldTempPassword = User.getTempPassword();
		while(!tempPasswordCheck) {
			tempPassword=sc.next();
			if (tempPassword.equals(oldTempPassword)) {
//				System.out.println("True");
				tempPasswordCheck = true;
			}
			else {
//				System.out.println("False");
				System.out.print("Please Re-Enter Old Password : ");
			}
		}
	}
	//	Checks Temporary Password (for First Login)	---	END
	//	Checks New Password (for First Login)	---	START
	public static void passwordComparison() {
		String passOne, passTwo;
		boolean compareCheck = false;
		while(!compareCheck) {
			System.out.print("Enter New Password : ");
			passOne = sc.next();
			while(passOne.equals(User.getTempPassword())) {
				System.out.println("New Password CANNOT be the same as the Previous Password");
				System.out.print("Enter New Password : ");
				passOne = sc.next();
			}
			System.out.print("Re-Enter New Password : ");
			
			passTwo = sc.next();
			if(passTwo.equals(passOne)) {
				User.setPassword(passTwo);
				compareCheck = true;
			}
			else {
				System.out.println("Incorrect Password");
			}
		}
	}
	//	Checks New Password (for First Login)	---	END
	//	Checks Security Question's Answer	---	START
	public static void securityAnswer() {
		boolean answerCheck = false;
		while(!answerCheck) {
			if(securityAnswer==null) {
			sc.nextLine();
			}
			securityAnswer =sc.nextLine();
			if(securityAnswer.matches("([a-zA-Z])*")){
				User.setSecurityAnswer(securityAnswer);
				answerCheck = true;
			}
			else {
				System.out.println("Security Question's Answer can only be ONE word");
				System.out.print("Please Re-Enter Answer : ");
			}
		}
	}
	//	Checks Security Question's Answer	---	END
	//	Compare Security Answer (Input with Database)	---	START
	public static void compareAnswer() {
		String inputSecurityAnswer = sc.next();
		if(inputSecurityAnswer.equals(User.getSecurityAnswer())) {
			passwordComparison();
		}
		else {
			System.out.println("Incorrect Answer"
					+ "Would you like to try again? (Y/N) : ");
			LoginController.yesNoSecurity(sc.next());
		}
	}
	//	Compare Security Answer (Input with Database)	---	END
	
}
