
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Person {
	private final static int DAILYGOAL = 2000;
	private int current, level, shell, feed, experience, percentage, timeStamp;
	private String userID;

	public Person() {

	}

	public Person(String id, int current, int lv, int shell, int feed, int ex, int timeStamp) {

		this.userID = id;
		this.current = current;
		this.level = lv;
		this.shell = shell;
		this.feed = feed;
		this.experience = ex;
		this.timeStamp = timeStamp;
	}
	public String getUserID() 
	{
		return userID;
	}
	public int getDailyGoal() 
	{
		return DAILYGOAL;
	}
	public int getCurrent() 
	{
		return current;
	}
	public int getShell() 
	{
		return shell;
	}
	public int getExperience() 
	{
		return experience;
	}
	public int getLevel() 
	{
		return level;
	}
	public int getPercentage() 
	{
		return current*100/DAILYGOAL;
	}
	public int getTimeStamp() 
	{
		return timeStamp;
	}
	public void setCurrent(int num)throws SQLException
	{
		current=num;
	}
	public void updateLevel() throws SQLException {
		Connection conn = null;

		try {
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn.prepareStatement("UPDATE FROM UserInfo SET Level = Level + 1 WHERE ID = ?");
			stat.setString(1, userID);
			stat.executeUpdate();
		}

		finally {
			conn.close();
		}
	}

	

	public void addExperience(int e) throws SQLException {
		Connection conn = null;

		try {
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn
					.prepareStatement("UPDATE UserInfo SET Experience = Experience + ? WHERE ID = ?");
			stat.setInt(1, e);
			stat.setString(2, userID);
			stat.executeUpdate();

			experience += e;

			if (experience >= 15) {
				PreparedStatement stat1 = conn.prepareStatement("UPDATE UserInfo SET Level = 5 WHERE ID = ?");
				stat1.setString(1, userID);
				stat1.executeUpdate();
			} else if (experience >= 10) {
				PreparedStatement stat2 = conn.prepareStatement("UPDATE UserInfo SET Level = 4 WHERE ID = ?");
				stat2.setString(1, userID);
				stat2.executeUpdate();
			} else if (experience >= 6) {
				PreparedStatement stat3 = conn.prepareStatement("UPDATE UserInfo SET Level = 3 WHERE ID = ?");
				stat3.setString(1, userID);
				stat3.executeUpdate();
			} else if (experience >= 3) {
				PreparedStatement stat4 = conn.prepareStatement("UPDATE UserInfo SET Level = 2 WHERE ID = ?");
				stat4.setString(1, userID);
				stat4.executeUpdate();
			} else if (experience >= 1) {
				PreparedStatement stat5 = conn.prepareStatement("UPDATE UserInfo SET Level = 1 WHERE ID = ?");
				stat5.setString(1, userID);
				stat5.executeUpdate();
			} else if (experience == 0) {
				PreparedStatement stat6 = conn.prepareStatement("UPDATE UserInfo SET Level = 0 WHERE ID = ?");
				stat6.setString(1, userID);
				stat6.executeUpdate();
			}

		}

		finally {
			conn.close();
		}
	}

	public void addWater(int water) throws SQLException {
		try 
		{
			Connection conn = SimpleDataSource.getConnection();
			PreparedStatement stat= conn.prepareStatement("SELECT * FROM UserInfo WHERE ID=? ");
			stat.setString(1,userID);
			ResultSet r=stat.executeQuery();
			if(r.next())
			{
				PreparedStatement stat2 = conn.prepareStatement("UPDATE UserInfo SET current= current + ? WHERE ID = ?");
				stat2.setInt(1, water);
				stat2.setString(2, userID);
				stat2.executeUpdate();
			}
			current+=water;
		}

		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JFrame(),"add water");
		}
	}

	

	public void updatePercentage() throws SQLException {
		Connection conn = null;

		try 
		{
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn
					.prepareStatement("UPDATE FROM DailyRecord SET TargetPercent =  ? WHERE ID = ?");
			stat.setInt(1, (int) current / DAILYGOAL);
			stat.setString(2, userID);
			stat.executeUpdate();
		}

		finally {
			conn.close();
		}
	}


	public void addShell(int shell) throws SQLException {
		Connection conn = null;

		try 
		{
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat= conn.prepareStatement("SELECT * FROM UserInfo WHERE ID=? ");
			stat.setString(1,userID);
			ResultSet r=stat.executeQuery();
			if(r.next())
			{
				PreparedStatement stat2 = conn.prepareStatement("UPDATE UserInfo SET Shells = Shells + ? WHERE ID = ?");
				stat2.setInt(1, shell);
				stat2.setString(2, userID);
				stat2.executeUpdate();
			}
			this.shell+=shell;
		}

		finally {
			conn.close();
		}
	}

	

	public void addCount(int c) throws SQLException 
	{
		Connection conn = null;

		try 
		{
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn
					.prepareStatement("UPDATE FROM DailyRecord SET Login = Login + ? WHERE ID = ?");
			stat.setInt(1, c);
			stat.setString(2, userID);
			stat.executeUpdate();
		}

		finally {
			conn.close();
		}
	}

	public void setTS(int ts) throws SQLException
	{
		Connection conn = null;

		try 
		{
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn
					.prepareStatement("UPDATE FROM UserInfo SET TimeStamp=? WHERE ID = ?");
			stat.setInt(1, ts);
			stat.setString(2, userID);
			stat.executeUpdate();
		}

		finally {
			conn.close();
		}
	}

	public void updateCount() throws SQLException {
		Connection conn = null;

		try
		{
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn
					.prepareStatement("UPDATE FROM DailyRecord SET Login = Login + 1 WHERE ID = ?");
			stat.setString(1, userID);
			stat.executeUpdate();
		}

		finally {
			conn.close();
		}
	}

	

	
}
