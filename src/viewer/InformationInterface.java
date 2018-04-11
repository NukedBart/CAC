/*
 * 显示用户信息界面
 */
package viewer;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.Modify;
import controller.SetUI;
import model.DataBase;
import model.FileText;
import model.LoadCheckBox;
import model.LoadComboBox;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class InformationInterface
{

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
					InformationInterface window = new InformationInterface();
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
	public InformationInterface() 
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
		
		frame.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
	            DataBase.modifyDataBase("softwareupdate", "Edition", MenuInterface.version, "OnlineNumber", 
	            		String.valueOf(Integer.parseInt(
	            		DataBase.searchDataBase(
	            				"softwareupdate", "Edition", MenuInterface.version, "OnlineNumber", true)) - 1));
				//关闭窗口时的事件，之前运行
			}
		});
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE\u83DC\u5355");
		btnNewButton.addActionListener(new ActionListener()//返回菜单
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frame.setVisible(false);
				MenuInterface window = new MenuInterface();
				window.getFrame().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("幼圆", Font.PLAIN, 20));
		btnNewButton.setBounds(15, 504, 120, 40);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		if(DataBase.searchDataBase("user", "Code", MenuInterface.code, "HeadPortrait", true).equals(""))//初始头像
			lblNewLabel.setIcon(new ImageIcon(InformationInterface.class.getResource("/headPortrait/1.png")));
		else
			lblNewLabel.setIcon(new ImageIcon(InformationInterface.class.getResource(
					DataBase.searchDataBase("user", "Code", MenuInterface.code, "HeadPortrait", true))));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(140, 15, 48, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() //选择头像
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				lblNewLabel.setIcon(new ImageIcon(InformationInterface.class.getResource(
						"/headPortrait/" + comboBox.getSelectedItem())));
			}
		});
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(200, 37, 90, 26);
		frame.getContentPane().add(comboBox);
		LoadComboBox.chooseHeadPortrait(comboBox);//初始化头像选项
		
		JLabel lblNewLabel_1 = new JLabel("WIS\u53F7\uFF1A" + MenuInterface.code);//显示WIS号
		lblNewLabel_1.setForeground(new Color(153, 50, 204));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(100, 80, 250, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u6635\u79F0\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(100, 110, 60, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setText(DataBase.searchDataBase("user", "Code", MenuInterface.code, "Nickname", true));
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(160, 110, 190, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(100, 150, 60, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u7537\u751F");
		chckbxNewCheckBox.setFont(new Font("宋体", Font.PLAIN, 20));
		chckbxNewCheckBox.setBounds(160, 150, 80, 22);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u5973\u751F");
		chckbxNewCheckBox_1.setFont(new Font("宋体", Font.PLAIN, 20));
		chckbxNewCheckBox_1.setBounds(270, 150, 80, 22);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		LoadCheckBox.setCheckBox(chckbxNewCheckBox, chckbxNewCheckBox_1);//初始化两个CheckBox
		
		JLabel lblNewLabel_4 = new JLabel("\u751F\u65E5\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(100, 190, 60, 22);
		frame.getContentPane().add(lblNewLabel_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 18));
		comboBox_1.setBounds(160, 190, 70, 22);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("宋体", Font.PLAIN, 18));
		comboBox_2.setBounds(240, 190, 50, 22);
		frame.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.addMouseListener(new MouseAdapter() //动态刷新日数，监听事件要改
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				LoadComboBox.setDaysList(comboBox_1, comboBox_2, comboBox_3);
			}
		});
		comboBox_3.setFont(new Font("宋体", Font.PLAIN, 18));
		comboBox_3.setBounds(300, 190, 50, 22);
		frame.getContentPane().add(comboBox_3);
		
		LoadComboBox.setBirthDay(comboBox_1, comboBox_2, comboBox_3);//初始化生日选项
		
		JLabel lblNewLabel_5 = new JLabel("\u6CE8\u518C\u65F6\u95F4\uFF1A" + 
		DataBase.searchDataBase("user", "Code", MenuInterface.code, "RegisterTime", true));//显示注册时间
		lblNewLabel_5.setForeground(new Color(30, 144, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(100, 230, 250, 22);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u7B7E\u540D\uFF1A");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(100, 270, 60, 22);
		frame.getContentPane().add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setText(DataBase.searchDataBase("user", "Code", MenuInterface.code, "Signature", true));
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBounds(160, 270, 190, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\u90AE\u7BB1\uFF1A");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(100, 310, 60, 22);
		frame.getContentPane().add(lblNewLabel_7);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setText(DataBase.searchDataBase("user", "Code", MenuInterface.code, "Mailbox", true));
		textField_2.setBounds(160, 310, 190, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("\u8D22\u5BCC\uFF1A" + 
		DataBase.searchDataBase("user", "Code", MenuInterface.code, "Coin", true).concat("$"));
		lblNewLabel_8.setForeground(new Color(127, 255, 0));
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(100, 350, 250, 22);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(100, 390, 60, 22);
		frame.getContentPane().add(lblNewLabel_9);
		
		JPasswordField textField_3 = new JPasswordField();
		textField_3.setColumns(10);
		textField_3.setText(DataBase.searchDataBase("user", "Code", MenuInterface.code, "Password", true));
		textField_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_3.setBounds(160, 390, 190, 22);
		frame.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_10 = new JLabel("\u4FEE\u6539");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_10.addMouseListener(new MouseAdapter()//修改按钮
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 20));
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(Modify.judgeInformation(textField.getText(), textField_1.getText(), textField_2.getText(),
					  textField_3.getText(), lblNewLabel_2, lblNewLabel_6, lblNewLabel_7, lblNewLabel_9))
					//信息都合法
					Modify.modifyInformation(comboBox.getSelectedItem().toString(), //头像
							comboBox_1.getSelectedItem().toString(), //年份
							comboBox_2.getSelectedItem().toString(), //月份
							comboBox_3.getSelectedItem().toString(), //日期
							textField.getText(), textField_1.getText(), 
							textField_2.getText(), textField_3.getText(), 
							chckbxNewCheckBox.isSelected());
				else
					return;
			}
		});
		lblNewLabel_10.setForeground(new Color(255, 0, 0));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(290, 430, 60, 24);
		frame.getContentPane().add(lblNewLabel_10);
		
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() //监听
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				LoadCheckBox.chooseACheckBox(chckbxNewCheckBox, chckbxNewCheckBox_1);
			}
		});
		
		chckbxNewCheckBox_1.addMouseListener(new MouseAdapter() //监听
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				LoadCheckBox.chooseACheckBox(chckbxNewCheckBox_1, chckbxNewCheckBox);
			}
		});
		
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(InformationInterface.class.getResource("/logo/earth.png")));
		frame.setResizable(false);
		frame.setTitle("CAC");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SetUI.setFrameCenter(frame);
	}
}
