package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import utils.Protocol;

public class ServerEx extends Thread implements Protocol {
	
	private final static String TAG = "MainServer : ";

	ServerSocket serverSocket;
	HashMap<String, UserInfo> userInfo = new HashMap<String, UserInfo>();
//	Vector<RoomInfo> RI = new Vector<RoomInfo>();
	Database db;

	ServerEx() {
		db = new Database();
		Collections.synchronizedMap(userInfo);
		try {
			serverSocket = new ServerSocket(9900);
		} catch (IOException e) {
			System.out.println(TAG + "serverSocket");
			e.printStackTrace();
		}
		start();
	}

	public void start() {
		while (true) {
			try {
				Thread th = new Thread(new UserInfo(serverSocket.accept()));
				th.start();
			} catch (IOException e) {
				System.out.println(TAG + "start().thread");
				e.printStackTrace();
			}
		}
	}

	public String CheckId(String s) {
		Iterator it = userInfo.keySet().iterator();
		boolean t = true;
		while (it.hasNext()) {
			if (userInfo.get(it.next()).ID.equals(s)) {
				t = false;
			}
		}
		return String.valueOf(t);
	}

//	public RoomInfo getRoomInfo(String s) {
//		int size = RI.size();
//		String roomname = s;
//		RoomInfo reginfo = null;
//		for (int i = 0; i < size; i++) {
//			reginfo = RI.get(i);
//			if (reginfo.getRoomName() == roomname) {
//				break;
//			}
//		}
//		return reginfo;
//	}

//	public String FRoomUpdate(int i) {
//		return RI.get(i).getRoomInfo();
//	}

	public void sendtoall(String s) {
		String msg = s;
		Iterator it = userInfo.keySet().iterator();
		while (it.hasNext()) {
			DataOutputStream reg = userInfo.get(it.next()).dout;
			try {
				reg.writeUTF(msg);
			} catch (Exception e) {
				System.out.println(TAG + "msg");
				e.printStackTrace();
			}
		}
	}

	class UserInfo implements Runnable {
		
		Socket usersocket;
		DataOutputStream dout;
		String ID;
		RoomInfo Room;

		UserInfo(Socket s) {
			try {
				usersocket = s;
				dout = new DataOutputStream(s.getOutputStream());
			} catch (Exception e) {
				System.out.println(TAG + "UserInfo.Socket");
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			DataInputStream din;
			try {
				din = new DataInputStream(usersocket.getInputStream());
				while (din != null) {
					String h = din.readUTF();
					processing(h);
				}
			} catch (IOException e) {
				if (Room.getRoomSize() == 0) {
					sendtoall(Room.getRoomName());
//					RI.remove(Room);
				} else {
					Room.broadcast(ID);
					Room.remove(ID);
					if (Room.host == ID)
						Room.TokenChange(ID);
				}
			} finally {
				userInfo.remove(ID);
				usersocket = null;
				dout = null;
				Room = null;
			}
		}

		public void requestMsg(String s) {
			String msg = s;
			try {
				dout.writeUTF(msg);
			} catch (Exception e) {
				System.out.println(TAG + "requestMsg");
				e.printStackTrace();
			}
		}

		public void processing(String s) {
			String msg = s;
			String[] g = msg.split("#");

			switch (Integer.valueOf(g[1])) {
			case Signin:
				requestMsg(db.Signin(g[0]) + "#" + Signin);
				break;
			case SignCheck:
				requestMsg(db.Checkid(g[0]) + "#" + SignCheck);
				break;
			case Login:
				String check = db.Checkid(g[0]);
				String incheck = CheckId(g[0]);

				requestMsg(check + "/" + incheck + "#" + Login);

				if (Boolean.valueOf(check) && Boolean.valueOf(incheck)) {
					ID = g[0];
					userInfo.put(ID, this);
//					if (RI.size() != 0) {
//						for (int i = 0; i < RI.size(); i++) {
//							requestMsg(FRoomUpdate(i) + "#" + RoomListAdd);
//						}
					}
				}
				break;
//			case CreRoom:
//				Room = new RoomInfo(g[0], ID);
//				RI.add(Room);
//				Room.setUserInfo(this);
//				requestMsg(" #" + Roomin);
//				requestMsg("true#" + TokenChange);
//				sendtoall(Room.getRoomInfo() + "#" + RoomListAdd);
//				Room.broadcast(ID + "#" + UserListAdd);
//				break;
//			case Roomin:
//				boolean t = true;
//				Room = getRoomInfo(g[0]);
//				try {
//					for (int i = 0; i < Room.getRoomSize(); i++) {
//						requestMsg(Room.RoomUser.get(i).ID + "#" + UserListAdd);
//					}
//				} catch (NullPointerException e) {
//					requestMsg(g[0] + "#" + UserListRemove);
//					t = false;
//				}
//				if (t) {
//					Room.setUserInfo(this);
//					requestMsg(" #" + Roomin);
//					sendtoall(Room.getRoomInfo() + "#" + RoomListAdd);
//					Room.broadcast(ID + "#" + UserListAdd);
//				}
//				break;
//			case ExitRoom:
//				Room.TokenChange(ID);
//				requestMsg("false" + "#" + TokenChange);
//				Room.remove(ID);
//				if (Room.getRoomSize() == 0) {
//					sendtoall(Room.getRoomName() + "#" + RoomListRemove);
//					requestMsg(" #" + RoomClear);
//					RI.remove(Room);
//				} else {
//					sendtoall(Room.getRoomInfo() + "#" + RoomListAdd);
//					requestMsg(" #" + RoomClear);
//					Room.broadcast(ID + "#" + UserListRemove);
//				}
//				break;
//			case TokenChange:
//				Room.TokenChange(ID);
//				requestMsg("false" + "#" + TokenChange);
//				break;
//			case Chat:
//				Room.broadcast(msg);
//				break;
//			case Draw:
//				Room.broadcast(msg);
//				break;
//			default:
//				Room.broadcast(msg);
//				break;
			}
		}

//	class RoomInfo {
//		private Vector<UserInfo> RoomUser = new Vector<UserInfo>();
//		private String roomname;
//		private String host;
//		private int roomsize;
//
//		RoomInfo(String s, String host) {
//			roomname = s;
//			this.host = host;
//		}

		public void setUserInfo(UserInfo i) {
			RoomUser.add(i);
			roomsize = RoomUser.size();
		}

		public void TokenChange(String name) {
			for (int i = 0; i < RoomUser.size(); i++) {
				if (RoomUser.get(i).ID != name) {
					RoomUser.get(i).requestMsg("true" + "#" + TokenChange);
					host = RoomUser.get(i).ID;
					break;
				}
			}
		}

		public String getRoomName() {
			return roomname;
		}

		public int getRoomSize() {
			return roomsize;
		}

		public String getRoomInfo() {
			return roomname + "/" + host + "/" + roomsize;
		}

		public void remove(String s) {
			String removename = s;
			for (int i = 0; i < roomsize; i++) {
				if (removename.equals(RoomUser.elementAt(i).ID)) {
					RoomUser.remove(i);
					break;
				}
			}
			roomsize = RoomUser.size();
		}

		public void broadcast(String s) {
			String msg = s;
			for (int i = 0; i < roomsize; i++) {
				try {
					RoomUser.get(i).dout.writeUTF(msg);
				} catch (IOException e) {
				} catch (NullPointerException e1) {
				}

			}
		}
	}

	public static void main(String args[]) {
		new server();
	}
}
