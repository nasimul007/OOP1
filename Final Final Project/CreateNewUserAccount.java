import java.lang.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class CreateNewUserAccount extends JFrame implements ActionListener
{
	private JLabel labelUserName, labelEmail, labelPassword, labelUserType, labelName, labelMobile, labelAddress, labelAge, labelGender, mandatory;
	private boolean isActive;
	private JTextField tfUserName, tfEmail, tfPassword, tfName, tfMobile, tfAge, tfAddress, tfGender;
	private JComboBox comboUserType,comboGender, comboAge;
	private JButton buttonBack, buttonLogout, buttonInsert;
	JPanel panel;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age;
	
	public CreateNewUserAccount()
	{
		super("Create New User Account");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelUserName = new JLabel("Username : *");
		labelUserName.setBounds(10,20,80,30);
		panel.add(labelUserName);
		
		labelEmail = new JLabel("E-Mail : *");
		labelEmail.setBounds(10,60,80,30);
		panel.add(labelEmail);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(100,20,100,30);
		panel.add(tfUserName);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(100,60,100,30);
		panel.add(tfEmail);
		
		labelPassword = new JLabel("Password : *");
		labelPassword.setBounds(210,20,80,30);
		panel.add(labelPassword);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(300,20,100,30);
		panel.add(tfPassword);
		
		/*
		labelUserType = new JLabel("Type : *");
		labelUserType.setBounds(210,60,80,30);
		panel.add(labelUserType);
		
		String s[] = {"admin","user"};
		comboUserType = new JComboBox(s);
		comboUserType.setBounds(300,60,100,30);
		panel.add(comboUserType);*/
		
		labelName = new JLabel("Name : *");
		labelName.setBounds(10,100,80,30);
		panel.add(labelName);
		
		tfName = new JTextField();
		tfName.setBounds(100,100,300,30);
		panel.add(tfName);
		
		labelMobile = new JLabel("Mobile : ");
		labelMobile.setBounds(210,60,80,30);
		panel.add(labelMobile);
		
		tfMobile = new JTextField("");
		tfMobile.setBounds(300,60,100,30);
		panel.add(tfMobile);
		
		labelAddress = new JLabel("Address : ");
		labelAddress.setBounds(10,140,80,30);
		panel.add(labelAddress);
		
		tfAddress =new JTextField();
		tfAddress.setBounds(100,140,300,30);
		panel.add(tfAddress);
		
		labelAge = new JLabel("Age : *");
		labelAge.setBounds(10,180,80,30);
		panel.add(labelAge);
		
		tfAge = new JTextField();
		tfAge.setBounds(100,180,100,30);
		panel.add(tfAge);
		/*String age[]= new String[120];
		//age[0]="0";
		for(int i=0;i<120;i++)
		{
			age[i]=Integer.toString(i);
		}
		comboAge = new JComboBox(age);
		comboAge.setBounds(100,180,100,30);
		panel.add(comboAge);*/
		
		
		labelGender = new JLabel("Gender : *");
		labelGender.setBounds(210,180,80,30);
		panel.add(labelGender);
		
		String gender[]= {"Male","Female","Other"};
		comboGender = new JComboBox(gender);
		comboGender.setBounds(300,180,100,30);
		panel.add(comboGender);
		
		mandatory = new JLabel("  *  : Mandatory field");
		mandatory.setForeground(Color.RED);
		mandatory.setBounds(10,230,150,30);
		
		panel.add(mandatory);
		
		buttonInsert = new JButton("Register");
		buttonInsert.setBounds(200,300,100,30);
		buttonInsert.addActionListener(this);
		panel.add(buttonInsert);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(200,350,100,30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
	    
		/*
		buttonLogout = new JButton("Log Out");
		buttonLogout.setBounds(300,350,100,30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);*/
		
		this.add(panel);
		//this.User_Name=User_Name;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonBack.getText()))
		{
			LoginUI l = new LoginUI();
			l.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonInsert.getText()))
		{
			int k=0;
			
			String query = "select id from user_table where username='"+tfUserName.getText()+"'";
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
				insertIntoDB();
				tfUserName.setText("");
				tfEmail.setText("");
				tfPassword.setText("");
				tfName.setText("");
				tfMobile.setText("");
				tfAddress.setText("");
				tfAge.setText("");
				String sss = "Account Created";
				JOptionPane.showMessageDialog(this, sss);
				LoginUI lg = new LoginUI();
				lg.setVisible(true);
				this.setVisible(false);
			}
			else if(k!=0)
			{
				String x = "This Username already exist";
				JOptionPane.showMessageDialog(this, x);
			}
		}
		
	}
	public void insertIntoDB()
	{
		if(tfUserName.getText().equals("") || tfEmail.getText().equals("") || tfPassword.getText().equals("") || tfName.getText().equals(""))
		{
			String k = "Please fill up mandatory fields!";
			JOptionPane.showMessageDialog(this, k);
			return;
		}
		String usertype = "user";
		//String query = "INSERT INTO account VALUES ('"+idTF.getText()+"','"+passwordPF.getText()+"',"+numberTF.getText()+",'"+nameTF.getText()+"',"+initialBalanceTF.getText()+");";
		String query = "INSERT INTO user_table(username, email, password, user_type, name, mobile, address, age, gender,is_active) VALUES ('"+tfUserName.getText()+"','"+tfEmail.getText()+"','"+tfPassword.getText()+"','"+usertype+"','"+tfName.getText()+"','"+tfMobile.getText()+"','"+tfAddress.getText()+"','"+tfAge.getText()+"','"+comboGender.getSelectedItem().toString()+"','1');";
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
        }
    }
	
	
}