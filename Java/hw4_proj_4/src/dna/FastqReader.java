package dna;

import java.io.*;

//
// Reads lines from a BufferedReader and builds a FastqRecord.
//

public class FastqReader 
{
	private BufferedReader theBufferedReader;
	
	//
	// Makes a FastqReader using a buffered reader
	//
	public FastqReader(BufferedReader br)
	{
		theBufferedReader = br;
	}
	
	//
	//Reads and returns the next Fastq Record in the file
	//
	public FastqRecord readRecord() throws IOException, RecordFormatException
	{
		// checks for End of File
		String defline = theBufferedReader.readLine();			
		if(defline == null)
		{					
			System.out.println("EOF");
			return null;			
		}
			
		// reads and writes file
		else
		{
			String sequence = theBufferedReader.readLine();
			theBufferedReader.readLine();
			String quality = theBufferedReader.readLine();
			return new FastqRecord(defline, sequence, quality);
		}
	}
}
