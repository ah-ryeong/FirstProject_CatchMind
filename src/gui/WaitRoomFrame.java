package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class WaitRoomFrame {

	private JFrame frame;
	public JLabel LrList;
	public JPanel ProomList;
	public JButton btin, btRoomcr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaitRoomFrame window = new WaitRoomFrame();
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
	public WaitRoomFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 922, 645);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LrList = new JLabel("Room List");
		LrList.setBounds(44, 30, 237, 43);
		frame.getContentPane().add(LrList);
		
		ProomList = new JPanel();
		ProomList.setBounds(43, 92, 522, 468);
		frame.getContentPane().add(ProomList);
		
		btin = new JButton("입장");
		btin.setBounds(595, 499, 272, 61);
		frame.getContentPane().add(btin);
		
		btRoomcr = new JButton("방 만들기");
		btRoomcr.setBounds(595, 420, 272, 61);
		frame.getContentPane().add(btRoomcr);
	}
}
