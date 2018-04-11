/*
 * 用户登录界面
 * 更新时间：
 * ZQW-2018-03-13
 * ZQW-2018-03-14
 * ZQW-2018-03-16
 * ZQW-2018-03-20
 * ZQW-2018-03-23
 * ZQW-2018-03-24
 */

package viewer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

import controller.GetCode;
import controller.Login;
import controller.SetUI;
import model.FileText;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class LoginInterface 
{

	private JFrame frame;
	public JFrame getFrame()
	{
		return frame;
	}
	private JTextField textField;
	public Timer timer;//检测控件状态

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
					LoginInterface window = new LoginInterface();
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
	public LoginInterface() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() //初始化登录界面
	{
		//设置窗口的基本属性
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("等线 Light", Font.PLAIN, 18));
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginInterface.class.getResource("/logo/earth.png")));
		frame.setTitle("CAC\u767B\u5F55");
		frame.setBounds(200, 200, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginInterface.class.getResource("/loginImage/padlock.png")));
		lblNewLabel.setBounds(240, 220, 48, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginInterface.class.getResource("/loginImage/key.png")));
		lblNewLabel_1.setBounds(240, 290, 48, 48);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u6CA1\u6709\u8D26\u53F7\uFF1F");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(365, 170, 90, 37);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 24));
		textField.setBounds(300, 231, 234, 37);
		if(RegisterInterface.which)//注册页面成功转来
		{
			textField.setText(GetCode.code);
			GetCode.code = "";//重置摇号
			RegisterInterface.which = false;//重置
		}
		else//其他页面转来
		{
			textField.setText(FileText.getLineText(4));
			RegisterInterface.which = false;//重置
		}
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPasswordField textField_1 = new JPasswordField();//设置密码框
		textField_1.setFont(new Font("宋体", Font.PLAIN, 24));
		textField_1.setBounds(300, 301, 234, 37);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("WIS\u51FA\u54C1\uFF1ACAC\u9707\u64BC\u53D1\u5E03\uFF01");
		lblNewLabel_5.setForeground(Color.CYAN);
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 28));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(144, 15, 533, 48);
		frame.getContentPane().add(lblNewLabel_5);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() //记住密码
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(chckbxNewCheckBox.isSelected())//勾选了
				{
					FileText.writeLineText(2, "1");//修改勾选状态
					FileText.writeLineText(5, textField_1.getText());//存储当前密码
				}
				else
				{
					FileText.writeLineText(2, "0");
					FileText.writeLineText(5, "0");//删除当前密码
					textField_1.setText(null);//清空密码
				}
			}
		});
		chckbxNewCheckBox.setFont(new Font("幼圆", Font.PLAIN, 18));
		chckbxNewCheckBox.setBounds(300, 353, 110, 29);
		if(FileText.getLineText(2).equals("1"))
		{
			chckbxNewCheckBox.setSelected(true);//自动勾选
			textField_1.setText(FileText.getLineText(5));//真的记住了密码
		}
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		chckbxNewCheckBox_1.addMouseListener(new MouseAdapter() //自动登录
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(!chckbxNewCheckBox.isSelected() && chckbxNewCheckBox_1.isSelected())//记住密码未被勾选
				{
					JOptionPane.showMessageDialog(null, "请选择记住密码！", "有服务提示", 1);
					chckbxNewCheckBox_1.setSelected(false);
				}
				else 
				{
					if(!chckbxNewCheckBox_1.isSelected())//未选择自动登录
					{
						FileText.writeLineText(3, "0");
						lblNewLabel_5.setText("\u6B22\u8FCE\u4F7F\u7528WIS\u5B9E\u9A8C\u5BA4\u51FA\u54C1\u7684\u68CB\u724C\u6E38\u620FCAC");
					}
					else
					{//自动登录
						FileText.writeLineText(3, "1");//记录勾选
						Login.loginMenu(textField, textField_1, chckbxNewCheckBox_1, frame, timer, lblNewLabel_5);
					}
				}
			}
		});
		chckbxNewCheckBox_1.setFont(new Font("幼圆", Font.PLAIN, 18));
		chckbxNewCheckBox_1.setBounds(424, 353, 110, 29);
		if(FileText.getLineText(3).equals("1"))
			chckbxNewCheckBox_1.setSelected(true);//自动勾选
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("\u53BB\u6CE8\u518C");
		lblNewLabel_3.addMouseListener(new MouseAdapter() //转到注册页面
		{
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{
				frame.setVisible(false);
				RegisterInterface window = new RegisterInterface();
				window.getFrame().setVisible(true);
			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.MAGENTA);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(461, 170, 77, 37);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u767B\u5F55");
		lblNewLabel_4.addMouseListener(new MouseAdapter() //登录操作
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				lblNewLabel_4.setForeground(Color.green);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblNewLabel_4.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Login.loginMenu(textField, textField_1, chckbxNewCheckBox_1, frame, timer, lblNewLabel_5);
			}
		});
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel_4.setIcon(null);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(549, 295, 48, 48);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel label = new JLabel("\u8BF7\u767B\u5F55\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 24));
		label.setBounds(240, 168, 110, 37);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setIcon(new ImageIcon(LoginInterface.class.getResource("/loginImage/text.png")));
		lblNewLabel_6.setBounds(15, 80, 42, 42);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u7528\u6237\u987B\u77E5");
		lblNewLabel_7.setFont(new Font("等线 Light", Font.PLAIN, 10));
		lblNewLabel_7.setBounds(15, 122, 42, 20);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setIcon(new ImageIcon(LoginInterface.class.getResource("/loginImage/edit.png")));
		lblNewLabel_8.setBounds(15, 142, 42, 42);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("\u66F4\u65B0\u8BB0\u5F55");
		lblNewLabel_9.setFont(new Font("等线 Light", Font.PLAIN, 10));
		lblNewLabel_9.setBounds(15, 184, 42, 20);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(LoginInterface.class.getResource("/loginImage/clipboard.png")));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(15, 204, 42, 42);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("\u6700\u8FD1\u6D3B\u52A8");
		lblNewLabel_11.setFont(new Font("等线 Light", Font.PLAIN, 10));
		lblNewLabel_11.setBounds(15, 246, 42, 20);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("\u5FD8\u8BB0\u5BC6\u7801");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setForeground(Color.BLUE);
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_12.setBounds(549, 231, 90, 37);
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(LoginInterface.class.getResource("/loginImage/settings.png")));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setBounds(737, 80, 42, 42);
		frame.getContentPane().add(lblNewLabel_13);
		
		JButton btnNewButton = new JButton("\u5355\u673A\u7248");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				JOptionPane.showMessageDialog(null, "敬请期待！", "无服务提示", 1);
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("幼圆", Font.PLAIN, 18));
		btnNewButton.setBounds(15, 497, 90, 42);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_14 = new JLabel("\u8BBE\u7F6E");
		lblNewLabel_14.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				lblNewLabel_14.setForeground(Color.cyan);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblNewLabel_14.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			}
		});
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setFont(new Font("等线 Light", Font.PLAIN, 16));
		lblNewLabel_14.setBounds(737, 122, 42, 20);
		frame.getContentPane().add(lblNewLabel_14);
		
		SetUI.setFrameCenter(frame);
		timer = new Timer();//用于初次登录
		if(ConnectInterface.which && chckbxNewCheckBox_1.isSelected() && chckbxNewCheckBox.isSelected())
		{
			lblNewLabel_5.setText("登录中...");
			timer.schedule(new TimerTask()
			{
				@Override
				public void run() 
				{
					// TODO Auto-generated method stub
					if(chckbxNewCheckBox_1.isSelected() && chckbxNewCheckBox.isSelected())//更新自动登录状态
					{
						ConnectInterface.which = false;
						Login.loginMenu(textField, textField_1, chckbxNewCheckBox_1, frame, timer, lblNewLabel_5);
					}
				}
			}, 2000);//两秒后自动登录
		}
	}
}
