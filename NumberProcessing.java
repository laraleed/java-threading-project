
// 1. Multi-threaded Number Processing
public class NumberProcessing {
	
	public static void main(String[] args) {
        // Create threads
        EvenSumThread evenSumThread = new EvenSumThread();
        OddSumThread oddSumThread = new OddSumThread();
        
        // Start both threads
        evenSumThread.start();
        oddSumThread.start();
        
        try {
            // Wait for both threads to complete
            evenSumThread.join();
            oddSumThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Synchronize the threads to avoid race conditions while printing the results 
        synchronized (NumberProcessing.class) {
            System.out.println("Even Sum: " + evenSumThread.getEvenSum());
            System.out.println("Odd Sum: " + oddSumThread.getOddSum());
        }
	}
}

class EvenSumThread extends Thread{
	private int even_sum = 0;

    @Override
    public void run() {
        // Sum of even numbers from 1 to 1000
        for (int i = 2; i <= 1000; i += 2) {
            even_sum += i;
        }
    }
     
    // Returns the even sum
    public int getEvenSum() {
        return even_sum;
    }
}

class OddSumThread extends Thread{
	private int odd_sum = 0;

    @Override
    public void run() {
        // Sum of odd numbers from 1 to 1000
        for (int i = 1; i <= 1000; i += 2) {
            odd_sum += i;
        }
    }

    // Returns the odd sum
    public int getOddSum() {
        return odd_sum;
    }
}