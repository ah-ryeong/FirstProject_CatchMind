package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Client.MainClient;
import oracle.net.aso.c;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	public LoginFrame loginFrame = this;
	public JPanel pLogin;
	public JButton btID, btPW, btSign, btLogin;
	public JTextField tfID, tfpw;
	public MainClient mainClient;
	
	// 생성자
	public LoginFrame() {
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}
	
	public void setmainclient(MainClient mainClient) {
		this.mainClient = mainClient;
	}
	
	// 객체생성
	private void initObject() {
		pLogin = new JPanel();
		
		btID = new JButton("아이디");
		btPW = new JButton("비밀번호");
		btSign = new JButton("회원가입");
		btLogin = new JButton("로그인");
		
		tfID = new JTextField();
		tfpw = new JTextField();
	}

	// 데이터 초기화
	private void initData() {
		
	}
	
	// 디자인
	private void initDesign() {
		// 1. 기본세팅
		loginFrame.setTitle("Login");
		loginFrame.setBounds(100, 100, 393, 269);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		// 2. 패널세팅
		getContentPane().add(pLogin);
		pLogin.setLayout(null);
		
		// 3. 디자인 
		tfID.setColumns(10);
		tfID.setBounds(197, 46, 128, 24);
		btID.setBounds(67, 45, 105, 27);
		tfpw.setBounds(197, 99, 128, 26);
		btPW.setBounds(67, 99, 105, 27);
		tfpw.setColumns(10);
		btSign.setBounds(94, 160, 89, 27);
		btLogin.setBounds(197, 160, 89, 27);
		
		// 4. 패널에 컴포넌트 추가
		pLogin.add(btID);
		pLogin.add(tfID);
		pLogin.add(btPW);
		pLogin.add(tfpw);
		pLogin.add(btSign);
		pLogin.add(btLogin);
	}
	
	// 리스너 등록
	private void initListener() {
		
		btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameRoomFrame(mainClient);
				loginFrame.setVisible(false);
			}
		});
		
		btSign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SigninFame(mainClient);
				loginFrame.setVisible(false);
			}
		});
	}
	
}
