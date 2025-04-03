package dna;
//
// records the exception message so the user can retrieve it
//

public class RecordFormatException extends Exception
{ 
	// creates the message 
	public RecordFormatException(String message) 
	{
		super(message);
	}
}