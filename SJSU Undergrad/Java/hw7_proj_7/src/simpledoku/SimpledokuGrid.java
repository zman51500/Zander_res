package simpledoku;

import java.util.*;


public class SimpledokuGrid 
{
	private int				nCellsPerSide;
	private int[][]			values;
	
	
	public SimpledokuGrid(int nCellsPerSide)
	{
		this.nCellsPerSide = nCellsPerSide;
		values = new int[nCellsPerSide][nCellsPerSide];
	}
	
	
	// Copy nCellsPerSide and values.
	public SimpledokuGrid(SimpledokuGrid src)
	{
		nCellsPerSide = src.nCellsPerSide;
		values = new int[nCellsPerSide][nCellsPerSide];
		for (int i=0; i<nCellsPerSide; i++)
			for (int j=0; j<nCellsPerSide; j++)
				values[i][j] = src.values[i][j];
	}	
	
	
	//
	// Returns true if the input list contains a repeated value that isn't zero.
	// Call this from isLegal().
	//
	private boolean containsNonZeroRepeats(ArrayList<Integer> ints)
	{
		Set<Integer> set = new HashSet<Integer>();
		for (int i: ints)
			if (i != 0  &&  set.contains(i))
				return true;
			else
				set.add(i);
		return false;
	}
	
	
	public boolean isLegal()
	{
		// Check all rows. For each row, put the corresponding numbers from
		// the values[][] array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats().
		for (int j=0; j<nCellsPerSide; j++)
		{
			ArrayList<Integer> valsInRow = new ArrayList<Integer>();
			for (int i=0; i<nCellsPerSide; i++)
				valsInRow.add(values[i][j]);
			if (containsNonZeroRepeats(valsInRow))
				return false;
		}

		// Check all columns. For each column, put the corresponding numbers from
		// the values[][] array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats().
		for (int i=0; i<nCellsPerSide; i++)
		{
			ArrayList<Integer> valsInCol = new ArrayList<Integer>();
			for (int j=0; j<nCellsPerSide; j++)
				valsInCol.add(values[i][j]);
			if (containsNonZeroRepeats(valsInCol))
				return false;
		}
		
		// Check top-left to bottom-right diagonal. 
		ArrayList<Integer> valsInDiag = new ArrayList<Integer>();
		for (int i=0; i<nCellsPerSide; i++)
			valsInDiag.add(values[i][i]);
		if (containsNonZeroRepeats(valsInDiag))
			return false;
		
		
		// Check top-right to bottom-left diagonal. 
		ArrayList<Integer> valsInOtherDiag = new ArrayList<Integer>();
		for (int i=0; i<nCellsPerSide; i++)
			valsInOtherDiag.add(values[i][nCellsPerSide-i-1]);
		if (containsNonZeroRepeats(valsInOtherDiag))
			return false;
		
		return true;
	}
	
	
	// Returns true if all members of the values[][] array are non-zero.
	public boolean isFull()
	{
		for (int i=0; i<nCellsPerSide; i++)
			for (int j=0; j<nCellsPerSide; j++)
				if (values[i][j] == 0)
					return false;
		return true;
	}	
	
	
	public Evaluation evaluate()
	{
		if (!isLegal())
			return Evaluation.ABANDON;
		else if (isFull())
			return Evaluation.ACCEPT;
		else
			return Evaluation.CONTINUE;
	}
	
	
	// Returns a size=2 array of ints that index the next empty cell in the values[][] array.
	// The 2 ints in the returned array are the first and second indices into values.
	// Returns null if this grid is full.
	private int[] getIndicesOfNextEmptyCell()
	{
		int[] xy = new int[2];
		
		for (xy[0]=0; xy[0]<nCellsPerSide; xy[0]++)
			for (xy[1]=0; xy[1]<nCellsPerSide; xy[1]++)
				if (values[xy[0]][xy[1]] == 0)
					return xy;
		
		return null;
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list containing nCellsPerSide grids that look like the 
	// current grid, except the empty member contains 1, 2, 3 .... nCellsPerSide. 
	// 
	//
	// Example: if this grid = 12300
	//                         00000
	//                         00000
	//                         00000
	//                         00000
	//
	// Then the returned array list contains:
	//
	//      12310        12320        12330        12340        12350
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//
	ArrayList<SimpledokuGrid> generateNextGrids()
	{		
		int[] indicesOfNext = getIndicesOfNextEmptyCell();
		ArrayList<SimpledokuGrid> nextGrids = new ArrayList<SimpledokuGrid>();
		for (int x=1; x<=nCellsPerSide; x++)
		{
			SimpledokuGrid newGrid = new SimpledokuGrid(this);
			newGrid.values[indicesOfNext[0]][indicesOfNext[1]] = x;
			nextGrids.add(newGrid);
		}
		return nextGrids;
	}
	
	
	public String toString()
	{
		String s = "";
		for (int j=0; j<nCellsPerSide; j++)
		{
			for (int i=0; i<nCellsPerSide; i++)
			{
				if (values[j][i] == 0)
					s += ".";
				else
					s += ("" + values[j][i]);
			}
			s += "\n";
		}
		return s;
	}
}
