// gets a sorted hashset archive

package movies;

import java.util.*;

public class HashFilmArchive extends HashSet<Movie> implements FilmArchive
{
	// gets the hashset sorted into an arraylist
	public ArrayList<Movie> getSorted()
		{
			TreeSet<Movie> sort = new TreeSet<Movie>(this);
			return new ArrayList<Movie>(sort);
		}
	
	public static void main(String[] args) 
	{
		HashFilmArchive archive = new HashFilmArchive(); 
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

