/*
 * �Խ������������������
 * ����ʱ�䣺ZQW-2018-03-13
 */

package controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SetUI 
{
	
	private  SetUI() {}
	
	public static void setFrameCenter(JFrame jf)//����������Ļ�м��������÷�������������
    {
     /*  ˼·��
         A�����Ȼ�ȡ��Ļ�ĳ��Ϳ�
         B��Ȼ���ȡ����ĳ��Ϳ�
         C��������Ļ�ĳ��Ϳ�ֱ��ȥ����ģ��ٳ���2
         D���õ���ֵ��Ϊ������ֵ�����
      */
     //��ȡĬ�Ϲ��߰�
		Toolkit tl = Toolkit.getDefaultToolkit();
        /*
         * ��ȡ��Ļ�ĳ��Ϳ���ȷ��ʲô���ͣ���double�� 
         * public abstract Dimension getScreenSize()
         */
        Dimension d = tl.getScreenSize();
        double ScreenHeigth = d.getHeight();
        double ScreenWidth = d.getWidth();
        //��ȡ����ĳ��Ϳ�,�����int���
        int FrameWidth = jf.getWidth();
        int FrameHeigth = jf.getHeight();  
        //����ٳ���2����Ϊ���ô�����ֵ���������Ҫ����int��ģ�����Ҫת����int��
        int Higth = (int)(ScreenHeigth - FrameHeigth)/2;
        int Width = (int)(ScreenWidth - FrameWidth)/2;
        //ֵ��Ϊ������ֵ�����
        jf.setLocation(Width, Higth);
         //ȥ�������е��ø÷���
     }

}
