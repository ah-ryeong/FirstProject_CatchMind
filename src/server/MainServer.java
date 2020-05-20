package server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import Client.MainClient;
import paint.MyCanvas;
import utils.Protocol;

public class MainServer {

	private final static String TAG = "MainServer : ";

	ServerSocket serverSocket;
	Vector<SocketThread> vc;
	String turnWord = null;
	int turn = 0;
	public boolean 정답;

	public MainServer() throws Exception {
		vc = new Vector<>();
		serverSocket = new ServerSocket(3500);
		System.out.println(TAG + "서버접속완료");

		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("요청이 들어왔습니다.");
			SocketThread st = new SocketThread(socket);
			Thread newWorker = new Thread(st);
			newWorker.start();
			vc.add(st);
		}
	}
	

	// 새로운 스레드에게 버퍼를 연결할 수 있게 socket을 전달
	class SocketThread implements Runnable {
		Socket socket;
		BufferedReader br;
		BufferedWriter bw;
		
		public BufferedImage bi;
		public MyCanvas myCanvas;
		public int x;
		public int y;
	
		public SocketThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

				String msg = "";
				while ((msg = br.readLine()) != null) {
					System.out.println("클라이언트 : " + msg);
					router(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}

		public void router(String msgLine) {

			String[] msg = msgLine.split(":");
			String protocol = msg[0];
			
			if (protocol.equals(Protocol.CHAT)) {
				String username = msg[1];
				String chatMsg = msg[2];
				System.out.println(TAG + "현재 제시어 : " + turnWord);
				System.out.println(TAG + "chatMsg : " + chatMsg);
				
				if (chatMsg.equals(turnWord)) {
					System.out.println(TAG + "정답 :" + chatMsg + "turnWord : " + turnWord);
					chattingMsg(username + ":" + chatMsg); // ta 뿌리기, ta 에 정답입니다 뿌리기 
					nextTurn();
				} else {
					System.out.println(TAG + "메세지 : " + chatMsg + "turnWord : " + turnWord);
					chattingMsg(username + ":" + chatMsg); // ta 뿌리기 
				}
				
			} else if (protocol.equals(Protocol.STARTGAME)) {
				startGame();
				
			} else if(protocol.equals(Protocol.DRAW)) {
				try {
					for (SocketThread socketThread : vc) {
						if(socketThread != this) {
							socketThread.bw.write(Protocol.DRAW + ":" + msg[1] + "\n");
							socketThread.bw.flush();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				 
			} 
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

		public void chattingMsg(String chatMsg) {
			try {
				for (SocketThread socketThread : vc) {
					if (socketThread != this) {
						socketThread.bw.write(Protocol.CHAT + ":" + chatMsg + "\n");
						socketThread.bw.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 제시어를 턴의 주인에게 뿌리기
		public void startGame() {
			System.out.println(TAG + "표시 1 : 성공");
			turnWord = new Word().getStr();
			try {
				for (int i = 0; i < vc.size(); i++) {
					if (i == turn) {
						// StartGame
						System.out.println(TAG + "표시 2 : 성공");
						System.out.println(TAG + "표시 2 : 메세지 프로토콜 : " + Protocol.STARTGAME + ":" +  turnWord+ "\n");
						vc.get(i).bw.write(Protocol.STARTGAME + ":" + turnWord + "\n");
						vc.get(i).bw.flush();
					} else {
						System.out.println(TAG + "표시 3 : 성공");
						System.out.println(TAG + "표시 3 : 메세지 프로토콜 : " + Protocol.STARTGAME + ":" + "false" + "\n");
						vc.get(i).bw.write(Protocol.STARTGAME + ":" + "false" + "\n");
						vc.get(i).bw.flush();
					}
				}

				turn++;
				if (turn == vc.size()) { // 3
					turn = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 제시어 맞추면 다음턴으로 넘어가기
		public void nextTurn() {
			startGame();
		}

	}

	public static void main(String[] args) {
		try {
			new MainServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
