/*
 * �����ж��������ı����Ͳ����������
 * ����ʱ�䣺
 * ZQW-2018-03-15
 */
package model;

public class JudgeTextField
{
	private JudgeTextField() {}
	
	//�ж�ĳ���ȷ�Χ�����ַ����ķ����������ֵ�
	public static String judgeName(String textname, int textlength, int minlength, int maxlength)
	{
		if(textlength < minlength)//���Ȳ���
			return textname.concat("���̣�");
		else
			if(textlength > maxlength)//���ȳ���
				return textname.concat("������");
			else//���Ⱥ���
				return "";
	}
	
	//�ж����������ַ����Ƿ���ͬ
	public static String judgeTwo(String textname, String text_1, String text_2)
	{
		if(text_1.equals(text_2))//��ͬ
			return "";
		else
			return textname.concat("��һ�£�");
	}
	
	//�ж�ĳ���ȷ�Χ���ֺ�Ӣ����ĸ��ɵķ���
	public static String judgePassword(String textname, String text, int minlength, int maxlength)
	{
		int textlength = text.length();//�ȼ��������
		int i;//�����ж��Ƿ�ȫ���ϸ�
		String lengthinformation = judgeName(textname, textlength, minlength, maxlength);//��ó�����Ϣ
		if(!lengthinformation.equals(""))//���Ȳ��ϸ�
			return lengthinformation;
		else//����ַ�
		{
			char [] temptext = text.toCharArray();//�洢ÿ���ַ�
			for(i = 0; i < textlength; )
			{
				if((temptext[i] >= '0' && temptext[i] <= '9')||(temptext[i] >= 'A' && temptext[i] <= 'Z')||
						(temptext[i] >= 'a' && temptext[i] <= 'z'))//�ж��ַ��Ƿ�ϸ�
					i++;
				else
					break;
			}
			if(i == textlength)//���ϸ�
				return "";
			else
				return textname.concat("���зǷ��ַ���");
		}
			
	}
	
	//�ж��Ƿ�Ϊ��Ч�ʼ���ʽ
	public static String judgeMail(String textname, String text)
	{
		int i;
		int textlength = text.length();
		char [] temptext = text.toCharArray();//�洢ÿ���ַ�
		for(i = 0; i < textlength; )
		{
			if(temptext[i] != '@')
			{
				if((temptext[i] >= '0' && temptext[i] <= '9')||(temptext[i] >= 'A' && temptext[i] <= 'Z')||
						(temptext[i] >= 'a' && temptext[i] <= 'z'))
					i++;
				else//@ǰ�溬�зǷ��ַ�
					i = textlength;
			}
			else//�ҵ���һ��@
				break;	
		}
		if(i == 0 || i == textlength || textlength < 8)
			return textname.concat("��ʽ����ȷ��");
		else//���@�����ַ�
		{
			if(temptext[textlength - 1] != 'm' || temptext[textlength - 2] != 'o' || 
					temptext[textlength - 3] != 'c' || temptext[textlength - 4] != '.')//�����ĸ��ַ�
				return textname.concat("��ʽ����ȷ��");
			else
			{
				if(textlength - 5 - i == 0)
					return textname.concat("��ʽ����ȷ��");
				else
				{
					char [] mid = new char[textlength - 5 - i];//�洢�м��ַ�������
					for(int j = i + 1; j < textlength - 4; j++)
					{
						mid[j - i -1] = temptext[j];
					}
					String med = String.valueOf(mid);
					if(med.equals("126") || med.equals("189") || med.equals("qq") || med.equals("outlook"))
						return "";
					else
						return textname.concat("������");
				}
			}
		}
	}

}
