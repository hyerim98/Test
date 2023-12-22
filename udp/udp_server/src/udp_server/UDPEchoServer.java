package udp_server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPEchoServer {
	
	public UDPEchoServer(int port) {
		try {
			// 매개변수 포트로 소켓을 생성하여 DatagramSocket 객체를 생성
			DatagramSocket ds = new DatagramSocket(port);
			while(true) {
				byte[] buffer = new byte[512];
				// client에서 받을 데이터그램 생성
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				System.out.println("ready");
				// 데이터 받는다
				ds.receive(dp);
				String str = new String(dp.getData());
				System.out.println("수신된 데이터 : " + str);
				
				InetAddress ia = dp.getAddress();
				port = dp.getPort();
				System.out.println("client ip : " + ia + ", client port : " + port);
				// 서버 정보 전송
				dp = new DatagramPacket(dp.getData(), dp.getData().length, ia, port);
				// 전송
				ds.send(dp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UDPEchoServer(3000);
	}

}
