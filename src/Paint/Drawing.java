package Paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JPanel;

import Paint.PSocketServer.SocketThread;
import gui.listenAdapter;

//메인 컴포넌트가 있는 메인 함수 : 실제 마우스 이벤트랑 버튼 이벤트 처리되는 곳이다.
public class Drawing extends listenAdapter {

	Socket socket; // 소켓저장
	BufferedReader reader;
	PrintWriter writer;
	Scanner sc;
	Canvas can;

	String line; // 좌표값을 저장하는 스트링
	int x;
	int y;
	int w = 7;
	int h = 7;
	Color cr = Color.BLACK;
	boolean drag = true;
//	boolean drag = false;

	public Drawing() {
//		initialize();
		try {
			socket = new Socket("localhost", 9502); // 상대방의 아이피와 포트를 입력하고 변수에 저장
			SocketThread st = new SocketThread(); // 소켓 연결을 유지할 스레드 생성
			st.start(); // 스레드 시작

			writer = new PrintWriter(socket.getOutputStream(), true); // 버퍼드라이터에 소켓 스트림 연결
			sc = new Scanner(System.in);

			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {

						while (!drag) { // 드래그 상태가 아니면 대기
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

						writer.println(line); // 드래그 상태면 좌표를 서버로 전송한다.
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class SocketThread extends Thread { // 여기 더 해야함

		@Override
		public void run() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = null;

				while ((line = reader.readLine()) != null) {
					System.out.println("from server : " + line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	class MyPanel extends JPanel implements ActionListener { // 커스텀 패널
		public MyPanel() {

			addMouseMotionListener(new MouseMotionAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) { // 마우스 드래그시 드래그 true
					drag = true;
					line = e.getX() + "," + e.getY(); // 마우스 x,y 좌표 저장
					int x = e.getX(); // 화면에 그리기 위한 x
					int y = e.getY(); // 화면에 그리기 위한 y
					((MyCanvas2) can).x = x;
					((MyCanvas2) can).y = y;
					can.repaint();
				}

				@Override
				public void mouseMoved(MouseEvent e) { // 마우스 드래그 해제시 drag가 false가 된다.
					drag = false;
				}

			});

		}

		@Override
		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
			g.fillArc(x, y, 10, 10, 0, 360);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			Object o = e.getSource();
//			DrawingBT can2 = (DrawingBT)can;
//			
//			if ( o = btBr) {
//				can2.cr = Color.BLACK;
//			} else if (o = btR){
//				can2.cr = Color.RED;
//			}
			

		}

	}

	// JPanel 
	public static void main(String[] args) {
		new Drawing();
	}
}
