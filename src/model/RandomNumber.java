/*
 * 产生各种随机数的类
 * 更新时间：
 * ZQW-2018-03-15
 */

package model;

public class RandomNumber
{
	private RandomNumber() {}
	
	//产生一个范围内的随机数
	public static int getOne(int min, int max)
	{
		int random;
		random =  min + (int)(Math.random() * (max - min));
		return random;
	}

}
