package project2;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.FileWriter;

public class FileWrite {

	
	
	
	
	public static void saveToFile(Messages m) {
		
		try {
			
			File f=new File("/Users/theodorekalogridis/Desktop/"+"Receiver- "+m.getReceiver()+" Sent by-"+m.getSender()+".txt");
			FileWriter fw=new FileWriter(f,true);
			PrintWriter pw=new PrintWriter(fw);
			
			pw.write(m.toString()+"\n");
			pw.close();
		}catch(IOException e){
			System.out.println("Error:SaveToFile ");
			
		}
		
	}
}
	
	
	
	
	


