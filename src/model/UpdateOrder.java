/*
 * ��������
 */
package model;

public class UpdateOrder
{
	private UpdateOrder() {}
	
	//�������
	public static int getOrder(String code)
	{
		int number = 1;//�洢��������
		int mycoin = DataBase.queryDataBase("user", "Code", code, "Coin");
		int [] coin = new int[]{-1, -1, -1, -1, -1};
		String version = FileText.getLineText(6);
		String [] Number = new String[5];
		//���������Ա
		for(; number < 6; number++)
			if((Number[number - 1] = DataBase.searchDataBase//�ж���������
			("softwareupdate", "Edition", version, "Number" + number, true)).equals(""))
				break;
		//�������΢��
		for(int i = 0; i < number - 1; i++)
			coin[i] = DataBase.queryDataBase("user", "Code", Number[i], "Coin");
		switch(number)
		{
		case 1:break;
		case 2:break;
		case 3:break;
		case 4:break;
		case 5:break;
		case 6:break;
		}
		return 0;
	}

}
