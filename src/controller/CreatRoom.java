/*
 * 创建房间
 */

package controller;

import javax.swing.JOptionPane;

import model.DataBase;
import model.RandomNumber;
import viewer.MenuInterface;

public class CreatRoom 
{
	private CreatRoom() {}
	
	public static String CODE = "";//存储产生的房间号
	public static String BetCoin = "";//存储房间底注
	public static int myNumber = 0;//存储本人座位
	
	//创建房间
	public static boolean createRoom(int which, String property)
	{
		do 
		{
			CODE = String.valueOf(RandomNumber.getOne(1000, 9999));//获得房间号
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
			JOptionPane.showMessageDialog(null, "您的资金不足！");
			return false;
		}
		if(DataBase.insertRoomToDataBase("room", CODE, property))//创建成功
		{
			DataBase.modifyDataBase("room", "Code", CODE, "BetCoin", BetCoin);//修改底注
			DataBase.modifyDataBase("room", "Code", CODE, "Playing", "no");//修改回合
			DataBase.modifyDataBase("room", "Code", CODE, "Man0", MenuInterface.code);//增加号码
			myNumber = 0;
			return true;
		}
		else
			return false;
	}
	
	//选择房间
	public static boolean chooseRoom(String code)
	{
		CODE = (code = code.substring(0, 4));//获得房间号
		int number = DataBase.queryDataBase("room", "Code", code, "ManNumber");//存储房间人数
		if(number == 6)//人数已满
		{
			JOptionPane.showMessageDialog(null, "该房间人数已满！");
			return false;
		}
		else
		{
			if(DataBase.queryDataBase("user", "Code", MenuInterface.code, "Coin") < 10 * Integer.parseInt(
						(BetCoin = DataBase.searchDataBase("room", "Code", code, "BetCoin", true))))
			{//资金不足
				JOptionPane.showMessageDialog(null, "您的资金不足！");
				return false;
			}
			else//添加玩家到房间
			{
				for(int i = 0; i < 6; i++)
					if(DataBase.searchDataBase("room", "Code", code, "Man" + i, true).equals(""))//该位置没人
					{
						DataBase.modifyDataBase("room", "Code", code, "Man" + i, MenuInterface.code);//上位
						DataBase.modifyDataBase("room", "Code", code, "ManNumber", 
								String.valueOf(number + 1));//增加人数
						myNumber = i;
						break;
					}
				return true;
			}
		}
	}
	
	//搜索房间
	public static boolean queryRoom(String code)
	{
		CODE = code;
		if(DataBase.searchDataBase("room", "Code", code, "Exist", true).equals(""))//房间不存在
		{
			JOptionPane.showMessageDialog(null, "房间不存在！");
			return false;
		}
		else
		{
			int number = DataBase.queryDataBase("room", "Code", code, "ManNumber");//存储房间人数
			if(number == 6)//人数已满
			{
				JOptionPane.showMessageDialog(null, "该房间人数已满！");
				return false;
			}
			else
			{
				if(DataBase.queryDataBase("user", "Code", MenuInterface.code, "Coin") < 10 * Integer.parseInt(
						(BetCoin = DataBase.searchDataBase("room", "Code", code, "BetCoin", true))))
				{//资金不足
					JOptionPane.showMessageDialog(null, "您的资金不足！");
					return false;
				}
				else//添加玩家到房间
				{
					for(int i = 0; i < 6; i++)
						if(DataBase.searchDataBase("room", "Code", code, "Man" + i, true).equals(""))//该位置没人
						{
							DataBase.modifyDataBase("room", "Code", code, "Man" + i, MenuInterface.code);//上位
							DataBase.modifyDataBase("room", "Code", code, "ManNumber", 
									String.valueOf(number + 1));//增加人数
							myNumber = i;
							break;
						}
					return true;
				}
			}
		}
	}
	

}
