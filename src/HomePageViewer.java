
import java.sql.SQLException;
import javax.swing.JFrame;

public class HomePageViewer {
	public static void main(String[] args) throws SQLException
	{
		// TODO Auto-generated method stub
		JFrame frame=new HomePageFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Home Page");
		frame.setVisible(true);
	}
}
