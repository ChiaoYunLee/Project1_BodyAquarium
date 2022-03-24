import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.Box;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomePageFrame extends JFrame
{
	private final static int FRAME_WIDTH=400;
	private final static int FRAME_HEIGHT=550;
	private JFrame frame1,frame2,frame3,frame4;
	private JButton aquariumButton;
	private JButton recordButton;
	private JPanel panel,panel2,panel3;
	private JLabel label;
	private JTextField idField;
	private JButton idSubmit;
	private String id;
	private Register register;
	public HomePageFrame() throws SQLException 
	{
		register=new Register();
		panel=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		label=new JLabel("Please enter your id first:");
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		createAquariumBtn();
		createRecordBtn();
		createIDSubmitBtn();
		createPanel();
	}
	public Register getRegister()
	{
		return register;
	}
	private void createAquariumBtn()
	{
		ImageIcon img= new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/fishtank2.png");
		img.setImage(img.getImage().getScaledInstance(90,90, Image.SCALE_DEFAULT ));
		aquariumButton=new JButton(img);
		class AquariumListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
				frame1=new AquariumFrame(id);
				frame1.setLocation(400,0);
				frame1.setVisible(true);
				}
				catch(SQLException e1)
				{
					JOptionPane.showMessageDialog(new JFrame(),"SQL Exception");
				}
			}
		}
		ActionListener listener=new AquariumListener();
		aquariumButton.addActionListener(listener);
	}
	private void createRecordBtn() throws SQLException
	{
		ImageIcon img= new ImageIcon("/Users/irislee/eclipse-workspace2/BodyAquarium/images/record2.png");
		img.setImage(img.getImage().getScaledInstance(90,90, Image.SCALE_DEFAULT ));
		recordButton=new JButton(img);
		class RecordListener implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					if(register.findPerson(id)==null)
					{
						frame3=new RecordingPageFrame();
						frame3.setLocation(400,0);
						frame3.setVisible(true);
					}
					else
					{
						frame4=new RecordingPageFrame(id);
						frame4.setLocation(400,0);
						frame4.setVisible(true);
					}
					
				}
				catch(SQLException e)
				{
					JOptionPane.showMessageDialog(new JFrame(),"SQL Exception");
				}
				
			}
		}
		ActionListener listener=new RecordListener();
		recordButton.addActionListener(listener);
	}
	private void createIDSubmitBtn()
	{
		idField=new JTextField(10);
		idSubmit=new JButton("Submit");
		class idBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
					id=idField.getText();
				try
				{
					if(register.findPerson(id)==null)
					{
						register.addPerson(id);
						register.addProduct(id);
						
					}
				}
				catch(SQLException e1)
				{
					JOptionPane.showMessageDialog(new JFrame(),"Submit B");
				}
				
			}
		}
		ActionListener l=new idBtnListener();
		idSubmit.addActionListener(l);
	}
	private void createPanel()
	{
		panel2.add(label);
		panel2.add(idField);
		panel2.add(idSubmit);
		panel3.add(recordButton);
		panel3.add(aquariumButton);
		BoxLayout layout=new BoxLayout(panel3,BoxLayout.X_AXIS);
		panel3.setLayout(layout);
		panel3.add(Box.createGlue());
		panel3.add(recordButton);
		panel3.add(Box.createGlue());
		panel3.add(aquariumButton);
		panel3.add(Box.createGlue());
		panel.setLayout(new BorderLayout());
		panel.add(panel2,BorderLayout.NORTH);
		panel.add(panel3,BorderLayout.CENTER);
		add(panel);
	}
}
