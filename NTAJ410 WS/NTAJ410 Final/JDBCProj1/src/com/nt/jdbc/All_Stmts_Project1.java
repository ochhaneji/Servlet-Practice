package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*create or replace  procedure  p_find_pass_fail(m1 in number,
        m2 in number,
        m3 in number,
        result out varchar)
as
begin
if(m1<35 or m2<35 or m3<35)then
result:='FAIL';
else
result:='PASS';
end if;
end;
/
*/

public class All_Stmts_Project1 extends JFrame implements ActionListener  {
	private static final String  GET_ALL_SNOs="SELECT SNO FROM ALL_STUDENT";
	private static final String  GET_STUD_BY_NO="SELECT SNAME,M1,M2,M3 FROM ALL_STUDENT WHERE SNO=?";
	private static final String  CALL_RESULTS="{ CALL P_FIND_PASS_FAIL(?,?,?,?)}";
	 private JLabel lno,lname,lm1,lm2,lm3,lresult;
	 private JTextField tname,tm1,tm2,tm3,tresult;
	 private JComboBox<Integer> tcno;
	 private JButton  bDetails,bResult;
	 private Connection con;
	 private Statement st;
	 private PreparedStatement ps;
	 private CallableStatement cs;
	 private ResultSet rs1,rs2;
	 
	public All_Stmts_Project1() {
		super("JDBC--Mini Project");
		System.out.println("Constructor");
		setSize(400,500);
		setLayout(new FlowLayout());
		setBackground(Color.GRAY);
		//add comps
		lno=new JLabel("Sno::");
		add(lno);
		tcno=new JComboBox();
		add(tcno);
	   
		  bDetails=new JButton("details");
		  bDetails.addActionListener(this);
		  add(bDetails);
		
		  lname=new JLabel("student name");
		  add(lname);
		  tname=new JTextField(10);
		  add(tname);
		  
		  lm1=new JLabel("marks1");
		  add(lm1);
		  tm1=new JTextField(10);
		  add(tm1);
	
		  lm2=new JLabel("Marks2");
		  add(lm2);
		  tm2=new JTextField(10);
		  add(tm2);
	
		  lm3=new JLabel("Marks3");
		  add(lm3);
		  tm3=new JTextField(10);
		  add(tm3);
	
		  bResult=new JButton("Result");
		  bResult.addActionListener(this);
		  add(bResult);
		  
		  lresult=new JLabel("Result");
		  add(lresult);
		  tresult=new JTextField(10);
		  add(tresult);
		  //disable editing on text boxes
		  tname.setEditable(false);
		  tm1.setEditable(false);
		  tm2.setEditable(false);
		  tm3.setEditable(false);
		  tresult.setEditable(false);
		  
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setVisible(true);
	     initialize();
	     this.addWindowListener(new MyWindowAdapter());
	  
	}
	
	public  void initialize() {
		System.out.println("All_Stmts_Project.initialize()");
		try {
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create Statement obj
			st=con.createStatement();
			//perform Load on Startup activity to get snos to ComboBox
			rs1=st.executeQuery(GET_ALL_SNOs);
			while(rs1.next()) {
				tcno.addItem(rs1.getInt(1));
			}//while
			//create PreparedStatement obj
			ps=con.prepareStatement(GET_STUD_BY_NO);
			//create CallableStatement obj
			cs=con.prepareCall(CALL_RESULTS);
			//register OUT params with JDBC types
			cs.registerOutParameter(4,Types.VARCHAR);
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
	}

	
	public static void main(String[] args) {
		System.out.println("main(-)");
	   new All_Stmts_Project1();	

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		int no=0;
		int m1=0,m2=0,m3=0;
		System.out.println("All_Stmts_Project.actionPerformed(-)");
/*		if(ae.getActionCommand().equalsIgnoreCase("details")) {
			System.out.println("details button");
		}
		else {
			System.out.println("result button");
		}*/
		if(ae.getSource()==bDetails) {
			System.out.println("details button");
			try {
				//read selected item from combo Box
				no=(int) tcno.getSelectedItem();
				//set the above selected item as query param value
				ps.setInt(1,no);
				//execute the SQL query
				rs2=ps.executeQuery();
				//read ResultSet obj(rs2)  data and set Text boxes
				if(rs2.next()) {
					tname.setText(rs2.getString(1));
					tm1.setText(String.valueOf(rs2.getInt(2)));
					tm2.setText(String.valueOf(rs2.getInt(3)));
					tm3.setText(rs2.getString(4));
				}
				}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		}
		else {
			System.out.println("result Button");
			try {
			   //read values from text boxes
				m1=Integer.parseInt(tm1.getText());
				m2=Integer.parseInt(tm2.getText());
				m3=Integer.parseInt(tm3.getText());
				//set values to IN params of PL/SQL Procedure
				cs.setInt(1, m1);
				cs.setInt(2,m2);
				cs.setInt(3,m3);
				//execute PL/SQL procedure
				cs.execute();
				//read value from Out param and set to Text Box
				 tresult.setText(cs.getString(4));
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//actionPerfomed(-)
			
		
	}//main

    class  MyWindowAdapter extends WindowAdapter{
    	@Override
    	public void windowClosing(WindowEvent e) {
    		System.out.println("All_Stmts_Project.windowClosing()");
    		//close jdbc objs
    		try {
    			if(rs2!=null)
    				rs2.close();
    		}//try
    		catch(SQLException se) {
    			se.printStackTrace();
    		}
    		try {
    			if(rs1!=null)
    				rs1.close();
    		}//try
    		catch(SQLException se) {
    			se.printStackTrace();
    		}
    		try {
    			if(cs!=null)
    				cs.close();
    		}//try
    		catch(SQLException se) {
    			se.printStackTrace();
    		}
    		try {
    			if(ps!=null)
    				ps.close();
    		}//try
    		catch(SQLException se) {
    			se.printStackTrace();
    		}
    		
    		try {
    			if(st!=null)
    				st.close();
    		}//try
    		catch(SQLException se) {
    			se.printStackTrace();
    		}
    		
    		try {
    			if(con!=null)
    				con.close();
    		}//try
    		catch(SQLException se) {
    			se.printStackTrace();
    		}
    		
    	}//mehtod
    }//inner class

	

}//class
