/*
 * �й����ݿ�Ĳ�����
 * ����ʱ�䣺
 * ZQW-2018-03-14
 * ZQW-2018-03-15
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import viewer.ConnectInterface;

public class DataBase 
{
	
	private DataBase() {}
	
	private static String DRIVER = "com.mysql.jdbc.Driver";                // ��ͬ�����ݿ���Ҫ���ز�ͬ�Ĳ�����������ص���MySQL���ݿ������     
	private static String USER = "root";                                   // MySQL����ʱ���û���            
	private static String PASSWORD = "326623";                             // MySQL����ʱ������   
	
	//�����������ݿ�״̬�ķ���
	public static boolean linkDataBase()
	{
		try
		{
			Class.forName(DRIVER);//ע������/��������
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD); //�������Ӷ��� /�������ݿ� 
			Statement statement = connection.createStatement(); //����Statement/PreparedStatement��������ִ��SQL���    
	        statement.close();
	   	    connection.close();
	   	    return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//��ѯ����
	public static String searchDataBase(String tablename, String Code, String code, String keyword, boolean which)
	{
		String data = ""; //�洢�ֶ�����
		String Sql = "";  //�洢SQL���
		if(which)
			Sql = "select * from " + tablename + " where " + Code + " = '" + code +"'"; //��׼��ѯ��SQL���
		else
			Sql = "select * from " + tablename + " where " + Code + " like '%" + keyword + "%' or Nickname like '%"
		           + keyword + "%' or Birthday like '%" + keyword + "%'";   //ģ����ѯ��SQL���
		try 
		{
			Class.forName(DRIVER);//ע������/��������
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Sql);//����� 
			while(rs.next())
	        {
	            if(which)
	        	    data = rs.getString(keyword);
	            else
	            {
	        	   data = data.concat(rs.getString("Code").concat(" - "));//ͨ��ģ����ѯ���һ���ֶε�һ������
	        	   switch(rs.getString("Nickname").length())
	        	   {//�������
	        	       case 2:data = data.concat(rs.getString("Name").concat("      ;"));break;
	        	       case 3:data = data.concat(rs.getString("Name").concat("     ;"));break;
	        	       case 4:data = data.concat(rs.getString("Name").concat("    ;"));break;
	        	       case 5:data = data.concat(rs.getString("Name").concat("   ;"));break;
	        	       case 6:data = data.concat(rs.getString("Name").concat("  ;"));break;
	        	       case 7:data = data.concat(rs.getString("Name").concat(" ;"));break;
	        	       case 8:data = data.concat(rs.getString("Name").concat(";"));break;
	        	       default:break;
	        	   }
	            }
	         }
	         rs.close();
		     statement.close();
		     connection.close();
		}
		catch(Exception e)
		{
		       e.printStackTrace();
		       data = "";
		}
		return data;
	}
	
	//�����ֶα�׼ģ����ѯ
	public static String searchProbable(String tablename, String name, String which, String keyword)
	{
		String data = ""; //�洢�ֶ�����
		String Sql = "select * from " + tablename + " where " + name + " like '%" + keyword + "%'"; 
		//ģ����ѯ��SQL���;
		try 
		{
			Class.forName(DRIVER);//ע������/��������
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Sql);//����� 
			while(rs.next())
	        {
	           data = data.concat(rs.getString(which).concat("-"));//ͨ��ģ����ѯ���һ���ֶε�һ������	       
	        }
	        rs.close();
		    statement.close();
		    connection.close();
		}
		catch(Exception e)
		{
		    e.printStackTrace();
	        data = "";
		}
		return data;
	}
	
	//��ѯ���η���
	public static int queryDataBase(String tablename, String Code, String code, String keyword)
	{
		int data = 0; //�洢�ֶ�����
		String Sql = "";  //�洢SQL���
			Sql = "select * from " + tablename + " where " + Code + " = '" + code +"'"; //��׼��ѯ��SQL���
		try 
		{
			Class.forName(DRIVER);//ע������/��������
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Sql);//����� 
			while(rs.next())
	        {
	            
	        	    data = rs.getInt(keyword);
	         }
	         rs.close();
		     statement.close();
		     connection.close();
		}
		catch(Exception e)
		{
		       e.printStackTrace();
		       data = 0;
		}
		return data;
	}
	
	//�޸ķ���
	public static boolean modifyDataBase(String tablename, String Code, String code, 
			String keyword, String updatedata)
	{
        String Sql = "update " + tablename + " set " + keyword + " = '" + 
	updatedata + "' where " + Code + " = '" + code + "'";
		try
		{
			Class.forName(DRIVER);//ע������/��������
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			Statement statement = connection.createStatement();
	   		statement.executeUpdate(Sql);//�������ݵ�SQL���ִ��
		    statement.close();
		    connection.close();
			return true;
		}						
	    catch(Exception e)
		{
		    e.printStackTrace();
		    return false;
		}
	}
	
	//�������˺ŷ���
	public static boolean insertDataBase(String code, String password, String nickname, String mailbox)
	{
		String Sql = "insert into user (Code, Password, Nickname, Mailbox) values (?, ?, ?, ?)";
		try
		{
			Class.forName(DRIVER);//ע������/��������
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			PreparedStatement statement = connection.prepareStatement(Sql);
		    statement.setString(1, code);
			statement.setString(2, password);
			statement.setString(3, nickname);
			statement.setString(4, mailbox);
			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//�����·���ķ���
	public static boolean insertRoomToDataBase
	(String tablename, String code, String property)
	{
		String Sql = "insert into " + tablename + " (Code, Property) values (?, ?)";
		try
		{
			Class.forName(DRIVER);//ע������/��������
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			PreparedStatement statement = connection.prepareStatement(Sql);
		    statement.setString(1, code);
			statement.setString(2, property);
			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
