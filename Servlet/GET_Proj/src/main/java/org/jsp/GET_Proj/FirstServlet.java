package org.jsp.GET_Proj;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
public class FirstServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sid=req.getParameter("i");
		int id=Integer.parseInt(sid);
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Load and Register the Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Establish the connection between the java application and the database server");
			pstmt=con.prepareStatement("select * from form.Student where id=?");
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			PrintWriter out=resp.getWriter();		
			if(rs.next()){
				String name=rs.getString("name");
				String dept=rs.getString("dept");
				out.println("<html><body bgcolor='orange'>"
						+"<h1>Student name is"+name+" from "+dept+"</h1>"+"</body></html>");
				out.close();
			}
			else {
				out.println("<html><body bgcolor='white'>"
						+"<h1>No Data Found</h1>"+"</body></html>");
				out.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
