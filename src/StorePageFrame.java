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

public class StorePageFrame extends JFrame {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 500;

	private ImageIcon shellIcon;

	private JLabel Plantname;
	private JLabel Plantp;
	private ImageIcon Plant;
	private JButton Plantprice;

	private JLabel Crabname;
	private JLabel Crabp;
	private ImageIcon Crab;
	private JButton Crabprice;

	private JLabel Feedname;
	private JLabel Feedp;
	private ImageIcon Feed;
	private JButton Feedprice;

	private JLabel totalMoneyIcon;
	private JLabel totalMoney;

	private String id;

	public StorePageFrame() throws SQLException {
		shellIcon = new ImageIcon("shell.jpg");
		Plantname = new JLabel("AquaticPlants");
		Plant = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/plant.jpg");
		Plant.setImage(Plant.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		Plantp = new JLabel(Plant);
		Plantprice = new JButton("1200", shellIcon);
		Plantprice.setOpaque(true);
		Plantprice.setBackground(new Color(255, 250, 250));
		Plantprice.setSize(Plantprice.getPreferredSize());

		Feedname = new JLabel("Feed");
		Feed = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/feedo.jpg");
		Feed.setImage(Feed.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		Feedp = new JLabel(Feed);
		Feedprice = new JButton("80", shellIcon);
		Feedprice.setOpaque(true);
		Feedprice.setBackground(new Color(255, 250, 250));
		Feedprice.setSize(Feedprice.getPreferredSize());

		Crabname = new JLabel("Crab");
		Crab = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/crab.jpg");
		Crab.setImage(Crab.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		Crabp = new JLabel(Crab);
		Crabprice = new JButton("2500", shellIcon);
		Crabprice.setOpaque(true);
		Crabprice.setBackground(new Color(255, 250, 250));
		Crabprice.setSize(Crabprice.getPreferredSize());

		totalMoneyIcon = new JLabel(shellIcon);
		totalMoney = new JLabel("" + getShell());
		totalMoney.setSize(totalMoney.getPreferredSize());

		JPanel exitPanel = new JPanel();
		JPanel mainPanel = new JPanel();

		JPanel DecoPanel = new JPanel();
		DecoPanel.setOpaque(false);
		JPanel FeedPanel = new JPanel();
		FeedPanel.setOpaque(false);

		mainPanel.setBackground(new Color(173, 216, 230));
		exitPanel.setBackground(new Color(255, 250, 250));

		add(exitPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		setLocation(800, 0);

		mainPanel.add(DecoPanel);
		mainPanel.add(FeedPanel);
		mainPanel.setLayout(new GridLayout(2, 1));

		DecoPanel.add(createPlant());
		DecoPanel.add(createCrab());

		FeedPanel.add(createFeed());

		exitPanel.add(totalMoneyIcon);
		exitPanel.add(totalMoney);

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setMenubar();
	}

	public StorePageFrame(String id) throws SQLException {
		this.id = id;

		shellIcon = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/shell.jpg");
		Plantname = new JLabel("AquaticPlants");
		Plant = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/plant.jpg");
		Plant.setImage(Plant.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		Plantp = new JLabel(Plant);
		Plantprice = new JButton("1200", shellIcon);
		Plantprice.setOpaque(true);
		Plantprice.setBackground(new Color(255, 250, 250));
		Plantprice.setSize(Plantprice.getPreferredSize());

		Feedname = new JLabel("Feed");
		Feed = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/feedo.jpg");
		Feed.setImage(Feed.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		Feedp = new JLabel(Feed);
		Feedprice = new JButton("80", shellIcon);
		Feedprice.setOpaque(true);
		Feedprice.setBackground(new Color(255, 250, 250));
		Feedprice.setSize(Feedprice.getPreferredSize());

		Crabname = new JLabel("Crab");
		Crab = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/crab.jpg");
		Crab.setImage(Crab.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		Crabp = new JLabel(Crab);
		Crabprice = new JButton("2500", shellIcon);
		Crabprice.setOpaque(true);
		Crabprice.setBackground(new Color(255, 250, 250));
		Crabprice.setSize(Crabprice.getPreferredSize());

		totalMoneyIcon = new JLabel(shellIcon);
		totalMoney = new JLabel("" + getShell());
		totalMoney.setSize(totalMoney.getPreferredSize());

		JPanel exitPanel = new JPanel();
		JPanel mainPanel = new JPanel();

		JPanel DecoPanel = new JPanel();
		DecoPanel.setOpaque(false);
		JPanel FeedPanel = new JPanel();
		FeedPanel.setOpaque(false);

		mainPanel.setBackground(new Color(173, 216, 230));
		exitPanel.setBackground(new Color(255, 250, 250));

		add(exitPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		setLocation(800, 0);

		mainPanel.add(DecoPanel);
		mainPanel.add(FeedPanel);
		mainPanel.setLayout(new GridLayout(2, 1));

		DecoPanel.add(createPlant());
		DecoPanel.add(createCrab());

		FeedPanel.add(createFeed());

		exitPanel.add(totalMoneyIcon);
		exitPanel.add(totalMoney);

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setMenubar();
	}

	public int getShell() throws SQLException {
		return findPerson(id).getShell();
	}

	public JPanel createPlant() {
		class PlantpriceButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Connection conn = null;

				try {
					if (findPerson(id).getShell() >= 1200) {
						findPerson(id).addShell(-1200);
						conn = SimpleDataSource.getConnection();
						PreparedStatement stat1 = conn.prepareStatement("SELECT * FROM Product WHERE ID = ?");
						stat1.setString(1, id);
						ResultSet result = stat1.executeQuery();

						if (result.next()) {
							if (result.getInt("Plant") < 1) {
								totalMoney.setText("" + findPerson(id).getShell());
								PreparedStatement stat2 = conn
										.prepareStatement("UPDATE Product SET Plant = Plant + 1 WHERE ID = ?");
								stat2.setString(1, id);
								stat2.executeUpdate();
								conn.close();
							}
						}
					}

					else {
						JOptionPane.showMessageDialog(new JFrame(),
								"You don't have enough shells.\nPlease drink more water :)");
					}
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		ActionListener listener1 = new PlantpriceButtonListener();
		Plantprice.addActionListener(listener1);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.setOpaque(false);

		JPanel jp1 = new JPanel();
		jp1.setOpaque(false);
		Plantp.setSize(30, 30);

		panel.add(Plantname);

		panel.add(Plantp);

		panel.add(jp1);
		jp1.add(Plantprice);

		return panel;

	}

	public JPanel createCrab() {
		class CrabpriceButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Connection conn = null;

				try {
					if (findPerson(id).getShell() >= 2500) {
						findPerson(id).addShell(-2500);
						conn = SimpleDataSource.getConnection();
						PreparedStatement stat1 = conn.prepareStatement("SELECT * FROM Product WHERE ID = ?");
						stat1.setString(1, id);

						ResultSet result = stat1.executeQuery();
						if (result.next()) {
							if (result.getInt("Crab") < 1) {
								totalMoney.setText("" + findPerson(id).getShell());
								PreparedStatement stat2 = conn
										.prepareStatement("UPDATE Product SET Crab = Crab + 1 WHERE ID = ?");
								stat2.setString(1, id);
								stat2.executeUpdate();
								conn.close();
							}
						}
					}

					else {
						JOptionPane.showMessageDialog(new JFrame(),
								"You don't have enough shells.\nPlease drink more water :)");
					}
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		ActionListener listener1 = new CrabpriceButtonListener();
		Crabprice.addActionListener(listener1);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.setOpaque(false);

		JPanel jp1 = new JPanel();
		jp1.setOpaque(false);
		Crabp.setSize(30, 30);

		panel.add(Crabname);

		panel.add(Crabp);

		panel.add(jp1);
		jp1.add(Crabprice);

		return panel;

	}

	public JPanel createFeed() {
		class FeedpriceButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Connection conn = null;

				try {

					if (findPerson(id).getShell() >= 80) {
						findPerson(id).addShell(-80);
						totalMoney.setText("" + findPerson(id).getShell());
						conn = SimpleDataSource.getConnection();
						PreparedStatement stat = conn
								.prepareStatement("UPDATE UserInfo SET Feed = Feed + 80 WHERE ID = ?");
						stat.setString(1, id);
						stat.executeUpdate();
						conn.close();
					}

					else {
						JOptionPane.showMessageDialog(new JFrame(),
								"You don't have enough shells.\nPlease drink more water :)");
					}
				}

				catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		ActionListener listener1 = new FeedpriceButtonListener();
		Feedprice.addActionListener(listener1);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.setOpaque(false);

		JPanel jp1 = new JPanel();
		jp1.setOpaque(false);
		Feedp.setSize(30, 30);

		panel.add(Feedname);
		panel.add(Feedp);
		panel.add(jp1);

		jp1.add(Feedprice);

		return panel;
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

	public void setMenubar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("MENU");
		JMenuItem exitItem = new JMenuItem("Exit");

		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				try {
					AquariumFrame frame = new AquariumFrame(id);
					frame.setVisible(true);
					frame.setLocation(400, 0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		}

		ActionListener listener = new ExitListener();
		exitItem.addActionListener(listener);

		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(exitItem);
	}
}