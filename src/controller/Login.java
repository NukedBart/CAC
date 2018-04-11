/*
 * ��¼����
 * �޸�ʱ�䣺
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

	//����¼���ݵķ������˺ź�����
	public static int checkData(String code, String password)
	{
		String pwd = DataBase.searchDataBase("user", "Code", code, "Password", true);
		if(pwd.equals(""))//�˺Ų�����
			return -1;
		else
		{
			if(!pwd.equals(password))//���벻��
				return 0;
			else
				return 1;
		}
	}
	
	//���ص�¼�ķ���
	public static void loginMenu(JTextField code, JTextField pwd, JCheckBox loginbox, 
			JFrame frame, Timer timer, JLabel label)
	{
		switch(Login.checkData(code.getText(), pwd.getText()))
		{
		case -1:
		{
			label.setText("�˺Ų����ڣ�");
			loginbox.setSelected(false);//ȡ����ѡ
			FileText.writeLineText(3, "0");
			break;
		}
		case 0:
		{
			label.setText("�˺Ż��������");
			loginbox.setSelected(false);
			FileText.writeLineText(3, "0");
			break;
		}
		case 1://��¼
		{
			FileText.writeLineText(4, code.getText());//�洢��ǰ�˺�
			FileText.writeLineText(5, pwd.getText()); //�洢��ǰ����
            label.setText("��¼�ɹ���");
            ConnectInterface.which = false;
            DataBase.modifyDataBase("softwareupdate", "Edition", FileText.getLineText(6), "OnlineNumber", 
            		String.valueOf(Integer.parseInt(
            		DataBase.searchDataBase(
            				"softwareupdate", "Edition", FileText.getLineText(6), "OnlineNumber", true)) + 1));
			frame.setVisible(false);
			MenuInterface window = new MenuInterface();
			window.getFrame().setVisible(true);
			timer.cancel();//�ͷ�����
			break;
		}
		}
	}
	
}
