package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Client.MainClient;
import dao.UserDao;
import models.User;
import utils.Protocol;

public class SigninFame extends JFrame {

	private final static String TAG = "SigninFame : ";

	private SigninFame signinFrame = this;

	public JPanel Login;
	public JTextField tfSid, tfSpw;
	public JButton btSID, btIdCheck, btSPW, btSign, btCancel;
	public static MainClient mainClient;
	public GameRoomFrame grf;

	// 생성자
	public SigninFame(MainClient mainClient, GameRoomFrame grf) {
		this.mainClient = mainClient;
		this.grf = grf;
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}

	// 객체생성
	private void initObject() {
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
		signinFrame.setTitle("회원가입");
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
		btCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setmainclient(mainClient, grf);
				loginFrame.setVisible(true);
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
						LoginFrame loginFrame = new LoginFrame();
						loginFrame.setmainclient(mainClient, grf);
						loginFrame.setVisible(true);
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
