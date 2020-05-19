package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import gui.GameRoomFrame;
import gui.LoginFrame;
import server.MainServer;
import utils.Protocol;

public class MainClient {
	
	private static final String TAG = "MainClient : ";
	
	Socket socket;
	BufferedWriter bw;
	BufferedReader keyboardln;
	MainClient mainClient = this;
	GameRoomFrame gameroomFrame;

	public MainClient(GameRoomFrame gameroomFrame) {
		this.gameroomFrame = gameroomFrame;

		try {
			socket = new Socket("localhost", 3500);
			ReadThread rt = new ReadThread();
			Thread newWorker = new Thread(rt);
			newWorker.start();

			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

			keyboardln = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 스타트 버튼 클릭 : StartGame, Chat : 안녕
	public void send(String outputMsg) {
		try {
			bw.write(outputMsg + "\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class ReadThread implements Runnable {

		@Override
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				String inputMsg = "";
				while ((inputMsg = br.readLine()) != null) {
					router(inputMsg);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void router(String msgLine) {
			// 만약에 Chat
			System.out.println(TAG + "router : " + msgLine);
			
			String[] msg = msgLine.split(":");
			String protocol = msg[0];
			if (protocol.equals(Protocol.CHAT)) {
				String chatMsg = msg[1];
				gameroomFrame.taChat.append(chatMsg + "\n");
			} else if (protocol.equals(Protocol.STARTGAME)) {
				// 만약에 제시어:false -> 그림판 비활성화, 채팅창 활성화, 제시어부분 클리어
			    // 만약에 제시어:다른게 -> 그림판 활성화, 채팅창 비활성화, 제시어부분 넣어주기
				String chatMsg = msg[1];
				if(chatMsg.equals("false")) {
					gameroomFrame.PDrawing.setEnabled(false);
					gameroomFrame.tfChat.setEnabled(true);
					gameroomFrame.tfCard.setText("");
				} else { // 제시어 턴의 주인 
					gameroomFrame.PDrawing.setEnabled(true);
					gameroomFrame.tfChat.setEditable(false);
					gameroomFrame.tfCard.setText(chatMsg);
				}
			}
		}
	}
}
