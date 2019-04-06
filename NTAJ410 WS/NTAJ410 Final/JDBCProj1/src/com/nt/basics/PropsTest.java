package com.nt.basics;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropsTest {

	public static void main(String[] args)throws Exception {
		InputStream is=null;
		Properties props=null;
		//Locate Text properties file
		is=new FileInputStream("src/com/nt/commons/file.properties");
		//Create an empty java.util.Properties class obj
		props=new Properties();
		//Load Text Properties info to Proeprties class obj
		props.load(is);
		System.out.println("props data"+ props);
		System.out.println("per.name key value ::"+props.getProperty("per.name"));
		System.out.println("per.age key value ::"+props.getProperty("per.age"));
	}//main
}//class
