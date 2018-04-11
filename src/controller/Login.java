/*
 * 登录操作
 * 修改时间：
 * ZQW-2018-03-16
 * ZQW-2018-03-24
 */
package controller;

import java.util.Timer;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.DataBase;
import model.FileText;
import viewer.ConnectInterface;
import viewer.MenuInterface;

public class Login 
{
	private Login() {}

	//检测登录数据的方法：账号和密码
	public static int checkData(String code, String password)
	{
		String pwd = DataBase.searchDataBase("user", "Code", code, "Password", true);
		if(pwd.equals(""))//账号不存在
			return -1;
		else
		{
			if(!pwd.equals(password))//密码不对
				return 0;
			else
				return 1;
		}
	}
	
	//加载登录的方法
	public static void loginMenu(JTextField code, JTextField pwd, JCheckBox loginbox, 
			JFrame frame, Timer timer, JLabel label)
	{
		switch(Login.checkData(code.getText(), pwd.getText()))
		{
		case -1:
		{
			label.setText("账号不存在！");
			loginbox.setSelected(false);//取消勾选
			FileText.writeLineText(3, "0");
			break;
		}
		case 0:
		{
			label.setText("账号或密码错误！");
			loginbox.setSelected(false);
			FileText.writeLineText(3, "0");
			break;
		}
		case 1://登录
		{
			FileText.writeLineText(4, code.getText());//存储当前账号
			FileText.writeLineText(5, pwd.getText()); //存储当前密码
            label.setText("登录成功！");
            ConnectInterface.which = false;
            DataBase.modifyDataBase("softwareupdate", "Edition", FileText.getLineText(6), "OnlineNumber", 
            		String.valueOf(Integer.parseInt(
            		DataBase.searchDataBase(
            				"softwareupdate", "Edition", FileText.getLineText(6), "OnlineNumber", true)) + 1));
			frame.setVisible(false);
			MenuInterface window = new MenuInterface();
			window.getFrame().setVisible(true);
			timer.cancel();//释放任务
			break;
		}
		}
	}
	
}
