// Models a movie object

package movies;

public class Movie implements Comparable<Movie>
{
	private String title;
	private int year;
	
	// creates a movie object with a title and year of release
	public Movie(String t, int y) 
	{
		title = t;
		year = y;
	}

	// returns the title of the movie
	public String getTitle()
	{
		return title;
	}
	
	// returns the year of release of the movie
	public int getYear()
	{
		return year;
	}
	
	// converts a movie object to a readable string
	@Override
	public String toString()
	{
		return "Movie " + title + " " + "(" + year + ")";
	}
	
	// checks if two movies are the same
	@Override
	public boolean equals(Object o)
	{
		Movie m = (Movie)o;
		return this.compareTo(m) == 0;
	}
	
	// compares title then year in order to sort
	@Override
	public int compareTo(Movie m) 
	{
		int titlecmp = this.title.compareTo(m.title);
		if (titlecmp != 0)
		{
			return titlecmp;
		}
		else
		{
			return (int)Math.signum(this.year - m.year);
		}
	}
	
	//contains 10 movies 
	//The 0th and 1st same title different years
	//The 2nd and 3rd elements different titles same year
	//The 4th and 5th same title same year
	//The rest random
	public static Movie[] getTestMovies()
	{
		Movie[] movies = new Movie[10];
		movies[0] = new Movie("IT", 1990);	
		movies[1] = new Movie("IT", 1999);
		movies[2] = new Movie("Hunger Games", 2004);
		movies[3] = new Movie("Run", 2004);
		movies[4] = new Movie("BAM", 2006);
		movies[5] = new Movie("BAM", 2006);
		movies[6] = new Movie("Click", 2002);
		movies[7] = new Movie("Elf", 2009);
		movies[8] = new Movie("Speed", 2010);
		movies[9] = new Movie("This", 2011);
		return movies;
	}
	
	// gets the hashcode of the movie
	@Override
	public int hashCode()
	{
		return title.hashCode() + year;
	}
}
