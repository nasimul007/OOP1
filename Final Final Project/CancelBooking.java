import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
import java.util.Date;
import java.text.*;

public class CancelBooking extends JFrame implements ActionListener
{
	private JTable canceltable;
	private JPanel panel;
	private JScrollPane cancelScroll;
	DefaultTableModel tableModel;
	private JButton buttonDelete, buttonBack;
	int i;
	private JLabel labelPleaseSelectToCancelBooking;
	int UserId;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age;
	
	public CancelBooking(int UserId)
	{
		super("Booking Cancellation Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);

		this.UserId=UserId;
		panel = new JPanel();
        panel.setLayout(null);
		
		String []col = {"Serial No","Book ID","Movie name","Date","Show Time"};
		
		labelPleaseSelectToCancelBooking = new JLabel("Please, select one from below to cancel booking.");
		labelPleaseSelectToCancelBooking.setBounds(50,80,400,20);
		panel.add(labelPleaseSelectToCancelBooking);
		
		canceltable = new JTable();
		tableModel = new DefaultTableModel(0,5);
		tableModel.setColumnIdentifiers(col);
		canceltable.setModel(tableModel);
		cancelScroll = new JScrollPane(canceltable);
		cancelScroll.setBounds(50,100,400,150);
		panel.add(cancelScroll);
		
		populateDatatable();
		
		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(250,280,80,50);
		buttonDelete.addActionListener(this);
		panel.add(buttonDelete);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(150,280,80,50);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);

		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(buttonDelete.getText()))
		{
			int row = canceltable.getSelectedRow();
			if (row < 0) return;
			String DeleteID = canceltable.getModel().getValueAt(row, 1).toString();
			
			Connection con = null;
			Statement stm = null;
			String query = "delete FROM book_seat where book_id = '"+DeleteID+"';";
			String query2 = "delete FROM book_table where id = '"+DeleteID+"';";
			System.out.println(query);
			//stm.executeQuery(query);
			try
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
				stm = con.createStatement();
				//stm.executeQuery("SELECT id,username,email,user_type,name,age FROM user_table");
				stm.execute(query);
				stm.execute(query2);
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " + ex.getMessage());
			}
			
			tableModel.setRowCount(0);
			populateDatatable();
		}
		
		else if(elementText.equals(buttonBack.getText()))
		{
			String query = "SELECT id, username, email, name, user_type, mobile, address, age, gender FROM user_table where id='"+UserId+"'";
			Connection con=null;//for connection
			Statement st = null;//for query execution
			ResultSet rs = null;//to get row by row result from DB
			try
			{
				Class.forName("com.mysql.jdbc.Driver");//load driver
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
				st = con.createStatement();//create statement
				rs = st.executeQuery(query);//getting result
				//System.out.println(""+id);
				
				while(rs.next())
				{
					System.out.println("test2");
					ID = rs.getInt("id");
				    Email = rs.getString("email");
					User_Type = rs.getString("user_type");
					User_Name = rs.getString("username");
					Name = rs.getString("name");
					Mobile_No = rs.getString("mobile");
					Address = rs.getString("address");
					Age = rs.getInt("age");
					Gender = rs.getString("gender");
					//System.out.println(movie+director+imdb+genre);
									
				}
				
			}
			catch(Exception e){}
			
			UserHome ah = new UserHome(ID,User_Name,Email,Password,User_Type,Name,Mobile_No,Address,Age,Gender);
			ah.setVisible(true);
			this.setVisible(false);
		}
	}

	
	
	public void populateDatatable()
	{
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	  

		System.out.println("Current Date: " + ft.format(dNow));
		
		String currentDate = ft.format(dNow);
		
		Connection con = null;
		Statement stm = null;
		String query = "SELECT bt.id,mt.movie_name,bt.date,stt.show_time FROM book_table bt inner join movie_table mt on bt.movie_id=mt.id inner join show_time_table stt on stt.id=bt.show_time_id WHERE bt.user_id="+UserId+" and bt.date>='"+ft.format(dNow)+"'";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			stm = con.createStatement();
			//stm.executeQuery("SELECT id,username,email,user_type,name,age FROM user_table");
			stm.executeQuery(query);
            ResultSet rs = stm.getResultSet();
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			//stm = con.createStatement();
			//ResultSet rs = stm.executeQuery(query);
			
			while (rs.next())
			{
				String ID = rs.getString("id");
				String Movie_Name = rs.getString("movie_name");
				String Movie_Date = rs.getString("date");
				String Show_Time = rs.getString("show_time");
				
				i++;
				String k = Integer.toString(i);
				
				tableModel.addRow (new Object[] {k,ID,Movie_Name,Movie_Date,Show_Time});
				
			}
			
		}
			
		catch(Exception ex)
		{
			System.out.println("Exception : " + ex.getMessage());
		}
		
	}
	
	/*
	public static void main(String []a)
	{
		CancelBooking cb = new CancelBooking();
		cb.setVisible(true);
	}*/
}