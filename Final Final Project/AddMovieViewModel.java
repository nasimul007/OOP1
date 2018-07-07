import java.sql.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AddMovieViewModel
{
	String movieName,director,genre,releaseDate,imdb;
	public void setMovieName(String movieName)
	{
		this.movieName = movieName;
	}
	public String getMovieName()
	{
		return movieName;
	}
	
	public void setDirector(String director)
	{
		this.director = director;
	}
	public String getDirector()
	{
		return director;
	}
	
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	public String getGenre()
	{
		return genre;
	}
	
	public void setReleaseDate(String releaseDate)
	{
		this.releaseDate = releaseDate;
	}
	public String getReleaseDate()
	{
		return releaseDate;
	}
	
	public void setImdb(String imdb)
	{
		this.imdb=imdb;
	}
	public String getImdb()
	{
		return imdb;
	}
}