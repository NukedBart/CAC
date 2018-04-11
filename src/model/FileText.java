/*
 * ���ڶ�ָ���ļ����ж�д����
 * ����ʱ�䣺
 * ZQW-2018-03-15
 * ZQW-2018-03-16
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileText 
{
	private FileText() {}
	
	//��ȡָ���е����ݵķ���
	public static String getLineText(int whichline)
	{
		String [] result = new String[whichline];//�洢��ȡ���
        File file = new File("src/files/UserInformation");  
        BufferedReader reader = null;  
        try 
        {   
            reader = new BufferedReader(new FileReader(file));
            for(int i = 0; i < whichline; i++)
            {
            	result[i] = reader.readLine();//�洢ÿһ�е���Ϣ
            }
            reader.close();  
        } 
        catch (IOException e) 
        {  
            e.printStackTrace();  
        }
        finally 
        {  
            if (reader != null) 
            {  
                try 
                {  
                    reader.close();  
                } 
                catch (IOException e1) 
                {}  
            }  
        } 
        return result[whichline - 1];
	}

	//���ļ�����д�����ķ���
	public static boolean setText(String data)
	{
        try 
        {   
            File file = new File("src/files/UserInformation");
            FileWriter filewritter = new FileWriter(file.getPath());  
            filewritter.write(data);  
            filewritter.close();  
            return true;
        } 
        catch (IOException e) 
        {  
            e.printStackTrace();  
            return false;
        }  
	}

	//���ļ�ָ����д�����ݵķ���
	public static boolean writeLineText(int whichline, String data) 
	{
		int lines = 7;//�ļ���Ч��Ϣ����
		String text = "";//��ʱ�洢�޸ĵ��ı�
		for(int i = 1; i <= lines; i++)
		{
			if(i == whichline)
				text = text.concat(data.concat("\n"));//�޸�ָ������Ϣ
			else
				text = text.concat(getLineText(i).concat("\n"));//�����ճ�
		}
		if(!setText(text))//�޸��ļ�ʧ��
		{
			JOptionPane.showMessageDialog(null, "��Ϣ�ļ��쳣��", "�޷�����ʾ", 0);
		    return false;
		}
		else//����޸�
			return true;		
	}
}
