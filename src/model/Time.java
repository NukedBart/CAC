/*
 * 获得时间的类
 * ZQW-2018-03-28
 */
package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time 
{
	private Time() {}
	
	private static Calendar nowDate = new GregorianCalendar();      // 获取当前系统平台下默认的日期、时间和时区  
	
    public static int year = nowDate.get(Calendar.YEAR);        // 获取当前系统平台下默认日期的“年”  
    public static String Year = String.valueOf(year);
    
    public static int month = nowDate.get(Calendar.MONTH) + 1;  // 获取当前系统平台下默认日期的“月” 
    public static String Month = String.valueOf(month);
    
    public static int day = nowDate.get(Calendar.DAY_OF_MONTH); // 获取当前系统平台下默认日期的“日”
    public static String Day = String.valueOf(day);
    
    public static int hour = nowDate.get(Calendar.HOUR_OF_DAY);// 获取当前系统平台下默认日期的“时”
    public static String Hour = String.valueOf(hour);
    
    public static int minute = nowDate.get(Calendar.MINUTE);// 获取当前系统平台下默认日期的“分”
    public static String Minute = String.valueOf(minute);
    
    public static int second = nowDate.get(Calendar.SECOND);// 获取当前系统平台下默认日期的“秒”
    public static String Second = String.valueOf(second);
    
    public static String successiveDate = Year + Month + Day;
    public static String YMDDate = Year + "-" + Month + "-" + Day;
    public static String MDYDate = Month + "-" + Day + "-" + Year;
    public static String DMYDate = Day + "-" + Month + "-" + Year;
    
    public static String Hours = Hour + ":" + Minute + ":" + Second;
    
    public static String Date = YMDDate + " " + Hours;
    
    //根据年月来获得天数
    public static int getDaysOfMonth(int y, int m)
    {
    	int days = 0;
    	switch(m)
    	{
    		case 1 : days = 31; break;
    		case 2 : days = y % 4 == 0 ? 29 : 28; break;//闰年与否
    		case 3 : days = 31; break;
    		case 4 : days = 30; break;
    		case 5 : days = 31; break;
    		case 6 : days = 30; break;
    		case 7 : days = 31; break;
    		case 8 : days = 31; break;
    		case 9 : days = 30; break;
    		case 10: days = 31; break;
    		case 11: days = 30; break;
    		case 12: days = 31; break;
   		}
    	return days;
    }
    
    //根据年月日字符串格式返回指定的整形年月日
    public static int getIntDate(String date, String which)
    {
    	int choose = 0; //存储数据
    	int begin = 0;//存储字符串开始的索引值
    	int end = 0;  //存储字符串结束的索引值
    	char [] Date = date.toCharArray();
    	if(which.equals("year"))//需要年份
    		choose = 0;
    	if(which.equals("month"))//需要月份
    		choose = 1;
    	if(which.equals("day"))//需要天数
    		choose = 2;
    	for(int i = 0, j = 0; i < date.length(); i++)
    	{
    		if(j == choose)
    		{
    			begin = i;
    			break;
    		}
    		if(Date[i] == '-')
    			j++;
    	}//获得起始索引
    	for(int i = date.length() - 1, j = 0; i >= 0; i--)
    	{
    		if(j == 2 - choose)
    		{
    			end = i + 1;
    			break;
    		}
    		if(Date[i] == '-')
    			j++;
    	}//获得结束索引
    	if(begin != end)//正常时
    		return Integer.parseInt(date.substring(begin, end));
    	else//只有一个字符时
    		return Integer.parseInt(String.valueOf(Date[begin]));
    }
    
    
}
