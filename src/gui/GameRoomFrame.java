package gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.MainClient;
import Paint.Drawing;
import Paint.MyCanvas2;

public class GameRoomFrame extends JFrame{

	public JPanel Canvas, Canv ;
	public JTextField tfCard, tfChat;
	public JButton btEnter, btCard, btGstart;
	public JTextArea taChat, taUserList;
	public JLabel LuserList;
	public MainClient mainClient;
//	public Drawing dw;
	public MyCanvas2 cv2;


	public GameRoomFrame(MainClient mainClient) {
		this.mainClient = mainClient;
//		initialize();
	}
	
	class MyPanel extends JPanel{
		MyCanvas2 cv2;
		public MyPanel(MyCanvas2 cv2) {
			this.cv2 = cv2;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 962, 738);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		btCard = new JButton("제시어");
		btCard.setBounds(40, 46, 112, 34);
		getContentPane().add(btCard);
		
		tfCard = new JTextField();
		tfCard.setBounds(160, 46, 262, 34);
		getContentPane().add(tfCard);
		tfCard.setColumns(10);
		
		Canvas = new JPanel();
		Canvas.setBounds(40, 106, 502, 541);
		getContentPane().add(Canvas);
		
		btGstart = new JButton("게임시작");
		btGstart.setBounds(580, 46, 323, 63);
		getContentPane().add(btGstart);
		
		LuserList = new JLabel("User List");
		LuserList.setBounds(579, 121, 308, 27);
		getContentPane().add(LuserList);
		
		taUserList = new JTextArea();
		taUserList.setBounds(580, 157, 323, 120);
		getContentPane().add(taUserList);
		taChat = new JTextArea();
		taChat.setBounds(580, 292, 323, 305);
		getContentPane().add(taChat);
		
		tfChat = new JTextField();
		
		tfChat.setBounds(580, 609, 229, 38);
		getContentPane().add(tfChat);
		tfChat.setColumns(10);
		
		btEnter = new JButton("Enter");
		btEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("GameRoomFrame : 통신X 이벤트 : "+tfChat.getText());
				taChat.append(tfChat.getText()+"\n");
				//taChat.setText(taChat.getText()+tfChat.getText()+ "\n");
				mainClient.send(tfChat.getText());
				tfChat.setText("");
				
			}
		});
		btEnter.setBounds(823, 609, 80, 38);
		getContentPane().add(btEnter);
	}
}
