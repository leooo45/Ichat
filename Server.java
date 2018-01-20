import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			Socket socket = ss.accept();
			System.out.println("connecting");
			DataInputStream input = new DataInputStream(socket.getInputStream());
			String clientInputStr = input.readUTF();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
			// 处理客户端数据
			System.out.println("客户端发过来的内容:" + clientInputStr);
			String s = input.readUTF();
			System.out.println("server get:" + s);
			socket.close();
			input.close();
			ss.close();
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}