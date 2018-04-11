/*
 * 注册时的操作类
 * 更新时间：
 * ZQW-2018-03-15
 * ZQW-2018-03-16
 */
package controller;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DataBase;
import model.FileText;
import model.JudgeTextField;
import model.Time;

public class Register 
{
	private Register() {}
	
	//用于检测用户输入数据的合法性并反馈给用户的方法
	public static boolean registerWIS(JTextField NicknameText, JTextField PasswordText, JTextField PasswordText2, 
			JTextField MailText, JLabel label_1, JLabel label_2, JLabel label_3)
	{
		if(!FileText.getLineText(1).equals("0"))//已注册
			return false;
		else
		{
			if(!JudgeTextField.judgeName("昵称", NicknameText.getText().length(), 2, 8).equals(""))
			{//昵称有问题时
				label_1.setText(JudgeTextField.judgeName("昵称", NicknameText.getText().length(), 2, 8));
				label_1.setForeground(Color.red);
				return false;
			}
			else
			{
				label_1.setText("正确");
				label_1.setForeground(Color.green);
			}
			if(!JudgeTextField.judgeTwo("密码", PasswordText.getText(), PasswordText2.getText()).equals(""))
			{//两次密码不一致时
				label_2.setText(JudgeTextField.judgeTwo("密码", PasswordText.getText(), PasswordText2.getText()));
				label_2.setForeground(Color.red);
				return false;
			}
			else
			{
				if(!JudgeTextField.judgePassword("密码", PasswordText.getText(), 6, 16).equals(""))
				{//密码不符合要求
					label_2.setText(JudgeTextField.judgePassword("密码", PasswordText.getText(), 6, 16));
					label_2.setForeground(Color.red);
					return false;
				}
				else
				{
					label_2.setText("正确");
					label_2.setForeground(Color.green);
				}
			}
			if(!JudgeTextField.judgeMail("邮箱", MailText.getText()).equals(""))
			{//邮箱有问题时
				label_3.setText(JudgeTextField.judgeMail("邮箱", MailText.getText()));
				label_3.setForeground(Color.red);
				return false;
			}
			else
			{
				label_3.setText("正确");
				label_3.setForeground(Color.green);
				return true;
			}
		}
	}
	
    //注册时对数据库进行操作的方法
	public static boolean addToDataBase(JTextField Nickname, JTextField Password, JTextField Mail)
	{

		if(!DataBase.insertDataBase(GetCode.code, Password.getText(), Nickname.getText(), Mail.getText()))
		{
			JOptionPane.showMessageDialog(null, "注册失败！", "无服务提示", 0);
			return false;
		}
		else
		{
			int lines = 7;//文件内容的行数
			String temptext = "";//临时存储文件内容
			for(int i = 1; i <= lines; i++)
			{
				if(i == 1)//修改第一行
					temptext = "1\n";
				else
					temptext = temptext.concat(FileText.getLineText(i).concat("\n"));//存储文件内容
			}
			if(!FileText.setText(temptext))//修改文件失败
			{
				JOptionPane.showMessageDialog(null, "信息文件异常！", "无服务提示", 0);
			    return false;
			}
			else//完成注册
			{
				DataBase.modifyDataBase("user", "Code", GetCode.code, "RegisterTime", Time.YMDDate);
				return true;
			}
		}
	}
	
}
