/*
 * ����ʽ�ȴ����ӷ���������
 * ����ʱ�䣺
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

	public static Timer timer;//�ڲ���Ҫʱ�������ر����񣬽�ʡ�ռ�
	private static int which = 1;//ָʾ�ĸ���ǩ�仯
	private static boolean cannot = true;//��ֹ����ˢ�´����Ĳ���ȷ��ʾ
	
	public static void waitConnect(JLabel label, JLabel label_1, JLabel label_2, JLabel label_3)//��ʾ�û�����ִ������Ĳ������÷������е�һ��
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
							label_1.setFont(new Font("��Բ", Font.BOLD, 22));
							label_3.setFont(new Font("��Բ", Font.BOLD, 16));
							which = 2;break;
						case 2:
							label_2.setFont(new Font("��Բ", Font.BOLD, 22));
							label_1.setFont(new Font("��Բ", Font.BOLD, 16));
							which = 3;break;
						case 3:
							label_3.setFont(new Font("��Բ", Font.BOLD, 22));
							label_2.setFont(new Font("��Բ", Font.BOLD, 16));
							which = 1;break;
					}
				}
			}, 0, 300);
			cannot = false;
		}
	}
}
