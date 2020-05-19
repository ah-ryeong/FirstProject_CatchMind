package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.MainClient;
import oracle.net.aso.c;
import utils.Protocol;

public class GameRoomFrame extends JFrame {

	private final static String TAG = "GameroomFrame : ";

	private GameRoomFrame gameroomFrame = this;
	public JTextField tfCard, tfChat;
	public JButton btEnter, btCard, btGstart, btBlack, btRed, btBlue, btEraser, btAlldel;
	public JTextArea taChat, taUserList;
	public JLabel LuserList;
	public MainClient mainClient;
	public JPanel canvas;
	public PaintPanel paintPanel;
	JPanel paint;

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
		canvas = new JPanel();

		canvas.setLayout(null);

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
		paintPanel = new PaintPanel();
		paint = new JPanel();
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
		canvas.setBounds(40, 106, 502, 541);
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
		paintPanel.setBackground(Color.WHITE);
		paintPanel.setBounds(14, 83, 463, 428);

		// 3. 패널에 컴포넌트 추가
		getContentPane().add(btCard);
		getContentPane().add(tfCard);
		getContentPane().add(canvas);
		getContentPane().add(btGstart);
		getContentPane().add(LuserList);
		getContentPane().add(taUserList);
		getContentPane().add(taChat);
		getContentPane().add(tfChat);
		getContentPane().add(btEnter);
		canvas.add(btBlack);
		canvas.add(btRed);
		canvas.add(btBlue);
		canvas.add(btEraser);
		canvas.add(btAlldel);
		canvas.add(paintPanel);
	}

	// 리스너 등록
	private void initListener() {

		btEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("GameRoomFrame : 통신X 이벤트 : " + tfChat.getText());
				taChat.append(tfChat.getText() + "\n");
				String msgLine = Protocol.CHAT + ":" + tfChat.getText();
				mainClient.send(msgLine);
				tfChat.setText("");

			}
		});
		
	}

	class PaintPanel extends JPanel {

		int oldX, oldY;
		int curX, curY;
		int w = 7;
		int h = 7;
		// 마우스가 움직인 부분에 대하여 저장해서 라인을 그려주는 벡터
		Vector<Point> move = new Vector<Point>();
		// 마우스 드래그가 여러번일때 그 부분에 대해서 저장할 부분
		Vector<Vector> list = new Vector<Vector>();
		int a[], b[];

		public PaintPanel() { // 마우스의 움직임에 따라 좌표 저장

			this.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent e) {

				}

				@Override
				public void mouseDragged(MouseEvent e) {
					curX = e.getX();
					curY = e.getY();
					move.add(new Point(curX, curY));
					PaintPanel.this.getGraphics().drawLine(oldX, oldY, curX, curY);
					oldX = curX;
					oldY = curY;
				}
			});

			this.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					// 마우스 버튼이 눌렸을 때 현재의 버튼 포인트 정보를 저장 
					oldX = e.getX();
					oldY = e.getY();
					move.add(new Point(oldX, oldY));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					// 마우스 버튼 떨어질때 현재까지 백터에 저장할 것을 벡터에 저장을 해서 이때까지 그린 그림에 대한 정보를 유지 
					list.add(move); // 현재 벡터에 저장된 것을 백터의 백터에 저장?
					move = new Vector<Point>(); // 백터 초기화 -> 끝난부분에서 다음 그래그 시작까지 라인 안 그리게!
				}
			
			});
		}

		@Override
		protected void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			// Iterator 인터페이스를 이용해서 벡터나 리스트에 저장된 내용 호출 
			for (Vector vs :list) {
				Iterator it = vs.iterator();
				a = new int[vs.size()];
				b = new int[vs.size()];
				int k = 0;
				
				while (it.hasNext()) {
					Point pt = (Point)it.next();
					a[k] = pt.x;
					b[k] = pt.y;
					k++;
					
				}
				graphics.drawPolygon(a, b, a.length);
			}
		}
		
		class point {
			int x, y;
			public point(int a, int b) {
				x = a;
				y = b;
			}
		}

	}
}
