package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Client.MainClient;
import dao.UserDao;
import models.User;
import utils.Protocol;
import javax.swing.JSeparator;
import java.awt.Color;

public class SigninFame extends JFrame {

	private final static String TAG = "SigninFame : ";

	private SigninFame signinFrame = this;

	public JPanel Login;
	public JTextField tfSid;
	public JPasswordField tfSpw;
	public JButton btSID, btIdCheck, btSPW, btSign, btCancel;
	public JSeparator separator;
	public static MainClient mainClient;

	// 생성자
	public SigninFame(MainClient mainClient) {
		this.mainClient = mainClient;
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}

	// 객체생성
	private void initObject() {
		Login = new JPanel();
		Login.setBackground(Color.WHITE);

		btSID = new JButton("아이디");
		btIdCheck = new JButton("중복확인");
		btSPW = new JButton("비밀번호");
		btSign = new JButton("가입하기");
		btCancel = new JButton("취소");
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);

		tfSid = new JTextField();
		tfSpw = new JPasswordField();
	}

	// 데이터 초기화
	private void initData() {

	}

	// 디자인
	private void initDesign() {

		// 1. 기본세팅
		signinFrame.setTitle("회원가입");
		signinFrame.setBounds(100, 100, 359, 437);
		signinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signinFrame.setLocationRelativeTo(null);
		signinFrame.getContentPane().add(Login, BorderLayout.CENTER);

		// 2. 패널세팅
		Login.setLayout(null);

		// 3. 디자인
		btSID.setBounds(24, 64, 89, 27);
		tfSid.setBounds(24, 93, 178, 41);
		tfSid.setColumns(10);
		Border borderLine = BorderFactory.createLineBorder(Color.BLACK, 3);
		tfSid.setBorder(borderLine);
		btIdCheck.setBounds(216, 94, 98, 38);
		btSPW.setBounds(24, 162, 89, 27);
		tfSpw.setBounds(25, 191, 290, 41);
		Border borderLine1 = BorderFactory.createLineBorder(Color.BLACK, 3);
		tfSpw.setBorder(borderLine1);
		tfSpw.setColumns(10);
		btSign.setBounds(29, 315, 124, 41);
		btCancel.setBounds(181, 315, 124, 41);
		separator.setBounds(98, 276, 130, 7);

		// 4. 패널에 컴포넌트 추가
		Login.add(btSID);
		Login.add(tfSid);
		Login.add(btIdCheck);
		Login.add(btSPW);
		Login.add(tfSpw);
		Login.add(btSign);
		Login.add(btCancel);
		Login.add(separator);
	}

	private void initListener() {
		btCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				signinFrame.setVisible(false);
			}
		});

		btSign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 1, 2 TF에 있는 값을 가져와서 User에 담음
				System.out
						.println(TAG + "회원가입 : " + "userName : " + tfSid.getText() + "/ password : " + tfSpw.getText());
				if (tfSid.getText().length() == 0 || tfSpw.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "빈 칸을 입력해주세요.");
				} else {
					User user = new User(tfSid.getText(), tfSpw.getText());

					UserDao ud = UserDao.getInstance();
					int result = ud.가입(user);
					// 4. return 값을 확인해서 로직을 직접 짜기(성공, 실패)
					if (result == 1) {
						// 5. 성공
						JOptionPane.showMessageDialog(null, "가입 성공");
						new LoginFrame();
						signinFrame.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "가입에 실패하였습니다");
					}
				}
			}
		});

		btIdCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserDao userDao = UserDao.getInstance();
				int result = userDao.확인(tfSid.getText());

				if (result == 1) {
					// 가입성공
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
				} else {
					// 가입실패
					JOptionPane.showMessageDialog(null, "아이디가 중복됩니다.");
					tfSid.setText("");
				}

			}
		});
	}
}