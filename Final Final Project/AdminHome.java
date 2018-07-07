//package GUI;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AdminHome extends JFrame implements ActionListener
{
	private JLabel labelName, labelEmail, labelId, labelUserType, labelMobile, labelAdress, labelAge, labelGender;
	private JLabel labelNameOfUser, labelEmailOfUser, labelIdOfUser, labelUserTypeOfUser, labelMobileOfUser, labelAdressOfUser, labelAgeOfUser, labelGenderOfUser;
	private JButton buttonChangePassword, buttonLogOut, buttonAddHall, buttonAddUser, buttonAddMovie;
	JButton buttonDeleteMovie, buttonUserList;
	//private JLabel labelNameOfUser, labelEmailOfUser
	private JPanel panel;
	//String dataTable[][], column[];
	//JTable tableInfo;
	String User_Name;
	
	AdminHome(int ID, String User_Name, String Email, String Password, String User_Type, String Name, String Mobile_No, String Address, int Age, String Gender)
	{
		super("Admin Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setSize(800,500);
		
		labelName = new JLabel("Name: ");
		labelEmail = new JLabel("Email: ");
		labelId = new JLabel("ID: ");
		labelUserType = new JLabel("User Type: ");
		labelMobile = new JLabel("Mobile: ");
		labelAdress = new JLabel("Address: ");
		labelAge = new JLabel("Age: ");
		labelGender = new JLabel("Gender: ");
		labelNameOfUser = new JLabel(""+Name);
		labelEmailOfUser = new JLabel(""+Email);
		labelIdOfUser = new JLabel(""+ID);
		labelUserTypeOfUser = new JLabel(""+User_Type);
		labelMobileOfUser = new JLabel(""+Mobile_No);
		labelAdressOfUser = new JLabel(""+Address);
		labelAgeOfUser = new JLabel(""+Age);
		labelGenderOfUser = new JLabel(""+Gender);
		
		labelName.setBounds(10,10,100-20,30);
		labelEmail.setBounds(10,50,100-20,30);
		labelId.setBounds(10,100,100-20,30);
		labelUserType.setBounds(10,150,100-20,30);
		labelMobile.setBounds(10,200,100-20,30);
		labelAdress.setBounds(10,250,100-20,30);
		labelAge.setBounds(10,300,100-20,30);
		labelGender.setBounds(10,350,100-20,30);
		labelNameOfUser.setBounds(120-20,10,200,30);
		labelEmailOfUser.setBounds(120-20,50,200,30);
		labelIdOfUser.setBounds(120-20,100,200,30);
		labelUserTypeOfUser.setBounds(120-20,150,200,30);
		labelMobileOfUser.setBounds(120-20,200,200,30);
		labelAdressOfUser.setBounds(120-20,250,200,30);
		labelAgeOfUser.setBounds(120-20,300,200,30);
		labelGenderOfUser.setBounds(120-20,350,200,30);
		
		panel.add(labelName);
		panel.add(labelEmail);
		panel.add(labelId);
		panel.add(labelUserType);
		panel.add(labelMobile);
		panel.add(labelAdress);
		panel.add(labelAge);
		panel.add(labelGender);
		panel.add(labelNameOfUser);
		panel.add(labelEmailOfUser);
		panel.add(labelIdOfUser);
		panel.add(labelUserTypeOfUser);
		panel.add(labelMobileOfUser);
		panel.add(labelAdressOfUser);
		panel.add(labelAgeOfUser);
		panel.add(labelGenderOfUser);
		
		buttonAddHall = new JButton("Add New Hall");
		buttonAddHall.setBounds(340,30,120,30);
		buttonAddHall.addActionListener(this);
		panel.add(buttonAddHall);
		
		buttonAddUser = new JButton("New admin");
		buttonAddUser.setBounds(340,80,120,30);
		buttonAddUser.addActionListener(this);
		panel.add(buttonAddUser);
		
		buttonAddMovie = new JButton("Add Movie");
		buttonAddMovie.setBounds(340,130,120,30);
		buttonAddMovie.addActionListener(this);
		panel.add(buttonAddMovie);
		
		buttonDeleteMovie = new JButton("Delete Movie");
		buttonDeleteMovie.setBounds(340,180,120,30);
		buttonDeleteMovie.addActionListener(this);
		panel.add(buttonDeleteMovie);
		
		buttonUserList = new JButton("User List");
		buttonUserList.setBounds(340,230,120,30);
		buttonUserList.addActionListener(this);
		panel.add(buttonUserList);
		
		buttonChangePassword = new JButton("change password?");
		buttonChangePassword.setBounds(10,400,200,30);
		buttonChangePassword.addActionListener(this);
		panel.add(buttonChangePassword);
		
		buttonLogOut = new JButton("Log Out");
		buttonLogOut.setBounds(280,400,100,30);
		buttonLogOut.addActionListener(this);
		panel.add(buttonLogOut);
		
		this.add(panel);
		
		this.User_Name=User_Name;
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(buttonAddHall.getText()))
		{
			AddHall ah = new AddHall(User_Name);
			ah.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(buttonAddUser.getText()))
		{
			CreateNewAdminAccount cn = new CreateNewAdminAccount(User_Name);
			cn.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(buttonChangePassword.getText()))
		{
			ChangePassword cp = new ChangePassword(User_Name);
			cp.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(buttonLogOut.getText()))
		{
			LoginUI lg = new LoginUI();
			lg.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(buttonAddMovie.getText()))
		{
			AddMovie am = new AddMovie(User_Name);
			am.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(buttonDeleteMovie.getText()))
		{
			DeleteMovie am = new DeleteMovie(User_Name);
			am.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(buttonUserList.getText()))
		{
			UserList am = new UserList(User_Name);
			am.setVisible(true);
			this.setVisible(false);
		}
		
	}
	
	/*
	public static void main(String []args){
		AdminHome admin = new AdminHome();
		admin.setVisible(true);
	}*/
	
}