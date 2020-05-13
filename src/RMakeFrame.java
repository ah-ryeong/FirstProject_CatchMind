import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class RMakeFrame {

	private JFrame frame;
	private JTextField tfRName;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 376, 225);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 358, 178);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btRName = new JButton("방 제목");
		btRName.setBounds(48, 55, 105, 27);
		panel.add(btRName);
		
		tfRName = new JTextField();
		tfRName.setBounds(168, 56, 149, 24);
		panel.add(tfRName);
		tfRName.setColumns(10);
		
		JButton btRMake = new JButton("방 만들기");
		btRMake.setBounds(102, 119, 162, 35);
		panel.add(btRMake);
	}
}
