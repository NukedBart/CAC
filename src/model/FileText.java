/*
 * 用于对指定文件进行读写操作
 * 更新时间：
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
	
	//读取指定行的内容的方法
	public static String getLineText(int whichline)
	{
		String [] result = new String[whichline];//存储读取结果
        File file = new File("src/files/UserInformation");  
        BufferedReader reader = null;  
        try 
        {   
            reader = new BufferedReader(new FileReader(file));
            for(int i = 0; i < whichline; i++)
            {
            	result[i] = reader.readLine();//存储每一行的信息
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

	//对文件进行写操作的方法
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

	//项文件指定行写入内容的方法
	public static boolean writeLineText(int whichline, String data) 
	{
		int lines = 7;//文件有效信息行数
		String text = "";//临时存储修改的文本
		for(int i = 1; i <= lines; i++)
		{
			if(i == whichline)
				text = text.concat(data.concat("\n"));//修改指定行信息
			else
				text = text.concat(getLineText(i).concat("\n"));//其余照抄
		}
		if(!setText(text))//修改文件失败
		{
			JOptionPane.showMessageDialog(null, "信息文件异常！", "无服务提示", 0);
		    return false;
		}
		else//完成修改
			return true;		
	}
}
