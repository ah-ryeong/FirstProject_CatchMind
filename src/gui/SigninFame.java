package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Client.MainClient;

public class SigninFame extends JFrame {
	
	private final static String TAG = "SigninFame : ";

	public JFrame frame;
	public JPanel Login;
	public JTextField tfSid, tfSpw;
	public JButton btSID, btIdCheck, btSPW, btSign, btCancel;
	public MainClient mainClient;
	

	/**
	 * Create the application.
	 */
	public SigninFame(MainClient mainClient) {
		this.mainClient = mainClient;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Login = new JPanel();
		frame.getContentPane().add(Login, BorderLayout.CENTER);
		Login.setLayout(null);
		
		btSID = new JButton("아이디");
		btSID.setBounds(54, 66, 124, 29);
		Login.add(btSID);
		
		tfSid = new JTextField();
		tfSid.setBounds(198, 66, 139, 29);
		Login.add(tfSid);
		tfSid.setColumns(10);
		
		btIdCheck = new JButton("중복확인");
		btIdCheck.setBounds(351, 66, 89, 29);
		Login.add(btIdCheck);
		
		btSPW = new JButton("비밀번호");
		btSPW.setBounds(54, 118, 124, 29);
		Login.add(btSPW);
		
		tfSpw = new JTextField();
		tfSpw.setBounds(198, 120, 139, 27);
		Login.add(tfSpw);
		tfSpw.setColumns(10);
		
		btSign = new JButton("가입하기");
		btSign.setBounds(118, 191, 124, 29);
		Login.add(btSign);
		
		btCancel = new JButton("취소");
		btCancel.setBounds(264, 192, 124, 27);
		Login.add(btCancel);
	}

}
