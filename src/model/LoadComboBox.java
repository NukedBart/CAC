/*
 * 初始化comboBox选项
 */
package model;


import javax.swing.JComboBox;

import viewer.MenuInterface;

public class LoadComboBox 
{
	private LoadComboBox() {}

	//加载菜单房间选项
	public static void setRoom(JComboBox box)
	{
		String room = DataBase.searchProbable("room", "Exist", "Code", "yes");//获得当前房间信息
		if(room.equals(""))//变为无房间
		{
			box.removeAllItems();
			box.addItem("Empty");//提示用户
		}
		else
		{
			box.removeAllItems();//简单粗暴
			for(int i = 0; i < room.length() / 5; i++)
				box.addItem(room.substring(i * 5, i * 5 + 4) + ":" + 
			DataBase.searchDataBase("room", "Code", room.substring(i * 5, i * 5 + 4), "Property", true));
		}
	}
	
	//加载赌注选项
	public static void setBetCoin(JComboBox box)
	{
		box.addItem("1    $");
		box.addItem("100  $");
		box.addItem("500  $");
		box.addItem("1000 $");
		box.addItem("5000 $");
		box.addItem("10000$");
		box.addItem("50000$");
	}
	
	
	//加载头像选项
	public static void chooseHeadPortrait(JComboBox combobox)
	{
		String headportrait = 
				DataBase.searchDataBase("user", "Code", MenuInterface.code, "HeadPortrait", true);
		for(int i = 1; i < 21; i++)
		{
			combobox.addItem(String.valueOf(i) + ".png");
		}
		if(!headportrait.equals(""))
			//设置了头像
			combobox.setSelectedItem(headportrait.substring(14, headportrait.length()));
	}
	
	//设置生日选项
	public static void setBirthDay(JComboBox box_1, JComboBox box_2, JComboBox box_3)
	{
		String birthday = DataBase.searchDataBase("user", "Code", MenuInterface.code, "Birthday", true);
		for(int i = 1; i <= 50; i++)//设置年份选项，五十年长度
			box_1.addItem(Time.year - i);
		for(int i = 1; i <= 12; i++)//默认设置当前月数
			box_2.addItem(i);
		for(int i = 1; i <= 31; i++)//默认天数
			box_3.addItem(i);
		if(!birthday.equals(""))//设置了生日
		{
			box_1.setSelectedItem(Time.getIntDate(birthday, "year"));
			box_2.setSelectedItem(Time.getIntDate(birthday, "month"));
			box_3.setSelectedItem(Time.getIntDate(birthday, "day"));
			
		}
	}
	
	//ComboBox监听
	public static void setDaysList(JComboBox box_1, JComboBox box_2, JComboBox box_3)
	{
		int days = Time.getDaysOfMonth(Integer.parseInt(box_1.getSelectedItem().toString()), 
				Integer.parseInt(box_2.getSelectedItem().toString()));//获得天数
		int boxItemCount = box_3.getItemCount();//提前获得项数
		if(boxItemCount > days)//天数多了
		{
			for(int i = 0; i < boxItemCount - days; i++)
			{
				box_3.removeItemAt(boxItemCount - i - 1);	
			}
		}
		else
			if(boxItemCount < days)//天数不够
			{
				for(int i = 0; i < days - boxItemCount; i++)
				{
					box_3.addItem(boxItemCount + i + 1);
				}
			}
	}
	
	
	
}
