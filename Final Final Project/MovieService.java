import java.sql.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MovieService
{

	public boolean addMovie(AddMovieViewModel movie) throws Exception
	{		
		Connection con = null; //DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
		Statement stm = null; //con.createStatement();
		String query = "INSERT INTO movie_table(movie_name,director,imdb_rating,genre,release_date) VALUES ('"+movie.getMovieName()+"','"+movie.getDirector()+"','"+movie.getImdb()+"','"+movie.getGenre()+"','"+movie.getReleaseDate()+"');";
		System.out.println(query);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			stm = con.createStatement();
			stm.execute(query);
			System.out.println("Success");
			
		}
		catch(Exception ex){
			System.out.println("Exception : " + ex.getMessage());
		}
		finally
		{	
		    try
			{
				if(stm!=null)
				{
					stm.close();
				}
				if(con!=null)
				{
					con.close();
				}
			}
			catch(Exception ex)
			{
			    System.out.println("Exception : " + ex.getMessage());
		    }
		}
		return true;
	}
}