import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



/**
 * @author Sanjeev
 * Date : May 2017
 * Description:
 * Method List:
 * TransacitonList()-Constructor which initializes private variables
 * private TransactionRecord [] getList ()
 * private int getSize()
 * public boolean insert (TransactionRecord record)
 * public boolean delete (TransactionRecord record)
 * public boolean change(TransactionRecord record)
 * public void saveFile (String filename) throws IOException
 * public static void main(String[] args) throws IOException 
 */
 

public class TransactionList {
	// declares a variable
	private TransactionRecord list[];
	private int maxSize;
	private int size;
	private BufferedWriter bufferWriter;
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferReader;

	//private File file;

	//constructor
	public TransactionList() {
		this.maxSize = 20;
		this.size = 0;
		this.list = new TransactionRecord[maxSize];
		//this.file = new File("transactions.txt");
		this.bufferWriter = null;
		this.fileWriter = null;
		this.fileReader = null;
		this.bufferReader = null;
	}

	private TransactionRecord [] getList (){
		return this.list;
	}

	// method to get size
	private int getSize(){
		return this.size;
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

	public boolean delete(TransactionRecord record){
		for (int where = 0; where < size; where++){
			if (list[where].getTransactionType().equalsIgnoreCase(record.getTransactionType())){
				list[where] = list[size-1]; // puts last one in my spot
				size--;   // decrease size of list
				return true;
			}
		}
		return false;
	}


	public boolean change (TransactionRecord record, int index){
		if (index < maxSize){
			list[index] = record;
			return true;
		}
		return false;
	}

	public int binarySearch (String searchKey){
		int low = 0;
		int high = size -1;
		int middle;

		while (low <= high){
			middle = (high + low)/2;
			if (searchKey.equalsIgnoreCase(list[middle].getTransactionType())){
				return middle;  // element was found
			}
			else if(searchKey.compareToIgnoreCase(list[middle].getTransactionType())<0){
				high = middle -1;
			}
			else {
				low = middle + 1;
			}
		}
		return -1;  // element was not found
	}


	public String readFile(String fileName) throws IOException {


		fileReader = new FileReader(fileName);
		bufferReader = new BufferedReader(fileReader);

		String currentLine = "";
		String output = "";

		while ((currentLine = bufferReader.readLine()) != null) {
			//System.out.println(sCurrentLine);
			output += currentLine + "\n";
		}

		bufferReader.close();
		fileReader.close();

		return output;

	}

	public void saveToFile() throws IOException{
		TransactionRecord record;
		String output = "";

		for (int i=0; i<this.size; i++) {
			record = list[i];
			char accountType = record.getAccountType();
			String transactionType = record.getTransactionType();
			double amount = record.getAmount();

			if (transactionType == "Deposit")
				output += accountType + "\t" + transactionType + "\t\t" + amount + "\n";
			else
				output += accountType + "\t" + transactionType + "\t" + amount + "\n";


			File file = new File("transactions.txt");


			if (!file.exists()) {
				file.createNewFile();
			}


			this.fileWriter = new FileWriter(file.getAbsoluteFile(), true);
			this.bufferWriter = new BufferedWriter(this.fileWriter);

			this.bufferWriter.write(output);

			this.bufferWriter.close();
			this.fileWriter.close();
		}
	}



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		TransactionRecord info = null;
		TransactionList transactionList = new TransactionList();

		String transType = "Deposit";
		char accType = 's';
		double amount = 1000;
		
		for (int i=1; i<=20; i++) {
			if (i % 3 == 0) {
				transType = "Withdraw";
			}
			else {
				transType = "Deposit";
			}
			
			if (i % 5 == 0) {
				accType = 'c';
			}
			else {
				accType = 's';
			}
			
			info = new TransactionRecord();
			info.setTransactionType(transType);
			info.setAccountType(accType);
			info.setAmount(amount);
			transactionList.insert(info);
			transactionList.saveToFile();

			amount += 500;
		}
		
//		

		String transactions = transactionList.readFile("transactions.txt");

		System.out.println(transactions);
	}

}
