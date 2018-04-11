/*
 * ��������
 */

package controller;

import javax.swing.JOptionPane;

import model.DataBase;
import model.RandomNumber;
import viewer.MenuInterface;

public class CreatRoom 
{
	private CreatRoom() {}
	
	public static String CODE = "";//�洢�����ķ����
	public static String BetCoin = "";//�洢�����ע
	public static int myNumber = 0;//�洢������λ
	
	//��������
	public static boolean createRoom(int which, String property)
	{
		do 
		{
			CODE = String.valueOf(RandomNumber.getOne(1000, 9999));//��÷����
		} while (DataBase.searchDataBase("room", "Code", CODE, "Exist", true).equals("yes"));
		switch(which)
		{
		case 0:property = property.concat(BetCoin = "1");break;
		case 1:property = property.concat(BetCoin = "100");break;
		case 2:property = property.concat(BetCoin = "500");break;
		case 3:property = property.concat(BetCoin = "1000");break;
		case 4:property = property.concat(BetCoin = "5000");break;
		case 5:property = property.concat(BetCoin = "10000");break;
		case 6:property = property.concat(BetCoin = "50000");break;
		default:break;
		}
		if(DataBase.queryDataBase("user", "Code", MenuInterface.code, "Coin") < 
				Integer.parseInt(BetCoin) * 10)
		{
			JOptionPane.showMessageDialog(null, "�����ʽ��㣡");
			return false;
		}
		if(DataBase.insertRoomToDataBase("room", CODE, property))//�����ɹ�
		{
			DataBase.modifyDataBase("room", "Code", CODE, "BetCoin", BetCoin);//�޸ĵ�ע
			DataBase.modifyDataBase("room", "Code", CODE, "Playing", "no");//�޸Ļغ�
			DataBase.modifyDataBase("room", "Code", CODE, "Man0", MenuInterface.code);//���Ӻ���
			myNumber = 0;
			return true;
		}
		else
			return false;
	}
	
	//ѡ�񷿼�
	public static boolean chooseRoom(String code)
	{
		CODE = (code = code.substring(0, 4));//��÷����
		int number = DataBase.queryDataBase("room", "Code", code, "ManNumber");//�洢��������
		if(number == 6)//��������
		{
			JOptionPane.showMessageDialog(null, "�÷�������������");
			return false;
		}
		else
		{
			if(DataBase.queryDataBase("user", "Code", MenuInterface.code, "Coin") < 10 * Integer.parseInt(
						(BetCoin = DataBase.searchDataBase("room", "Code", code, "BetCoin", true))))
			{//�ʽ���
				JOptionPane.showMessageDialog(null, "�����ʽ��㣡");
				return false;
			}
			else//�����ҵ�����
			{
				for(int i = 0; i < 6; i++)
					if(DataBase.searchDataBase("room", "Code", code, "Man" + i, true).equals(""))//��λ��û��
					{
						DataBase.modifyDataBase("room", "Code", code, "Man" + i, MenuInterface.code);//��λ
						DataBase.modifyDataBase("room", "Code", code, "ManNumber", 
								String.valueOf(number + 1));//��������
						myNumber = i;
						break;
					}
				return true;
			}
		}
	}
	
	//��������
	public static boolean queryRoom(String code)
	{
		CODE = code;
		if(DataBase.searchDataBase("room", "Code", code, "Exist", true).equals(""))//���䲻����
		{
			JOptionPane.showMessageDialog(null, "���䲻���ڣ�");
			return false;
		}
		else
		{
			int number = DataBase.queryDataBase("room", "Code", code, "ManNumber");//�洢��������
			if(number == 6)//��������
			{
				JOptionPane.showMessageDialog(null, "�÷�������������");
				return false;
			}
			else
			{
				if(DataBase.queryDataBase("user", "Code", MenuInterface.code, "Coin") < 10 * Integer.parseInt(
						(BetCoin = DataBase.searchDataBase("room", "Code", code, "BetCoin", true))))
				{//�ʽ���
					JOptionPane.showMessageDialog(null, "�����ʽ��㣡");
					return false;
				}
				else//�����ҵ�����
				{
					for(int i = 0; i < 6; i++)
						if(DataBase.searchDataBase("room", "Code", code, "Man" + i, true).equals(""))//��λ��û��
						{
							DataBase.modifyDataBase("room", "Code", code, "Man" + i, MenuInterface.code);//��λ
							DataBase.modifyDataBase("room", "Code", code, "ManNumber", 
									String.valueOf(number + 1));//��������
							myNumber = i;
							break;
						}
					return true;
				}
			}
		}
	}
	

}
