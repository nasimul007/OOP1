import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.util.List;

public class MovieInfoTime extends JFrame implements ActionListener {
    private JLabel labelMovieName, labelDirector, labelImdb, labelGenre, labelReleaseDate;
    private JLabel labelSelectDate, labelSelectTime;
    JComboBox comboSelectDate, comboSelectTime;
    private JLabel showMovieName, showDirector, showImdb, showGenre, showReleaseDate;
    private JPanel panel;
    private JButton buttonNext, buttonBack;
    String User_Name, movie;

    MovieInfoTime() {
    }

    public MovieInfoTime(String movie, String director, double imdb,String releasedate, String genre, String User_Name) {

        super("Movie Info and Date-Time Selection");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.User_Name = User_Name;
        this.movie = movie;
        panel = new JPanel();
        panel.setLayout(null);

        labelMovieName = new JLabel("Movie Name : ");
        labelDirector = new JLabel("Director : ");
        labelImdb = new JLabel("Imdb : ");
        labelGenre = new JLabel("Genre : ");
        labelReleaseDate = new JLabel("Released Date : ");
        labelSelectDate = new JLabel("Select Date : ");
        labelSelectTime = new JLabel("Select Time : ");

        labelMovieName.setBounds(10, 55, 100, 30);
        labelDirector.setBounds(10, 100, 100, 30);
        labelReleaseDate.setBounds(10,235,100,30);
        labelImdb.setBounds(10, 145, 100, 30);
        labelGenre.setBounds(10, 190, 100, 30);
        labelSelectDate.setBounds(10, 300, 100, 30);
        labelSelectTime.setBounds(10, 350, 100, 30);

        panel.add(labelDirector);
        panel.add(labelGenre);
        panel.add(labelImdb);
        panel.add(labelMovieName);
        panel.add(labelReleaseDate);
        panel.add(labelSelectDate);
        panel.add(labelSelectTime);

        showMovieName = new JLabel("" + movie);
        showDirector = new JLabel("" + director);
        showImdb = new JLabel("" + imdb);
        showGenre = new JLabel("" + genre);
        showReleaseDate = new JLabel(""+releasedate);

        showMovieName.setBounds(150, 55, 250, 30);
        showDirector.setBounds(150, 100, 250, 30);
        showImdb.setBounds(150, 145, 250, 30);
        showGenre.setBounds(150, 190, 250, 30);
        showReleaseDate.setBounds(150, 235, 250, 30);

        panel.add(showMovieName);
        panel.add(showDirector);
        panel.add(showImdb);
		panel.add(showReleaseDate);
        panel.add(showGenre);
        //panel.add();
        //String date[] = {"2017-12-28","2017-12-29","2017-12-30","2017-12-31","2018-01-01"};
        String date[] = getMovieDateList(movie);
        comboSelectDate = new JComboBox(date);
        comboSelectDate.setBounds(150, 300, 100, 30);
        panel.add(comboSelectDate);

        String time[] = {"11.00-02.00", "02.00-05.00", "05.00-08.00"};
        comboSelectTime = new JComboBox(time);
        comboSelectTime.setBounds(150, 350, 200, 30);
        panel.add(comboSelectTime);

        buttonBack = new JButton("Back");
        buttonBack.setBounds(10, 400, 100, 50);
        buttonBack.addActionListener(this);
        panel.add(buttonBack);

        buttonNext = new JButton("NEXT");
        buttonNext.setBounds(150, 400, 100, 50);
        buttonNext.addActionListener(this);
        panel.add(buttonNext);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        String elementText = ae.getActionCommand();
        if (elementText.equals(buttonNext.getText())) {
            SeatBooking sb = new SeatBooking(User_Name, movie, this);
            sb.setVisible(true);
            this.setVisible(false);
        }

		/*else if(elementText.equals(buttonChangePassword.getText()))
		{
			ChangePassword cp = new ChangePassword();
			cp.setVisible(true);
			this.setVisible(false);
		}*/
        else if (elementText.equals(buttonBack.getText())) {
            BookPage lg = new BookPage(User_Name);
            lg.setVisible(true);
            this.setVisible(false);
        }
    }

    private String[] getMovieDateList(String movie) {
        String toDate = "";
        List<String> dateList = new ArrayList<String>();
        String query = "SELECT mh.to_date FROM movie_table mt inner JOIN movie_hall mh ON mt.id = mh.movie_id WHERE mt.movie_name = '" + movie + "'";
        System.out.println(query);
        //Connection con=null;//for connection
        //Statement st = null;//for query execution
        //ResultSet rs = null;//to get row by row result from DB
        try {
            System.out.println("test1");
            Class.forName("com.mysql.jdbc.Driver");//load driver
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_booking", "root", "");
            Statement st = con.createStatement();//create statement
            ResultSet rs = st.executeQuery(query);//getting result
            //System.out.println(""+id);
            System.out.println("test2");

            while (rs.next()) {
                System.out.println("test3");
                toDate = rs.getString("to_date");
                System.out.println(toDate);

                //System.out.println(movie+director+imdb+genre);
            }

        } catch (Exception e) {
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date smallDate;
            Date today = new Date();
            today = dateFormat.parse(dateFormat.format(today));
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(today);
            cal.add(Calendar.DATE, 6);

            Date afterFive = cal.getTime();
            Date toDateMovie = dateFormat.parse(toDate);

            if (afterFive.compareTo(toDateMovie) > 0) {
                smallDate = toDateMovie;
            } else {

                smallDate = afterFive;
            }


            while (today.compareTo(smallDate) <= 0) {
                dateList.add(dateFormat.format(today));
                cal.setTime(today);
                cal.add(Calendar.DATE, 1);
                today = cal.getTime();
            }

            System.out.println(dateList.size());
        } catch (Exception e) {
            System.out.println("KHAISE");
        }

        String[] array = dateList.toArray(new String[dateList.size()]);
        return array;
    }
}