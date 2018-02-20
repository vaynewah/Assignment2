package assignments;
import java.util.Scanner;
//	Output Class	---	START
class output{
	static int arraySize;
	static Scanner sc = new Scanner(System.in);
	static int arrayOne[] = new int [arraySize];
	//	Display Array	---	START
	static void displayArraySize(int arraySize) {
		int num1=-1,num2=1,num3;
		int arrayOne[] = new int [arraySize];
		System.out.print("Output : ");
		for(int i=0; i<arraySize;i++) {
			num3 = num1 + num2;	//	Next Number = First Number + Second Number // First Number : 0 = (-1+1)
			arrayOne[i] = num3;	//	Save first Number
			System.out.print(arrayOne[i] + " " );
			num1 = num2;
			num2 = num3;
		}
		setArray(arrayOne);
	}
	//	Display Array	---	END
	//	Display Error	---	START
	static int displayError() {
		System.out.println("Error, Array Size must be ranging from 1 to 12 \n");	//	If user input Array Size<0 || Array Size>12, Error will appear
		arraySize=0;
		return arraySize;
	}
	//	Display Error	---	END
	//	Display Elements, in the array, that are lesser than Max Value	---	START
	static void displayMax(int maxValue) {
		arrayOne = getArray();
		System.out.print("Output lesser than Max Value : ");
		for(int i=0; i<arrayOne.length;i++) {
			if(arrayOne[i] < maxValue) {	//	If number is lesser than User Input (maxValue)
				System.out.print(arrayOne[i] + " ");	//	Display all the numbers that are lesser than User Input
			}
		}
	}
	//	Display Elements, in the array, that are lesser than Max Value	---	END
	
	static void setArray(int arrayOne[]) {
		output.arrayOne = arrayOne;
	}
	static int[] getArray() {
		return arrayOne;
	}
}
//	Output Class	---	END
public class Array {
	public static void main(String[] args) {
		int arraySize = 0;
		int maxValue;
		Scanner sc = new Scanner(System.in);
		while(arraySize==0) {
			System.out.print("Enter Array Size : ");
			arraySize = sc.nextInt();
			//	If User enter an Array Size >12, Display Error	---	START
			if (arraySize>12 || arraySize<=0) {
				arraySize = output.displayError();
			}
			//	If User enter an Array Size >12, Display Error	---	END
		output.displayArraySize(arraySize);
		}
		System.out.println("");
		System.out.print("Enter Max Value : " );
		maxValue = sc.nextInt();
		output.displayMax(maxValue);
	}
}