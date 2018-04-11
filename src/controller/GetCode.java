/*
 * 摇号时的操作类
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
	
	public static String code = "";//存储随机号
	
	//检测是否摇过号的方法
	private static boolean checkGetCode()
	{
		if(FileText.getLineText(1).equals("0"))//没摇过
			return false;
		else//已经注册
			return true;
	}

	//产生号码的方法
	public static void getCode(JTextField textfield)
	{
		if(checkGetCode())
			JOptionPane.showMessageDialog(null, "您已经注册过了！", "无服务提示", 1);
		else
		{
			do
			{
				code = String.valueOf(RandomNumber.getOne(100000, 1000000000));
			}//获得可以使用的随机号
			while (DataBase.searchDataBase("user", "Code", code, "Nickname", true) != "");
			textfield.setText(code);
		}
	}
}
