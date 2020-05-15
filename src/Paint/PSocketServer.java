package Paint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

// 얘가 1번 -> Canvas 디자인 부분임 -> Drawing 마우스이벤트부분임 
public class PSocketServer {
	ServerSocket serverSocket; // 서버소켓은 연결요청을 대기하다가 연결이 되면 소켓을 리턴하고 서버소켓에 연결된 선을 끊는다
	Vector<SocketThread> vc; // 소켓 스레드를 저장하기 위한 변수
	int x;
	int y;
	String line = null;
	String pos;

	public PSocketServer() {

		try {
			serverSocket = new ServerSocket(20000); // 포트를 20000번으로 하는 서버소켓을 생성

			vc = new Vector<>(); // 벡터 배열 생성

			while (true) {
				System.out.println("요청 대기");
				Socket socket = serverSocket.accept(); // 서버소켓에 요청이 오는 것을 대기
				System.out.println("요청 받음");
				SocketThread st = new SocketThread(socket); // 소켓을 받는 스레드를 생성
				st.start(); // 스레드 시작
				vc.add(st); // 벡터 배열에 스레드 저장

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class SocketThread extends Thread { // 소켓통신을 할 스레드를 만들기 위한 내부 클래스
		Socket socket; // 외부에서 받은 소켓을 넣는 공간
		String id; // 유저 id 저장
		BufferedReader reader;
		PrintWriter writer;

		public SocketThread(Socket socket) {
			this.socket = socket; // 스레드가 생성 되면 소켓을 받아서 저장한다
		}

		@Override
		public void run() {

			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);

				String line = null;
				while ((line = reader.readLine()) != null) { 
					System.out.println(line); // 클라이언트에서 받은 좌표 값을 출력
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	private void getPos() {
		// 좌표값을 다른 클라이언트에게 보내는 메서드 제작해야됨
	}

	public static void main(String[] args) {
		new PSocketServer();
	}
}
