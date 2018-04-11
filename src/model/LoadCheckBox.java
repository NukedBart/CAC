/*
 * ����checkBox
 */
package model;

import javax.swing.JCheckBox;

import viewer.MenuInterface;


public class LoadCheckBox 
{
	private LoadCheckBox() {}
	
	//��ʼ��
	public static void setCheckBox(JCheckBox box_1, JCheckBox box_2)
	{
		if(DataBase.queryDataBase("user", "Code", MenuInterface.code, "Gender") == 0)//Ϊ��
			box_1.setSelected(true);
		else
			box_2.setSelected(true);
	}
	
	//��box_1Ϊ��������
	public static void chooseACheckBox(JCheckBox box_1, JCheckBox box_2)
	{
		if(box_1.isSelected())//�ı�֮��
			box_2.setSelected(false);
		else
		{
			box_1.setSelected(true);
			box_2.setSelected(false);
		}
	}
	
}
