import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Register {
	private String id;
	public void addPerson(String id)throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		try
		{	
			PreparedStatement stat =conn.prepareStatement("SELECT * FROM UserInfo WHERE ID=?");
			stat.setString(1, id);
			ResultSet r=stat.executeQuery();
			if(!r.next())
			{
				PreparedStatement stat2 = conn.prepareStatement("INSERT INTO UserInfo VALUES (?,0,0,0,0,0,0,0)");
				stat2.setString(1, id);
				stat2.executeUpdate();
			}
		}
		finally {
			conn.close();
		}
	}
	public Person findPerson(String id) throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		try
		{
			PreparedStatement stat=conn.prepareStatement("SELECT * FROM UserInfo WHERE ID=?");
			stat.setString(1, id);
			ResultSet r=stat.executeQuery();
			if(r.next())
			{
				int current=r.getInt("current");
				int lv=r.getInt("Level");
				int shell=r.getInt("Shells");
				int feed=r.getInt("Feed");
				int xp=r.getInt("Experience");
				int ts=r.getInt("TimeStamp");
				Person p=new Person(id,current,lv,shell,feed,xp,ts);
				return p;
			}
			return null;
		}
		finally {
			conn.close();
		}
	}
	public void addProduct(String id)throws SQLException
	{
		Connection conn = SimpleDataSource.getConnection();
		try
		{	
			PreparedStatement stat =conn.prepareStatement("SELECT * FROM Product WHERE ID=?");
			stat.setString(1, id);
			ResultSet r=stat.executeQuery();
			if(!r.next())
			{
				PreparedStatement stat2 = conn.prepareStatement("INSERT INTO Product VALUES (?,0,0,0)");
				stat2.setString(1, id);
				stat2.executeUpdate();
			}
		}
		finally {
			conn.close();
		}
	}
}
