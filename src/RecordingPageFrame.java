import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RecordingPageFrame extends JFrame {
	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 550;
	private JButton bubble240, bubble360, bubble480, bubble600;
	private JLabel lvLabel, shellLabel, nextLabel;
	private JLabel lv, shell, next;
	private JLabel dailyCurrentLabel, mlLabel, percentLabel;
	private JLabel current, percent;
	private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10, panel11;
	private JButton fish, personBtn, historyBtn, refreshBtn;
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	private String id;

	public RecordingPageFrame() throws SQLException {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		lvLabel = new JLabel("lv.");
		shellLabel = new JLabel("shell:");
		nextLabel = new JLabel("To next level: ");
		lv = new JLabel("0");
		shell = new JLabel("0");
		next = new JLabel();
		percentLabel = new JLabel("%");
		dailyCurrentLabel = new JLabel("Current:");
		mlLabel = new JLabel("ml");
		ImageIcon fishImg = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/newfish.png");
		fishImg.setImage(fishImg.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
		fish = new JButton(fishImg);
		createHistoryBtn();
		createRefreshBtn();
		createPanel();
	}

	public RecordingPageFrame(String id) throws SQLException {

		this.id = id;
		findPerson(id);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		lvLabel = new JLabel("lv.");
		shellLabel = new JLabel("shell:");
		nextLabel = new JLabel("To next level: ");
		lv = new JLabel();
		shell = new JLabel();
		next = new JLabel();
		percentLabel = new JLabel("%");
		dailyCurrentLabel = new JLabel("Current:");
		mlLabel = new JLabel("ml");
		ImageIcon fishImg = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/newfish.png");
		fishImg.setImage(fishImg.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
		fish = new JButton(fishImg);
		createRecordPart();
		createHistoryBtn();
		createRefreshBtn();
		createPanel();
		update();

	}

	public Person findPerson(String id) throws SQLException {
		Connection conn = SimpleDataSource.getConnection();
		try {
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
		} finally {
			conn.close();
		}
	}

	public void createRecordPart() throws SQLException {
		ImageIcon icon240 = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/240ml4.png");
		icon240.setImage(icon240.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ImageIcon icon360 = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/360ml2.png");
		icon360.setImage(icon360.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ImageIcon icon480 = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/480ml2.png");
		icon480.setImage(icon480.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ImageIcon icon600 = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/600ml2.png");
		icon600.setImage(icon600.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		bubble240 = new JButton(icon240);
		bubble360 = new JButton(icon360);
		bubble480 = new JButton(icon480);
		bubble600 = new JButton(icon600);
		current = new JLabel();
		percent = new JLabel();
		class bubbleListener240 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				try {
					if (findPerson(id).getCurrent() < 2000) {

						if (findPerson(id).getCurrent() + 240 <= 2000) {
							findPerson(id).addShell(120);
							shell.setText(findPerson(id).getShell() + "");

						} else if (findPerson(id).getCurrent() + 240 > 2000) {
							findPerson(id).addShell((2000 - findPerson(id).getCurrent()) / 2);
							shell.setText(findPerson(id).getShell() + "");
						}

					}
					findPerson(id).addWater(240);
					current.setText(findPerson(id).getCurrent() + "");
					percent.setText(findPerson(id).getPercentage() + "");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "add water");
				}
			}
		}
		ActionListener listener = new bubbleListener240();
		bubble240.addActionListener(listener);
		class bubbleListener360 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				try {
					if (findPerson(id).getCurrent() < 2000) {

						if (findPerson(id).getCurrent() + 360 <= 2000) {
							findPerson(id).addShell(180);
							shell.setText(findPerson(id).getShell() + "");
						} else if (findPerson(id).getCurrent() + 360 > 2000) {
							findPerson(id).addShell((2000 - findPerson(id).getCurrent()) / 2);
							shell.setText(findPerson(id).getShell() + "");
						}
					}
					findPerson(id).addWater(360);
					current.setText(findPerson(id).getCurrent() + "");
					percent.setText(findPerson(id).getPercentage() + "");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "add water");
				}
			}
		}
		ActionListener listener2 = new bubbleListener360();
		bubble360.addActionListener(listener2);
		class bubbleListener480 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				try {
					if (findPerson(id).getCurrent() < 2000) {

						if (findPerson(id).getCurrent() + 480 <= 2000) {
							findPerson(id).addShell(240);
							shell.setText(findPerson(id).getShell() + "");
						} else if (findPerson(id).getCurrent() + 480 > 2000) {
							findPerson(id).addShell((2000 - findPerson(id).getCurrent()) / 2);
							shell.setText(findPerson(id).getShell() + "");
						}

					}
					findPerson(id).addWater(480);
					current.setText(findPerson(id).getCurrent() + "");
					percent.setText(findPerson(id).getPercentage() + "");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "add water");
				}
			}
		}
		ActionListener listener3 = new bubbleListener480();
		bubble480.addActionListener(listener3);
		class bubbleListener600 implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				try {
					if (findPerson(id).getCurrent() < 2000) {

						if (findPerson(id).getCurrent() + 600 <= 2000) {
							findPerson(id).addShell(300);
							shell.setText(findPerson(id).getShell() + "");
						} else if (findPerson(id).getCurrent() + 600 > 2000) {
							findPerson(id).addShell((2000 - findPerson(id).getCurrent()) / 2);
							shell.setText(findPerson(id).getShell() + "");
						}
					}
					findPerson(id).addWater(600);
					current.setText(findPerson(id).getCurrent() + "");
					percent.setText(findPerson(id).getPercentage() + "");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(), "add water");
				}
			}
		}
		ActionListener listener4 = new bubbleListener600();
		bubble600.addActionListener(listener4);

	}

	public void createHistoryBtn() {
		ImageIcon history = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/time.png");
		history.setImage(history.getImage().getScaledInstance(130,130, Image.SCALE_DEFAULT));
		historyBtn = new JButton(history);
		class HistoryListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame frame = new HistoryFrame(id);
					frame.setLocation(1200, 0);
					frame.setVisible(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "SQL Exception");
				}
			}
		}
		ActionListener listener = new HistoryListener();
		historyBtn.addActionListener(listener);
	}

	public void createRefreshBtn() throws SQLException {
		ImageIcon refresh = new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/newday2.png");
		refresh.setImage(refresh.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
		refreshBtn = new JButton(refresh);
		refreshBtn.setSize(200,200);
		class RefreshListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				try {
					conn = SimpleDataSource.getConnection();
					PreparedStatement stat = conn.prepareStatement("SELECT * FROM UserInfo WHERE ID=?");
					stat.setString(1, id);

					ResultSet r = stat.executeQuery();

					if (r.next()) {
						PreparedStatement stat1 = conn.prepareStatement("INSERT INTO History VALUES (?,?,CURDATE() - INTERVAL 1 DAY)");
						stat1.setString(1, id);
						stat1.setInt(2, findPerson(id).getCurrent());
						stat1.executeUpdate();

						PreparedStatement stat2 = conn.prepareStatement("UPDATE UserInfo SET current = 0 WHERE ID = ?");
						stat2.setString(1, id);

						stat2.executeUpdate();

						current.setText(0 + "");
						percent.setText(0 + "");

						findPerson(id).addExperience(1);
						lv.setText(findPerson(id).getLevel() + "");

						if (findPerson(id).getLevel() == 0) {
							next.setText(0 + "/" + 1 + "");
						} else if (findPerson(id).getLevel() == 1) {
							next.setText(findPerson(id).getExperience() - 1 + "/" + 2 + "");
						} else if (findPerson(id).getLevel() == 2) {
							next.setText(findPerson(id).getExperience() - 3 + "/" + 3 + "");
						} else if (findPerson(id).getLevel() == 3) {
							next.setText(findPerson(id).getExperience() - 6 + "/" + 4 + "");
						} else if (findPerson(id).getLevel() == 4) {
							next.setText(findPerson(id).getExperience() - 10 + "/" + 5 + "");
						} else if (findPerson(id).getLevel() == 5) {
							next.setText("Maximum");
						}

						if (findPerson(id).getExperience() == 0) {
							PreparedStatement stat3 = conn
									.prepareStatement("UPDATE UserInfo SET Fish = 0 WHERE ID = ?");
							stat3.setString(1, id);
							stat3.executeUpdate();
						} else if (findPerson(id).getExperience() == 1) {
							PreparedStatement stat3 = conn
									.prepareStatement("UPDATE UserInfo SET Fish = 1 WHERE ID = ?");
							stat3.setString(1, id);
							stat3.executeUpdate();
						} else if (findPerson(id).getExperience() == 3) {
							PreparedStatement stat3 = conn
									.prepareStatement("UPDATE UserInfo SET Fish = 2 WHERE ID = ?");
							stat3.setString(1, id);
							stat3.executeUpdate();
						} else if (findPerson(id).getExperience() == 6) {
							PreparedStatement stat3 = conn
									.prepareStatement("UPDATE UserInfo SET Fish = 3 WHERE ID = ?");
							stat3.setString(1, id);
							stat3.executeUpdate();
						} else if (findPerson(id).getExperience() == 10) {
							PreparedStatement stat3 = conn
									.prepareStatement("UPDATE UserInfo SET Fish = 4 WHERE ID = ?");
							stat3.setString(1, id);
							stat3.executeUpdate();
						} else if (findPerson(id).getExperience() == 15) {
							PreparedStatement stat3 = conn
									.prepareStatement("UPDATE UserInfo SET Fish = 5 WHERE ID = ?");
							stat3.setString(1, id);
							stat3.executeUpdate();
						}

					}
				} catch (SQLException e3) {
					JOptionPane.showMessageDialog(new JFrame(), "SQL Exception");
				}
			}
		}
		ActionListener listener4 = new RefreshListener();
		refreshBtn.addActionListener(listener4);
	}

	public void update() throws SQLException {
		current.setText(findPerson(id).getCurrent() + "");
		percent.setText(String.valueOf(findPerson(id).getPercentage()));
		if (findPerson(id).getLevel() == 0) {
			next.setText(0 + "/" + 1 + "");
		} else if (findPerson(id).getLevel() == 1) {
			next.setText(findPerson(id).getExperience() - 1 + "/" + 2 + "");
		} else if (findPerson(id).getLevel() == 2) {
			next.setText(findPerson(id).getExperience() - 3 + "/" + 3 + "");
		} else if (findPerson(id).getLevel() == 3) {
			next.setText(findPerson(id).getExperience() - 6 + "/" + 4 + "");
		} else if (findPerson(id).getLevel() == 4) {
			next.setText(findPerson(id).getExperience() - 10 + "/" + 5 + "");
		} else if (findPerson(id).getLevel() == 5) {
			next.setText("Maximum");
		}
		lv.setText(String.valueOf(findPerson(id).getLevel()));
		shell.setText(String.valueOf(findPerson(id).getShell()));
	}

	public void createPanel() {
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout());
		panel1 = new JPanel(new GridLayout(4, 1));
		panel1.setBackground(Color.white);
		panel1.add(bubble600);
		panel1.add(bubble480);
		panel1.add(bubble360);
		panel1.add(bubble240);
		panel2 = new JPanel(new GridLayout(3, 1));
		panel3 = new JPanel();
		panel3.setBackground(Color.white);
		panel3.add(percent);
		panel3.add(percentLabel);
		panel4 = new JPanel();
		panel4.add(fish);
		panel4.setBackground(Color.white);
		panel5 = new JPanel();
		panel5.add(dailyCurrentLabel);
		panel5.add(current);
		panel5.add(mlLabel);
		panel5.setBackground(Color.white);
		panel2.add(panel3);
		panel2.add(panel4);
		panel2.add(panel5);
		///////////////////////
		panel6 = new JPanel(new GridLayout(3, 3));
		panel6.add(lvLabel);
		panel6.add(lv);
		panel6.add(nextLabel);
		panel6.add(next);
		panel6.add(shellLabel);
		panel6.add(shell);
		panel6.setBackground(Color.white);
		panel6.setBorder(new EtchedBorder());
		/////////////////////
		panel7 = new JPanel();
		panel7.add(historyBtn);
		panel7.setBackground(Color.white);
		panel8 = new JPanel();
		panel8.add(refreshBtn);
		panel8.setBackground(Color.white);
		panel11 = new JPanel(new GridLayout(3, 1));
		panel11.setBackground(Color.white);
		panel11.add(panel6);
		panel11.add(panel7);
		panel11.add(panel8);
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.CENTER);
		panel.add(panel11, BorderLayout.EAST);
		add(panel);
	}

}