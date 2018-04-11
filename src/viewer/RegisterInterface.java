/*
 * 用户注册界面
 * 更新时间：
 * ZQW-2018-03-14
 * ZQW-2018-03-15
 * ZQW-2018-03-16
 * ZQW-2018-03-23
 */

package viewer;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.GetCode;
import controller.Register;
import controller.SetUI;
import model.FileText;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterInterface 
{

	public static boolean which = false;//用来存储注册新账号成功与否的信息
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	public JFrame getFrame()
	{
		return frame;
	}

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
					RegisterInterface window = new RegisterInterface();
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
	public RegisterInterface()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4E00\u53F0\u7535\u8111\u53EA\u5141\u8BB8\u6CE8\u518C\u4E00\u6B21\uFF01");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(193, 15, 388, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("WIS\u53F7\u7801");
		lblNewLabel_1.setFont(new Font("幼圆", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(100, 100, 100, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u6635\u79F0");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("幼圆", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(100, 180, 100, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5BC6\u7801");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("幼圆", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(100, 260, 100, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_4.setFont(new Font("幼圆", Font.PLAIN, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(100, 340, 100, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u90AE\u7BB1");
		lblNewLabel_5.setFont(new Font("幼圆", Font.PLAIN, 20));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(100, 420, 100, 30);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setEditable(false);
		textField.setBounds(215, 102, 200, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBounds(215, 182, 200, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setBounds(215, 262, 200, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_3.setBounds(215, 342, 200, 28);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_4.setBounds(215, 422, 200, 28);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		lblNewLabel_7 = new JLabel("\u6211\u8981\u9753\u53F7");
		lblNewLabel_7.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 22));
			}
			public void mouseExited(MouseEvent arg0) 
			{
				lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "请申请管理员！", "有服务提示", 1);
			}
		});
		lblNewLabel_7.setForeground(Color.CYAN);
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(545, 100, 120, 30);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE\u767B\u5F55");
		btnNewButton.addMouseListener(new MouseAdapter()//返回登录界面
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				frame.setVisible(false);
				LoginInterface window = new LoginInterface();
				window.getFrame().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(15, 502, 142, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u7533\u8BF7\u7BA1\u7406\u5458");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(null, "敬请期待！", "无服务提示", 1);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setBounds(637, 502, 142, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_9 = new JLabel("6-9\u4F4D\u968F\u673A\u53F7\u7801");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(215, 130, 160, 20);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("2-8\u4E2A\u5B57\u7B26");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_10.setBounds(215, 210, 160, 20);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("6-16\u4E2A\u5B57\u7B26");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_11.setBounds(215, 290, 160, 20);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("\u7528\u4E8E\u627E\u56DE\u5BC6\u7801");
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_12.setBounds(215, 450, 160, 20);
		
		lblNewLabel_6 = new JLabel("\u6447\u53F7");
		lblNewLabel_6.addMouseListener(new MouseAdapter()//摇号操作
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				lblNewLabel_6.setFont(new Font("黑体", Font.BOLD, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblNewLabel_6.setFont(new Font("黑体", Font.BOLD, 20));
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(FileText.getLineText(1).equals("0"))//未注册
					GetCode.getCode(textField);
				else
				{
					lblNewLabel_9.setText("\u60A8\u5DF2\u7ECF\u6CE8\u518C\u8FC7\u4E86\uFF01");
					lblNewLabel_9.setForeground(Color.red);
				}
			}
		});
		lblNewLabel_6.setForeground(Color.MAGENTA);
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("黑体", Font.BOLD, 20));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(443, 100, 66, 30);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("\u786E\u8BA4\u6CE8\u518C");
		lblNewLabel_8.addMouseListener(new MouseAdapter()//注册操作
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(Register.registerWIS(textField_1, textField_2, textField_3, textField_4, 
						lblNewLabel_10, lblNewLabel_11, lblNewLabel_12))
				{//输入数据没问题时连接数据库
					if(GetCode.code.equals(""))//记得回登录界面时重置其值
					{//未摇号
						lblNewLabel_9.setForeground(Color.red);
						lblNewLabel_9.setText("请摇号！");
					}
					else
					{
						if(Register.addToDataBase(textField_1, textField_2, textField_4))//加载登录页面
						{
							FileText.writeLineText(4, GetCode.code);//存储获得的账号
							which = true;//注册成功
							frame.setVisible(false);
							LoginInterface window = new LoginInterface();
							window.getFrame().setVisible(true);
						}
						else
							return;
					}
				}
				else
					return;
			}
		});
		lblNewLabel_8.setForeground(new Color(30, 144, 255));
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(443, 420, 100, 30);
		frame.getContentPane().add(lblNewLabel_8);
		
		frame.getContentPane().add(lblNewLabel_12);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterInterface.class.getResource("/logo/earth.png")));
		frame.setTitle("CAC\u6CE8\u518C");
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SetUI.setFrameCenter(frame);
	}
}
