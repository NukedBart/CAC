/*
 * 更新排名
 */
package model;

public class UpdateOrder
{
	private UpdateOrder() {}
	
	//获得排名
	public static int getOrder(String code)
	{
		int number = 1;//存储排名人数
		int mycoin = DataBase.queryDataBase("user", "Code", code, "Coin");
		int [] coin = new int[]{-1, -1, -1, -1, -1};
		String version = FileText.getLineText(6);
		String [] Number = new String[5];
		//获得排名人员
		for(; number < 6; number++)
			if((Number[number - 1] = DataBase.searchDataBase//判断排名人数
			("softwareupdate", "Edition", version, "Number" + number, true)).equals(""))
				break;
		//获得排名微币
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
