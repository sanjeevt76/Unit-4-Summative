import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * @author Sanjeev
 * Date : May 2017
 * Description:
 * Method List:
 */

public class TransactionList {
	// declares a variable
	private TransactionRecord list[];
	private int maxSize;
	private int size;
	private File file;

	//constructor
	public TransactionList() throws FileNotFoundException {
		this.maxSize = 100;
		this.size = 0;
		this.list = new TransactionRecord[maxSize];
		this.file = new File("transactions.txt");
	}
	// method that inserts a new transaction to the list
	public boolean insert(TransactionRecord record) {
		if (size < maxSize) {
			size++;
			list[size - 1] = record;
			return true;
		}
		return false;
	}
	public void saveListToFile() throws IOException{
		TransactionRecord record;
		String output = "";
		
		for (int i=0; i<size; i++) {
			record = list[i];
			char accountType = record.getAccountType();
			String transactionType = record.getTransactionType();
			double amount = record.getAmount();
			
			output = accountType + "\t" + transactionType + "\t" + amount;
			
			//this.writer.write(output);
			FileUtils.writeStringToFile(file,output);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		TransactionRecord info = new TransactionRecord();
		TransactionList transactionList = new TransactionList();
		
		info.setTransactionType("Withdraw");
		info.setAccountType('s');
		info.setAmount(574.50);
		info.setStartAmount(950.00);
		info.setEndAmount(375.50);
		
		transactionList.insert(info);
		transactionList.saveListToFile();
	}

}
