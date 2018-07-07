import java.sql.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HallService
{

	public ArrayList<HallModel> getHallList() throws Exception
	{		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
		Statement stm = con.createStatement();
		String query = "select * from hall_table";
		ArrayList<HallModel> hallList = new ArrayList<HallModel>();
		System.out.println(query);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			//stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			
			
			while (rs.next())
			{
				HallModel hall = new HallModel();
				hall.setId(rs.getString("id"));
				hall.setHallName(rs.getString("hall_name"));
				
				hallList.add(hall);
			}
			return hallList;
			
		}
		catch(Exception ex){
			System.out.println("Exception : " + ex.getMessage());
		}
		finally
		{			
			stm.close();
			con.close();
		}
		return hallList;
	}
	
	
}