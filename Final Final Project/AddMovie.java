import java.sql.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.util.Date;

public class AddMovie extends JFrame implements ActionListener
{
    private JPanel panel1;
    private JLabel labelMovieName, labelDirectorName, labelImdb, labelGenre, labelReleaseDate, labelHallName, labelFromDate, labelToDate, labelWhichHall;
    private JTextField textFieldMovieName, textFieldDirectorName, textFieldImdb, textFieldGenre, textFieldReleaseDate, textFieldFromDate, textFieldToDate;
    private JComboBox comboBoxHallName;
    private JButton buttonAddToDatabase, buttonLogout, back;
	private JLabel labelId;
	private JTextField textFieldId;
	private JComboBox combo;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age, HallID, MovieID;
	String s;

	
	
    public AddMovie(String User_Name)
    {
        super("Add Movie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);

        panel1 = new JPanel();
        panel1.setLayout(null);
		
		Date dNow = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		System.out.println("Current Date: " + ft.format(dNow));
		s = ft.format(dNow);
		
		
        labelMovieName = new JLabel("Movie Name : ");
        labelMovieName.setBounds(50,50-30-10,100,30);
        panel1.add(labelMovieName);

        textFieldMovieName = new JTextField();
        textFieldMovieName.setBounds(200,50-30-10,150,30);
        panel1.add(textFieldMovieName);

        labelDirectorName = new JLabel("Director : ");
        labelDirectorName.setBounds(50,100-30-10,100,30);
        panel1.add(labelDirectorName);

        textFieldDirectorName = new JTextField();
        textFieldDirectorName.setBounds(200,100-30-10,150,30);
        panel1.add(textFieldDirectorName);

        labelImdb = new JLabel("IMDB rating : ");
        labelImdb.setBounds(50,150-30-10,100,30);
        panel1.add(labelImdb);

        textFieldImdb = new JTextField();
        textFieldImdb.setBounds(200,150-30-10,150,30);
        panel1.add(textFieldImdb);

        labelGenre = new JLabel("Genre : ");
        labelGenre.setBounds(50,200-30-10,100,30);
        panel1.add(labelGenre);

        textFieldGenre = new JTextField();
        textFieldGenre.setBounds(200,200-30-10,150,30);
        panel1.add(textFieldGenre);
		
		labelReleaseDate = new JLabel("Released Date : ");
		labelReleaseDate.setBounds(50,250-30-10,100,30);
		panel1.add(labelReleaseDate);
		
		textFieldReleaseDate = new JTextField();
		textFieldReleaseDate.setBounds(200,250-30-10,150,30);
		panel1.add(textFieldReleaseDate);
		
		labelFromDate = new JLabel("from date : ");
		labelFromDate.setBounds(50,270-10,100,30);
		panel1.add(labelFromDate);
		
		textFieldFromDate = new JTextField();
		textFieldFromDate.setBounds(200,270-10,150,30);
		panel1.add(textFieldFromDate);
		
		labelToDate = new JLabel("to date : ");
		labelToDate.setBounds(50,320-10,100,30);
		panel1.add(labelToDate);
		
		textFieldToDate = new JTextField();
		textFieldToDate.setBounds(200,320-10,150,30);
		panel1.add(textFieldToDate);
		
		
		
		
		buttonAddToDatabase = new JButton("Add Movie");
		buttonAddToDatabase.setBounds(300,400+10,150,30);
		buttonAddToDatabase.addActionListener(this);
		panel1.add(buttonAddToDatabase);
		
		buttonLogout = new JButton("logout");
		buttonLogout.setBounds(400-20,10,100,40);
		buttonLogout.addActionListener(this);
		panel1.add(buttonLogout);
		
		back = new JButton("BACK");
		back.setBounds(50,400+10,100,30);
		back.addActionListener(this);
		panel1.add(back);
		
		labelWhichHall = new JLabel("hall name: ");
		labelWhichHall.setBounds(50,360,100,30);
		panel1.add(labelWhichHall);
		
		combo = new JComboBox();
		combo.setBounds(370-170,360,100,30);
		panel1.add(combo);
		this.add(panel1);
		try{
		//HallModel hall = new HallModel();
		ArrayList<HallModel> hallList =  new HallService().getHallList();
		for(HallModel hall : hallList){
		combo.addItem(new ComboItem(hall.hallName, hall.id));
		}
		}catch(Exception ex)
		{
		System.out.println("Exception : " + ex.getMessage());
		}

		
        this.add(panel1);
		this.User_Name=User_Name;

    }
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		
		if(elementText.equals(buttonAddToDatabase.getText()))
		{
			int k=0;
			
			String query = "select id from movie_table where movie_name='"+textFieldMovieName.getText()+"'";
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
				addToDatabase();
				JOptionPane.showMessageDialog(this,"Added to Database");
				textFieldDirectorName.setText("");
				textFieldGenre.setText("");
				
				textFieldMovieName.setText("");
				textFieldImdb.setText("");
				textFieldReleaseDate.setText("");
				textFieldFromDate.setText("");
				textFieldToDate.setText("");
			}
			else if(k!=0)
			{
				String x = "This Movie Name already exist";
				JOptionPane.showMessageDialog(this, x);
			}
		}
		else if(elementText.equals(buttonLogout.getText()))
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
	
	
	//jdbc:mysql://localhost:3306/movie_ticket_booking
	
	public void addToDatabase()
	{
		//String query = "INSERT INTO movie_table(movie_name,director,imdb_rating,genre,release_date) VALUES ('"+textFieldMovieName.getText()+"','"+textFieldDirectorName.getText()+"','"+textFieldImdb.getText()+"','"+textFieldGenre.getText()+"','"+textFieldReleasedate.getText()+"');";
//		String query = "INSERT INTO movie_table(movie_name,director,genre,release_date) VALUES ('abc Movie', 'xyz Director', 'action', '2000-12-08')";
        //System.out.println(query);
		
		

		AddMovieViewModel movie = new AddMovieViewModel();
		movie.setMovieName(textFieldMovieName.getText());
		movie.setDirector(textFieldDirectorName.getText());
		movie.setImdb(textFieldImdb.getText());
		movie.setGenre(textFieldGenre.getText());
		movie.setReleaseDate(textFieldReleaseDate.getText());
		
		MovieService movieServie = new MovieService();
		try{
			movieServie.addMovie(movie);
		}		
		catch(Exception ex){
			System.out.println("Exception : " + ex.getMessage());
		}
		//HallService hall = new HallService();
		
		Connection con = null; //DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
		Statement stm = null; //con.createStatement();
		String query = "SELECT id FROM hall_table where hall_name='"+combo.getSelectedItem().toString()+"'";
		System.out.println(query);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
			stm = con.createStatement();
			stm.execute(query);
			ResultSet rs = stm.executeQuery(query);
			System.out.println("Success");
			while(rs.next())
				{
					System.out.println("test2");
					HallID = rs.getInt("id");
				    System.out.println(HallID);	
				}
			
			query = "SELECT id FROM movie_table where movie_name='"+textFieldMovieName.getText()+"'";
			stm.execute(query);
			rs = stm.executeQuery(query);
			System.out.println("Success for movie name");
			while(rs.next())
				{
					System.out.println("test3");
					MovieID = rs.getInt("id");
				    System.out.println(MovieID);	
				}
			
			 
			
			query = "INSERT INTO movie_hall(movie_id,hall_id,added_date_time,from_date,to_date) VALUES ('"+MovieID+"','"+HallID+"','"+s+"','"+textFieldFromDate.getText()+"','"+textFieldToDate.getText()+"');";
			stm.execute(query);
			System.out.println("Successfully inserted into movie hall table");
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " + ex.getMessage());
		}
		finally
		{	
		    try
			{
				if(stm!=null){stm.close();}
				if(con!=null){con.close();}
			}
			catch(Exception ex)
			{
			    System.out.println("Exception : " + ex.getMessage());
		    }
		}
		
	}
	

}
