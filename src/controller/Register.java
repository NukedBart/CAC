/*
 * ע��ʱ�Ĳ�����
 * ����ʱ�䣺
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
	
	//���ڼ���û��������ݵĺϷ��Բ��������û��ķ���
	public static boolean registerWIS(JTextField NicknameText, JTextField PasswordText, JTextField PasswordText2, 
			JTextField MailText, JLabel label_1, JLabel label_2, JLabel label_3)
	{
		if(!FileText.getLineText(1).equals("0"))//��ע��
			return false;
		else
		{
			if(!JudgeTextField.judgeName("�ǳ�", NicknameText.getText().length(), 2, 8).equals(""))
			{//�ǳ�������ʱ
				label_1.setText(JudgeTextField.judgeName("�ǳ�", NicknameText.getText().length(), 2, 8));
				label_1.setForeground(Color.red);
				return false;
			}
			else
			{
				label_1.setText("��ȷ");
				label_1.setForeground(Color.green);
			}
			if(!JudgeTextField.judgeTwo("����", PasswordText.getText(), PasswordText2.getText()).equals(""))
			{//�������벻һ��ʱ
				label_2.setText(JudgeTextField.judgeTwo("����", PasswordText.getText(), PasswordText2.getText()));
				label_2.setForeground(Color.red);
				return false;
			}
			else
			{
				if(!JudgeTextField.judgePassword("����", PasswordText.getText(), 6, 16).equals(""))
				{//���벻����Ҫ��
					label_2.setText(JudgeTextField.judgePassword("����", PasswordText.getText(), 6, 16));
					label_2.setForeground(Color.red);
					return false;
				}
				else
				{
					label_2.setText("��ȷ");
					label_2.setForeground(Color.green);
				}
			}
			if(!JudgeTextField.judgeMail("����", MailText.getText()).equals(""))
			{//����������ʱ
				label_3.setText(JudgeTextField.judgeMail("����", MailText.getText()));
				label_3.setForeground(Color.red);
				return false;
			}
			else
			{
				label_3.setText("��ȷ");
				label_3.setForeground(Color.green);
				return true;
			}
		}
	}
	
    //ע��ʱ�����ݿ���в����ķ���
	public static boolean addToDataBase(JTextField Nickname, JTextField Password, JTextField Mail)
	{

		if(!DataBase.insertDataBase(GetCode.code, Password.getText(), Nickname.getText(), Mail.getText()))
		{
			JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�", "�޷�����ʾ", 0);
			return false;
		}
		else
		{
			int lines = 7;//�ļ����ݵ�����
			String temptext = "";//��ʱ�洢�ļ�����
			for(int i = 1; i <= lines; i++)
			{
				if(i == 1)//�޸ĵ�һ��
					temptext = "1\n";
				else
					temptext = temptext.concat(FileText.getLineText(i).concat("\n"));//�洢�ļ�����
			}
			if(!FileText.setText(temptext))//�޸��ļ�ʧ��
			{
				JOptionPane.showMessageDialog(null, "��Ϣ�ļ��쳣��", "�޷�����ʾ", 0);
			    return false;
			}
			else//���ע��
			{
				DataBase.modifyDataBase("user", "Code", GetCode.code, "RegisterTime", Time.YMDDate);
				return true;
			}
		}
	}
	
}
