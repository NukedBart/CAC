/*
 * �޸���Ϣ����
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
	
	//ȷ����Ϣ�ķ���
	public static boolean judgeInformation(String name, String signature, String mailbox, String password,
			JLabel label_1, JLabel label_2, JLabel label_3, JLabel label_4)
	{
		if(!JudgeTextField.judgeName("�ǳ�", name.length(), 2, 8).equals(""))//���Ʋ��Ϸ�
		{
			label_1.setForeground(Color.red);
			return false;
		}
		else
			label_1.setForeground(Color.black);
		if(!JudgeTextField.judgeName("ǩ��", signature.length(), 0, 9).equals(""))//ǩ�����Ϸ�
		{
			label_2.setForeground(Color.red);
			return false;
		}
		else
			label_2.setForeground(Color.black);
		if(mailbox.length() != 0 && !JudgeTextField.judgeMail("����", mailbox).equals(""))//���䲻�Ϸ�
		{
			label_3.setForeground(Color.red);
			return false;
		}
		else 
			label_3.setForeground(Color.black);
		if(password.length() != 0 && !JudgeTextField.judgePassword("����", password, 6, 16).equals(""))//���벻�Ϸ�
		{
			label_4.setForeground(Color.red);
			return false;
		}
		else 
			label_4.setForeground(Color.black);
		return true;
	}
	
	
	//�޸���Ϣ�ķ���
	public static void modifyInformation(String headportrait, String year, String month, String day,
			String nickname, String signature, String mailbox, String password, boolean sex)
	{
		DataBase.modifyDataBase("user", "Code", MenuInterface.code, "HeadPortrait", 
				"/headPortrait/" + headportrait);//�޸�ͷ��
		DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Birthday", 
				year + "-" + month + "-"+ day);//�޸�����
		DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Nickname", nickname);//�޸��ǳ�
		DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Signature", signature);//�޸�ǩ��
		if(!mailbox.equals(""))//Ϊ�վͲ��޸�
			DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Mailbox", mailbox);//�޸�����
		if(!password.equals(""))//Ϊ�վͲ��޸�
			DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Password", password);//�޸�����
		if(sex)//����
			DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Gender", "0");//�޸��Ա�
		else
			DataBase.modifyDataBase("user", "Code", MenuInterface.code, "Gender", "1");
		JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
	}
	
}
