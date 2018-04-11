/*
 * 修改信息的类
 */
package controller;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.DataBase;
import model.JudgeTextField;
import viewer.MenuInterface;

public class Modify
{
	private Modify() {}
	
	//确认信息的方法
	public static boolean judgeInformation(String name, String signature, String mailbox, String password,
			JLabel label_1, JLabel label_2, JLabel label_3, JLabel label_4)
	{
		if(!JudgeTextField.judgeName("昵称", name.length(), 2, 8).equals(""))//名称不合法
		{
			label_1.setForeground(Color.red);
			return false;
		}
		else
			label_1.setForeground(Color.black);
		if(!JudgeTextField.judgeName("签名", signature.length(), 0, 9).equals(""))//签名不合法
		{
			label_2.setForeground(Color.red);
			return false;
		}
		else
			label_2.setForeground(Color.black);
		if(mailbox.length() != 0 && !JudgeTextField.judgeMail("邮箱", mailbox).equals(""))//邮箱不合法
		{
			label_3.setForeground(Color.red);
			return false;
		}
		else 
			label_3.setForeground(Color.black);
		if(password.length() != 0 && !JudgeTextField.judgePassword("密码", password, 6, 16).equals(""))//密码不合法
		{
			label_4.setForeground(Color.red);
			return false;
		}
		else 
			label_4.setForeground(Color.black);
		return true;
	}
	
	
	//修改信息的方法
	public static void modifyInformation(String headportrait, String year, String month, String day,
			String nickname, String signature, String mailbox, String password, boolean sex)
	{
		DataBase.modifyDataBase("user", "Code", MenuInterface.code, "HeadPortrait", 
				"/headPortrait/" + headportrait);//修改头像
		DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Birthday", 
				year + "-" + month + "-"+ day);//修改生日
		DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Nickname", nickname);//修改昵称
		DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Signature", signature);//修改签名
		if(!mailbox.equals(""))//为空就不修改
			DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Mailbox", mailbox);//修改邮箱
		if(!password.equals(""))//为空就不修改
			DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Password", password);//修改密码
		if(sex)//男生
			DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Gender", "0");//修改性别
		else
			DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Gender", "1");
		JOptionPane.showMessageDialog(null, "修改成功！");
	}
	
}
