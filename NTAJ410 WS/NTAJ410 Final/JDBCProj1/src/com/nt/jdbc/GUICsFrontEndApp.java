package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*create or replace procedure p_authentication(user in varchar,
        pass in varchar,
        result out varchar)as
cnt number(5);
begin
select count(*)  into cnt from userlist where uname=user and pwd=pass;
if(cnt<>0) then
result:='VALID CREDENTIALS';
else
result:='INVALID CREDENTIALS';
end if;
end;
/
*/

/*SQL> desc userlist;
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
UNAME                                     NOT NULL VARCHAR2(15)
PWD                                                VARCHAR2(15)
*/
public class GUICsFrontEndApp extends JFrame implements ActionListener,WindowListener{
	private static final String CALL_P_AUTHENTICATION="{CALL P_AUTHENTICATION(?,?,?)}";
	private JLabel luname,lpwd,lresult;
	private JTextField tuname,tpwd;
	private JButton btn;
	private Connection con;
	private CallableStatement cs; 
	
	
	
	public GUICsFrontEndApp() {
		System.out.println("Constructor");
		setTitle("Login App");
		setSize(300,300);
		setLayout(new FlowLayout());
		setBackground(Color.GRAY);
		// add comps 
		luname=new JLabel("username::");
		add(luname);
		tuname=new JTextField(10);
		add(tuname);
		
		lpwd=new JLabel("password:");
		add(lpwd);
		tpwd=new JTextField(10);
		add(tpwd);
		
		btn=new JButton("Login");
		btn.addActionListener(this);
		add(btn);
		
		lresult=new JLabel("");
		lresult.setForeground(Color.RED);
		add(lresult);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeConnection();
		this.addWindowListener(this);
	}
	
	private void makeConnection() {
		System.out.println("makeConnection()");
		try {
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create CallableStatement obj
			cs=con.prepareCall(CALL_P_AUTHENTICATION);
			//register OUT params with JDBC types
			cs.registerOutParameter(3,Types.VARCHAR);
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
	}//makeConnection


	public static void main(String[] args) {
		System.out.println("Start of main(-) methoid");
         new GUICsFrontEndApp();
         System.out.println("End of main(-) method");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String user=null,pass=null;
		System.out.println("GUICsFrontEndApp::actionPerformed(-)");
		try {
			//read text box values
			user=tuname.getText();
			pass=tpwd.getText();
			//set values to IN params
			cs.setString(1,user);
			cs.setString(2,pass);
			//call/execute PL/SQL procedure
			cs.execute();
			//gather results from OUT params and set Result Label
			lresult.setText(cs.getString(3));
		}
		catch(SQLException se) {
			se.printStackTrace();
			lresult.setText("Internal Problem");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//actionPerformed(-)

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("GUICsFrontEndApp.windowClosing(-)");
		//close jdbc objs
		try {
			if(cs!=null)
				cs.close();
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
