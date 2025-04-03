package dna;

//
//Models a Fasta Record object
//
public class FastaRecord implements DNARecord 
{	
	private String defline;
	private String sequence;
	//
	// Creates a FastaRecord if inputs are in teh correct format
	//
	public FastaRecord(String defline, String sequence) throws RecordFormatException
	{
		// checks the first char to see if its legal
		char c = defline.charAt(0);
		if(c != '>')
		{
			throw new RecordFormatException("Bad 1st char in defline in fasta record: saw " + c + ", expected >");
		}
		else
		{
			this.defline = defline;
			this.sequence = sequence;
		}
	}
	
	
	//
	//	Creates a Fasta Record using a known Fastq Record
	//
	public FastaRecord(FastqRecord fastqRec)
	{
		String def = fastqRec.getDefline();
		this.defline = ">" + def.substring(1);
		this.sequence = fastqRec.getSequence();
	}
	
	

	// 
	// returns the defline
	//
	public String getDefline()
	{
		return defline;
	}
	
	//
	//returns the DNA sequence 
	//
	public String getSequence()
	{
		return sequence;
	}

	
	
	//
	// checks two Fasta objects for equality
	// both defline and sequence
	//
	public boolean equals(Object o)
	{
		FastaRecord fa = (FastaRecord)o;
		if(this.defline.contentEquals(fa.defline))
		{
			if(this.sequence.equals(fa.sequence))
			{
				return true;
			}
		}
		return false;
	}

	//
	// returns the hascode of a Fasta Record
	//
	
	public int hashCode()
	{
		return this.defline.hashCode() + this.sequence.hashCode();
	}
}
