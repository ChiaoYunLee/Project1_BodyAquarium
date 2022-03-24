import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HistoryFrame extends JFrame
{
	private HomePageFrame home;
	private String id;
	private JTextArea area;
	private JPanel panel;
	final static int FRAME_WIDTH=250;
	final static int FRAME_HEIGHT=600;
	public HistoryFrame()
	{
	}
	public HistoryFrame(String id)throws SQLException
	{
		this.id=id;
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		panel=new JPanel();
		area= new JTextArea();
		area.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		area.setEditable(false);
		area.setLineWrap(true);
		createPanel();
	}
	
	public void createPanel()throws SQLException
	{
		
		Connection conn=null;
		try
		{
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn.prepareStatement("SELECT * FROM History WHERE ID=?");
			stat.setString(1, id);
			ResultSet r = stat.executeQuery();
			if(r.next())
			{
				Date date=r.getDate("Date");
				int record=r.getInt("DailyRecord");
				area.append("  "+record+ "ml  /  "+date+"\n");
				while(r.next())
				{
					date=r.getDate("Date");
					record=r.getInt("DailyRecord");
					area.append("  "+record+ "ml  /  "+date+"\n");
				}
			}
			panel.add(area);
			panel.setBackground(Color.white);
			add(panel);
		}
		finally
		{
			conn.close();
		}
	}
}
