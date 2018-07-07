import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.Date;

public class BookPage extends JFrame implements ActionListener,KeyListener
{
	private JPanel panel;
	private JTable movieTable;
	private JScrollPane movieScrollPane;
	private JComboBox comboMovie;
	private JLabel movieName, searchMovie, labelPleaseSelect;
	private JButton next, back;
	JTextField textFieldSearchMovie;
	String movie,director,genre,date;
	Double imdb;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age;
	DefaultTableModel tableModel;
	
	public BookPage(String User_Name) //throws Exception
	{
		super("Ticket Booking");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
        panel.setLayout(null);
		
		int i=0;
				
		comboMovie = new JComboBox();

		searchMovie = new JLabel("Search Movie");
		searchMovie.setBounds(50,15,100,30);
		panel.add(searchMovie);

		textFieldSearchMovie = new JTextField();
		textFieldSearchMovie.setBounds(150,15,150,30);
		textFieldSearchMovie.addKeyListener(this);
		panel.add(textFieldSearchMovie);
		
		labelPleaseSelect = new JLabel("Please, select a movie to book.");
		labelPleaseSelect.setBounds(50,80,200,20);
		panel.add(labelPleaseSelect);
		
		String []col = {"Serial No","Movie Name", "Director"};
		movieTable = new JTable();
		tableModel = new DefaultTableModel(0,5);
		tableModel.setColumnIdentifiers(col);
		movieTable.setModel(tableModel);
		movieScrollPane = new JScrollPane(movieTable);
		movieScrollPane.setBounds(50,100,400,150);
		panel.add(movieScrollPane);

		//String query = "SELECT * FROM 'movie_table'";
		
		//System.out.println(query);
		//ResultSet rs = st.getResultSet();

		PopulateTable(tableModel, "");

		Object comboIteam = comboMovie.getSelectedItem();
        //System.out.println(comboIteam);
		comboMovie.setBounds(50,250,250,50);
		//panel.add(comboMovie);
		
		movieName = new JLabel("Select a movie name to book ticket");
		movieName.setBounds(50,200,250,50);
		//panel.add(movieName);
		
		next = new JButton("NEXT");
		next.setBounds(150,350,80,50);
		next.addActionListener(this);
		panel.add(next);
		
		back = new JButton("BACK");
		back.setBounds(50,350,80,50);
		back.addActionListener(this);
		panel.add(back);
		
		this.add(panel);
		this.User_Name=User_Name;
		
	}

	private void PopulateTable( DefaultTableModel tableModel, String searchKey) {
		
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	  

		System.out.println("Current Date: " + ft.format(dNow));
		
		String currentDate = ft.format(dNow);
		
		String query = "";
		if (searchKey.equalsIgnoreCase("")){
			query = "SELECT * FROM movie_table where id IN (SELECT movie_id FROM movie_hall WHERE to_date>='"+currentDate+"') and is_active=1 ";
		}else{
			query = "SELECT * FROM movie_table where id IN (SELECT movie_id FROM movie_hall WHERE to_date>='"+currentDate+"') and is_active=1 and movie_name LIKE '%"+searchKey+"%' ";
		}
		int i = 0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			Statement stm = con.createStatement();
			stm.executeQuery(query);
            ResultSet rs = stm.getResultSet();


			while (rs.next())
			{
				String movieName = rs.getString("movie_name");
				String releasedDate = rs.getString("release_date");
				String director = rs.getString("director");

				i++;
				String k = Integer.toString(i);

				tableModel.addRow (new Object[] {k,movieName,director});
				//comboMovie.addItem(""+movieName);
			}

		}

		catch(Exception ex)
		{
			System.out.println("Exception : " + ex.getMessage());
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(next.getText()))
		{
			int row = movieTable.getSelectedRow();
			if (row < 0) return;
			String movieName = movieTable.getModel().getValueAt(row, 1).toString();
			String query = "SELECT * FROM movie_table where movie_name = '"+movieName+"';";
			System.out.println(query);
			//Connection con=null;//for connection
			//Statement st = null;//for query execution
			// rs = null;//to get row by row result from DB
			try
			{
				Class.forName("com.mysql.jdbc.Driver");//load driver
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
				Statement st = con.createStatement();//create statement
				ResultSet rs = st.executeQuery(query);//getting result
				System.out.println("test1");
				while(rs.next())
				{
					System.out.println("test2");
					movie = rs.getString("movie_name");
				    director = rs.getString("director");
					imdb = rs.getDouble("imdb_rating");
					date = rs.getString("release_date");
					genre = rs.getString("genre");
					//System.out.println(movie+director+imdb+genre);
					
					
				}
				
			}
			catch(Exception e){}
			
			MovieInfoTime ah = new MovieInfoTime(movie,director,imdb,date,genre,User_Name);
			ah.setVisible(true);
			this.setVisible(false);
		}
		
		/*else if(elementText.equals(buttonChangePassword.getText()))
		{
			ChangePassword cp = new ChangePassword();
			cp.setVisible(true);
			this.setVisible(false);
		}*/
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
			
			UserHome ah = new UserHome(ID,User_Name,Email,Password,User_Type,Name,Mobile_No,Address,Age,Gender);
			ah.setVisible(true);
			this.setVisible(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		tableModel.setRowCount(0);
		comboMovie.removeAllItems();
		PopulateTable(tableModel, textFieldSearchMovie.getText());
	}

/*
	public static void main(String args[])
	{
		BookPage lg = new BookPage("qwe");
		lg.setVisible(true);
	}*/
	
}