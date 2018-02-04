import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
@SuppressWarnings("serial")
public class Test extends JFrame{
  private static String s = "";
  public static final String IP_ADDR = "localhost";//服务器地址
  public static final int PORT = 12345;
  public static Socket socket = null;
  public static JTextArea t1 = new JTextArea();
  public static JTextArea t2 = new JTextArea();
  public Test() {
	  //JPanel p = (JPanel)this.getContentPane();
	  Font f = new Font("宋体",Font.BOLD,20);
	  setLayout(null);
	  
	  t1.setBounds(27,20,400,400);
	  t1.setFont(f);
	  
	  t2.setBounds(27,440,400,100);
	  t2.setFont(f);
	  add(t1);
	  add(t2);
	  JButton btn = new JButton("sent");
	  btn.setBounds(360,560,70,30);
	  add(btn);
	  btn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				//创建一个流套接字并将其连接到指定主机上的指定端口号
				socket = new Socket(IP_ADDR, PORT);
				//读取服务器端数据
				// DataInputStream input = new DataInputStream(socket.getInputStream());
				//向服务器端发送数据
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				//System.out.print("客户请输入: \t");
				String s1 = t2.getText();
				out.writeUTF(s1);
				out.close();
				s = s + "发出信息 ： " + s1 + '\n';
				t1.setText(s);
				socket.close();
			} 
			catch(Exception e1) {
			}
		}
	});
	  JLabel l = new JLabel(new ImageIcon("E:\\my programming\\CChat\\src\\pic\\timg.jpg"));
	  l.setBounds(0,0,480,660);
	  add(l);
	  setTitle("my friend");
	  setSize(480,660);
	  setLocationRelativeTo(null);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public static void main(String[] args) {
	  Test t = new Test();
	  t.setVisible(true);
	  while(true) {
		  try {
		  DataInputStream input = new DataInputStream(socket.getInputStream());
		  String s2 = input.readUTF();
		  if(s2 != "") {
			  s = s + "收到信息  ： " + s2 + '\n';
			  t1.setText(s);
		  }
		  //server.close();
		  socket.close();
		  input.close();
	  }
		  catch(IOException e3) {
			  System.out.println(e3.getMessage());}  
		  }
  }
}