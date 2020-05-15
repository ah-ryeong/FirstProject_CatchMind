package gui;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class RMakeFrame {

	public JFrame frame;
	public JPanel RMake;
	public JTextField tfRName;
	public JButton btRName, btRMake;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RMakeFrame window = new RMakeFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RMakeFrame() {
		initialize(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object c) {
		frame = new JFrame();
		frame.setBounds(100, 100, 376, 225);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		RMake = new JPanel();
		RMake.setBounds(0, 0, 358, 178);
		frame.getContentPane().add(RMake);
		RMake.setLayout(null);
		
		btRName = new JButton("방 제목");
		btRName.setBounds(48, 55, 105, 27);
		RMake.add(btRName);
		
		tfRName = new JTextField();
		tfRName.setBounds(168, 56, 149, 24);
		RMake.add(tfRName);
		tfRName.setColumns(10);
		
		btRMake = new JButton("방 만들기");
		btRMake.setBounds(102, 119, 162, 35);
		btRMake.addActionListener((ActionListener)c);
		RMake.add(btRMake);
	}
	
	public String getRoomName()
	{
		return tfRName.getText();
	}
	public String getcreatebuttonactioncommend()
	{
		return btRMake.getActionCommand();
	}
	public void setTextClear()
	{
		tfRName.setText(" ");
	}
}
