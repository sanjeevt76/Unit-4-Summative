import java.text.DecimalFormat;

class Customer
{

}


public class Account
{
	// class data
	private double balance;
	private long accountNumber;
	private final long MAXACCT = 999999999999L;
	private Customer owner;

	// Default constructor
	public Account ()
	{
		//initializes the balance
		this.balance = 0.0;

		// creates an account number
		generateAcctNumber ();

		// intializes Customer object 
		this.owner = new Customer ();
	}

	// Overloaded Constructor 
	public Account (Customer theOwner)
	{
		//initializes balance
		this.balance = 0.0;

		// generates an account number
		generateAcctNumber ();

		// initializes customer object theOwner
		theOwner = new Customer ();

	}


	public void deposit (double amount)
	{
		// deposits money
		if(amount > 0)
			this.balance = this.balance + amount;

	}


	public boolean withdraw (double amount)
	{
		// Checks if the amount can be withdrawn
		// and returns true if it is possible
		// updates balance
		if(amount <= this.balance) {
			this.balance = this.balance - amount;
			return true;
		}
		else 
			return false;
	}


	public double getBalance ()
	{
		// returns the current balance
		return this.balance;
	}


	public void generateAcctNumber ()
	{
		// optional method
		// used to reset or regenerate account number
		long min = 100000000000L;
		accountNumber =(long)Math.floor(Math.random() * (MAXACCT-min+1))+min;
	}


	public long readAccountNumber()
	{
		// returns account number
		return this.accountNumber;
	}
	
	public String toString (){
		
		DecimalFormat df = new DecimalFormat("0.00");
		String balanceFormatted = df.format(this.balance);
		return(this.accountNumber + " = $" + balanceFormatted);
	}
	
	public void setBalance(double bal)
	{
		if(bal > 0)
			this.balance = this.balance + bal;	
	}

	public static void main (String args[])
	{
		// Self Testing Main
		Account savingsAccount = new Account();
		System.out.println(savingsAccount);
		
		savingsAccount.deposit(1000);
		System.out.println(savingsAccount);
		
		if(savingsAccount.withdraw(10000))
		System.out.println(savingsAccount);
		else 
			System.out.println("Cannot widthdraw more then the balance");
	}

	// other useful methods could include a method to
	// change the customer object and
	// to retrieve the customer method
	// toString method
	

}
