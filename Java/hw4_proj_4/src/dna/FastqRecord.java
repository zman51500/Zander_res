package dna;

//
// FastqRecord contains the defline, sequence, and quality string
// from a record in a fastq file.
//

public class FastqRecord implements DNARecord 
{
	private String defline;
	private String sequence;
	private String quality;
	
	//
	// Creates a Fastq Record if the conditions are met
	//
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{	
		// checks to see if defline starts with a @
		char c = defline.charAt(0);
		if(c != '@')
		{
			throw new RecordFormatException("Bad 1st char in defline in fastq record: saw " + c + ", expected @");
		}
		else
		{
			this.defline = defline;
			this.sequence = sequence;
			this.quality = quality;
		}
	}
	
	

	//
	// returns the defline
	//
	public String getDefline()
	{
		return defline;
	}
	
	// returns the sequence
	public String getSequence()
	{
		return sequence;
	}

	//
	// Checks to see if two Fastq Records have equal deflines and sequences and qualities
	//
	@Override
	public boolean equals(Object o)
	{
		FastqRecord fq = (FastqRecord)o;
		if(this.defline.equals(fq.defline))
		{
			if(this.sequence.equals(fq.sequence))
					{
						if(this.quality.equals(fq.quality))
						{
							return true;
						}
					}
		}
		return false;
	}
	
	//
	// Checks the quality
	// if there is a $ or # the quality is low
	//
	public boolean qualityIsLow()
	{
		if(this.quality.contains("$") && this.quality.contains("#"))
		{
			return true;
		}
		return false;
	}
	
	
	//
	// returns the hash code of the Fastq Record
	//
	public int hashCode()
	{
		return this.defline.hashCode() + this.sequence.hashCode() + this.quality.hashCode();
	}
}
