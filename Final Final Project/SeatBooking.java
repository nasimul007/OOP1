import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.util.List;

public class SeatBooking extends JFrame implements ActionListener {
    private JLabel labelGold, labelSilver, labelPlatinum;
    private JButton gold1, gold2, gold3, gold4, gold5;
    private JButton silver1, silver2, silver3, silver4, silver5;
    private JButton platinum1, platinum2, platinum3, platinum4, platinum5;
    private JButton buttonBook, buttonBack, buttonlogout;
    private JPanel panel;
    private
    int g1, g2, g3, g4, g5, s1, s2, s3, s4, s5, p1, p2, p3, p4, p5, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15;
    int g, s, p;
    String movie_name, director, genre, date;
    Double imdb;
    String User_Name, MovieDate, checkMovieDate;
    String movie, MovieTime, SeatNumber;
    int UserID, MovieID, ShowID, BookID, checkShowID, checkShowID2, checkBookID, BookID2;
    MovieInfoTime mh;
    ArrayList<Integer> bookIdList = new ArrayList<>();
	ArrayList<Integer> bookIdList2 = new ArrayList<>();
    ArrayList<String> List = new ArrayList<String>();
    ArrayList<String> List2 = new ArrayList<String>();
    ArrayList<Integer> List3 = new ArrayList<Integer>();
    Iterator<String> itr;
    Iterator<Integer> itr2;
    int count;

    public SeatBooking(String User_Name, String movie, MovieInfoTime mh) {
        super("Seat Booking");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        this.movie = movie;
        panel = new JPanel();
        panel.setLayout(null);

        labelGold = new JLabel("Gold : 500 taka");
        labelGold.setBounds(10, 50, 200, 30);
        panel.add(labelGold);

        labelSilver = new JLabel("Silver : 300 taka");
        labelSilver.setBounds(10, 10, 200, 30);
        panel.add(labelSilver);

        labelPlatinum = new JLabel("Platinum : 1000 taka");
        labelPlatinum.setBounds(10, 90, 200, 30);
        panel.add(labelPlatinum);

        gold1 = new JButton("g1");
        gold2 = new JButton("g2");
        gold3 = new JButton("g3");
        gold4 = new JButton("g4");
        gold5 = new JButton("g5");


        silver1 = new JButton("s1");
        silver2 = new JButton("s2");
        silver3 = new JButton("s3");
        silver4 = new JButton("s4");
        silver5 = new JButton("s5");

        platinum1 = new JButton("p1");
        platinum2 = new JButton("p2");
        platinum3 = new JButton("p3");
        platinum4 = new JButton("p4");
        platinum5 = new JButton("p5");

        gold1.setBounds(160 + 20, 50, 30 + 20, 30);
        gold2.setBounds(200 + 30, 50, 30 + 20, 30);
        gold3.setBounds(240 + 40, 50, 30 + 20, 30);
        gold4.setBounds(280 + 50, 50, 30 + 20, 30);
        gold5.setBounds(320 + 60, 50, 30 + 20, 30);

        silver1.setBounds(160 + 20, 10, 30 + 20, 30);
        silver2.setBounds(200 + 30, 10, 30 + 20, 30);
        silver3.setBounds(240 + 40, 10, 30 + 20, 30);
        silver4.setBounds(280 + 50, 10, 30 + 20, 30);
        silver5.setBounds(320 + 60, 10, 30 + 20, 30);

        platinum1.setBounds(160 + 20, 90, 30 + 20, 30);
        platinum2.setBounds(200 + 30, 90, 30 + 20, 30);
        platinum3.setBounds(240 + 40, 90, 30 + 20, 30);
        platinum4.setBounds(280 + 50, 90, 30 + 20, 30);
        platinum5.setBounds(320 + 60, 90, 30 + 20, 30);

        gold1.addActionListener(this);
        gold2.addActionListener(this);
        gold3.addActionListener(this);
        gold4.addActionListener(this);
        gold5.addActionListener(this);
        silver1.addActionListener(this);
        silver2.addActionListener(this);
        silver3.addActionListener(this);
        silver4.addActionListener(this);
        silver5.addActionListener(this);
        platinum1.addActionListener(this);
        platinum2.addActionListener(this);
        platinum3.addActionListener(this);
        platinum4.addActionListener(this);
        platinum5.addActionListener(this);

        panel.add(gold1);
        panel.add(gold2);
        panel.add(gold3);
        panel.add(gold4);
        panel.add(gold5);

        panel.add(platinum1);
        panel.add(platinum2);
        panel.add(platinum3);
        panel.add(platinum4);
        panel.add(platinum5);

        panel.add(silver1);
        panel.add(silver2);
        panel.add(silver3);
        panel.add(silver4);
        panel.add(silver5);

        buttonlogout = new JButton("Log Out");
        buttonlogout.setBounds(30, 320, 100, 50);
        buttonlogout.addActionListener(this);
        //panel.add(buttonlogout);

        buttonBack = new JButton("Back");
        buttonBack.setBounds(30+50, 320, 100, 50);
        buttonBack.addActionListener(this);
        panel.add(buttonBack);

        buttonBook = new JButton("confirm");
        buttonBook.setBounds(30+50+200, 320, 100, 50);
        buttonBook.addActionListener(this);
        panel.add(buttonBook);
		

        this.mh = mh;
        MovieDate = mh.comboSelectDate.getSelectedItem().toString();
        MovieTime = mh.comboSelectTime.getSelectedItem().toString();
        this.movie = movie;
        this.User_Name = User_Name;
        checkSeat();
        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        String elementText = ae.getActionCommand();
        if (elementText.equals(buttonBook.getText())) {
            for (int i = 1; i <= 15; i++) {
                if (g1 == 1) {
                    gold1.setBackground(null);
                    gold1.setEnabled(false);
                    g1 = 0;
                    List.add(gold1.getText());
                } else if (g2 == 1) {
                    gold2.setBackground(null);
                    gold2.setEnabled(false);
                    g2 = 0;
                    List.add(gold2.getText());
                } else if (g3 == 1) {
                    gold3.setBackground(null);
                    gold3.setEnabled(false);
                    g3 = 0;
                    List.add(gold3.getText());
                } else if (g4 == 1) {
                    gold4.setBackground(null);
                    gold4.setEnabled(false);
                    g4 = 0;
                    List.add(gold4.getText());
                } else if (g5 == 1) {
                    gold5.setBackground(null);
                    gold5.setEnabled(false);
                    g5 = 0;
                    List.add(gold5.getText());
                } else if (s1 == 1) {
                    silver1.setBackground(null);
                    silver1.setEnabled(false);
                    s1 = 0;
                    List.add(silver1.getText());
                } else if (s2 == 1) {
                    silver2.setBackground(null);
                    silver2.setEnabled(false);
                    s2 = 0;
                    List.add(silver2.getText());
                } else if (s3 == 1) {
                    silver3.setBackground(null);
                    silver3.setEnabled(false);
                    s3 = 0;
                    List.add(silver3.getText());
                } else if (s4 == 1) {
                    silver4.setBackground(null);
                    silver4.setEnabled(false);
                    s4 = 0;
                    List.add(silver4.getText());
                } else if (s5 == 1) {
                    silver5.setBackground(null);
                    silver5.setEnabled(false);
                    s5 = 0;
                    List.add(silver5.getText());
                } else if (p1 == 1) {
                    platinum1.setBackground(null);
                    platinum1.setEnabled(false);
                    p1 = 0;
                    List.add(platinum1.getText());
                } else if (p2 == 1) {
                    platinum2.setBackground(null);
                    platinum2.setEnabled(false);
                    p2 = 0;
                    List.add(platinum2.getText());
                } else if (p3 == 1) {
                    platinum3.setBackground(null);
                    platinum3.setEnabled(false);
                    p3 = 0;
                    List.add(platinum3.getText());
                } else if (p4 == 1) {
                    platinum4.setBackground(null);
                    platinum4.setEnabled(false);
                    p4 = 0;
                    List.add(platinum4.getText());
                } else if (p5 == 1) {
                    platinum5.setBackground(null);
                    platinum5.setEnabled(false);
                    p5 = 0;
                    List.add(platinum5.getText());
                }
            }
            
			if(List.size()==0)
			{
				String ssss = "Please select seat to confirm";
				JOptionPane.showMessageDialog(this, ssss);
			}
			else if(List.size()>0)
			{
				int t = g + s + p;
				String k = "Total Cost : " + t + " taka";
				JOptionPane.showMessageDialog(this, k);
				System.out.println(List);
				booking();
			}
            
            
			
        } else if (elementText.equals(gold1.getText())) {
            if (i1 == 0) {
                gold1.setBackground(Color.GRAY);
                g1 = 1;
                g += 500;
                i1 = 1;
            } else if (i1 == 1) {
                gold1.setBackground(null);
                g1 = 0;
                g -= 500;
                i1 = 0;
            }
        } else if (elementText.equals(gold2.getText())) {
            if (i2 == 0) {
                gold2.setBackground(Color.GRAY);
                g2 = 1;
                g += 500;
                i2 = 1;
            } else if (i2 == 1) {
                gold2.setBackground(null);
                g2 = 0;
                g -= 500;
                i2 = 0;
            }
        } else if (elementText.equals(gold3.getText())) {
            if (i3 == 0) {
                gold3.setBackground(Color.GRAY);
                g3 = 1;
                g += 500;
                i3 = 1;
            } else if (i3 == 1) {
                gold3.setBackground(null);
                g3 = 0;
                g -= 500;
                i3 = 0;
            }
        } else if (elementText.equals(gold4.getText())) {
            if (i4 == 0) {
                gold4.setBackground(Color.GRAY);
                g4 = 1;
                g += 500;
                i4 = 1;
            } else if (i4 == 1) {
                gold4.setBackground(null);
                g4 = 0;
                g -= 500;
                i4 = 0;
            }
        } else if (elementText.equals(gold5.getText())) {
            if (i5 == 0) {
                gold5.setBackground(Color.GRAY);
                g5 = 1;
                g += 500;
                i5 = 1;
            } else if (i5 == 1) {
                gold5.setBackground(null);
                g5 = 0;
                g -= 500;
                i5 = 0;
            }
        } else if (elementText.equals(silver1.getText())) {
            if (i6 == 0) {
                silver1.setBackground(Color.GRAY);
                s1 = 1;
                s += 300;
                i6 = 1;
            } else if (i6 == 1) {
                silver1.setBackground(null);
                s1 = 0;
                s -= 300;
                i6 = 0;
            }
        } else if (elementText.equals(silver2.getText())) {
            if (i7 == 0) {
                silver2.setBackground(Color.GRAY);
                s2 = 1;
                s += 300;
                i7 = 1;
            } else if (i7 == 1) {
                silver2.setBackground(null);
                s2 = 0;
                s -= 300;
                i7 = 0;
            }
        } else if (elementText.equals(silver3.getText())) {
            if (i8 == 0) {
                silver3.setBackground(Color.GRAY);
                s3 = 1;
                s += 300;
                i8 = 1;
            } else if (i8 == 1) {
                silver3.setBackground(null);
                s3 = 0;
                s -= 300;
                i8 = 0;
            }
        } else if (elementText.equals(silver4.getText())) {
            if (i9 == 0) {
                silver4.setBackground(Color.GRAY);
                s4 = 1;
                s += 300;
                i9 = 1;
            } else if (i9 == 1) {
                silver4.setBackground(null);
                s4 = 0;
                s -= 300;
                i9 = 0;
            }
        } else if (elementText.equals(silver5.getText())) {
            if (i10 == 0) {
                silver5.setBackground(Color.GRAY);
                s5 = 1;
                s += 300;
                i10 = 1;
            } else if (i10 == 1) {
                silver5.setBackground(null);
                s5 = 0;
                s -= 300;
                i10 = 0;
            }
        } else if (elementText.equals(platinum1.getText())) {
            if (i11 == 0) {
                platinum1.setBackground(Color.GRAY);
                p1 = 1;
                p += 1000;
                i11 = 1;
            } else if (i11 == 1) {
                platinum1.setBackground(null);
                p1 = 0;
                p -= 1000;
                i11 = 0;
            }
        } else if (elementText.equals(platinum2.getText())) {
            if (i12 == 0) {
                platinum2.setBackground(Color.GRAY);
                p2 = 1;
                p += 1000;
                i12 = 1;
            } else if (i12 == 1) {
                platinum2.setBackground(null);
                p2 = 0;
                p -= 1000;
                i12 = 0;
            }
        } else if (elementText.equals(platinum3.getText())) {
            if (i13 == 0) {
                platinum3.setBackground(Color.GRAY);
                p3 = 1;
                p += 1000;
                i13 = 1;
            } else if (i13 == 1) {
                platinum3.setBackground(null);
                p3 = 0;
                p -= 1000;
                i13 = 0;
            }
        } else if (elementText.equals(platinum4.getText())) {
            if (i14 == 0) {
                platinum4.setBackground(Color.GRAY);
                p4 = 1;
                p += 1000;
                i14 = 1;
            } else if (i14 == 1) {
                platinum4.setBackground(null);
                p4 = 0;
                p -= 1000;
                i14 = 0;
            }
        } else if (elementText.equals(platinum5.getText())) 
		{
            if (i15 == 0) {
                platinum5.setBackground(Color.GRAY);
                p5 = 1;
                p += 1000;
                i15 = 1;
            } else if (i15 == 1) {
                platinum5.setBackground(null);
                p5 = 0;
                p -= 1000;
                i15 = 0;
            }
        } 
		else if (elementText.equals(buttonlogout.getText())) 
			{
				LoginUI lg = new LoginUI();
				lg.setVisible(true);
				this.setVisible(false);
			} 
		else if (elementText.equals(buttonBack.getText())) 
			{
				String query = "SELECT movie_name, director, imdb_rating, release_date, genre FROM movie_table where movie_name = '" + movie + "';";
				System.out.println(query);
	 
				try {
					Class.forName("com.mysql.jdbc.Driver");//load driver

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking", "root", "");
					Statement st = con.createStatement();//create statement
					ResultSet rs = st.executeQuery(query);//getting result
					System.out.println("test1");
					while (rs.next()) {
						System.out.println("test2");
						movie = rs.getString("movie_name");
						director = rs.getString("director");
						imdb = rs.getDouble("imdb_rating");
						date = rs.getString("release_date");
						genre = rs.getString("genre");
					  
					}

				} 
				catch (Exception e) {}

				MovieInfoTime ah = new MovieInfoTime(movie, director, imdb, date, genre, User_Name);
				ah.setVisible(true);
				this.setVisible(false);
			} 
		
    }

    public void booking() {
        Connection con = null; //DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
        Statement stm = null; //con.createStatement();
        String query = "SELECT id FROM user_table where username='" + User_Name + "'";
        System.out.println(query);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking", "root", "");
            stm = con.createStatement();
            stm.execute(query);
            ResultSet rs = stm.executeQuery(query);
            System.out.println("Success in booking");
            while (rs.next()) {
                System.out.println("test2");
                UserID = rs.getInt("id");
                System.out.println(UserID);
            }

            query = "SELECT id FROM movie_table where movie_name='" + movie + "'";
            stm.execute(query);
            rs = stm.executeQuery(query);
            System.out.println("Success for movie name");
            while (rs.next()) {
                System.out.println("test3");
                MovieID = rs.getInt("id");
                System.out.println(MovieID);
            }
            query = "SELECT id FROM show_time_table where show_time='" + MovieTime + "'";
            stm.execute(query);
            rs = stm.executeQuery(query);
            System.out.println("Success for Show time");
            while (rs.next()) {
                System.out.println("test4");
                ShowID = rs.getInt("id");
                System.out.println(ShowID);
            }


            query = "INSERT INTO book_table(user_id,movie_id,date,show_time_id) VALUES ('" + UserID + "','" + MovieID + "','" + MovieDate + "','" + ShowID + "');";
            stm.execute(query);
            System.out.println("Successfully inserted into Book table");


            query = "SELECT id FROM book_table where movie_id='" + MovieID + "' and user_id='" + UserID + "'";
            stm.execute(query);
            rs = stm.executeQuery(query);
            System.out.println("Success for Show time");
            while (rs.next()) {
                System.out.println("test4");
                BookID = rs.getInt("id");
                System.out.println(BookID);
            }

            itr = List.iterator();
            while (itr.hasNext()) {
                Object element = itr.next();
                System.out.println(element + "");
                query = "INSERT INTO book_seat(book_id,	seat_numbers) VALUES ('" + BookID + "','" + element + "');";
                stm.execute(query);
                System.out.println("Successfully inserted into Book Seat table");
            }

        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                System.out.println("Exception : " + ex.getMessage());
            }
        }
    }

    public void checkSeat() {
        Connection con = null; //DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking","root","");
        Statement stm = null; //con.createStatement();
        String query = "SELECT id FROM show_time_table where show_time='" + MovieTime + "'";
        //= "SELECT date, show_time_id FROM book_table where username='"+User_Name+"'";
        //System.out.println(query);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking", "root", "");
            stm = con.createStatement();
            stm.execute(query);
            ResultSet rs = stm.executeQuery(query);
            System.out.println("Success");

            while (rs.next()) {
                System.out.println("test4");
                checkShowID = rs.getInt("id");
                System.out.println(checkShowID);
            }

            query = "SELECT id FROM book_table where date='" + MovieDate + "' and show_time_id='" + checkShowID + "' and movie_id = (select id from movie_table where movie_name = '"+movie+"')";
            stm.execute(query);
            rs = stm.executeQuery(query);
            System.out.println("Success for last");

            while (rs.next()) {
                System.out.println("last test");
                checkBookID = rs.getInt("id");
                bookIdList.add(checkBookID);
                System.out.println(checkMovieDate + "" + checkShowID2);
            }

            for (int bookId : bookIdList) {
                query = "SELECT seat_numbers FROM book_seat where book_id='" + bookId + "'";
                stm.execute(query);
                ResultSet rs1 = stm.executeQuery(query);
                System.out.println("Success for last last");
                while (rs1.next()) {
                    System.out.println("last last last test");
                    SeatNumber = rs1.getString("seat_numbers");
                    //checkBookID = rs.getInt("id");
                    System.out.println(SeatNumber);
                    List2.add(SeatNumber);

                    if (SeatNumber.equals(gold1.getText())) {
                        gold1.setEnabled(false);
                    } else if (SeatNumber.equals(gold2.getText())) {
                        gold2.setEnabled(false);
                    } else if (SeatNumber.equals(gold3.getText())) {
                        gold3.setEnabled(false);
                    } else if (SeatNumber.equals(gold4.getText())) {
                        gold4.setEnabled(false);
                    } else if (SeatNumber.equals(gold5.getText())) {
                        gold5.setEnabled(false);
                    } else if (SeatNumber.equals(silver1.getText())) {
                        silver1.setEnabled(false);
                    } else if (SeatNumber.equals(silver2.getText())) {
                        silver2.setEnabled(false);
                    } else if (SeatNumber.equals(silver3.getText())) {
                        silver3.setEnabled(false);
                    } else if (SeatNumber.equals(silver4.getText())) {
                        silver4.setEnabled(false);
                    } else if (SeatNumber.equals(silver5.getText())) {
                        silver5.setEnabled(false);
                    } else if (SeatNumber.equals(platinum1.getText())) {
                        platinum1.setEnabled(false);
                    } else if (SeatNumber.equals(platinum2.getText())) {
                        platinum2.setEnabled(false);
                    } else if (SeatNumber.equals(platinum3.getText())) {
                        platinum3.setEnabled(false);
                    } else if (SeatNumber.equals(platinum4.getText())) {
                        platinum4.setEnabled(false);
                    } else if (SeatNumber.equals(platinum5.getText())) {
                        platinum5.setEnabled(false);
                    }
                }

            }
                
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                System.out.println("Exception : " + ex.getMessage());
            }
        }
    }

}