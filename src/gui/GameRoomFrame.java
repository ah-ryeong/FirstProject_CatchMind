package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.MainClient;
import Paint.Drawing;
import Paint.MyCanvas2;

public class GameRoomFrame extends JFrame {

	private final static String TAG = "GameroomFrame : ";

	private GameRoomFrame gameroomFrame = this;
	public JTextField tfCard, tfChat;
	public JButton btEnter, btCard, btGstart, btBlack, btRed, btBlue, btEraser, btAlldel;
	public JTextArea taChat, taUserList;
	public JLabel LuserList;
	public MainClient mainClient;
	public JPanel Canvas, PDrawing;
//	public Drawing dw;
//	public MyCanvas2 cv2;

	public GameRoomFrame(MainClient mainClient) {
		this.mainClient = mainClient;
		initObject();
		initData();
		initDesign();
		initListener();
	}

	// 객체생성
	public void initObject() {
		btCard = new JButton("제시어");
		btGstart = new JButton("게임시작");
		tfCard = new JTextField();
		Canvas = new JPanel();
		PDrawing = new JPanel();
		Canvas.setLayout(null);

		LuserList = new JLabel("User List");
		taUserList = new JTextArea();
		tfChat = new JTextField();
		btEnter = new JButton("Enter");
		taChat = new JTextArea();
		btBlack = new JButton(new ImageIcon("src/images/black.png"));
		btRed = new JButton(new ImageIcon("src/images/red.png"));
		btBlue = new JButton(new ImageIcon("src/images/blue.png"));
		btEraser = new JButton("지우기");
		btAlldel = new JButton("모두 지우기");
	}

	// 데이터초기화
	private void initData() {

	}

	// 디자인
	public void initDesign() {
		// 1. 기본세팅
		setTitle("Game Room");
		setBounds(100, 100, 962, 738);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// 2. 패널세팅
		btCard.setBounds(40, 46, 112, 34);
		tfCard.setBounds(160, 46, 262, 34);
		tfCard.setColumns(10);
		btGstart.setBounds(580, 46, 323, 63);
		LuserList.setBounds(579, 121, 308, 27);
		taUserList.setBounds(580, 157, 323, 120);
		Canvas.setBounds(40, 106, 502, 541);
		taChat.setBounds(580, 292, 323, 305);
		tfChat.setBounds(580, 609, 229, 38);
		tfChat.setColumns(10);
		btEnter.setBounds(823, 609, 80, 38);
		btBlack.setBounds(14, 12, 54, 46);
		btBlack.setPreferredSize(new Dimension(54, 46));
		btRed.setBounds(82, 12, 54, 46);
		btRed.setPreferredSize(new Dimension(54, 46));
		btBlue.setBounds(150, 12, 54, 46);
		btBlue.setPreferredSize(new Dimension(54, 46));
		btEraser.setBounds(273, 12, 85, 46);
		btAlldel.setBounds(372, 12, 116, 46);
		PDrawing.setBounds(14, 81, 474, 448);
		PDrawing.setBackground(Color.WHITE);

		// 3. 패널에 컴포넌트 추가
		getContentPane().add(btCard);
		getContentPane().add(tfCard);
		getContentPane().add(Canvas);
		getContentPane().add(btGstart);
		getContentPane().add(LuserList);
		getContentPane().add(taUserList);
		getContentPane().add(taChat);
		getContentPane().add(tfChat);
		getContentPane().add(btEnter);
		Canvas.add(btBlack);
		Canvas.add(btRed);
		Canvas.add(btBlue);
		Canvas.add(btEraser);
		Canvas.add(btAlldel);
		Canvas.add(PDrawing);
	}

	// 리스너 등록
	private void initListener() {

		btEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("GameRoomFrame : 통신X 이벤트 : " + tfChat.getText());
				taChat.append(tfChat.getText() + "\n");
				// taChat.setText(taChat.getText()+tfChat.getText()+ "\n");
				mainClient.send(tfChat.getText());
				tfChat.setText("");

			}
		});
	}
}
