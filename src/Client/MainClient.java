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

public class MainClient {
	Socket socket;
	BufferedWriter bw;
	BufferedReader keyboardln;
	MainClient mainClient = this;
	GameRoomFrame grf;
	
	public MainClient() {
		
		try {
			grf = new GameRoomFrame(mainClient);
			socket = new Socket("localhost", 3000);
			ReadThread rt = new ReadThread();
			Thread newWorker = new Thread(rt);
			newWorker.start();
			
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			
			keyboardln = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setmainclient(mainClient);
		loginFrame.setVisible(true);
	}
	
	public void send(String outputMsg) {
		try {
			System.out.println("MainClient : send() : "+outputMsg);
			bw.write(outputMsg + "\n");
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	class ReadThread implements Runnable {

		@Override
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				String inputMsg = "";
				while((inputMsg = br.readLine()) != null) {
					System.out.println("MainClient : 사용자 : " + inputMsg);
					// ta뿌리기
					grf.taChat.setText(grf.taChat.getText() + inputMsg + "\n");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
