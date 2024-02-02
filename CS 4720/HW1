// Nicholas Chesi

/*
 * what the timing accuracy was based on how you did the timing, 
 * and how closely the results match the theoretical timing based on your n, 
 * and the big O's for the two algorithms.
 * 
 * For my timing I used System.nanoTime() before and after, then figured out the difference
 * for each of the different sorting algorithms. For my selection sort, I expected n^2 timing
 * for my 100,000 n's and that equals 10,000,000,000, compared to 500,000 expected for the merge
 * sort with n log(n), we expected the merge sort to be 20,000 times faster than the selection sort.
 * In reality the merge sort was only 10 times faster, than the selection sort, this could be due to
 * the hardware, the recursion of the merge sort, and any processes running on the OS being on the schools
 * computer in the math center.
 * 
 * */
package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HW1 {

	public static void main(String[] args) {
		// Calls readInput to read the file input and then calls selection
		// sort to sort the input.
		// Then it prints the array
		// Selection sort print
		long startTime = System.nanoTime();
		printArray(selectionSort(readInput()));
		long endTime = System.nanoTime();
		long ssortTime = (endTime - startTime);
		// Merge sort, same process as the above call stack
		long startTimeM = System.nanoTime();
		printArray(mergeSort(readInput()));
		long endTimeM = System.nanoTime();
		long msortTime = (endTimeM - startTimeM);
		
		System.out.println("Selection Sort: " + ssortTime);
		// 2512698700, 2.512 seconds
		System.out.println("Merge Sort: " + msortTime);
		// 354448499, 0.354 seconds
	}

	private static int[] readInput() {
		int[] input = null;

		// Try loop to avoid throwing an exception in main
		try {
			// Reading in the file
			//File fileToRead = new File("input.txt");
			File fileToRead = new File("input_large.txt");
			//File fileToRead = new File("input_ta.txt");
			Scanner readFile = new Scanner(fileToRead);
			// Counter to 0, to count number of items in the file
			int counter = 0;
			// Checks the file and adds to counter to
			while (readFile.hasNextInt()) {
				counter++;
				// Read the integer to move the scanner forward
				readFile.nextInt();
			}
			readFile.close();

			// Reinitialize the scanner to read the file again
			readFile = new Scanner(fileToRead);
			// Setting the size of the array to what the while loop counted
			input = new int[counter];
			// reading in the actually integers to the array
			for (int i = 0; i < counter; i++) {
				if (readFile.hasNextInt()) {
					input[i] = readFile.nextInt();
				}
			}
			// Closing the file
			readFile.close();
			// Catch for the file not found
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// returning input for the selection sort
		return input;
	}

	private static int[] selectionSort(int[] unsortedArray) {
		// Copies contents of the array
		int[] sortedArray = unsortedArray;

		for (int i = 0; i < unsortedArray.length - 1; i++) {
			// Sets the current position to i for each iteration of i
			int currentIndex = i;
			// Creates a loop that is one greater than i, so that we can compare
			// the element that is one greater than i
			for (int j = i + 1; j < unsortedArray.length; j++) {
				// checks if position j in the array is less than the current position of i
				// in the array, to find the lowest element
				if (unsortedArray[j] < unsortedArray[currentIndex]) {
					currentIndex = j;
				}
			}
			// Temp holds the sorted array current index
			int temp = sortedArray[currentIndex];
			// setting sorted array of current index equal to the sorted array of position i
			sortedArray[currentIndex] = sortedArray[i];
			// setting the sortedArray[i] to temp, for the correct position
			sortedArray[i] = temp;
		}

		return sortedArray;
	}

	// Input: array A of n distinct integers.
	// Output: array with the same integers, sorted from
	// smallest to largest.
	// ignoring base cases
	// C := recursively sort first half of A
	// D := recursively sort second half of A
	// return Merge (C,D)

	private static int[] mergeSort(int[] unsortedArray) {
		// Base case if the array is of length 0 or 1, it's already sorted
		if (unsortedArray.length <= 1) {
			return unsortedArray;
		}

		// Find the middle of the array
		int middle = unsortedArray.length / 2;

		// Divide the array into two halves
		// copyOfRange is like array[mid:] or array[:mid] in python
		// it takes the left or right half!
		int[] leftHalf = Arrays.copyOfRange(unsortedArray, 0, middle);
		int[] rightHalf = Arrays.copyOfRange(unsortedArray, middle, unsortedArray.length);

		// Recursively sort both halves
		leftHalf = mergeSort(leftHalf);
		rightHalf = mergeSort(rightHalf);

		// Merge the sorted halves and return
		return merge(leftHalf, rightHalf);
	}

	// Merge two sorted arrays

	// Input: sorted arrays C and D (length n/2 each).
	// Output: sorted array B (length n).
	// Simplifying assumption: n is even.
	// 1 i := 1
	// 2 j := 1
	// 3 for k := 1 to n do
	// 4 if C[i] < D[j] then
	// 5 B[k] := C[i] // populate output array
	// 6 i := i + 1 // increment i
	// 7 else // D[j] < C[i]
	// 8 B[k] := D[j]
	// 9 j := j + 1

	private static int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];
		// Initializing integers for counting
		// Index for left array
		int leftSide = 0;
		// Index for right array
		int rightSide = 0;
		// Index for result array
		int resultIndex = 0;

		// Merge while there are elements in both arrays
		// Used while loop instead of a for, they are essentially the same
		// as I wanted to keep track of leftSide and rightSide, indexing integers,
		// this helped me to conceptualize it. It also has the same time complexity.
		while (leftSide < left.length && rightSide < right.length) {
			// If the ledt side is less than or equal to the right, we assign
			// the array result in position result index (our tracking index) to
			// that left side array element at position leftSide count
			// else we assign the right side element at position right side
			if (left[leftSide] <= right[rightSide]) {
				// Assigning element from left array to result array
				result[resultIndex] = left[leftSide];
				// Increment leftSide index
				leftSide++;
			} else {
				// Assigning element from right array to result array
				result[resultIndex] = right[rightSide];
				// Increment rightSide index
				rightSide++;
			}
			// Increment resultIndex index
			resultIndex++;
		}

		// Populating output array from left side
		// Both this while loop and the one below it could have been a for loop with
		// an if statement inside of it but, this, like the above while loop, helped
		// me to keep track of everything. Essentially this just assigns result the left
		// array and increments both counters while keeping track of the counters
		// previous
		// values from the above while loop
		while (leftSide < left.length) {
			// set result of element of index result to the element held by
			// left of index left side
			// we then increment both result index and left side counters
			result[resultIndex] = left[leftSide];
			resultIndex++;
			leftSide++;
		}
		// Populating output array from right side
		while (rightSide < right.length) {
			// this is the same as the above we are populating the rest of the array
			// and incrementing both indexes
			result[resultIndex] = right[rightSide];
			resultIndex++;
			rightSide++;
		}

		// Return the merged result
		return result;
	}

	// Prints array
	private static void printArray(int[] array) {
		// First line of the output
		System.out.println("Nicholas Chesi");
		// Printing the array for display
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
