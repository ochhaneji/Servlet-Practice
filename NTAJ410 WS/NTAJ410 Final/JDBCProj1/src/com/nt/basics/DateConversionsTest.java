package com.nt.basics;

import java.text.SimpleDateFormat;

public class DateConversionsTest {

	public static void main(String[] args) throws Exception{
		 //Converting String date value to java.util.Date class obj
		String s1="15-08-1947"; //dd-MM-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf.parse(s1);
		System.out.println("util date value ::"+ud1);
		//converting java.util.Date class obj to java.sql.Date class object
		long ms=ud1.getTime();  //ms between ud1 date to jan 1st 1970 midnight 00:00 hrs(epoch)
		System.out.println("ms="+ms);
		java.sql.Date sd1=new java.sql.Date(ms); 
		System.out.println("sql date value::"+sd1);
		
		//if String date value is there is yyyy-MM-dd pattern then it can
		///be converted directly to java.sql.Date class obj with out converting
		// it to java.util.Date class object
		String s2="2010-12-18"; // yyyy-MM-dd
		java.sql.Date sd2=java.sql.Date.valueOf(s2);
		System.out.println("sql date value::"+sd2);
		
		//converting java.sql.Date class obj to java.util.Date class obj
		java.util.Date ud2=(java.util.Date)sd2;
		System.out.println("util Date::"+ud2);
		
		//Converting java.util.Date class obj to String values
		SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
		String s4=sdf1.format(ud2);
		System.out.println("String date:::"+s4);
		
		
		
		
		

	}

}
