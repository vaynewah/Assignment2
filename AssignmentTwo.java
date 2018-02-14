package assignments;

import java.util.Arrays;
import java.util.Scanner;

//	QUESTION ONE	---	START
class QuestionOne {
	void treeOne() {	//	*
		int x,y;	//	**	<--	OUTPUT
		{			//	***	
			for(x=1; x<=5;x++) {	//	determines the Height of the triangle
				for(y=1; y<=x;y++) {	//	determines the number of columns of the triangle
					System.out.print("* ");
				}
				System.out.println();
			}
		}	
	}
	void treeTwo() {	//	1
		int x,y;	//	12	<--	OUTPUT
		{			//	123
			for(x=1; x<=5;x++) {	//	determines the Height of the triangle
				for(y=1; y<=x;y++) {	//	determines the number of columns of the triangle
					System.out.print(y + " ");
				}
				System.out.println();
			}
		}
	}
	void treeThree() {	//	  *	
		int x,y,z;		//	 * *
		{				//	* * *	
			for(x=1; x<=5;x++) {			//	determines the Height of the triangle
	
				for(z=1; z<=5-x; z++) {		//	determines the number of spaces
					System.out.print(" ");	//	e.g when the height(x) is 1, number of spaces will be 5-(x)1
				}
				for(y=1; y<=x;y++) {		//	determines the number of columns of the triangle
					System.out.print("* ");	//	spacing after * to form the triangle shape
				}
				System.out.println();
			}
		}
	}
	void treeFour() {	//	1
		int x,y;		//	22	<--	OUTPUT
		{				//	333
			for(x=1; x<=5;x++) {	//	determines the Height of the triangle
				for(y=1; y<=x;y++) {	//	determines the number of columns of the triangle
					System.out.print(x + " ");
				}
				System.out.println();
			}
		}
	}
	void treeFive() {	//	5
		int x,y;		//	54	<--	OUTPUT
		{				//	543
			for(x=5; x>=1;x--) {	//	determines the Height of the triangle
				for(y=5; y>=x;y--) {	//	determines the number of columns of the triangle
					System.out.print(y + " ");
					}
				System.out.println();
			}
		}
	}
	void treeSix(){		//	1	
		int x,y,z;		//	121		<--	OUTPUT
		{				//	12321
			for(x=1; x<=5;x++ ) {					//	determines the Height
				for(y=1; y<=x; y++) {
					System.out.print(y + " ");		//	output in ascending order
				}
				for (z=x;z>1; z--) {
					System.out.print(z-1 + " ");	//	output in descending order
				}
				System.out.println();
			}
		}
	}
}
//	QUESTION ONE	---	END

//	QUESTION TWO	---	START
class QuestionTwo {
	// QUESTION 2A	---	END
	void arrayOne()
	{
		{
			int input[] = {1,2,4,6,3,7,8,9,10,11};
			int inputSum = 0;
			int inputLength = 11;
			int sum = (inputLength*(inputLength+1))/2;
			System.out.print("Input : ");
				for( int i = 0; i < input.length; i++) {
					System.out.print(input[i] + " ");
					inputSum += input[i];
				}
				System.out.println();
				sum = sum - inputSum;
				System.out.print("Missing Output : " + sum);
		}
	}
	// QUESTION 2A	---	END
	// QUESTION 2B	---	START
	void arrayTwo()
	{
		{
			int i,j;
			int input[] = {5,2,9,15,2,7,7,18,15,5};
			boolean checker=false;				//	checks if element repeats itself
			//	Display original Input	---	START
			System.out.print("Input : ");
			for(i =0; i<input.length; i++ ) {
			System.out.print(input[i] + " " );
			}
			//	Display original Input	---	OUT
			
			System.out.println();
			System.out.print("First non-repeating Element : ");
			for(i =0; i<input.length; i++ ) {
				for(j=i+1; j<input.length; j++) {
					if(input[i]==input[j]){		//	if element repeats,
						checker=true;			//	checker becomes true
						break;
					}
					else {
						checker=false;			//	if false, means no repeating element
					}
				}
				if(checker==false) {			//	if false, non-repeating element appears
					System.out.print(input[i] + " ");
					break;						//	break so only first non-repeating element is displayed
				}
			}
		}
	}
	// QUESTION 2B	---	END

	// QUESTION 2C	---	START
	void arrayThree() {
		int i,j;
		int input[] = {5,2,9,15,2,7,7,18,15,5};
		int temp;	//	Temporary Array
		//	Display original Input	---	START
		System.out.print("Input : ");
		for(i=0; i<input.length; i++) {
			System.out.print(input[i]+ " " );
		}
		//	Display original Input	---	END
		for(i=0;i<input.length;i++) {
			for(j=i+1; j<input.length;j++) {
				if(input[i]>=input[j]) {	//	if first value (i) is greater than the next value(j) in line,
					temp = input[i];		//	Value (i) is stored in a temporary array
					input[i] = input[j];	//	Value (i) is replaced with value (j)	//	Smaller value shifts forward
					input[j] = temp;		//	Value (j) is replaced with value in temporary array	//	Next value in line is replaced with Greater Value
				}
			}
		}
		System.out.println();
		//	Display final Output	---	START
		System.out.print("Output : ");
		for(i=0; i<input.length; i++) {
			System.out.print(input[i]+ " " );
		}
		//	Display final Output	---	END
	}
	// QUESTION 2C	---	END
	// QUESTION 2D	---	START
	void arrayFour() {
		int i,j;
		int input[] = {16,7,89,20,7,99,21,56,12,10};
		boolean checker=false;				//	checks if element repeats itself
		//	Display original Input	---	START
		System.out.print("Input : ");
		for(i =0; i<input.length; i++ ) {
		System.out.print(input[i] + " " );
		}
		//	Display original Input	---	OUT
		
		System.out.println();
		System.out.print("Repeated elements are : ");
		for(i =0; i<input.length; i++ ) {
			for(j=i+1; j<input.length; j++) {
				if(input[i]==input[j]){		//	if element repeats,
					System.out.print(input[i]);
				}
				
			}
		}
	}
	// QUESTION 2D	---	END
	// QUESTION 2E	---	START
	void arrayFive() {
		int i,j;
		int input[] = {16,4,89,20,7,99,21,56,12,10};
		int temp;
//		Display original Input	---	START
		System.out.print("Input : ");
		for(i=0; i<input.length; i++) {
			System.out.print(input[i] + " " );
		}
//		Display original Input	---	END
		System.out.println();
		//	Finding & Displaying the Smallest value in the Array	---	START
		for(i=0; i<input.length; i++) {
			for(j=i+1; j<input.length; j++) {
				if(input[i]>=input[j]) {	//	if first value (i) is greater than the next value(j) in line,
					temp = input[i];		//	Value (i) is stored in a temporary array
					input[i] = input[j];	//	Value (i) is replaced with value (j)	//	Smaller value shifts forward
					input[j]=temp;			//	Value (j) is replaced with value in temporary array	//	Next value in line is replaced with Greater Value
				}
				
			}
		}
		for(i=0; i<input.length; i++) {
			System.out.println("Smallest Value : " + input[i]);
			break;
		}
		//	Finding & Displaying the Smallest value in the Array	---	END
		
		//	Finding & Displaying the Largest value in the Array	---	START
		for(i=0; i<input.length; i++) {
			for(j=i+1; j<input.length; j++) {
				if(input[i]<=input[j]) {	//	if first value (i) is lower than the next value(j) in line,
					temp = input[i];		//	Value (i) is stored in a temporary array
					input[i] = input[j];	//	Value (i) is replaced with value (j)	//	Greater value shifts forward
					input[j]=temp;			//	Value (j) is replaced with value in temporary array	//	Next value in line is replaced with Lower Value
				}
				
			}
		}
		for(i=0; i<input.length; i++) {
			System.out.println("Largest Value : " +input[i]);
			break;
		}
		//	Finding & Displaying the Largest value in the Array	---	END
	}
	// QUESTION 2E	---	END
	// QUESTION 2F	---	END
	void arraySix() {
		int i,j;
		int input[] = {16,4,20,20,7,99,21,56,12,10};
		//	Display original Input	---	START
		System.out.print("Input : ");
		for(i =0; i<input.length; i++ ) {
		System.out.print(input[i] + " " );
		}
		//	Display original Input	---	OUT
		System.out.println();
		System.out.print("Output without Duplicates : ");
		for(i =0; i<input.length; i++ ) {
			for(j =i+1; j<input.length; j++) {
				if(input[i]==input[j]){		//	if element repeats,
					i++;
				}
				else {
					input[i] = input[i];
				}
			}
			System.out.print(input[i] + " ");
		}
	}
	// QUESTION 2F	---	END
}
//	QUESTION TWO	---	END

//	QUESTION THREE	---	START
class QuestionThree{
	// QUESTION 3A	---	START
	void arrayStringOne() {
	String input[] = {"java", "oracle", "python", "scala", "hibernate"};
	String temp;
		for(int i=0; i<input.length; i++) {
			for(int j=i+1; j<input.length; j++) {
				temp = input[i];		//	Value (i) is stored in a temporary array
				input[i] = input[j];	//	Value (i) is overwritten with Value(j), which is the next element in the Array
				input[j]=temp;			//	Value (j) is replaced with value in temporary array
			}
			System.out.print(input[i] + " ");
		}
	}
	// QUESTION 3A	---	END
	// QUESTION 3B	---	START
	void arrayStringTwo() {
		int i;
		int j=0;
		String input = "oracle";
		//	Find the length of the Input	---	START
		int inputLength = input.length();
		//	Find the length of the Input	---	END
		//	Display original Input	---	START
		System.out.println("Original Input : " +input);
		//	Display original Input	---	END
		//	Read individual alphabet as an element	---	START
		char[] revInput = new char[input.length()];
		char[] revArray = input.toCharArray();
		for (i=inputLength-1;i>=0;i--) {
			revInput[j] = revArray[i];
			j++;
			}
		//	Read individual alphabet as an element	---	START
		//	Display Reversed Output
		System.out.print("Output (reversed) : " );
		System.out.print(revInput);
		//	Display Reversed Output
	}
	// QUESTION 3B	---	END
	// QUESTION 3C	---	START
	void arrayStringThree() {
		int i,j;
		String input[] = {"java", "hibernate", "python", "scala", "hibernate"};
		String temp[] = new String[input.length];
		int inputLength=input.length;
		//	Display original Input	---	START
		System.out.print("Input : ");
		for(i =0; i<input.length; i++ ) {
		System.out.print(input[i] + " " );
		}
		//	Display original Input	---	OUT
		System.out.println();
		System.out.print("Output without Duplicates : ");
		for(i=inputLength-1; i>=0; i--) {
			for(j=i-1; j>=0; j--) {
				if(input[i] == input[j]) {
					input[i] = input[j];
					i--;
				}
				else {
					input[i] = input[i];
				}
			}		
			temp[i] = input[i];
		}
		for(i=0; i<input.length; i++) {
			if(!(temp[i]==null))
			System.out.print(temp[i] + " " );
		}
	}
	// QUESTION 3C	---	END
	// QUESTION 3D	---	START
	void arrayStringFour() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Please Enter a Number : " );
		String input = sc.nextLine();
//		String input = "153";
		int i, j;
		int digits=0;
		int sum =0 ;
		int cubicNumber[] = new int[input.length()];
		int inputLength = input.length();
		System.out.println("Original Input : " +input);
		char[] revArray = input.toCharArray();
		for(i=0; i<inputLength;i++) {
			for(j=0; j<inputLength;j++) {
				//	Read each input as digits	---	START
				digits = Character.getNumericValue(revArray[i]);
				//	Read each input as digits	---	END
				//	Multiply the digits to the power of 3	---	START
				cubicNumber[i] = digits*digits*digits;
				//	Multiply the digits to the power of 3	---	END
			}
			//	Add all the numbers together	---	START
			sum += cubicNumber[i];
			//	Add all the numbers together	---	END
		}
		if(sum == Integer.parseInt(input)) {
			System.out.println(input +" is an Armstrong number");
		}
		else
			System.out.println(input +" is not an Armstrong number");
		
	}
	// QUESTION 3D	---	END
}
//	QUESTION THREE	---	END
//	QUESTION THREE	---	START
class QuestionFour{
	void questionFour() {
		int i,j;
		int arr1[] = {4,7,3,9,2};
		int arr2[] = {3,2,12,9,40,32,4};
		int output = 0;
//		Display original Input	---	START
		System.out.print("Array 1 : ");
		for(i=0;i<arr1.length;i++) {
			System.out.print(arr1[i]+" ");
		}
		System.out.println();
		System.out.print("Array 2 : ");
		for(i=0;i<arr2.length;i++) {
			System.out.print(arr2[i]+" ");
		}
//		Display original Input	---	END
		System.out.println();
		for(i=0; i<arr1.length;i++) {
			for(j=0;j<arr2.length;j++) {
				if(arr1[i] == arr2[j]) {
					output = arr1[i];
					System.out.print(output + " ");
				}
			}
		}
	}
}


public class AssignmentTwo {

	public static void main(String[] args) {
		
		//	Assignment 2	-	Question 1	---	START
//		QuestionOne QuestionOne = new QuestionOne();
//		QuestionOne.treeOne();
//		QuestionOne.treeTwo();
//		QuestionOne.treeThree();
//		QuestionOne.treeFour();
//		QuestionOne.treeFive();
//		QuestionOne.treeSix();
		//	Assignment 2	-	Question 1	---	END
		
		//	Assignment 2	-	Question 2	---	START
//		QuestionTwo QuestionTwo = new QuestionTwo();
//		QuestionTwo.arrayOne();
//		QuestionTwo.arrayTwo();
//		QuestionTwo.arrayThree();
//		QuestionTwo.arrayFour();
//		QuestionTwo.arrayFive();
//		QuestionTwo.arraySix();
		//	Assignment 2	-	Question 2	--- END

		//	Assignment 2	-	Question 3	---	START
//		QuestionThree QuestionThree = new QuestionThree();
//		QuestionThree.arrayStringOne();
//		QuestionThree.arrayStringTwo();
//		QuestionThree.arrayStringThree();
//		QuestionThree.arrayStringFour();
		//	Assignment 2	-	Question 3	---	END
		
		//	Assignment 2	-	Question 4	---	START
//		QuestionFour QuestionFour = new QuestionFour();
//		QuestionFour.questionFour();
		//	Assignment 2	-	Question 4	---	END
	}

}


