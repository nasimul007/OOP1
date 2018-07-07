import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;

public class UserList extends JFrame implements ActionListener, KeyListener
{
	private JTable userTable;
	private JPanel panel;
	private JScrollPane userScroll;
	private JButton deleteUser, buttonBack, back;
	private JTextField userTF,textFieldSearchUser;
	private JLabel title, searchUser;
	String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	int ID, Age;
	DefaultTableModel tableModel;
	
	public UserList(String user)
	{
		super("User List");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
        panel.setLayout(null);
		
		int i=0;
		
		String []col = {"Serial No","ID","user name","Email", "User Type","Name","Age"};
		
		searchUser = new JLabel("Search User");
		searchUser.setBounds(50,15,100,30);
		panel.add(searchUser);
		
		textFieldSearchUser = new JTextField();
		textFieldSearchUser.setBounds(150,15,150,30);
		textFieldSearchUser.addKeyListener(this);
		panel.add(textFieldSearchUser);
		
		userTable = new JTable();
		tableModel = new DefaultTableModel(0,5);
		tableModel.setColumnIdentifiers(col);
		userTable.setModel(tableModel);
		userScroll = new JScrollPane(userTable);
		userScroll.setBounds(50,100,400,150);
		panel.add(userScroll);
		
		PopulateTable(tableModel, "");
		
		Connection con = null;
		Statement stm = null;
		//String query = "SELECT * FROM `user_table` ";
		
		//System.out.println(query);
		//ResultSet rs = st.getResultSet();
		
		
		//userScroll = new JScrollPane(userTable);
		//userScroll.setBounds(50,50,200,100);
		//panel.add(userScroll);
		
		title = new JLabel("Select a user to delete");
		title.setBounds(50,250,200,50);
		panel.add(title);
		
		deleteUser = new JButton("Delete");
		deleteUser.setBounds(50+300,300,100,40);
		deleteUser.addActionListener(this);
		panel.add(deleteUser);
		
		buttonBack = new JButton("logout");
		buttonBack.setBounds(350,20-5,100,30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		//50,300,100,40
		back = new JButton("BACK");
		back.setBounds(50,300,100,40);
		back.addActionListener(this);
		panel.add(back);
		
		this.add(panel);
		User_Name=user;
		
	}
	
	private void PopulateTable( DefaultTableModel tableModel, String searchKey) {
		String query = "";
		if (searchKey.equalsIgnoreCase("")){
			query = "select * from user_table where is_active=1";
		}else{
			query = "select * from user_table WHERE is_active=1 and username LIKE '%"+searchKey+"%' ";
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
				String ID = rs.getString("id");
				String User_Name = rs.getString("username");
				String Email = rs.getString("email");
				String User_Type = rs.getString("user_type");
				String NAME = rs.getString("name");
				String Age = rs.getString("age");
				
				i++;
				String k = Integer.toString(i);
				tableModel.addRow (new Object[] {k,ID,User_Name,Email,User_Type,NAME,Age});
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
		if(elementText.equals(buttonBack.getText()))
		{
			LoginUI ah = new LoginUI();
			ah.setVisible(true);
			this.setVisible(false);
		}
		
		else if(elementText.equals(deleteUser.getText()))
		{
			deleteFromDB();
			System.out.println("A user deleted successfully");
			tableModel.setRowCount(0);
			PopulateTable(tableModel, "");
		}
		
		else if(elementText.equals(back.getText()))
		{
			System.out.println(User_Name);
		    String query = "SELECT id, name, email, user_type, mobile, address, age, gender FROM user_table where username='"+User_Name+"'";     

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
	
	public void deleteFromDB()
	{
		int row = userTable.getSelectedRow();
		if (row < 0) return;
		String DeleteID = userTable.getModel().getValueAt(row, 1).toString();
		
		String query = "update user_table set is_active=0 where id="+DeleteID+";";
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
	
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		tableModel.setRowCount(0);
		//comboMovie.removeAllItems();
		PopulateTable(tableModel, textFieldSearchUser.getText());
	}
	
}