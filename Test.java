import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("serial")
public class Test extends JFrame{
	static private Socket clientSocket;
	public static void main(String[] args) {
		try {
			String serverIP;
			serverIP = "localhost";
			clientSocket = new Socket(serverIP, 6789);
			Test test = new Test();
			test.setVisible(true);
			test.start();
		}
		catch(IOException e3) {
			System.out.println(e3.getMessage());
		}

	}
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
					// 建立输出流，给服务端发信息
					PrintWriter pw = new PrintWriter(
							new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);


					pw.println(t2.getText());
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	  	JLabel l = new JLabel(new ImageIcon("./01.jpg"));
	  	l.setBounds(0,0,480,660);
	  	add(l);
	  	setTitle("my friend");
	  	setSize(480,660);
	  	setLocationRelativeTo(null);
	  	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	}
	public void start() {
		try {
			// 接收服务器端发送过来的信息的线程启动
			Scanner scanner = new Scanner(System.in);
			setName(scanner);
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new Test.ListenrServser());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void setName(Scanner scan) throws Exception {
		String name;
		//创建输出流
		PrintWriter pw = new PrintWriter(
				new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"),true);
		//创建输入流
		BufferedReader br = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));

		while(true) {
			System.out.println("请创建您的昵称：");
			name = scan.nextLine();
			if (name.trim().equals("")) {
				System.out.println("昵称不得为空");
			} else {
				pw.println(name);
				String pass = br.readLine();
				if (pass != null && (!pass.equals("OK"))) {
					System.out.println("昵称已经被占用，请重新输入：");
				} else {
					System.out.println("昵称“"+name+"”已设置成功，可以开始聊天了");
					break;
				}
			}
		}
	}
  	class ListenrServser implements Runnable {
		@Override
		public void run() {
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
				String msgString;
				while((msgString = br.readLine())!= null) {
					t1.append(msgString + '\n');
					System.out.println(msgString);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}