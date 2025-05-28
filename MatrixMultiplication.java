import java.util.Scanner; // import to get user input

// 3. Matrix Multiplication with Threads
public class MatrixMultiplication implements Runnable {
	int[][] matrix1;
	int[][] matrix2;
	int[][] result;
	
	// Run method to calculate the result matrix
	@Override
	public void run() {
		for ( int i = 0 ; i < matrix1.length ; i++ ) {
			for ( int j = 0; j < matrix2[i].length ; j++ ) {
				for ( int k = 0; k < matrix2.length ;k++)
					result[i][j] += matrix1[i][k] * matrix2[k][j];
			}
		}
	}
	
	// Constructor to add matrix1 and matrix2
	public MatrixMultiplication(int[][] m1, int[][] m2) {
		try {
			if (m1[0].length != m2.length)
				throw new Exception("This multiplication cannot be done.");
			else {
				matrix1 = m1;
				matrix2 = m2;
				result = new int[m1.length][m2[0].length];
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	// A method to create a matrix with user input
	public static int[][] createMatrix(int row_size,int column_size){
		int[][] matrix = new int[row_size][column_size];
		Scanner scan = new Scanner(System.in);
		for(int i = 0 ; i < row_size ; i++) {
			for(int j = 0 ; j < column_size ; j++)
				matrix[i][j] = scan.nextInt();
		}
		return matrix;
	}

	// A method to print a matrix
	public static void printMatrix(int[][] matrix) {
		for(int i = 0 ; i < matrix.length ; i++) {
			for(int j = 0 ; j < matrix[0].length ; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		// Matrix sizes can be arranged by changing the entries of the createMatrix method
		System.out.println("Enter Matrix A:");
		int[][] matrix1 = createMatrix(2,2); // Creating matrix1
		
		System.out.println("Enter Matrix B:");
		int[][] matrix2 = createMatrix(2,2); // Creating matrix 2
		
		// Creating the matrix multiplication class and add to the thread
		MatrixMultiplication matrix_multiplication = new MatrixMultiplication(matrix1,matrix2);
		Thread t1 = new Thread(matrix_multiplication);
		
		// Start the thread
		t1.start();
		try {
			t1.join(); // wait until the thread is finished
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Result:");
		printMatrix(matrix_multiplication.result); // print the resultant matrix
		
		
	}

}
