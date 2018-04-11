/*
 * 提醒式等待连接服务器的类
 * 更新时间：
 * ZQW-2018-03-14
 */

package controller;

import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Waiting 
{
	
	private Waiting() {}

	public static Timer timer;//在不需要时便于外界关闭任务，节省空间
	private static int which = 1;//指示哪个标签变化
	private static boolean cannot = true;//防止恶意刷新带来的不正确显示
	
	public static void waitConnect(JLabel label, JLabel label_1, JLabel label_2, JLabel label_3)//提示用户正在执行最近的操作，该方法具有单一性
	{
		timer = new Timer();
		
		label.setVisible(true);
		label_1.setVisible(true);
		label_2.setVisible(true);
		label_3.setVisible(true);
		
		if(cannot)
		{
			timer.schedule(new TimerTask()
			{		
				@Override
				public void run()
				{
					// TODO Auto-generated method stub
					switch(which)
					{
						case 1:
							label_1.setFont(new Font("幼圆", Font.BOLD, 22));
							label_3.setFont(new Font("幼圆", Font.BOLD, 16));
							which = 2;break;
						case 2:
							label_2.setFont(new Font("幼圆", Font.BOLD, 22));
							label_1.setFont(new Font("幼圆", Font.BOLD, 16));
							which = 3;break;
						case 3:
							label_3.setFont(new Font("幼圆", Font.BOLD, 22));
							label_2.setFont(new Font("幼圆", Font.BOLD, 16));
							which = 1;break;
					}
				}
			}, 0, 300);
			cannot = false;
		}
	}
}
