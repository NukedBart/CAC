/*
 * 加载checkBox
 */
package model;

import javax.swing.JCheckBox;

import viewer.MenuInterface;


public class LoadCheckBox 
{
	private LoadCheckBox() {}
	
	//初始化
	public static void setCheckBox(JCheckBox box_1, JCheckBox box_2)
	{
		if(DataBase.queryDataBase("user", "Code", MenuInterface.code, "Gender") == 0)//为男
			box_1.setSelected(true);
		else
			box_2.setSelected(true);
	}
	
	//以box_1为监听对象
	public static void chooseACheckBox(JCheckBox box_1, JCheckBox box_2)
	{
		if(box_1.isSelected())//改变之后
			box_2.setSelected(false);
		else
		{
			box_1.setSelected(true);
			box_2.setSelected(false);
		}
	}
	
}
