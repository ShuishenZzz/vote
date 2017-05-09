package com.vote.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Func {
	
	/**
	 * ��ʽ����ʽ
	 */
	private static String formatPattern = "yyyy-MM-dd";
	
	private static SimpleDateFormat formatDate = new SimpleDateFormat(formatPattern);
	
	/**
	 * ��ʽ������ ���ַ���ת��Ϊ����
	 * @param datestr �����ַ���
	 * @return ��ʽ���������
	 */
	public static Date formatDate(String datestr){
		Date date = null;
		try {
			date = formatDate.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * �����ڸ�ʽ��Ϊyyyy-MM-dd��ʽ���ַ���
	 * @param date ����
	 * @return yyyy-MM-dd��ʽ���ַ���
	 */
	public static String formatString(Date date){
		String datestr = null;
		datestr = formatDate.format(date);
		return datestr;
	}
	
	/**
	   * ��ȡ��ǰ����
	   * @param format ��ʽ��ģ��
	   * @return ��ʽ��֮��������ַ���
	   */
	  public static String getCurrDate(String format)
	  {
	    SimpleDateFormat sdfDate = new SimpleDateFormat(format);
	    return sdfDate.format(Calendar.getInstance().getTime());
	  }
	  
	  /**
	   * ��ȡ��ǰ����
	   * @return ��ǰ�����ַ���
	   */
	  public static String getCurrDate()
	  {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy��MM��dd��");
	    return sdfDate.format(Calendar.getInstance().getTime());
	  }
	  
	  /**
	   * ��ȡ��ǰ���
	   * @return ��ǰ����ַ���
	   */
	  public static String getCurrYear()
	  {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy");
	    return sdfDate.format(Calendar.getInstance().getTime());
	  }
	
	  /**
	   * ���������ַ�������Ϊnull����""����ô����" "�����򷵻ش�����ַ���
	   * @param s ������ַ���
	   * @return ������ַ���
	   * @throws Exception
	   */
	   public static String getBlankSpaceString(String s) 
	   {
	     if(s==null)
	       return " ";
	     else if(s.equals(""))
	       return " ";
	     else
	       return s;
	   }  
	  
	  /**
	   * ���������ַ�������Ϊnull����ô����""�����򷵻ش�����ַ���
	   * @param o ������ַ�������
	   * @return String
	   * @throws Exception
	   */
	   public static String getString(Object o) 
	   {
	     if(o==null)
	       return "";
	     else
	       return String.valueOf(o);
	   }
	   
	   /**
	    * �����������ֲ���Ϊ0����ô����""�����򷵻ش��������
	    * @param d
	    * @return
	    */
	   public static String getString(Double d) 
	   {
	     if(d==0)
	       return "";
	     else if(String.valueOf(d).indexOf(".0")>-1)
	     {
	    	 return String.valueOf(d).substring(0,String.valueOf(d).indexOf(".0"));
	     }
	     else
	       return String.valueOf(d);
	   }
	   
	   /**
	    * ���ַ������������[ 'a','b' ]���أ����ַ�������ĳ���Ϊ0����ô����""
	    * @param a
	    * @return [ 'a','b' ]��ʽ���ַ���
	    */
	   public static String arrayToSqlIn(String[] a) 
	   {
	     String sR = "";
	     if(a.length==0)
	       sR = "";
	     else
	     {
	       for(int i=0;i<a.length;i++)
	       {
	         sR += "'" + a[i]+"',";
	       }
	       sR = sR.substring(0, sR.length()-1);
	     }
	     
	     return sR;
	   }  
	   
	   /**
	    * ���������������[ a,b ]���أ�������ĳ���Ϊ0����ô����""
	    * @param a
	    * @return
	    */
	   public static String arrayToSqlIn(int[] a) 
	   {
	     String sR = "";
	     if(a.length==0)
	       sR = "";
	     else
	     {
	       for(int i=0;i<a.length;i++)
	       {
	         sR += a[i]+",";
	       }
	       sR = sR.substring(0, sR.length()-1);
	     }
	     return sR;
	   } 
	   
	   /**
	    * ��ȡ�̶����ȵ����������
	    * @param curr ��ǰ��ʹ�õ������ֵ
	    * @param digit ����
	    * @return ���������
	    * @throws Exception
	    */
	   public static String getNewIndex(long curr, int digit) throws Exception
	   {
	     long idx = curr + 1;
	     long s = 1;
	     for (int i = 0; i < digit; i++)
	       s = s * 10;
	     if(idx>=s)
	       throw new Exception("��������˳��ų�����Χ["+s+"]");
	     
	     String sIdx = String.valueOf(s + idx).substring(1);

	     return sIdx;
	   }
	   
	   /**
	    * �ж����ںϷ���
	    * @param dateStr �����ַ��� 8λ����(yyyyMMdd)
	    * @return �������ȷ�����ڷ���true�����򷵻�false
	    */
	   public  static boolean checkDate(String dateStr) throws Exception
	   {
	     if(!isFixLengthNum(dateStr,8)) return false;
	     dateStr = dateStr.substring(0, 4)+"-"+dateStr.substring(4, 6)+"-"+dateStr.substring(6, 8);
	     DateFormat df = DateFormat.getDateInstance();
	     df.setLenient(false);
	     try
	     {
	       Date date = df.parse(dateStr);
	       return (date != null);
	     }
	     catch (Exception e)
	     {
	       return false;
	     }
	   }
	   
	   /**
	    * �����ַ������� һ�������ַ��ĳ���Ϊ2
	    * @param str �ַ���
	    * @return �ַ�������
	    * @throws Exception
	    */
	   public static int getStringLength(String str) throws Exception
	   {
	     String s = getString(str);
	     int length = 0;
	     length = s.getBytes("GBK").length;
	     return length;
	   }
	   
	   /**
	    * �ַ����Ƿ��ǹ̶����ȵ�����
	    * @param str �ַ���
	    * @param length ����
	    * @return ����Ƿ���true ���򷵻�false
	    * @throws Exception
	    */
	   public static boolean isFixLengthNum(String str,int length) throws Exception
	   {
	     String regexNum = "\\d{"+length+"}"; // �Ƚ���������ʽ
	     Pattern patternNum = Pattern.compile(regexNum); // ��ʽ���ȽϹ���
	     Matcher isNum = patternNum.matcher(str);
	     return isNum.matches();
	   }
	   
	  
	  /**
	   * ��java.util.dateת��Ϊjava.sql.date
	   * @param date java.util.date
	   * @return ת�����java.sql.date
	   */
	  public static java.sql.Date toSQLDate(Date date) 
	  {
	    if(date==null) return null;
	    java.sql.Date sqlDate = null;
	    try
	    {
	      sqlDate = new java.sql.Date(date.getTime());
	    }
	    catch(Exception ex)
	    {
	      ;
	    }
	    return sqlDate;
	  }
	  
	  /**
	   * ����2������֮����������
	   * @param startDate ����1
	   * @param endDate ����2
	   * @return ���������
	   * @throws Exception
	   */
	  public static long getYearsBetween(Date startDate,Date endDate) throws Exception
	  {
	    if(startDate == null || endDate == null) return 0;
	    long years = 0;
	    long passtime = endDate.getTime() - startDate.getTime();
	    years = passtime / (24 * 60 * 60 * 1000)/365;
	    return years;
	  }
	  
	  /**
	   * ����С��
	   * @param value ��ֵ
	   * @param scale С��λ��
	   * @return ���������Ľ��ֵ
	   * @throws Exception
	   */
	  public static double changeDecimal(double value, int scale) throws Exception
	  {
	    if(scale < 0)
	    {
	      throw new IllegalArgumentException("The scale must be a positive integer or zero");
	    }
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);//�������봦��
	    double num = bd.doubleValue();
	    return num;
	  }	
	
	/**
	 * ��ȡ�û���ʵIP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) { 
	     String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	         ip = request.getHeader("Proxy-Client-IP"); 
	     } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	         ip = request.getHeader("WL-Proxy-Client-IP"); 
	     } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	          ip = request.getRemoteAddr(); 
	      } 
	     return ip; 
	  }


}
