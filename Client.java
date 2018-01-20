import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;

public class Client{
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("27.17.168.109",8888);
			
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			Scanner input = new Scanner(System.in);
			System.out.println("请输入要传输的信息：");
			String s = input.nextLine();
			while(s != null) {
			
			out.writeUTF(s);
			s = input.nextLine();
			}
			socket.close();
			//in.close();
			out.close();
			input.close();
			
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
		}
}