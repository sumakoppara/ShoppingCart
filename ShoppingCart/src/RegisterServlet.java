

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
public class RegisterServlet extends HttpServlet {
	Connection con;
	PreparedStatement st;
	PrintWriter out;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			out = response.getWriter();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			con.setAutoCommit(false);
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			st = con.prepareStatement("insert into users values('"+name+"','"+username+"','"+password+"')");
			st.executeUpdate();
			out.println("Registered Successfully");
			out.println("<a href=\"/ShoppingCart/login.jsp\">Click here</a> to login");
			con.commit();
			st.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request,response);
		
	}

}
