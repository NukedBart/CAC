/*
 * 对界面的整体做调整的类
 * 更新时间：ZQW-2018-03-13
 */

package controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SetUI 
{
	
	private  SetUI() {}
	
	public static void setFrameCenter(JFrame jf)//将窗体在屏幕中间启动，该方法具有普适性
    {
     /*  思路：
         A：首先获取屏幕的长和宽
         B：然后获取窗体的长和宽
         C：再用屏幕的长和宽分别减去窗体的，再除以2
         D：得到的值作为窗体出现的坐标
      */
     //获取默认工具包
		Toolkit tl = Toolkit.getDefaultToolkit();
        /*
         * 获取屏幕的长和宽，不确定什么类型，用double类 
         * public abstract Dimension getScreenSize()
         */
        Dimension d = tl.getScreenSize();
        double ScreenHeigth = d.getHeight();
        double ScreenWidth = d.getWidth();
        //获取窗体的长和宽,这个是int类的
        int FrameWidth = jf.getWidth();
        int FrameHeigth = jf.getHeight();  
        //相减再除以2，因为设置窗体出现的坐标所需要的是int类的，所以要转换成int类
        int Higth = (int)(ScreenHeigth - FrameHeigth)/2;
        int Width = (int)(ScreenWidth - FrameWidth)/2;
        //值作为窗体出现的坐标
        jf.setLocation(Width, Higth);
         //去窗体类中调用该方法
     }

}
