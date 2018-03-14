package com.optimum.model;

public class User {
	// Input and Output Fields	---	START
	private static String name;
	private static String nric;
	private static String dateOfBirth;
	private static String email;
	private static String mobile;
	private static String password, tempPassword, retypePassword;
	private static String securityQuestion, securityAnswer;
	private static int attempts;
	private static String role,status;
	// Input and Output Fields	---	END
//		Setters & Getters ---	START
			//	Name	---	START
		public static void setName(String inputName) {
			name = inputName;
		}
		public static String getName() {
			return name;
		}
			//	Name	---	END
			//	NRIC	---	START
		public static void setNRIC(String inputNRIC) {
			nric = inputNRIC;
		}
		public static String getNRIC() {
			return nric;
		}
			//	NRIC	---	END
			//	Date of Birth	---	START
		public static void setDateOfBirth(String inputDateOfBirth) {
			dateOfBirth = inputDateOfBirth;
		}
		public static String getDateOfBirth() {
			return dateOfBirth;
		}
			//	Date of Birth	---	END
			//	Email	---	START
		public static void setEmail(String inputEmail) {
			email = inputEmail;
		}
		public static String getEmail() {
			return email;
		}
			//	Email	---	END
			//	Mobile	---	START
		public static void setMobile(String inputMobile) {
			mobile = inputMobile;
		}
		public static String getMobile() {
			return mobile;
		}
			//	Mobile	---	END
			//	Password	---	START
		public static void setPassword(String inputPassword) {
			password = inputPassword;
		}
		public static String getPassword() {
			return password;
		}
			//	Password	---	END
			//	Temporary Password	---	START
		public static void setTempPassword(String inputTempPassword) {
			tempPassword = inputTempPassword;
		}
		public static String getTempPassword() {
			return tempPassword;
		}
			//	Temporary Password	---	END
			//	Retype Password	---	START
		public static void setRetypePassword(String inputRetypePassword) {
			retypePassword = inputRetypePassword;
		}
		public static String getRetypePassword() {
			return retypePassword;
		}
			//	Retype Password	---	END
			//	Role (Admin/User)	---	START
		public static void setRole(String inputRole) {
			role = inputRole;
		}
		public static String getRole() {
			return role;
		}
			//	Role (Admin/User)	---	END
			//	Security Question --- START
		public static void setSecurityQuestion(String inputSecurityQuestion) {
			securityQuestion = inputSecurityQuestion;
		}
		public static String getSecurityQuestion() {
			return securityQuestion;
		}
			//	Security Question --- END
			//	Security Answer --- START
		public static void setSecurityAnswer(String inputSecurityAnswer) {
			securityAnswer = inputSecurityAnswer;
		}
		public static String getSecurityAnswer() {
			return securityAnswer;
		}
			//	Security Answer --- END
			//	Attempts	---	START
		public static void setAttempts(int newAttempts) {
			attempts = newAttempts;
		}
		public static int getAttempts() {
			return attempts;
		}
			//	Attempts	---	END
			//	Status	---	START
		public static void setStatus(String inputStatus) {
			status = inputStatus;
		}
		public static String getStatus() {
			return status;
		}
			//	Status	---	END
		//	Setters & Getters	---	END
		

}
