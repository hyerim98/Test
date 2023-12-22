package udp_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPEchoClient {
	
	private String str;
	private BufferedReader file;
	private static int SERVER_PORT = 3000;
	
	public UDPEchoClient(String ip, int port) {
		try {
			InetAddress ia = InetAddress.getByName(ip);
			// 매개변수 포트로 소켓을 생성하여 DatagramSocket 객체를 생성
			DatagramSocket ds = new DatagramSocket(port);
			System.out.print("message : ");
			file = new BufferedReader(new InputStreamReader(System.in));
			str = file.readLine();
			// str to buffer
			byte buffer[] = str.getBytes();
			// 데이터를 해당 ip, port로 전달
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, SERVER_PORT);
			// UDP 데이터그램을 전송
			ds.send(dp);
			buffer = new byte[512];
			dp = new DatagramPacket(buffer, buffer.length);
			// UDP 데이터그램을 받아서 이미 존재하는 DatagramPacket 객체인 dp에 저장
			ds.receive(dp);
			System.out.println("server ip : " + dp.getAddress() + ", server port : " + dp.getPort());
			System.out.println("수신된 데이터 : " + new String(dp.getData()).trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new UDPEchoClient("localhost", 2000);
	}

}
