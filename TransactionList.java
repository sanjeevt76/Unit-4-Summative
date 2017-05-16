import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//import java.io.*;

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
    private BufferedWriter bufferWriter;
    private FileWriter fileWriter;
    //private File file;

    //constructor
    public TransactionList() {
        this.maxSize = 100;
        this.size = 0;
        this.list = new TransactionRecord[maxSize];
        //this.file = new File("transactions.txt");
        this.bufferWriter = null;
        this.fileWriter = null;
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

    public void saveListToFile() throws IOException{
        TransactionRecord record;
        String output = "";

        for (int i=0; i<this.size; i++) {
            record = list[i];
            char accountType = record.getAccountType();
            String transactionType = record.getTransactionType();
            double amount = record.getAmount();

            output += accountType + "\t" + transactionType + "\t" + amount + "\n";

            //this.writer.write(output);
            File file = file = new File("transactions.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            this.fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            this.bufferWriter = new BufferedWriter(this.fileWriter);

            this.bufferWriter.write(output);

            this.bufferWriter.close();
            this.fileWriter.close();
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


        info.setTransactionType("Deposit");
        info.setAccountType('s');
        info.setAmount(100.00);
        info.setStartAmount(500.00);

        transactionList.insert(info);
        transactionList.delete(info);
        transactionList.saveListToFile();
    }

}
