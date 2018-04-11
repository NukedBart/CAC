/*
 * 有关数据库的操作类
 * 更新时间：
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
	
	private static String DRIVER = "com.mysql.jdbc.Driver";                // 不同的数据库需要加载不同的参数，这里加载的是MySQL数据库的驱动     
	private static String USER = "root";                                   // MySQL配置时的用户名            
	private static String PASSWORD = "326623";                             // MySQL配置时的密码   
	
	//反馈连接数据库状态的方法
	public static boolean linkDataBase()
	{
		try
		{
			Class.forName(DRIVER);//注册驱动/加载驱动
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD); //建立连接对象 /连接数据库 
			Statement statement = connection.createStatement(); //创建Statement/PreparedStatement对象，用来执行SQL语句    
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
	
	//查询方法
	public static String searchDataBase(String tablename, String Code, String code, String keyword, boolean which)
	{
		String data = ""; //存储字段数据
		String Sql = "";  //存储SQL语句
		if(which)
			Sql = "select * from " + tablename + " where " + Code + " = '" + code +"'"; //精准查询的SQL语句
		else
			Sql = "select * from " + tablename + " where " + Code + " like '%" + keyword + "%' or Nickname like '%"
		           + keyword + "%' or Birthday like '%" + keyword + "%'";   //模糊查询的SQL语句
		try 
		{
			Class.forName(DRIVER);//注册驱动/加载驱动
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Sql);//结果集 
			while(rs.next())
	        {
	            if(which)
	        	    data = rs.getString(keyword);
	            else
	            {
	        	   data = data.concat(rs.getString("Code").concat(" - "));//通过模糊查询获得一个字段的一组数据
	        	   switch(rs.getString("Nickname").length())
	        	   {//方便管理
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
	
	//单个字段标准模糊查询
	public static String searchProbable(String tablename, String name, String which, String keyword)
	{
		String data = ""; //存储字段数据
		String Sql = "select * from " + tablename + " where " + name + " like '%" + keyword + "%'"; 
		//模糊查询的SQL语句;
		try 
		{
			Class.forName(DRIVER);//注册驱动/加载驱动
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Sql);//结果集 
			while(rs.next())
	        {
	           data = data.concat(rs.getString(which).concat("-"));//通过模糊查询获得一个字段的一组数据	       
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
	
	//查询整形方法
	public static int queryDataBase(String tablename, String Code, String code, String keyword)
	{
		int data = 0; //存储字段数据
		String Sql = "";  //存储SQL语句
			Sql = "select * from " + tablename + " where " + Code + " = '" + code +"'"; //精准查询的SQL语句
		try 
		{
			Class.forName(DRIVER);//注册驱动/加载驱动
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Sql);//结果集 
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
	
	//修改方法
	public static boolean modifyDataBase(String tablename, String Code, String code, 
			String keyword, String updatedata)
	{
        String Sql = "update " + tablename + " set " + keyword + " = '" + 
	updatedata + "' where " + Code + " = '" + code + "'";
		try
		{
			Class.forName(DRIVER);//注册驱动/加载驱动
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + ConnectInterface.IP +":3306/ways", USER, PASSWORD);
			Statement statement = connection.createStatement();
	   		statement.executeUpdate(Sql);//更新数据的SQL语句执行
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
	
	//增加新账号方法
	public static boolean insertDataBase(String code, String password, String nickname, String mailbox)
	{
		String Sql = "insert into user (Code, Password, Nickname, Mailbox) values (?, ?, ?, ?)";
		try
		{
			Class.forName(DRIVER);//注册驱动/加载驱动
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
	
	//增加新房间的方法
	public static boolean insertRoomToDataBase
	(String tablename, String code, String property)
	{
		String Sql = "insert into " + tablename + " (Code, Property) values (?, ?)";
		try
		{
			Class.forName(DRIVER);//注册驱动/加载驱动
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
