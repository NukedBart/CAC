/*
 * ��ʼ��comboBoxѡ��
 */
package model;


import javax.swing.JComboBox;

import viewer.MenuInterface;

public class LoadComboBox 
{
	private LoadComboBox() {}

	//���ز˵�����ѡ��
	public static void setRoom(JComboBox box)
	{
		String room = DataBase.searchProbable("room", "Exist", "Code", "yes");//��õ�ǰ������Ϣ
		if(room.equals(""))//��Ϊ�޷���
		{
			box.removeAllItems();
			box.addItem("Empty");//��ʾ�û�
		}
		else
		{
			box.removeAllItems();//�򵥴ֱ�
			for(int i = 0; i < room.length() / 5; i++)
				box.addItem(room.substring(i * 5, i * 5 + 4) + ":" + 
			DataBase.searchDataBase("room", "Code", room.substring(i * 5, i * 5 + 4), "Property", true));
		}
	}
	
	//���ض�עѡ��
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
	
	
	//����ͷ��ѡ��
	public static void chooseHeadPortrait(JComboBox combobox)
	{
		String headportrait = 
				DataBase.searchDataBase("user", "Code", MenuInterface.code, "HeadPortrait", true);
		for(int i = 1; i < 21; i++)
		{
			combobox.addItem(String.valueOf(i) + ".png");
		}
		if(!headportrait.equals(""))
			//������ͷ��
			combobox.setSelectedItem(headportrait.substring(14, headportrait.length()));
	}
	
	//��������ѡ��
	public static void setBirthDay(JComboBox box_1, JComboBox box_2, JComboBox box_3)
	{
		String birthday = DataBase.searchDataBase("user", "Code", MenuInterface.code, "Birthday", true);
		for(int i = 1; i <= 50; i++)//�������ѡ���ʮ�곤��
			box_1.addItem(Time.year - i);
		for(int i = 1; i <= 12; i++)//Ĭ�����õ�ǰ����
			box_2.addItem(i);
		for(int i = 1; i <= 31; i++)//Ĭ������
			box_3.addItem(i);
		if(!birthday.equals(""))//����������
		{
			box_1.setSelectedItem(Time.getIntDate(birthday, "year"));
			box_2.setSelectedItem(Time.getIntDate(birthday, "month"));
			box_3.setSelectedItem(Time.getIntDate(birthday, "day"));
			
		}
	}
	
	//ComboBox����
	public static void setDaysList(JComboBox box_1, JComboBox box_2, JComboBox box_3)
	{
		int days = Time.getDaysOfMonth(Integer.parseInt(box_1.getSelectedItem().toString()), 
				Integer.parseInt(box_2.getSelectedItem().toString()));//�������
		int boxItemCount = box_3.getItemCount();//��ǰ�������
		if(boxItemCount > days)//��������
		{
			for(int i = 0; i < boxItemCount - days; i++)
			{
				box_3.removeItemAt(boxItemCount - i - 1);	
			}
		}
		else
			if(boxItemCount < days)//��������
			{
				for(int i = 0; i < days - boxItemCount; i++)
				{
					box_3.addItem(boxItemCount + i + 1);
				}
			}
	}
	
	
	
}
