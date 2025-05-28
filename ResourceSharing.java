// 4. Thread-safe Resource Sharing
import java.util.concurrent.locks.Lock; // to use Lock interface
import java.util.concurrent.locks.ReentrantLock; // to use Reentrant Lock

public class ResourceSharing implements Runnable {
	private int count; // The counter value
	private Lock lock = new ReentrantLock(true); // initializing a fair lock 
	
	@Override
	public void run() {
		// increment the counter 1000 times
		for(int i=0;i<1000;i++) {
			lock.lock(); // lock the critical section to prevent race condition
			try {
				count++;
			} finally {
				lock.unlock(); // unlock at the end
			}
		}
	}
	
	public int getCount() {
		lock.lock(); // lock the critical section to prevent an inconsistent result
		try {
			return count;
		} finally {
			lock.unlock(); // unlock at the end
		}
	}

	public static void main(String[] args) {
		
		ResourceSharing rs = new ResourceSharing();
		Thread[] threads = new Thread[3];
		for(int i=0;i<3;i++) {
			threads[i] = new Thread(rs); // create threads shares the same resource
			threads[i].start();
		}
		try {
			for(int j=0;j<3;j++) { 
				threads[j].join(); // wait for the thread to finish
				System.out.println("Thread "+ (j+1) +" incremented the counter."); 
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		// after all the threads finished, print the final counter value
		System.out.println("Final Counter Value: " + rs.getCount());
		
	}

}
