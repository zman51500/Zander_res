// gets a sorted arraylist with no duplicates

package movies;

import java.util.*;

public class ListFilmArchive extends ArrayList<Movie> implements FilmArchive
{
	//adds to the arraylist with no duplicates
	@Override
	public boolean add(Movie m)
	{
		boolean reallyAdded = false;
		for(Movie test: this)
		{
			if(test.equals(m))
			{
				return reallyAdded;
			}
		}
		reallyAdded = super.add(m);
		return reallyAdded;
	}
	
	// gets the sorted version of the arraylist
	public ArrayList<Movie> getSorted()
	{
		TreeSet<Movie> sort = new TreeSet<Movie>(this);
		return new ArrayList<Movie>(sort);
	}
	
	public static void main(String[] args) 
	{
		ListFilmArchive archive = new ListFilmArchive(); 
		for (Movie m: Movie.getTestMovies())
		{
			archive.add(m); 
		}
		for (Movie m: archive)
		{
			System.out.println(m); 
			System.out.println("**************"); 
		}
		for (Movie m: archive.getSorted())
		{
			System.out.println(m);
		}
	}

}
