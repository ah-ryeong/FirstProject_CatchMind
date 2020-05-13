import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SigninFame {

	private JFrame frame;
	private JTextField tfSid;
	private JTextField tfSpw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SigninFame window = new SigninFame();
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
	public SigninFame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btSID = new JButton("아이디");
		btSID.setBounds(54, 66, 124, 29);
		panel.add(btSID);
		
		tfSid = new JTextField();
		tfSid.setBounds(198, 66, 139, 29);
		panel.add(tfSid);
		tfSid.setColumns(10);
		
		JButton btIdCheck = new JButton("중복확인");
		btIdCheck.setBounds(351, 66, 89, 29);
		panel.add(btIdCheck);
		
		JButton btSPW = new JButton("비밀번호");
		btSPW.setBounds(54, 118, 124, 29);
		panel.add(btSPW);
		
		tfSpw = new JTextField();
		tfSpw.setBounds(198, 120, 139, 27);
		panel.add(tfSpw);
		tfSpw.setColumns(10);
		
		JButton btSign = new JButton("가입하기");
		btSign.setBounds(118, 191, 124, 29);
		panel.add(btSign);
		
		JButton btCancel = new JButton("취소");
		btCancel.setBounds(264, 192, 124, 27);
		panel.add(btCancel);
	}

}
