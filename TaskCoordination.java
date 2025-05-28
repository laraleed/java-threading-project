
// 2. Wait and Notify Mechanism for Task Coordination
public class TaskCoordination {

	public static void main(String[] args) {
		
		// Create the task and the even and odd number threads
		Task task = new Task();
		OddNumbers odd_numbers = new OddNumbers(task);
		EvenNumbers even_numbers = new EvenNumbers(task);
		
		Thread thread1 = new Thread(odd_numbers);
		Thread thread2 = new Thread(even_numbers);
		
		// Start the threads
		thread1.start();
		thread2.start();
		
	}

}

class Task {
	boolean isNumberEven = false; // check if it is odd or even to alternate between threads
	
	// Method for the OddNumbers thread
	public synchronized void OddPrinter(int i) {
		if (isNumberEven) {
			try {
				wait(); // Wait until it's time for odd number
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread 1: " + i); // Print the odd number
		isNumberEven = true; // Set the next number even
		notify(); // Notify the EvenNumbers thread to proceed
	}
	// Method for the EvenNumbers thread
	public synchronized void EvenPrinter(int i) {
		if (!isNumberEven) {
			try {
				wait(); // Wait until it's time for even number
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread 2: " + i); // Print the even number
		isNumberEven = false; // Set the next number as odd
		notify(); // Notify the OddNumbers thread to proceed
	}
}

class OddNumbers implements Runnable{
	Task task;
	
	public OddNumbers(Task t) {
		task = t;
	}
	
	@Override
	public void run() {
		// Use task's odd printer to print at the right time
		for(int i=1; i<=20; i+=2) {
				task.OddPrinter(i);
		}
		
	}
	
}

class EvenNumbers implements Runnable{
	Task task;
	
	public EvenNumbers(Task t) {
		task = t;
	}
	
	@Override
	public void run() {
		// Use task's even printer to print at the right time
		for(int i=2; i<=20; i+=2) {
				task.EvenPrinter(i);
		}
		
	}
	
}

