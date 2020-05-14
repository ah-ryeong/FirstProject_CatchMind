package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame {

	public JFrame frame;
	public JPanel panel;
	public JButton btID, btPW, btSign, btLogin;
	public JTextField tfID, textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
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
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 393, 269);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btID = new JButton("아이디");
		btID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btID.setBounds(67, 45, 105, 27);
		panel.add(btID);
		
		tfID = new JTextField();
		tfID.setBounds(197, 46, 128, 24);
		panel.add(tfID);
		tfID.setColumns(10);
		
		btPW = new JButton("비밀번호");
		btPW.setBounds(67, 99, 105, 27);
		panel.add(btPW);
		
		textField = new JTextField();
		textField.setBounds(197, 99, 128, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		btSign = new JButton("회원가입");
		btSign.setBounds(94, 160, 89, 27);
		panel.add(btSign);
		
		btLogin = new JButton("로그인");
		btLogin.setBounds(197, 160, 89, 27);
		panel.add(btLogin);
	}
}
