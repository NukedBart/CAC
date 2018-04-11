/*
 * 用户菜单界面
 * 更新时间：
 * ZQW-2018-03-16
 * ZQW-2018-03-23
 * ZQW-2018-03-24
 */
package viewer;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.CreatRoom;
import controller.SetUI;
import model.DataBase;
import model.FileText;
import model.LoadComboBox;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MenuInterface 
{

	private JFrame frame;
	public static String code = FileText.getLineText(4);//获取账号
	private Timer timer;//定时获取在线人数
	private JTextField textField;
	public static String version = FileText.getLineText(6);
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
					MenuInterface window = new MenuInterface();
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
	public MenuInterface() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("CAC");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MenuInterface.class.getResource("/logo/earth.png")));
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		frame.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
	            DataBase.modifyDataBase("softwareupdate", "Edition", version, "OnlineNumber", 
	            		String.valueOf(Integer.parseInt(
	            		DataBase.searchDataBase(
	            				"softwareupdate", "Edition", version, "OnlineNumber", true)) - 1));
				//关闭窗口时的事件，之前运行
			}
		});
		
		JButton btnNewButton = new JButton("\u9000\u51FA\u767B\u5F55");
		btnNewButton.addMouseListener(new MouseAdapter()//退出登录 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
	            DataBase.modifyDataBase("softwareupdate", "Edition", version, "OnlineNumber", 
	            		String.valueOf(Integer.parseInt(
	            		DataBase.searchDataBase(
	            				"softwareupdate", "Edition", version, "OnlineNumber", true)) - 1));
				frame.setVisible(false);
				LoginInterface window = new LoginInterface();
				window.getFrame().setVisible(true);
				timer.cancel();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(15, 495, 120, 50);
		
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		if(DataBase.searchDataBase("user", "Code", code, "HeadPortrait", true).equals(""))//未设置头像
			lblNewLabel.setIcon(new ImageIcon(MenuInterface.class.getResource("/headPortrait/1.png")));
		else
			lblNewLabel.setIcon(new ImageIcon(MenuInterface.class.getResource(
					DataBase.searchDataBase("user", "Code", code, "HeadPortrait", true))));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(81, 15, 48, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("显示错误！");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(15, 63, 180, 32);
		lblNewLabel_1.setText(DataBase.searchDataBase("user", "Code", code, "Nickname", true));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("100,000,000 Wib");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setForeground(new Color(106, 90, 205));
		lblNewLabel_2.setIcon(new ImageIcon(MenuInterface.class.getResource("/menuImage/cash.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(15, 95, 180, 32);
		lblNewLabel_2.setText(DataBase.searchDataBase("user", "Code", code, "Coin", true).concat("$"));
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		btnNewButton_1.addActionListener(new ActionListener() //修改信息
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.setVisible(false);
				InformationInterface window = new InformationInterface();
				window.frame.setVisible(true);
				timer.cancel();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setBounds(25, 130, 160, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("\u5728\u7EBF\u4EBA\u6570\uFF1A");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(639, 15, 140, 32);
		frame.getContentPane().add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter()//动态刷新房间选项
		{
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				LoadComboBox.setRoom(comboBox);
			}
		});
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(318, 63, 140, 30);
		frame.getContentPane().add(comboBox);
		
		
		JLabel lblNewLabel_4 = new JLabel("\u623F   \u95F4");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(235, 63, 81, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u9009\u62E9");
		lblNewLabel_5.addMouseListener(new MouseAdapter() //选择房间
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 22));
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(CreatRoom.chooseRoom(comboBox.getSelectedItem().toString()))
				{
					frame.setVisible(false);
					BetForThree window = new BetForThree();
					window.frame.setVisible(true);
					timer.cancel();
				}
			}
		});
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(466, 63, 60, 32);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(DataBase.searchDataBase("user", "Code", code, "Signature", true));
		lblNewLabel_6.setForeground(Color.MAGENTA);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(200, 15, 400, 30);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u8F93\u5165\u623F\u53F7");
		lblNewLabel_7.setForeground(Color.BLUE);
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(235, 130, 81, 30);
		frame.getContentPane().add(lblNewLabel_7);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(318, 130, 140, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("\u8FDB\u623F");
		lblNewLabel_8.addMouseListener(new MouseAdapter() //搜索房间
		{
			@Override
			public void mouseEntered(MouseEvent e) 
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
				if(CreatRoom.queryRoom(textField.getText()))
				{
					frame.setVisible(false);
					BetForThree window = new BetForThree();
					window.frame.setVisible(true);
					timer.cancel();
				}
			}
		});
		lblNewLabel_8.setForeground(Color.RED);
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(466, 130, 60, 30);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("\u8D22\u5BCC\u699C");
		lblNewLabel_9.setForeground(new Color(148, 0, 211));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		lblNewLabel_9.setBounds(60, 200, 100, 28);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("\u516C\u544A\u680F");
		lblNewLabel_10.setForeground(new Color(255, 0, 255));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("楷体", Font.PLAIN, 22));
		lblNewLabel_10.setBounds(650, 63, 80, 24);
		frame.getContentPane().add(lblNewLabel_10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.ORANGE);
		textPane.setEditable(false);
		textPane.setFont(new Font("宋体", Font.PLAIN, 20));
		textPane.setText(DataBase.searchDataBase("softwareupdate", "Edition", 
				version, "Notice", true));
		textPane.setBounds(605, 95, 174, 65);
		frame.getContentPane().add(textPane);
		
		JLabel lblNewLabel_11 = new JLabel("1 ");
		lblNewLabel_11.setForeground(Color.BLACK);
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_11.setBounds(15, 240, 200, 22);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("2 ");
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_12.setBounds(15, 270, 200, 22);
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("3 ");
		lblNewLabel_13.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_13.setBounds(15, 300, 200, 22);
		frame.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("4 ");
		lblNewLabel_14.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_14.setBounds(15, 330, 200, 22);
		frame.getContentPane().add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("5 ");
		lblNewLabel_15.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_15.setBounds(15, 360, 200, 22);
		frame.getContentPane().add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("A.\u8D4C\u4E09\u5F20");
		lblNewLabel_16.setForeground(new Color(0, 128, 0));
		lblNewLabel_16.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_16.setBounds(240, 240, 90, 22);
		frame.getContentPane().add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("B.\u627E\u670B\u53CB");
		lblNewLabel_17.setForeground(new Color(255, 0, 255));
		lblNewLabel_17.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_17.setBounds(240, 300, 90, 22);
		frame.getContentPane().add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("C.\u6597\u5730\u4E3B");
		lblNewLabel_18.setForeground(new Color(0, 0, 255));
		lblNewLabel_18.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_18.setBounds(240, 360, 90, 22);
		frame.getContentPane().add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("D.7\u738B523");
		lblNewLabel_19.setForeground(new Color(255, 20, 147));
		lblNewLabel_19.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_19.setBounds(240, 420, 90, 22);
		frame.getContentPane().add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("\u5E95\u6CE8");
		lblNewLabel_20.setForeground(new Color(255, 127, 80));
		lblNewLabel_20.setBackground(Color.WHITE);
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_20.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_20.setBounds(380, 200, 60, 22);
		frame.getContentPane().add(lblNewLabel_20);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_1.setBounds(350, 240, 120, 22);
		LoadComboBox.setBetCoin(comboBox_1);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_2.setBounds(350, 300, 120, 22);
		LoadComboBox.setBetCoin(comboBox_2);
		frame.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_3.setBounds(350, 360, 120, 22);
		LoadComboBox.setBetCoin(comboBox_3);
		frame.getContentPane().add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_4.setBounds(350, 420, 120, 22);
		LoadComboBox.setBetCoin(comboBox_4);
		frame.getContentPane().add(comboBox_4);
		
		JButton btnNewButton_2 = new JButton("\u521B\u5EFA\u623F\u95F4");
		btnNewButton_2.addActionListener(new ActionListener() //创建赌三张房间
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(CreatRoom.createRoom(comboBox_1.getSelectedIndex(), "A"))//创建成功
				{
					frame.setVisible(false);
					BetForThree window = new BetForThree();
					window.frame.setVisible(true);
				}
				else
					return;
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_2.setBounds(500, 240, 120, 22);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u4E07\u4EBA\u5802");
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_3.setBounds(640, 240, 100, 22);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\u521B\u5EFA\u623F\u95F4");
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_4.setBounds(500, 300, 120, 22);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("\u521B\u5EFA\u623F\u95F4");
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_5.setBounds(500, 360, 120, 22);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("\u521B\u5EFA\u623F\u95F4");
		btnNewButton_6.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_6.setBounds(500, 420, 120, 22);
		frame.getContentPane().add(btnNewButton_6);
		
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SetUI.setFrameCenter(frame);
		timer = new Timer();
		timer.schedule(new TimerTask() 
		{	
			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				lblNewLabel_3.setText("\u5728\u7EBF\u4EBA\u6570\uFF1A" + 
				DataBase.searchDataBase("softwareupdate", "Edition", version, "OnlineNumber", true));
			}
		}, 0, 5000);
		
		/*
		File imageFile = new File("src/menuImage/entertainment.jpg");
		BufferedImage img;
		try 
		{
			img = ImageIO.read(imageFile);
			BufferedImage outImg = img.getSubimage(540, 50, 210, 120);
			lblNewLabel_1.setIcon(new ImageIcon(outImg));
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
