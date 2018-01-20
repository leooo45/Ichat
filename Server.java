import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			Socket socket = ss.accept();
			System.out.println("已连接");
		 DataInputStream in = new DataInputStream(socket.getInputStream());
		 
		 String s = in.readUTF();
		 while(s != null) {
			 System.out.println("server接收到的信息为：" + s);
			 s = in.readUTF();
		 }
		
		 socket.close();
		
		 in.close();
		 ss.close();
	}
		catch(IOException e) {
			System.out.println(e);
		}
}
}