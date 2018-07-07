import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class ChangePassword extends JFrame implements ActionListener
{
	private JLabel labelOldPassword, labelNewPassword, labelConfirmPassword;
	private JPasswordField oldPassword, newPassword, confirmPassword;
	private JLabel labelId;
	private JButton buttonChange,buttonBack,back;
	private JPanel panel;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age;
	
	ChangePassword(String User_Name)
	{
		super("Change Password");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	
		panel = new JPanel();
		panel.setLayout(null);
		
		labelOldPassword = new JLabel("Old Password: ");
		labelNewPassword = new JLabel("New Password: ");
		//labelConfirmPassword = new JLabel("Confirm Password: ");
		
		labelOldPassword.setBounds(50,100,140,30);
		labelNewPassword.setBounds(50,150,140,30);
		//labelConfirmPassword.setBounds(50,200,140,30);
		
		panel.add(labelOldPassword);
		panel.add(labelNewPassword);
		//panel.add(labelConfirmPassword);
		
		oldPassword = new JPasswordField();
		newPassword = new JPasswordField();
		//confirmPassword = new JPasswordField();
		
		oldPassword.setBounds(250,100,150,30);
		newPassword.setBounds(250,150,150,30);
		//confirmPassword.setBounds(250,200,150,30);
		
		buttonBack = new JButton("Log Out");
		buttonBack.setBounds(350,30,80,40);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		buttonChange = new JButton("Change");
		buttonChange.setBounds(200,300,100,40);
		buttonChange.addActionListener(this);
		panel.add(buttonChange);
		
		panel.add(oldPassword);
		panel.add(newPassword);
		//panel.add(confirmPassword);
		
		back = new JButton("BACK");
		back.setBounds(10,300,100,40);
		back.addActionListener(this);
		panel.add(back);
		
		this.add(panel);
		this.User_Name=User_Name;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(buttonBack.getText()))
		{
			LoginUI lg = new LoginUI();
			lg.setVisible(true);
			this.setVisible(false);
		}
		
		else if(elementText.equals(buttonChange.getText()))
		{
			changePassword();
			String text = "Password changed successfully";
			JOptionPane.showMessageDialog(this,text);
		}
		
		else if(elementText.equals(back.getText()))
		{
			String stringType = "";
			System.out.println(User_Name);
		    String query = "SELECT id, name, email, user_type, mobile, address, age, gender FROM user_table where username='"+User_Name+"'";     
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
					Name = rs.getString("name");
					Mobile_No = rs.getString("mobile");
					Address = rs.getString("address");
					Age = rs.getInt("age");
					Gender = rs.getString("gender");
					//System.out.println(movie+director+imdb+genre);
					stringType = rs.getString("user_type");
									
				}
				
			}
			catch(Exception e){System.out.println("Khaise");}
			finally
			{
				try
				{
					if(rs!=null)
						rs.close();

					if(st!=null)
						st.close();

					if(con!=null)
						con.close();
				}
				catch(Exception ex){}
			}
			String stringAdmin = "admin";
			String stringUser="user";
			
			if(stringType.equals(stringAdmin)){
				AdminHome ah = new AdminHome(ID,User_Name,Email,Password,User_Type,Name,Mobile_No,Address,Age,Gender);
				ah.setVisible(true);
				this.setVisible(false);
			}
				else if(stringType.equals(stringUser)){
				UserHome ah = new UserHome(ID,User_Name,Email,Password,User_Type,Name,Mobile_No,Address,Age,Gender);
				ah.setVisible(true);
				this.setVisible(false);
			}
		}
			
	}
	
	public void changePassword()
	{
		System.out.println(User_Name);
		//String query = "SELECT 'id', 'username' FROM `user_table`;";     
			//Connection con=null;//for connection
			//Statement st = null;//for query execution
			//ResultSet rs = null;//to get row by row result from DB
			try
			{
				System.out.println("test1");
				Class.forName("com.mysql.jdbc.Driver");//load driver
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
				Statement st = con.createStatement();//create statement
				System.out.println("test");

						String query = "update user_table set password='"+newPassword.getText()+"' where username='"+User_Name+"'";
						System.out.println(query);
						st.executeUpdate(query);
						st.close();
						con.close();
				
//rs.close();
			}
			catch(Exception e){
				System.out.println("test2");
			}
	}


	/*
	public static void main(String []args)
	{
		ChangePassword cp = new ChangePassword();
		cp.setVisible(true);
	}*/
}