

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	Connection con;
	PreparedStatement st;
	PrintWriter out;
	ResultSet rs;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			out = response.getWriter();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			st = con.prepareStatement("select * from users where username='"+username+"' and password='"+password+"'");
			rs = st.executeQuery();
			if (rs.next())
			{
				String name = rs.getString("name");
				HttpSession session = request.getSession();
				session.setAttribute("name",name);
				response.sendRedirect("/ShoppingCart/OrderItemsServlet");
			}
			else
			{
				out.println("Invalid user");
				out.println("<a href=\"/ShoppingCart/login.jsp\">Click here</a> to login again"); 
			}
			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
