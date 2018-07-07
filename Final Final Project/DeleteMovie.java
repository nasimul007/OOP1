import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class DeleteMovie extends JFrame implements ActionListener
{
	private JPanel panel;
	private JTable movieTable;
	private JScrollPane movieScrollPane;
	//private JComboBox comboMovie;
	private JLabel movieName;
	private JButton delete, back,logOut;
	DefaultTableModel tableModel;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age,i;
	
	public DeleteMovie(String User_Name)// throws Exception
	{
		super("Delete a Movie");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
        panel.setLayout(null);
		
		String []col = {"Serial No","ID","Movie Name"};
		
		movieTable = new JTable();
		tableModel = new DefaultTableModel(0,5);
		tableModel.setColumnIdentifiers(col);
		movieTable.setModel(tableModel);
		movieScrollPane = new JScrollPane(movieTable);
		movieScrollPane.setBounds(50,100,400,150);
		panel.add(movieScrollPane);
		
		this.User_Name=User_Name;
		populateUpdate();
		
		
		movieName = new JLabel("Select a Movie to delete");
		movieName.setBounds(50,300,250,50);
		panel.add(movieName);
		
		delete = new JButton("DELETE");
		delete.setBounds(300,350,100,50);
		delete.addActionListener(this);
		panel.add(delete);
		
		back = new JButton("BACK");
		back.setBounds(50,350,80,50);
		back.addActionListener(this);
		panel.add(back);
		
		
		
		logOut = new JButton("Log Out");
		logOut.setBounds(350,20,100,50);
		logOut.addActionListener(this);
		panel.add(logOut);
		
		
		this.add(panel);
		
	}
	
	public void actionPerformed(ActionEvent a)
	{
		String elementText = a.getActionCommand();
		
		if(elementText.equals(delete.getText()))
		{
			deleteFromDB();
			movieName.setText("A Movie deleted successfully :)");
			tableModel.setRowCount(0);
			populateUpdate();
		}
		else if(elementText.equals(logOut.getText()))
		{
			LoginUI lg = new LoginUI();
			lg.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(back.getText()))
		{
			System.out.println(User_Name);
		    String query = "SELECT id, name, email, user_type, mobile, address, age, gender FROM user_table where username='"+User_Name+"'";     
			//Connection con=null;//for connection
			//Statement st = null;//for query execution
			//ResultSet rs = null;//to get row by row result from DB
			try
			{
				Class.forName("com.mysql.jdbc.Driver");//load driver
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
				Statement st = con.createStatement();//create statement
				ResultSet rs = st.executeQuery(query);//getting result
				//System.out.println(""+id);
				
				while(rs.next())
				{
					System.out.println("test2");
					ID = rs.getInt("id");
				    Email = rs.getString("email");
					User_Type = rs.getString("user_type");
					Name = rs.getString("name");
					Mobile_No = rs.getString("mobile");
					Address = rs.getString("address");
					Age = rs.getInt("age");
					Gender = rs.getString("gender");
					//System.out.println(movie+director+imdb+genre);
									
				}
				
			}
			catch(Exception e){}
			
			AdminHome ah = new AdminHome(ID,User_Name,Email,Password,User_Type,Name,Mobile_No,Address,Age,Gender);
			ah.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public void populateUpdate()
	{
		Connection con = null;
		Statement stm = null;
		//String query = "SELECT * FROM 'movie_table'";
		
		//System.out.println(query);
		//ResultSet rs = st.getResultSet();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			stm = con.createStatement();
			String query = "select id, movie_name from movie_table where is_active=1";
			stm.executeQuery(query);
            ResultSet rs = stm.executeQuery(query);
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			//stm = con.createStatement();
			//ResultSet rs = stm.executeQuery(query);
			
			while (rs.next())
			{
				String ID = rs.getString("id");
				String movieName = rs.getString("movie_name");
				
				i++;
				String k = Integer.toString(i);
				
				tableModel.addRow (new Object[] {k,ID,movieName});
				
			}
			
		}
			
		catch(Exception ex)
		{
			System.out.println("Exception : " + ex.getMessage());
		}
		finally
		{
			try
			{
				if(stm != null) stm.close();
				if(con!= null) con.close();
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " + ex.getMessage());
			}
		}
	}
	
	public void deleteFromDB()
	{
		int row = movieTable.getSelectedRow();
		if (row < 0) return;
		String DeleteID = movieTable.getModel().getValueAt(row, 1).toString();
		
		String query = "update movie_table set is_active=0 where id="+DeleteID+";";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking", "root", "");
			Statement stm = con.createStatement();
			stm.executeUpdate(query);
			stm.close();
			con.close();
					
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
    }
	
	
}