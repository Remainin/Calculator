package com.zhangmingshuai;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.*;
import java.util.Date;
import java.text.SimpleDateFormat;

class POL_Y  //���ʽ�е���ĸ��
{
	char ch;
	int pef;
}

class Poly //���ڴ洢���ʽ
{
	int coe;
	ArrayList<POL_Y> Ch = new ArrayList<POL_Y>();
}

//git test

//git test again

class f
{
    static ArrayList <String> S_3 = new ArrayList <String>();
    static Poly p_original[];
    static int abs[];
    public static boolean isNumeric(String str)
    {   										//�ж��ַ������Ƿ�Ϊ����
    	   Pattern pattern = Pattern.compile("[0-9]*"); 
    	   Matcher isNum = pattern.matcher(str);
    	   if( !isNum.matches() ){
    	       return false; 
    	   } 
    	   return true; 
    	}
	public boolean Judge(String a)
	{
		char c[] = a.toCharArray();
	    abs = new int[a.length()];
		for(int i=0;i<a.length();i++)
		{		//�ų����Ϸ��ַ�
			if(c[i]=='+' || c[i]=='-' || c[i]=='*' || c[i]=='^' || (c[i]>='0' && c[i]<='9') 
			|| (c[i]>='A' && c[i]<='Z') || (c[i]>='a' && c[i]<='z'));
			else
				return false;
		}
		if (a.length() == 0)
			return false;
		for(int i=0;i<a.length();i++)
			abs[i] = -1;
		int j=0,z=0;
		char d[];
		if(c[0]!='-')
		{
			d = new char[a.length()+1];
			d[0] = '+';
			for(int i =0;i<a.length();i++)
				d[i+1] = c[i];
		}
		else
		{
			d = new char[a.length()];
			for(int i =0;i<a.length();i++)
				d[i] = c[i];
		}
		for(int i=0;i<d.length;i++)
		{
			if(d[i]=='+')
				z++;
			else if(d[i]=='-')
			{
				d[i]='+';	
				abs[j]=z;    //�ڵ�j+1������ǰ�ж��ٸ�С��������Ӽ�����
				z++;		//Ҳ�����жϼ��ŵ�λ��
				j++;
			}
		}
		for(int i=0;i<c.length;i++)
			if(c[i]=='-')
				c[i] = '+';
		String S_1 = new String(c);
		String S_2[] = S_1.split("\\+");
		for(int i=0;i<S_2.length;i++)
			if(S_2[i].length()>0) 
				S_3.add(S_2[i]);
		for(int w=0;w<S_3.size();w++)
		{
			String st = S_3.get(w);
			char now_str[] = st.toCharArray();
			for(int i=0;i<now_str.length;i++)
			{		//���������㣬�����������ǰ�棬�����������ں���
				if(c[i]=='^') 
				{
					if (i==0 ||i==now_str.length-1||(c[i-1] >= '1'&&c[i-1]<='9')||c[i+1]=='-'||
					(c[i+1] >= 'A'&&c[i+1]<='Z')||(c[i+1] >= 'a'&&c[i+1]<='z'))
						return false;
				}
			}
				
			String every_st[] = st.split("\\^|\\*");
			for (int i=0;i<every_st.length;i++)
			{
				if (every_st[i].length()!=0)
				{
					char ch[] = every_st[i].toCharArray();
					if(ch.length>1)
					{
					for (int y=0;y<ch.length-1;y++)
					{		//�ų�3a,ba,b4���ֲ��Ϸ����龰
						if(((ch[y]>='0' && ch[y]<='9')&&((ch[y+1]>='a' && ch[y+1]<='z')||
						(ch[y+1]>='A' && ch[y+1]<='Z')))||
						(((ch[y+1]>='0' && ch[y+1]<='9')||(ch[y+1]>='a' && ch[y+1]<='z')||(ch[y+1]>='A' && ch[y+1]<='Z'))&&
						((ch[y]>='a' && ch[y]<='z')||(ch[y]>='A' && ch[y]<='Z'))))
							return false;
					}
					}
				}
			}
		}
		return true;
			
	}
	
	public  void expression()
	{	
		p_original = new Poly[S_3.size()];
		for(int i=0;i<S_3.size();i++)
		{
			p_original[i] = new Poly();
			p_original[i].coe = 1;
			char c[] = S_3.get(i).toCharArray();
			for(int j=0;j<c.length;)
			{
				if(c[j]=='^')   //Ѱ��������
				{
					POL_Y m = new POL_Y();
					m.ch = c[j-1];       //�ݲ���������ĸ��
					m.pef = c[j+1]-'0';  //��ָ��
					p_original[i].Ch.add(m);
					c[j-1]=c[j]=c[j+1] = '*';  //֮��㲻���Ǵ���
					j+=3;
				}
				else
					j++;
			}
			String st =  new String(c);
			String st_arr[] = st.split("\\*");
			for(int j=0;j<st_arr.length;j++)
			{					
				if (st_arr[j].length()!=0)
				{
				if(f.isNumeric(st_arr[j]))
				{
						int integer = Integer.parseInt(st_arr[j]);
						p_original[i].coe *= integer;  //Ŀǰ��С�˷����ʽ�Ŀ���ͨ����֪�����ּ���õ��Ľ��
				}
				else 
				{
					char now_char[] = st_arr[j].toCharArray();
					POL_Y m = new POL_Y();
					m.ch = now_char[0];          //��Ҫ���˷���δ֪��ĸ
					m.pef = 1;
					p_original[i].Ch.add(m);
				}
				}
			}
		}
		for(int i=0;i<S_3.size();i++)
			for(int j=0;j<abs.length && abs[j]!=-1;j++)
					if(abs[j]==i)   //������Ϊ��
						p_original[i].coe = -p_original[i].coe;
		print(p_original);
}
	public  void print(Poly p[])
	{
		int z;
		for(z=0;z<p.length;z++)
			if(p[z].coe!=0 )  
				break;
		if (z == p.length)
		{
			System.out.print("0");	//ȫ��0�������z=p.length-1;
			System.out.println();
			return;
		}
		System.out.print(p[z].coe);
		for(int j=0;j<p[z].Ch.size();j++)
			if(p[z].Ch.get(j).pef>1)
				System.out.print("*"+p[z].Ch.get(j).ch+"^"+p[z].Ch.get(j).pef);
			else
				System.out.print("*"+p[z].Ch.get(j).ch);
		for(int i=z+1;i<p.length;i++)
		{
			if(p[i].coe>0)
			{
				System.out.print("+"+p[i].coe);
				for(int j=0;j<p[i].Ch.size();j++)
					if(p[i].Ch.get(j).pef>1)
						System.out.print("*"+p[i].Ch.get(j).ch+"^"+p[i].Ch.get(j).pef);
					else
						System.out.print("*"+p[i].Ch.get(j).ch);
			}
			else if(p[i].coe<0)
			{
				System.out.print(p[i].coe);
				for(int j=0;j<p[i].Ch.size();j++)
					if(p[i].Ch.get(j).pef>1)
						System.out.print("*"+p[i].Ch.get(j).ch+"^"+p[i].Ch.get(j).pef);
					else
						System.out.print("*"+p[i].Ch.get(j).ch);
			}
		}
		System.out.println();
	}
		
	public boolean simplify(String a)
	{
		Poly p[] = new  Poly[p_original.length];     //����Ӧ�Ĵ洢�ṹ���и��Ʋ���
		for (int i=0;i<p_original.length;i++)			//����ԭ���ʽ������
		{
			p[i] = new Poly();
			p[i].coe = p_original[i].coe;
			for (int j=0;j<p_original[i].Ch.size();j++)
			{
				POL_Y m = new POL_Y();
				m.pef = p_original[i].Ch.get(j).pef;
				m.ch = p_original[i].Ch.get(j).ch;
				p[i].Ch.add(m);
			}
		}
		if(a.length()==9)    //����δ�����κα�����ֵ�����
		{
			print(p);
			return true;
		}
		else 
		{
			boolean flag = false;
			String sim[] = a.split("\\s+");
			for (int i=1;i<sim.length;i++)
			{
				char l_sim[] = sim[i].toCharArray();
				for(int j =2;j<l_sim.length;j++)              //���ڸ�������ֵΪ��λ�������
				{
					if (!(l_sim[j]>='0'&&l_sim[j]<='9'))  
						return false;
				}
				if ((l_sim[0]>='A' && l_sim[0]<='Z') || (l_sim[0]>='a' && l_sim[0]<='z') &&l_sim[1]=='=')
				{
					String ll_sim[] =sim[i].split("=");   //����֮�������ַ���ת����
					for(int w=0;w<p.length;w++)
						for(int j=0;j<p[w].Ch.size();j++)
						{
							if(p[w].Ch.get(j).ch==l_sim[0])			//�������µĸ�ֵ�����µ�coe
							{
								p[w].coe *= Math.pow(Integer.parseInt(ll_sim[1]),p[w].Ch.get(j).pef);
								p[w].Ch.remove(p[w].Ch.get(j));	//ɾ����Ӧ��δ֪����
								flag = true;
							}
						}
				}
				else
					return false;
			}
			/*for(int i=10;i<c.length;)  //�˷�����̭��ֻ�ܶԸ�λ����������x=999�Ͳ���������
			{
				if((c[i]>='A' && c[i]<='Z') || (c[i]>='a' && c[i]<='z') && c[i+1]=='='
					&& (c[i+2]>='0' && c[i+2]<='9'))
				{
					for(int w=0;w<p.length;w++)
						for(int j=0;j<p[w].Ch.size();j++)
						{
							if(p[w].Ch.get(j).ch==c[i])			//�������µĸ�ֵ�����µ�coe
							{
								p[w].coe *= Math.pow(c[i+2]-'0',p[w].Ch.get(j).pef);
								p[w].Ch.remove(p[w].Ch.get(j));	//ɾ����Ӧ��δ֪����
								flag = true;
							}
						}
					i += 4;
				}
				else
					return false;
			}*/
			int final_number=-1;  //���ϲ������ִ��ڸ�������
			int sum = 0;
			boolean f = true;
			for (int i=0;i<p.length;i++)
			{
				if(p[i].Ch.size()==0)
				{
					if (f)
					{
						final_number = i;
						f = false;
					}
					sum += p[i].coe;       //�����ֵ�󽫸�ֵɾ��
					p[i].coe = 0;
				}
			}
			if (final_number != -1)
			{
				p[final_number].coe = sum;
			}
			if(!flag)
				System.out.println("û�д˱���!");
			else
				print(p);
			return true;
		}
	}
	
	public boolean derivative(String a)
	{
		Poly p[] = new  Poly[p_original.length];
		for (int i=0;i<p_original.length;i++)
		{
			p[i] = new Poly();
			p[i].coe = p_original[i].coe;
			for (int j=0;j<p_original[i].Ch.size();j++)
			{
				POL_Y m = new POL_Y();
				m.pef = p_original[i].Ch.get(j).pef;
				m.ch = p_original[i].Ch.get(j).ch;
				p[i].Ch.add(m);
			}
		}
		boolean flag_1 = false;
		boolean flag_2[] = new boolean[p.length];
		for(int i=0;i<flag_2.length;i++)
			flag_2[i] = false;
		if(a.length()==5)
		{
			char c = a.toCharArray()[4];
			if((c>='A' && c<='Z') || (c>='a' && c<='z'))
			{
				for(int i=0;i<p.length;i++)
					for(int j=0;j<p[i].Ch.size();j++)
					{
						if(p[i].Ch.get(j).ch == c)
						{
							p[i].coe *= p[i].Ch.get(j).pef;
							p[i].Ch.get(j).pef--;
							if(p[i].Ch.get(j).pef==0)
								p[i].Ch.remove(p[i].Ch.get(j));
							flag_1 = true;
							flag_2[i] = true;			//flag2��¼��Ӧ������
						}
					}				
			}
			else
				return false;
			if(!flag_1)
				System.out.println("û�д˱���!");
			else
			{
				for(int i=0;i<flag_2.length;i++)
					if(!flag_2[i])
						p[i].coe = 0;
				print(p);
			}
			return true;
		}
		else
			return false;
	}
}

public class lab_1{
	public static void main(String args[])
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd	HH:mm:ss");
		Scanner in = new Scanner(System.in);
		String begin = new String();
		String end = new String();
		begin = df.format(new Date());
		Date no = new Date();
		long bb = no.getTime();
		f F = new f();
		String a = in.nextLine();
		if(!F.Judge(a))
			System.out.println("���벻�Ϸ�!");
		else
		{
			F.expression();
			while(true)
			{
				String b = in.nextLine();
				if(b.startsWith("!simplify"))
				{	
					if(!F.simplify(b))
						System.out.println("���벻�Ϸ�,��������");
				}		
				else if(b.startsWith("!d/d"))
				{	
					if(!F.derivative(b))
						System.out.println("���벻�Ϸ�,��������");
				}
				else if(b.startsWith("over"))
					{
					      end = df.format(new Date());
					      Date en = new Date();
						  long cc = en.getTime();
					      System.out.println("��ʼʱ��Ϊ��"+begin);
					      System.out.println("����ʱ��Ϊ��"+end);
					      long l = cc-bb;
					      long day = l/(24*60*60*1000);
					      long hour = (l/(60*60*1000)-day*24);
					      long min = ((l/(60*1000))-day*24*60-hour*60);
					      long s = (l/1000-day*24*60*60-hour*60*60-min*60);
					      System.out.println("�ķ�ʱ��Ϊ:"+day+"��"+hour+"Сʱ"+min+"��"+s+"��");
			        }
				else
					System.out.println("���벻�Ϸ�,��������");
			}
		}
	}
}
