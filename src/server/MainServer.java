package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import utils.Protocol;

public class MainServer{
	
	private final static String TAG = "MainServer : ";

	ServerSocket serverSocket;
	Vector<SocketThread>vc;                                                                                                          
	
	public MainServer() throws Exception {
		vc = new Vector<>();
		serverSocket = new ServerSocket(3500);
		
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
		
		public SocketThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
				
				String msg = "";
				while((msg = br.readLine()) != null) {
					System.out.println("클라이언트 : " + msg);
					router(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void router(String msgLine) {
			
			String[] msg =msgLine.split(":");
			String protocol = msg[0];
			if(protocol.equals(Protocol.CHAT)) {
				String chatMsg = msg[1];
				chattingMsg(chatMsg);
			}
		}
		
		public void chattingMsg(String chatMsg) {
			System.out.println(TAG + chatMsg + socket.getInetAddress());
			try {
				for(SocketThread socketThread : vc) {
					if (socketThread != this) {
						socketThread.bw.write(chatMsg + "\n");
						socketThread.bw.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
