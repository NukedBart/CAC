/*
 * 用于判断输入框的文本类型并发反馈结果
 * 更新时间：
 * ZQW-2018-03-15
 */
package model;

public class JudgeTextField
{
	private JudgeTextField() {}
	
	//判断某长度范围任意字符串的方法，如名字等
	public static String judgeName(String textname, int textlength, int minlength, int maxlength)
	{
		if(textlength < minlength)//长度不够
			return textname.concat("过短！");
		else
			if(textlength > maxlength)//长度超过
				return textname.concat("过长！");
			else//长度合适
				return "";
	}
	
	//判断两个输入字符串是否相同
	public static String judgeTwo(String textname, String text_1, String text_2)
	{
		if(text_1.equals(text_2))//相同
			return "";
		else
			return textname.concat("不一致！");
	}
	
	//判断某长度范围数字和英文字母组成的方法
	public static String judgePassword(String textname, String text, int minlength, int maxlength)
	{
		int textlength = text.length();//先计算出长度
		int i;//用来判断是否全部合格
		String lengthinformation = judgeName(textname, textlength, minlength, maxlength);//获得长度信息
		if(!lengthinformation.equals(""))//长度不合格
			return lengthinformation;
		else//检测字符
		{
			char [] temptext = text.toCharArray();//存储每个字符
			for(i = 0; i < textlength; )
			{
				if((temptext[i] >= '0' && temptext[i] <= '9')||(temptext[i] >= 'A' && temptext[i] <= 'Z')||
						(temptext[i] >= 'a' && temptext[i] <= 'z'))//判断字符是否合格
					i++;
				else
					break;
			}
			if(i == textlength)//都合格
				return "";
			else
				return textname.concat("含有非法字符！");
		}
			
	}
	
	//判断是否为有效邮件格式
	public static String judgeMail(String textname, String text)
	{
		int i;
		int textlength = text.length();
		char [] temptext = text.toCharArray();//存储每个字符
		for(i = 0; i < textlength; )
		{
			if(temptext[i] != '@')
			{
				if((temptext[i] >= '0' && temptext[i] <= '9')||(temptext[i] >= 'A' && temptext[i] <= 'Z')||
						(temptext[i] >= 'a' && temptext[i] <= 'z'))
					i++;
				else//@前面含有非法字符
					i = textlength;
			}
			else//找到第一个@
				break;	
		}
		if(i == 0 || i == textlength || textlength < 8)
			return textname.concat("格式不正确！");
		else//检查@后面字符
		{
			if(temptext[textlength - 1] != 'm' || temptext[textlength - 2] != 'o' || 
					temptext[textlength - 3] != 'c' || temptext[textlength - 4] != '.')//检查后四个字符
				return textname.concat("格式不正确！");
			else
			{
				if(textlength - 5 - i == 0)
					return textname.concat("格式不正确！");
				else
				{
					char [] mid = new char[textlength - 5 - i];//存储中间字符的数组
					for(int j = i + 1; j < textlength - 4; j++)
					{
						mid[j - i -1] = temptext[j];
					}
					String med = String.valueOf(mid);
					if(med.equals("126") || med.equals("189") || med.equals("qq") || med.equals("outlook"))
						return "";
					else
						return textname.concat("不存在");
				}
			}
		}
	}

}
