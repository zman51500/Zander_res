package dna;

import java.io.*;


//
// Writes a fasta record to a print writer.
//


public class FastaWriter 
{
	private PrintWriter thePrintWriter;
	
	//
	// takes a printwritter to create a FastaWritter
	//
	public FastaWriter(PrintWriter pw)
	{
		thePrintWriter = pw;
	}

	
	//
	// writes a Fasta record to a file
	//
	public void writeRecord(FastaRecord rec) throws IOException
	{
		thePrintWriter.println(rec.getDefline());
		thePrintWriter.println(rec.getSequence());
	}
}
