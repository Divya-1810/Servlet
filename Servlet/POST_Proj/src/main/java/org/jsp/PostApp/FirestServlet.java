package org.jsp.PostApp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
public class FirestServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
{
//Fetch UI/Form data
	String sid=req.getParameter("i");
	int id=Integer.parseInt(sid);
	String name=req.getParameter("nm");
	String dept=req.getParameter("dp");
	String sperc=req.getParameter("pr");
	double perc=Double.parseDouble(sperc);
	
	//Persentation logic //Servlet Technology
	PrintWriter out=resp.getWriter();
	out.println("<html><body bgcolor='orange'>"
			+"<h1>Student name is"+name+" from "+dept+"</h1>"+"</body></html>");
	out.close();
	//Persistence Logic//JDBC Technology
	Connection con=null;
	PreparedStatement pstmt=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Load and Register the Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
		System.out.println("Establish the connection between the java application and the database server");
		pstmt=con.prepareStatement("insert into form.student values(?,?,?,?)");
		pstmt.setInt(1,id);
		pstmt.setString(2,name);
		pstmt.setString(3,dept);
		pstmt.setDouble(4,perc);
		pstmt.executeUpdate();
		System.out.println("Record Inserted");
		
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	finally {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Closed all the costly resources");
	}
	
}
}
