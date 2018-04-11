/*
 * ���ʱ�����
 * ZQW-2018-03-28
 */
package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time 
{
	private Time() {}
	
	private static Calendar nowDate = new GregorianCalendar();      // ��ȡ��ǰϵͳƽ̨��Ĭ�ϵ����ڡ�ʱ���ʱ��  
	
    public static int year = nowDate.get(Calendar.YEAR);        // ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ��ꡱ  
    public static String Year = String.valueOf(year);
    
    public static int month = nowDate.get(Calendar.MONTH) + 1;  // ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ��¡� 
    public static String Month = String.valueOf(month);
    
    public static int day = nowDate.get(Calendar.DAY_OF_MONTH); // ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ��ա�
    public static String Day = String.valueOf(day);
    
    public static int hour = nowDate.get(Calendar.HOUR_OF_DAY);// ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ�ʱ��
    public static String Hour = String.valueOf(hour);
    
    public static int minute = nowDate.get(Calendar.MINUTE);// ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ��֡�
    public static String Minute = String.valueOf(minute);
    
    public static int second = nowDate.get(Calendar.SECOND);// ��ȡ��ǰϵͳƽ̨��Ĭ�����ڵġ��롱
    public static String Second = String.valueOf(second);
    
    public static String successiveDate = Year + Month + Day;
    public static String YMDDate = Year + "-" + Month + "-" + Day;
    public static String MDYDate = Month + "-" + Day + "-" + Year;
    public static String DMYDate = Day + "-" + Month + "-" + Year;
    
    public static String Hours = Hour + ":" + Minute + ":" + Second;
    
    public static String Date = YMDDate + " " + Hours;
    
    //�����������������
    public static int getDaysOfMonth(int y, int m)
    {
    	int days = 0;
    	switch(m)
    	{
    		case 1 : days = 31; break;
    		case 2 : days = y % 4 == 0 ? 29 : 28; break;//�������
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
    
    //�����������ַ�����ʽ����ָ��������������
    public static int getIntDate(String date, String which)
    {
    	int choose = 0; //�洢����
    	int begin = 0;//�洢�ַ�����ʼ������ֵ
    	int end = 0;  //�洢�ַ�������������ֵ
    	char [] Date = date.toCharArray();
    	if(which.equals("year"))//��Ҫ���
    		choose = 0;
    	if(which.equals("month"))//��Ҫ�·�
    		choose = 1;
    	if(which.equals("day"))//��Ҫ����
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
    	}//�����ʼ����
    	for(int i = date.length() - 1, j = 0; i >= 0; i--)
    	{
    		if(j == 2 - choose)
    		{
    			end = i + 1;
    			break;
    		}
    		if(Date[i] == '-')
    			j++;
    	}//��ý�������
    	if(begin != end)//����ʱ
    		return Integer.parseInt(date.substring(begin, end));
    	else//ֻ��һ���ַ�ʱ
    		return Integer.parseInt(String.valueOf(Date[begin]));
    }
    
    
}
