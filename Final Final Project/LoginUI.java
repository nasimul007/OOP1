import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class LoginUI extends JFrame implements ActionListener
{
	private JLabel labelUsername, labelPassword, title, title2, member1, member2;
	private JTextField textFieldUsername;
	private JPasswordField password;
	private JButton buttonLogin, exit, buttonRegister;
	private JPanel panel;
	private boolean flag;
	
	public LoginUI()
	{
		super("Login Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
        panel.setLayout(null);
		
		title = new JLabel("Movie Ticket Booking");
		title.setFont(new Font("Consolas",Font.BOLD,30));
		title.setBounds(50,50,400,50);
		panel.add(title);
		
		title2 = new JLabel("System");
		title2.setFont(new Font("Consolas",Font.BOLD,30));
		title2.setBounds(150,100,300,50);
		panel.add(title2);
		
		labelUsername = new JLabel("Username : ");
		labelUsername.setBounds(50,250,100,30);
		panel.add(labelUsername);
		
		labelPassword = new JLabel("Password :");
		labelPassword.setBounds(50,300,100,30);
		panel.add(labelPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(200,250,150,30);
		panel.add(textFieldUsername);
		
		password = new JPasswordField();
		password.setBounds(200,300,150,30);
		panel.add(password);
		
		buttonLogin = new JButton("Log In");
		buttonLogin.setBounds(200,350,80,30);
		buttonLogin.addActionListener(this);
		panel.add(buttonLogin);
		
		member1 = new JLabel("not a");
		member1.setBounds(405,340,100,30);
		//panel.add(member1);
		
		member2 = new JLabel("member?");
		member2.setBounds(400,355,100,30);
		//panel.add(member2);
		
		buttonRegister = new JButton("Register");
		buttonRegister.setBounds(300,350,100,30);
		buttonRegister.addActionListener(this);
		panel.add(buttonRegister);
		
		exit = new JButton("EXIT");
		exit.setBounds(200,400,200,30);
		exit.addActionListener(this);
		panel.add(exit);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		
		if(elementText.equals(buttonLogin.getText()))
		{
			System.out.println("hello");
			flag=true;
			check();
		}
		else if(elementText.equals(buttonRegister.getText()))
		{
			CreateNewUserAccount cu = new CreateNewUserAccount();
			cu.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(exit.getText()))
		{
			System.exit(0);
		}
		
		else{}
	}
	
	public void check()
	{
        String query = "SELECT * FROM `user_table`;";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
					
			while(rs.next())
			{
                int ID = rs.getInt("id");
                String User_Name = rs.getString("username");
				String Email = rs.getString("email");
				String Password = rs.getString("password");
				String User_Type = rs.getString("user_type");
				String Name = rs.getString("name");
				String Mobile_No = rs.getString("mobile");
				String Address = rs.getString("address");
				int Age = rs.getInt("age");
				String Gender = rs.getString("gender");
				
				if(User_Name.equals(textFieldUsername.getText()))
				{
					flag=false;
					if(Password.equals(password.getText()))
					{
						if(User_Type.equals("user"))
						{
							UserHome ush = new UserHome(ID,User_Name,Email,Password,User_Type,Name,Mobile_No,Address,Age,Gender);
							this.setVisible(false);
							ush.setVisible(true);
						}
						else if(User_Type.equals("admin"))
						{
							AdminHome adh = new AdminHome(ID,User_Name,Email,Password,User_Type,Name,Mobile_No,Address,Age,Gender);
							this.setVisible(false);
							adh.setVisible(true);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Invalid pass"); 
					}
				}			
			}
			if(flag){JOptionPane.showMessageDialog(this,"Invalid name"); }
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
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
    }
	
}