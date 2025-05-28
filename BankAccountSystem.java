// 6. Thread-safe Bank Account System
public class BankAccountSystem{
	
	private double balance; // The amount of money of the shared account

	// Synchronized depositing method from balance
	public synchronized void depositing(double amount) {
		balance += amount;
		System.out.println("Deposited: $" + amount);
	}
	
	// Synchronized withdrawing method from balance
	public synchronized void withdrawing(double amount) {
		if(balance < amount) // check if there is enough money to prevent overdrafts
			System.out.println("There is no $" + amount + " in this balance to withdraw.");
		else {
			balance -= amount; // withdrawn if there is no problem
			System.out.println("Withdrawn: $" + amount);
		}
	}
	
	// Returns the balance of the bank account
	public synchronized double getBalance() {
		return balance;
	}
	
	public static void main(String[] args) {
		
		// Create a bank account system
		BankAccountSystem bank_account = new BankAccountSystem();
		
		// Creating customers to update the same bank account balance
		CustomerThread customer1 = new CustomerThread(true,1000,bank_account);
		CustomerThread customer2 = new CustomerThread(false,500,bank_account);
		CustomerThread customer3 = new CustomerThread(false,700,bank_account);
		
		// Customer threads starts to update the balance
		customer1.start();
		customer2.start();
		customer3.start();
		
		// Wait until all threads are finished
		try {
			customer1.join();
			customer2.join();
			customer3.join();
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// At the end, print the final version of the balance of the bank account
		System.out.println("Final Balance: $" + bank_account.getBalance());
	}
}	

// Thread that represents customer activity
class CustomerThread extends Thread{
	
	private BankAccountSystem account; // Bank Account to update
	private boolean flag ; // True for depositing, False for withdrawn
	private double amount ; // Amount of money 
	
	@Override
	public void run() {
		if(flag) { // if flag is true, then it it depositing
			account.depositing(amount);
		} else { // if flag is false, it is withdrawing
			account.withdrawing(amount);
		}
	}
	
	// Constructor to create a customer
	public CustomerThread(boolean flag, double amount, BankAccountSystem account) {
		this.flag = flag;
		this.amount = amount;
		this.account = account;
	}

}