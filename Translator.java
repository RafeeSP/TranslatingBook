package com.project.translation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Translator {

	public static void main(String[] args) throws IOException {
		System.out.println("Translation Started");
		BufferedReader r1=null;
		BufferedReader r2=null;
		BufferedReader r3=null;
		BufferedWriter bookWriter = null;
		BufferedWriter fileWriter = null;
		int count=0;
		String bookData="";
		String modifiedFile;
		String csvWords[]=new String[3];
		int i=0,j=0;
		try {
			r1=new BufferedReader(new FileReader("src/findwords.txt"));
			r2=new BufferedReader(new FileReader("src/french_dictionary.csv"));
			r3=new BufferedReader(new FileReader("src/bookfile.txt"));
			String bookline = r3.readLine();
			while (bookline != null) 
			{
			bookData=bookData + bookline+"\n" ;
			bookline = r3.readLine();
			}
			String frenchWords =r2.readLine();
			String currentLine = r1.readLine();
			
			while (currentLine != null) 
			{
				while(frenchWords!=null) {
					for(String word:frenchWords.split(",")) {
						csvWords[i++]=word;
					}
					if(csvWords[0].equals(currentLine.trim()))
					{
						String[] book=bookData.split("[(' ',.~)]");
							for(String dd:book) {
								if(dd.equals(currentLine.trim()))
									count++;
						}
						String nd=csvWords[0]+","+csvWords[1]+","+count+"\n";
						fileWriter = new BufferedWriter(new FileWriter("src/frequency.csv",true));
						fileWriter.write(nd);
						count=0;
						if(csvWords[2]!=null) {
							modifiedFile = bookData.replaceAll(csvWords[0],csvWords[1]+csvWords[2]);
							bookData=modifiedFile;
						}
						else {
							 modifiedFile = bookData.replaceAll(" "+csvWords[0]+" "," "+csvWords[1]+" ");
							 modifiedFile = modifiedFile.replaceAll(" "+csvWords[0]+"."," "+csvWords[1]+".");
							 modifiedFile = modifiedFile.replaceAll(""+csvWords[0]+".",""+csvWords[1]+".");
							 modifiedFile = modifiedFile.replaceAll("."+csvWords[0]+" ","."+csvWords[1]+" ");
							 modifiedFile = modifiedFile.replaceAll(","+csvWords[0]+" ",","+csvWords[1]+" ");
							 modifiedFile = modifiedFile.replaceAll(" "+csvWords[0]+","," "+csvWords[1]+",");
							 modifiedFile = modifiedFile.replaceAll(" "+csvWords[0]+"\\)"," "+csvWords[1]+"\\)");
							 modifiedFile = modifiedFile.replaceAll(" "+csvWords[0]+"\\]"," "+csvWords[1]+"\\]");
							 modifiedFile = modifiedFile.replaceAll("\\("+csvWords[0]+" ","\\("+csvWords[1]+" ");
							 bookData=modifiedFile;
						}
						
						bookWriter = new BufferedWriter(new FileWriter("src/t8.shakespeare.txt"));
						bookWriter.write(modifiedFile);
						
						bookWriter.close();
						fileWriter.close();
					}
						i=0;
						csvWords[2]=null;
						frenchWords =r2.readLine();
						break;			
					}
				
				currentLine = r1.readLine();
			}		
		}
			
			catch (IOException e) 
			{
			} 
		System.out.println("Translation Completed");
	
	}
	

}
