package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.MainClient;
import paint.MyCanvas;
import utils.Protocol;

public class GameRoomFrame extends JFrame {

	private final static String TAG = "GameroomFrame : ";

	private GameRoomFrame gameroomFrame = this;
	public JTextField tfCard, tfChat;
	public JButton btEnter, btCard, btGstart, btBlack, btRed, btBlue, btEraser, btAlldel;
	public JTextArea taChat, taUserList;
	public JLabel LuserList;
	public JScrollPane scrollPane;
	public MainClient mainClient;
	public JPanel Canvas, PDrawing;
	private String username;
	private JLabel laUsername;
	public MyCanvas can;
	private String currentColor = "black";
		
	public GameRoomFrame(String username) {
		this.username = username;
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}

	// 객체생성
	public void initObject() {
		mainClient = new MainClient(gameroomFrame);
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
		scrollPane = new JScrollPane(taChat);
		btBlack = new JButton(new ImageIcon("src/images/black.png"));
		btRed = new JButton(new ImageIcon("src/images/red.png"));
		btBlue = new JButton(new ImageIcon("src/images/blue.png"));
		btEraser = new JButton("지우기");
		btAlldel = new JButton("모두 지우기");
		can = new MyCanvas();
//		mainClient.
	}

	// 데이터초기화
	private void initData() {
		
	}

	// 디자인
	public void initDesign() {
		// 1. 기본세팅
		setTitle("Game Room");
		setBounds(100, 100, 962, 738);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// 2. 패널세팅
		btCard.setBounds(40, 46, 112, 34);
		tfCard.setBounds(160, 46, 262, 34);
		tfCard.setColumns(10);
		btGstart.setBounds(580, 46, 323, 63);
		LuserList.setBounds(579, 121, 308, 27);
		taUserList.setBounds(580, 157, 323, 120);
		Canvas.setBounds(40, 106, 502, 541);
		scrollPane.setBounds(580, 292, 323, 305);
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
		can.setSize(474, 448);
		can.setBackground(Color.WHITE);

		// 3. 패널에 컴포넌트 추가
		getContentPane().add(btCard);
		getContentPane().add(tfCard);
		getContentPane().add(Canvas);
		getContentPane().add(btGstart);
		getContentPane().add(LuserList);
		getContentPane().add(taUserList);
		getContentPane().add(scrollPane);
		getContentPane().add(tfChat);
		getContentPane().add(btEnter);
		Canvas.add(btBlack);
		Canvas.add(btRed);
		Canvas.add(btBlue);
		Canvas.add(btEraser);
		Canvas.add(btAlldel);
		Canvas.add(PDrawing);
		PDrawing.add(can);
		
		laUsername = new JLabel(username);
		laUsername.setBounds(40, 10, 57, 15);
		getContentPane().add(laUsername);
	}

	// 리스너 등록
	private void initListener() {

		btEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				taChat.append("[" + username +"]"+tfChat.getText() + "\n");
				String myMsg = username + ":" + tfChat.getText();
				String msgLine = Protocol.CHAT + ":" + myMsg;
				// Chat:안녕
				mainClient.send(msgLine);
				tfChat.setText("");
			}
		});
		
		btGstart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// StartGame
				String msgLine = Protocol.STARTGAME + ":" + "false";
				mainClient.send(msgLine);
			}
		});
		tfChat.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					taChat.append("[" + username + "]" + tfChat.getText() + "\n");
					String myMsg = username + ":" + tfChat.getText();
					String msgLine = Protocol.CHAT + ":" + myMsg;
					mainClient.send(msgLine);
					tfChat.setText("");
				}
			}
			
		});
		
		MyHandler myHandler = new MyHandler();
		can.addMouseMotionListener(myHandler);
		btBlack.addActionListener(myHandler);
		btRed.addActionListener(myHandler);
		btBlue.addActionListener(myHandler);
		btEraser.addActionListener(myHandler);
		btAlldel.addActionListener(myHandler);	
	}
	
	public void setColor(String myColor) {
		if(myColor.equals("black")) {
			can.color = Color.BLACK;
		}else if(myColor.equals("red")) {
			can.color = Color.RED;
		}else if(myColor.equals("blue")) {
			can.color = Color.BLUE;
		}
	}
	
	class MyHandler implements MouseMotionListener, ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object object = e.getSource();
			
			if(object == btBlack) {
				can.color = Color.BLACK;
				currentColor = "black";
			} else if(object == btRed) {
				can.color = Color.RED;
				currentColor = "red";
			} else if(object == btBlue) {
				can.color = Color.BLUE;
				currentColor = "blue";
			} else if (object == btEraser) {
				can.color = can.getBackground();
			} else if(object == btAlldel) {
				Graphics graphics = can.getGraphics();
				graphics.clearRect(0, 0, getWidth(), getHeight());
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// 마우스를 드래그한 지점의 x좌표, y좌표를 얻어와서 can의 x,y 좌표값에 전달
			int XX = e.getX();
			int YY = e.getY();
			can.x = XX;
			can.y = YY;
			
			can.repaint();
			
			// 프로토콜 draw : 색, x값, y값 
			String msgLine = Protocol.DRAW + ":" + currentColor + "," + XX + "," + YY;
			
			mainClient.send(msgLine);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		
	}
}