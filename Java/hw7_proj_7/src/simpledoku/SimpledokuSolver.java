package simpledoku;

import java.util.*;


public class SimpledokuSolver 
{
	private int							nCellsPerSide;
	private SimpledokuGrid				solution;
	
	
	public SimpledokuSolver(int nCellsPerSide)
	{
		this.nCellsPerSide = nCellsPerSide;
	}
	
	
	public void solve()
	{
		SimpledokuGrid emptyGrid = new SimpledokuGrid(nCellsPerSide);
		solveRecurse(emptyGrid);
	}
		
	
	private void solveRecurse(SimpledokuGrid grid)
	{		
		System.out.println(grid);
		
		// The example you have seen in class finds all solutions. Here we just want
		// one solution. The 2 lines below force return from the recursion as soon as a
		// solution is found.
		if (solution != null)
			return;
		
		// Evaluate the current grid.
		Evaluation eval = grid.evaluate();
		
		if (eval == Evaluation.ABANDON)
		{
			// To abandon the current grid, just return.
			return;
		}
		else if (eval == Evaluation.ACCEPT)
		{
			// Set solution to be the current grid.
			solution = grid;
		}
		else
		{
			// Generate all possible next grids, and call solveRecurse on them.
			for (SimpledokuGrid nextGrid: grid.generateNextGrids())

				solveRecurse(nextGrid);
		}
	}

	
	public SimpledokuGrid getSolution()
	{
		return solution;
	}
	
	
	public static void main(String[] args)
	{
		SimpledokuSolver solver = new SimpledokuSolver(6);
		solver.solve();
		System.out.println("SOLUTION:\n" + solver.getSolution());
	}
}
