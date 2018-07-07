import java.lang.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AddHall extends JFrame implements ActionListener
{
	private JLabel labelAddNewHall;
	private JTextField tfHallName;
	private JButton buttonAddNewHall, buttonBack;
	private JPanel panel;
	//String User_Name;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age;
	
	public AddHall(String User_Name)
	{
		super("Add New Hall");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelAddNewHall = new JLabel("New Hall Name:");
		labelAddNewHall.setBounds(50,100,150,30);
		panel.add(labelAddNewHall);
		
		tfHallName = new JTextField();
		tfHallName.setBounds(200,100,150,30);
		panel.add(tfHallName);
		
		buttonAddNewHall = new JButton("Confirm");
		buttonAddNewHall.setBounds(200+100,300,100,30);
		buttonAddNewHall.addActionListener(this);
		panel.add(buttonAddNewHall);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(10+40,300,100,30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		this.add(panel);
		this.User_Name=User_Name;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(buttonBack.getText()))
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
		
		else if(elementText.equals(buttonAddNewHall.getText()))
		{
			int k=0;
			
			String query = "select id from hall_table where hall_name='"+tfHallName.getText()+"'";
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking", "root", "");
				Statement stm = con.createStatement();
				stm.execute(query);
				ResultSet rs = stm.getResultSet();
				while(rs.next())
				{
					k = rs.getInt("id");
				}
				stm.close();
				con.close();
				System.out.println("Success");
						
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
			
			if(k==0)
			{
				if(addNewHall())
				{			
					JOptionPane.showMessageDialog(this,"successful");
				}
				else
				{
					JOptionPane.showMessageDialog(this,"unsuccessful");
				}
			}
			else if(k!=0)
			{
				String x = "This Hall Name already exist";
				JOptionPane.showMessageDialog(this, x);
			}
		}
		/*else if(elementText.equals(buttonLogOut.getText()))
		{
			LoginUI lg = new LoginUI();
			lg.setVisible(true);
			this.setVisible(false);
		}*/
	}
	
	public boolean addNewHall(){
		String query = "INSERT INTO hall_table(hall_name) VALUES ('"+tfHallName.getText()+"');";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();
			System.out.println("Success");
			
					
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
			return false;
        }
		return true;
	}
	
	
}