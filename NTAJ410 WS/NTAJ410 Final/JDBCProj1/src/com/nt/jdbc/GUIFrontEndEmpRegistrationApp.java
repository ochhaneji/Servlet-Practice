package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*SQL> desc GUI_Employee
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
EID                                       NOT NULL NUMBER(5)
ENAME                                              VARCHAR2(20)
DESG                                               VARCHAR2(20)
SALARY                                             NUMBER(10)
EMAIL                                              VARCHAR2(20)
*/
/*SQL> create sequence GUI_EID_SEQ start with 100 increment by 1;*/
public class GUIFrontEndEmpRegistrationApp extends JFrame implements ActionListener,WindowListener {
	private static final String GUI_EMPLOYEE_INSERT_QUERY="INSERT INTO GUI_EMPLOYEE VALUES(GUI_EID_SEQ.NEXTVAL,?,?,?,?)";
	private JLabel lname,ldesg,lsalary,lemail,lresult;
	private JTextField tname,tdesg,tsalary,temail;
	private JButton btnRegister;
	private Connection con;
	private PreparedStatement ps;
	
	public GUIFrontEndEmpRegistrationApp() {
		System.out.println("GUIFrontEndEmpRegistrationApp:0-param constructor");
		setTitle("Employee Registration App");
		setLayout(new FlowLayout());
		setBackground(Color.GRAY);
		setSize(400,400);
		//add comps
		lname=new JLabel("Employee name::");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		
		ldesg=new JLabel("Employee Desg::");
		add(ldesg);
		tdesg=new JTextField(10);
		add(tdesg);
		
		lsalary=new JLabel("Employee Salary::");
		add(lsalary);
		tsalary=new JTextField(10);
		add(tsalary);
		
		lemail=new JLabel("Employee Email::");
		add(lemail);
		temail=new JTextField(10);
		add(temail);
		
		btnRegister=new JButton("Register");
		btnRegister.addActionListener(this);
		add(btnRegister);
		
		lresult=new JLabel("");
		add(lresult);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(this);
		
		//set Visibilty on 
		setVisible(true);
		makeConnection();
	}///constructor
	
	private  void makeConnection() {
		System.out.println("GUIFrontEndEmpRegistrationApp:makeConnection()");
		try {
			//register jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			 //create PReparedSTatement object
			 ps=con.prepareStatement(GUI_EMPLOYEE_INSERT_QUERY);
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//makeConnection()
	

	public static void main(String[] args) {
		System.out.println("main(-)");
		new GUIFrontEndEmpRegistrationApp();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUIFrontEndEmpRegistrationApp.actionPerformed(-)");
		String name=null,desg=null,email=null;
		int salary=0;
		int count=0;
		
		try {
			//read text box values
			name=tname.getText();
			desg=tdesg.getText();
			email=temail.getText();
			salary=Integer.parseInt(tsalary.getText());
			//set values to query params
			ps.setString(1,name);
			ps.setString(2,desg);
			ps.setInt(3,salary);
			ps.setString(4,email);
			//execute the Query
			count=ps.executeUpdate();
			//process the Result
			if(count==0) {
				lresult.setText("Registation failed");
				lresult.setForeground(Color.RED);
			}
			else {
				lresult.setText("Registration succeded");
				lresult.setForeground(Color.GREEN);
			}
		}//try
		catch(SQLException se) {
			lresult.setText("Registation failed");
			lresult.setForeground(Color.RED);
			se.printStackTrace();
		}
		catch(Exception e) {
			lresult.setText("Registation failed");
			lresult.setForeground(Color.RED);
			e.printStackTrace();
		}
	}//actionPerformed(-)

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("GUIFrontEndEmpRegistrationApp.windowClosing(-)");
		//close jdbc objs
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
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}//class

