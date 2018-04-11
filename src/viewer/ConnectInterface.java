/*
 * 启动程序时检测网络状态并连接服务器的界面
 * 更新时间：
 * ZQW-2018-03-13
 * ZQW-2018-03-14
 * ZQW-2018-03-16
 * ZQW-2018-03-20
 * ZQW-2018-03-23
 */

package viewer;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.SetUI;
import controller.Waiting;
import model.DataBase;
import model.FileText;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextField;

public class ConnectInterface 
{

	private JFrame frame;
	public static boolean which = false;//用来确定进入登录界面的时哪一个界面
	public static String IP = FileText.getLineText(7);//初始化IP
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ConnectInterface window = new ConnectInterface();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConnectInterface() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8FDE\u63A5\u670D\u52A1\u5668\u4E2D");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(76, 15, 148, 42);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("\u00B7");
		lblNewLabel_1.setFont(new Font("幼圆", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(96, 60, 36, 36);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("\u00B7");
		lblNewLabel_2.setFont(new Font("幼圆", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(132, 60, 36, 36);
		frame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		JLabel lblNewLabel_3 = new JLabel("\u00B7");
		lblNewLabel_3.setFont(new Font("幼圆", Font.BOLD, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(168, 60, 36, 36);
		frame.getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		JLabel lblip = new JLabel("\u670D\u52A1\u5668IP\uFF1A");
		lblip.setHorizontalAlignment(SwingConstants.CENTER);
		lblip.setFont(new Font("宋体", Font.PLAIN, 20));
		lblip.setBounds(76, 132, 148, 21);
		frame.getContentPane().add(lblip);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(76, 168, 148, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u8FDE\u63A5\u670D\u52A1\u5668");
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Timer timer = new Timer();
				Waiting.waitConnect(lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3);
				timer.schedule(new TimerTask() 
				{	
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						if(!textField.getText().equals(""))//输入了IP，进行更改
						{
							FileText.writeLineText(7, textField.getText());
							IP = textField.getText();
						}
						if(DataBase.linkDataBase())//连接成功则加载登录界面
						{
							which = true;//设置为该界面登录
							frame.setVisible(false);
						    LoginInterface window = new LoginInterface();
						    window.getFrame().setVisible(true);
							timer.cancel();
						}
						else//连接失败则提示用户并更新界面
						{
							Waiting.timer.cancel();
							lblNewLabel.setVisible(false);
							lblNewLabel_1.setVisible(false);
							lblNewLabel_2.setVisible(false);
							lblNewLabel_3.setVisible(false);
							timer.cancel();
							JOptionPane.showMessageDialog(null, "无法连接到服务器！", "无服务提示", 0);
						}
					}
				}, 0, 1000);
			}
		});
		btnNewButton.setFont(new Font("幼圆", Font.PLAIN, 18));
		btnNewButton.setBounds(76, 217, 148, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5355\u673A\u6E38\u620F");
		btnNewButton_1.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				JOptionPane.showMessageDialog(null, "敬请期待！", "无服务提示", 1);
			}
		});
		btnNewButton_1.setFont(new Font("幼圆", Font.PLAIN, 18));
		btnNewButton_1.setBounds(76, 287, 148, 42);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("\u7248\u672C\u53F7\uFF1AV1.0.0");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(76, 96, 148, 21);
		frame.getContentPane().add(lblNewLabel_4);
		
		frame.setTitle("CAC");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ConnectInterface.class.getResource("/logo/earth.png")));
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SetUI.setFrameCenter(frame);
	}
}
