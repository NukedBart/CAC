/*
 * �����������������
 * ����ʱ�䣺
 * ZQW-2018-03-15
 */

package model;

public class RandomNumber
{
	private RandomNumber() {}
	
	//����һ����Χ�ڵ������
	public static int getOne(int min, int max)
	{
		int random;
		random =  min + (int)(Math.random() * (max - min));
		return random;
	}

}
