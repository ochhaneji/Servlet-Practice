package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIScrollFrame extends JFrame  implements ActionListener {
	private static final String  GET_EMP_DETAILS="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	private JTextField teno,tename,tedesg,tesalary;
	private JLabel leno,lename,ledesg,lesalary;
	private JButton bFirst,bLast,bPrevious,bNext;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public GUIScrollFrame() {
		System.out.println("GUIScrollFrame:0-param constructor");
		setTitle("GUI ScrollFrame App");
		setSize(400,400);
		setLayout(new FlowLayout());
		setBackground(Color.gray);
		//add comps
		leno=new JLabel("Employee No:");
		add(leno);
		teno=new JTextField(10);
		add(teno);
		
		lename=new JLabel("Employee name:");
		add(lename);
		tename=new JTextField(10);
		add(tename);
		
		ledesg=new JLabel("Employee Desg:");
		add(ledesg);
		tedesg=new JTextField(10);
		add(tedesg);
		
		lesalary=new JLabel("Employee salary:");
		add(lesalary);
		tesalary=new JTextField(10);
		add(tesalary);
		
		bFirst=new JButton("first");
		add(bFirst);
		
		bNext=new JButton("next");
		add(bNext);
		bPrevious=new JButton("previous");
		add(bPrevious);
		bLast=new JButton("last");
		add(bLast);
		bFirst.addActionListener(this);
		bNext.addActionListener(this);
		bLast.addActionListener(this);
		bPrevious.addActionListener(this);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
		addWindowListener(new MyWindowAdapter());
	}//constructor
	
      private  void initialize() {
    	  InputStream is=null;
    	  Properties props=null;
    	  try {
    		 //Locate Properties file
    		  is=new FileInputStream("src/com/nt/commons/jdbc.properties");
    		  //Load data into java.util.Properties class obj
    		  props=new Properties();
    		  //load jdbc properties into java.util.Properties class obj
    		  props.load(is);
    		//register JDBC driver s/w
    		  Class.forName(props.getProperty("jdbc.driver"));
    		  //Establish the connection
    		  con=DriverManager.getConnection(props.getProperty("jdbc.url"),
    				                                                              props.getProperty("jdbc.username"),
    				                                                              props.getProperty("jdbc.password"));
    		  //create PreparedStatement obj having type,mode
    		  ps=con.prepareStatement(GET_EMP_DETAILS,
    				                                           ResultSet.TYPE_SCROLL_SENSITIVE,
    				                                           ResultSet.CONCUR_UPDATABLE);
    		  //send and execute SQL Query (ScrollableResultSet obj)
    		  rs=ps.executeQuery();
    	  }
    	  catch(SQLException se) {
    		  se.printStackTrace();
    	  }
    	  catch(ClassNotFoundException cnf) {
    		  cnf.printStackTrace();
    	  }
    	  catch(Exception e) {
    		  e.printStackTrace();
    	  }
    	  }//initialize()

	public static void main(String[] args) {
		System.out.println("GUIScrollFrame.main(-)");
		new GUIScrollFrame();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUIScrollFrame.actionPerformed()");
		boolean flag=false;
		try {
		if(ae.getSource()==bFirst) {
			System.out.println("first Button");
			rs.first();
			flag=true;
		}
		else if(ae.getSource()==bLast) {
			System.out.println("last Button");
			rs.last();
			flag=true;
		}
		else if(ae.getSource()==bNext) {
			System.out.println("next Button");
			if(!rs.isLast()) {
				rs.next();
				flag=true;
			}
			
		}
		else {
			System.out.println("previous Button");
			if(!rs.isFirst()) {
				rs.previous();
				flag=true;
			}
			
		}
		
		if(flag==true) {
			teno.setText(rs.getString(1));
			tename.setText(rs.getString(2));
			tedesg.setText(rs.getString(3));
			tesalary.setText(rs.getString(4));
		}
		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
	}//method
	
	//inner class
	private class  MyWindowAdapter extends WindowAdapter{
		
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("GUIScrollFrame.MyWindowAdapter.windowClosing()");
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		}//windowClosing(-)
	}//MyWindowAdapter
}//class
