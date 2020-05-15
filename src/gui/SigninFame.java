package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Client.MainClient;

public class SigninFame extends JFrame {

	private final static String TAG = "SigninFame : ";

	private JFrame signinFrame;
//	public JFrame frame;
	public JPanel Login;
	public JTextField tfSid, tfSpw;
	public JButton btSID, btIdCheck, btSPW, btSign, btCancel;
	public static MainClient mainClient;

	// 생성자
	public SigninFame(MainClient mainClient) {
		this.mainClient = mainClient;
//		initialize();
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}
	
	

	// 객체생성
	private void initObject() {
		signinFrame = new JFrame();
		Login = new JPanel();

		btSID = new JButton("아이디");
		btIdCheck = new JButton("중복확인");
		btSPW = new JButton("비밀번호");
		btSign = new JButton("가입하기");
		btCancel = new JButton("취소");

		tfSid = new JTextField();
		tfSpw = new JTextField();
	}

	// 데이터 초기화
	private void initData() {

	}

	// 디자인
	private void initDesign() {

		// 1. 기본세팅
		signinFrame.setBounds(100, 100, 510, 314);
		signinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signinFrame.setLocationRelativeTo(null);
		signinFrame.getContentPane().add(Login, BorderLayout.CENTER);

		// 2. 패널세팅
		Login.setLayout(null);

		// 3. 디자인
		btSID.setBounds(54, 66, 124, 29);
		tfSid.setBounds(198, 66, 139, 29);
		tfSid.setColumns(10);
		btIdCheck.setBounds(351, 66, 89, 29);
		btSPW.setBounds(54, 118, 124, 29);
		tfSpw.setBounds(198, 120, 139, 27);
		tfSpw.setColumns(10);
		btSign.setBounds(118, 191, 124, 29);
		btCancel.setBounds(264, 192, 124, 27);

		// 4. 패널에 컴포넌트 추가
		Login.add(btSID);
		Login.add(tfSid);
		Login.add(btIdCheck);
		Login.add(btSPW);
		Login.add(tfSpw);
		Login.add(btSign);
		Login.add(btCancel);
	}
	
	private void initListener() {
		btCancel.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				signinFrame.setVisible(false);
			}
		});
	}
	public static void main(String[] args) {
		new SigninFame(mainClient);
	}
}
