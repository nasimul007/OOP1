//package GUI;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class UserHome extends JFrame implements ActionListener
{
	private JLabel labelName, labelEmail, labelId, labelUserType, labelMobile, labelAdress, labelAge, labelGender;
	private JLabel labelNameOfUser, labelEmailOfUser, labelIdOfUser, labelUserTypeOfUser, labelMobileOfUser, labelAdressOfUser, labelAgeOfUser, labelGenderOfUser;
	private JButton buttonChangePassword, buttonLogOut, buttonBook, edit, buttonSearch, buttonCancel;
	//private JLabel labelNameOfUser, labelEmailOfUser
	private JPanel panel;
	private JTable table;
	private JTextField nameTF, emailTF, idTF, typeTF, mobileTF, addressTF, ageTF, genderTF, searchTF;
	//String dataTable[][], column[];
	//JTable tableInfo;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age, UserID;
	String movie,director,genre,date;
	double imdb;
	
	UserHome(int ID, String User_Name, String Email, String Password, String User_Type, String Name, String Mobile_No, String Address, int Age, String Gender)
	{
		super("User Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setSize(800,500);
		
		this.Name = Name;
		this.ID = ID;
		this.Age = Age;
		this.User_Name = User_Name;
		this.Email = Email;
		this.Password = Password;
		this.User_Type = User_Type;
		this.Mobile_No = Mobile_No;
		this.Address = Address;
		this.Gender = Gender;
		
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
		//labelId.setBounds(10,100,100-20,30);
		labelUserType.setBounds(10,150-50,100-20,30);
		labelMobile.setBounds(10,200-50,100-20,30);
		labelAdress.setBounds(10,250-50,100-20,30);
		labelAge.setBounds(10,300-50,100-20,30);
		labelGender.setBounds(10,350-50,100-20,30);
		
		labelNameOfUser.setBounds(120-20,10,200,30);
		labelEmailOfUser.setBounds(120-20,50,200,30);
		//labelIdOfUser.setBounds(120-20,100,200,30);
		labelUserTypeOfUser.setBounds(120-20,150-50,200,30);
		labelMobileOfUser.setBounds(120-20,200-50,200,30);
		labelAdressOfUser.setBounds(120-20,250-50,200+100,30);
		labelAgeOfUser.setBounds(120-20,300-50,200,30);
		labelGenderOfUser.setBounds(120-20,350-50,200,30);
		
		panel.add(labelName);
		panel.add(labelEmail);
		//panel.add(labelId);
		panel.add(labelUserType);
		panel.add(labelMobile);
		panel.add(labelAdress);
		panel.add(labelAge);
		panel.add(labelGender);
		panel.add(labelNameOfUser);
		panel.add(labelEmailOfUser);
		//panel.add(labelIdOfUser);
		panel.add(labelUserTypeOfUser);
		panel.add(labelMobileOfUser);
		panel.add(labelAdressOfUser);
		panel.add(labelAgeOfUser);
		panel.add(labelGenderOfUser);
		
		buttonBook = new JButton("Book Now");
		buttonBook.setBounds(320,30,140,30);
		buttonBook.addActionListener(this);
		panel.add(buttonBook);
		
		buttonCancel = new JButton("Cancel Booking");
		buttonCancel.setBounds(320,100,140,30);
		buttonCancel.addActionListener(this);
		panel.add(buttonCancel);
		
		searchTF = new JTextField();
		searchTF.setBounds(340,80,120,30);
		//panel.add(searchTF);
		
		buttonSearch = new JButton("Search");
		buttonSearch.setBounds(350,110,100,30);
		buttonSearch.addActionListener(this);
		//panel.add(buttonSearch);
		
		buttonChangePassword = new JButton("change password?");
		buttonChangePassword.setBounds(10,400,200,30);
		buttonChangePassword.addActionListener(this);
		panel.add(buttonChangePassword);
		
		buttonLogOut = new JButton("logout");
		buttonLogOut.setBounds(280,400,100,30);
		buttonLogOut.addActionListener(this);
		panel.add(buttonLogOut);
		
		edit = new JButton("EDIT");
		edit.setBounds(400,400,100,30);
		edit.addActionListener(this);
		//panel.add(edit);
		
		this.add(panel);
		
		this.User_Name=User_Name;
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(buttonBook.getText()))
		{
			BookPage ah = new BookPage(User_Name);
			ah.setVisible(true);
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
		else if(elementText.equals(buttonSearch.getText()))
		{
			System.out.println(User_Name);
		    String query = "SELECT movie_name, director, imdb_rating, release_date, genre FROM movie_table where movie_name='"+searchTF.getText()+"'";     
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
					movie = rs.getString("movie_name");
				    director = rs.getString("director");
					imdb = rs.getDouble("imdb_rating");
					date = rs.getString("release_date");
					genre = rs.getString("genre");
					System.out.println(movie+director+imdb+genre);
									
				}
				
			}
			catch(Exception e){}
			
			MovieInfoTime m = new MovieInfoTime(movie,director,imdb,date,genre,User_Name);
			m.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(buttonCancel.getText()))
		{
			
			CancelBooking cb = new CancelBooking(ID);
			cb.setVisible(true);
			this.setVisible(false);
			
		}
		else{}
	}
	
	
}