import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AquariumFrame extends JFrame {
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 660;

	private JButton storeBtn;
	private JLabel shellLabel;
	private JLabel feedLabel;
	private JPanel mainPanel;
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JPanel bgPanel;
	private String id;

	public AquariumFrame() throws SQLException {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Aquarium");

		createStoreBtn();
		createLabel();
		setBackground();
		updateBackground();
		createPanel();
		createMenubar();
	}

	public AquariumFrame(String id) throws SQLException {
		this.id = id;
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Aquarium");

		createStoreBtn();
		createLabel();
		setBackground();
		updateBackground();
		createPanel();
		createMenubar();
	}

	public void createStoreBtn() {
		ImageIcon icon = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/store2.png");
		icon.setImage(icon.getImage().getScaledInstance(70, 80, Image.SCALE_DEFAULT));
		storeBtn = new JButton(icon);
		storeBtn.setBackground(new Color(255, 255, 240));

		class StoreListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				JFrame frame;
				try {
					frame = new StorePageFrame(id);
					frame.setTitle("Store");
					frame.setVisible(true);
					frame.setLocation(400, 0);
					dispose();
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		ActionListener listener = new StoreListener();
		storeBtn.addActionListener(listener);
		storeBtn.setSize(30, 30);
	}

	public void createLabel() {
		shellLabel = new JLabel();
		shellLabel.setText("Shell: " + 0);
		feedLabel = new JLabel();
		feedLabel.setText("Feed: " + 0);

		try {
			Connection conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn.prepareStatement("SELECT Shells, Feed FROM UserInfo WHERE UserInfo.ID = ?");
			stat.setString(1, id);

			if (findPerson(id) != null) {
				ResultSet result = stat.executeQuery();
				if (result.next()) {
					shellLabel.setText("Shell: " + result.getInt("Shells"));
					feedLabel.setText("Feed: " + result.getInt("Feed"));
				}
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createPanel() {
		mainPanel = new JPanel();
		labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2, 1));
		labelPanel.add(shellLabel);
		labelPanel.add(feedLabel);
		labelPanel.setBackground(new Color(255, 255, 240));
		buttonPanel = new JPanel();
		buttonPanel.add(storeBtn);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(buttonPanel, BorderLayout.WEST);

		setLayout(new BorderLayout());
		add(labelPanel, BorderLayout.NORTH);
		add(bgPanel);
		add(mainPanel);
	}

	public void setBackground() {
		ImageIcon icon = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/aquarium.jpg");
		icon.setImage(icon.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		bgPanel = new ImagePanel(icon.getImage());
		bgPanel.setSize(800, 600);
	}

	public void updateBackground() throws SQLException {
		Connection conn = null;

		try {
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat1 = conn.prepareStatement(
					"SELECT UserInfo.Fish, Product.Plant, Product.Crab FROM UserInfo, Product WHERE UserInfo.ID = ? AND UserInfo.ID = Product.ID");
			stat1.setString(1, id);
			ResultSet result1 = stat1.executeQuery();

			if (result1.next()) {
				PreparedStatement stat2 = conn.prepareStatement(
						"SELECT Pic_path.Path FROM Pic_path WHERE Fish = ? AND Seaweed = ? AND Crab = ?");
				stat2.setInt(1, result1.getInt("Fish"));
				stat2.setInt(2, result1.getInt("Plant"));
				stat2.setInt(3, result1.getInt("Crab"));

				ResultSet result2 = stat2.executeQuery();
				if (result2.next()) {
					String path = result2.getString("Path");

					ImageIcon icon = new ImageIcon(
							"/Users/irislee/eclipse-workspace2/BodyAquarium/images/" + path);
					icon.setImage(icon.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
					bgPanel = new ImagePanel(icon.getImage());
				}
			}
		}

		finally {
			conn.close();
		}
	}

	public void createMenubar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 250, 250));
		JMenu menu = new JMenu("Menu");
		// JMenuItem refreshItem = new JMenuItem("Refresh");
		JMenuItem returnItem = new JMenuItem("Go Back→");
		JMenuItem feedItem = new JMenuItem("Feed");
		/*
		 * class RefreshListener implements ActionListener { public void
		 * actionPerformed(ActionEvent event) {
		 * 
		 * try { updateBackground(); createLabel(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } remove(labelPanel);
		 * remove(bgPanel); remove(mainPanel); add(labelPanel, BorderLayout.NORTH);
		 * add(bgPanel); add(mainPanel);
		 * 
		 * } }
		 */
		class ReturnListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		}

		class FeedListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Connection conn = null;

				try {
					conn = SimpleDataSource.getConnection();
					PreparedStatement stat1 = conn.prepareStatement("SELECT Feed FROM UserInfo WHERE ID = ?");
					stat1.setString(1, id);
					ResultSet result1 = stat1.executeQuery();

					if (result1.next()) {
						if (result1.getInt("Feed") >= 40) {
							PreparedStatement stat2 = conn
									.prepareStatement("UPDATE UserInfo SET Feed = Feed - 40 WHERE ID = ?");
							stat2.setString(1, id);
							stat2.executeUpdate();

							PreparedStatement stat3 = conn.prepareStatement("SELECT * FROM UserInfo WHERE ID = ?");
							stat3.setString(1, id);
							ResultSet result2 = stat3.executeQuery();
							if (result2.next()) {
								feedLabel.setText("Feed: " + result2.getInt("Feed"));

								ImageIcon icon = new ImageIcon(
										"/Users/irislee/eclipse-workspace2/BodyAquarium/images/happyfish.jpg");
								icon.setImage(icon.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
								JLabel label = new JLabel(icon);
								JFrame frame = new JFrame();
								frame.add(label);
								frame.setSize(400, 400);
								frame.setVisible(true);
								frame.setTitle("YEAH");

								conn.close();
							}

						} else {
							ImageIcon icon2 = new ImageIcon(
									"/Users/irislee/eclipse-workspace2/BodyAquarium/images/qqfish.jpg");
							icon2.setImage(icon2.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
							JLabel label2 = new JLabel(icon2);
							JFrame frame2 = new JFrame();
							frame2.add(label2);
							frame2.setSize(400, 400);
							frame2.setVisible(true);
							frame2.setTitle("Uh oh :(");

							conn.close();
						}

					}
				}

				catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "Error");
				}
			}
		}

		// ActionListener listener1 = new RefreshListener();
		ActionListener listener2 = new ReturnListener();
		ActionListener listener3 = new FeedListener();
		// refreshItem.addActionListener(listener1);
		returnItem.addActionListener(listener2);
		feedItem.addActionListener(listener3);

		setJMenuBar(menuBar);
		menuBar.add(menu);
		// menu.add(refreshItem);
		menu.add(returnItem);
		menu.add(feedItem);
	}

	public Person findPerson(String id) throws SQLException {
		Connection conn = null;

		try {
			conn = SimpleDataSource.getConnection();
			PreparedStatement stat = conn.prepareStatement("SELECT * FROM UserInfo WHERE ID=?");
			stat.setString(1, id);
			ResultSet r = stat.executeQuery();

			if (r.next()) {
				int current = r.getInt("current");
				int lv = r.getInt("Level");
				int shell = r.getInt("Shells");
				int feed = r.getInt("Feed");
				int xp = r.getInt("Experience");
				int ts = r.getInt("TimeStamp");
				Person p = new Person(id, current, lv, shell, feed, xp, ts);
				return p;
			}
			return null;
		}

		finally {
			conn.close();
		}
	}
}
