/*
 * ҡ��ʱ�Ĳ�����
 */

package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DataBase;
import model.FileText;
import model.RandomNumber;

public class GetCode 
{
	private GetCode() {}
	
	public static String code = "";//�洢�����
	
	//����Ƿ�ҡ���ŵķ���
	private static boolean checkGetCode()
	{
		if(FileText.getLineText(1).equals("0"))//ûҡ��
			return false;
		else//�Ѿ�ע��
			return true;
	}

	//��������ķ���
	public static void getCode(JTextField textfield)
	{
		if(checkGetCode())
			JOptionPane.showMessageDialog(null, "���Ѿ�ע����ˣ�", "�޷�����ʾ", 1);
		else
		{
			do
			{
				code = String.valueOf(RandomNumber.getOne(100000, 1000000000));
			}//��ÿ���ʹ�õ������
			while (DataBase.searchDataBase("user", "Code", code, "Nickname", true) != "");
			textfield.setText(code);
		}
	}
}
