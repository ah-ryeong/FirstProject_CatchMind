import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class WaitRoomFrame {

	private JFrame frame;

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
		
		JLabel LrList = new JLabel("Room List");
		LrList.setBounds(44, 30, 237, 43);
		frame.getContentPane().add(LrList);
		
		JPanel panel = new JPanel();
		panel.setBounds(43, 92, 522, 468);
		frame.getContentPane().add(panel);
		
		JButton btin = new JButton("입장");
		btin.setBounds(595, 499, 272, 61);
		frame.getContentPane().add(btin);
		
		JButton btRoomcr = new JButton("방 만들기");
		btRoomcr.setBounds(595, 420, 272, 61);
		frame.getContentPane().add(btRoomcr);
	}
}
