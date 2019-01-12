package bankaccounts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ibrahim
 */
public class BankAccounts {


    public static void main(String[] args) {
        
        String fileName = "ListofBankAccounts.txt";
        int lineCount = lineCount(fileName);
        int count;
        int blockLine =  1;
        int blockLineValue = 1;
        List<String> bankAccountArr = new ArrayList<String>();
        List<String> bankAccountArrCount = new ArrayList<String>();
        
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i;
            for (i=0;(line = bufferedReader.readLine()) != null;i++) {
                
                if (i==blockLine){
                    blockLineValue = Integer.parseInt(line);                
                }
                
                if (i>blockLine && line.length()>0) {
                    bankAccountArr.add(line);
                }
                
                if (line.length() == 0 || lineCount == i+1) {
                    blockLine = blockLine+blockLineValue + 2 ;
                    Collections.sort(bankAccountArr);
                    count= bankAccountArr.size();
                    for (int j=0;j<count;j++) {
                        bankAccountArrCount.add(bankAccountArr.get(j)+" "+Collections.frequency(bankAccountArr, bankAccountArr.get(j)));
                    }
                    
                    List<String> bankAccountArrLast = bankAccountArrCount.stream().distinct().collect(Collectors.toList());
                    count= bankAccountArrLast.size();
                    for (int j=0;j<count;j++) {
                        System.out.println(bankAccountArrLast.get(j));
                    }
                    
                    if (lineCount != i+1) {
                        System.out.println("");
                        bankAccountArr.clear();
                        bankAccountArrCount.clear();
                    }
                }
            }
            fileReader.close();
	} 
        catch (IOException e) {
            e.printStackTrace();
	}
    }
    
    public static int lineCount(String fileName){
        int lineCount=0;
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                lineCount++;
            }
            fileReader.close();
	} 
        catch (IOException e) {
            e.printStackTrace();
	}
    return lineCount; 
    } 
}
