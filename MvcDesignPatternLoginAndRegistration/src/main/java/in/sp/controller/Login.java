package in.sp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.sp.beans.User;
import in.sp.dnconn.DbConnect;

@WebServlet("/loginForm")
public class Login extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
             PrintWriter out = resp.getWriter();
             
             resp.setContentType("text/html");
		
		String myemail = req.getParameter("email1");
		String mypass = req.getParameter("pass1");
		
		try {
			
			System.out.println("In try block of login servlet");
			
			
			Connection conn = DbConnect.getConnection();
			
			String select_query = "Select * from register where email=? and password=?" ;
			
			PreparedStatement pstmt = conn.prepareStatement(select_query);
			pstmt.setString(1, myemail);
			pstmt.setString(2, mypass);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				System.out.println("In if block of login servlet");
				
				User user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setCity(rs.getString("city"));
				
				HttpSession session = req.getSession();
				session.setAttribute("session_user", user);
				
				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.forward(req, resp);
				
			}
			else {
				
				System.out.println("In else block of login servlet");
				out.println("<h3 style='color:red'>Email Id And Password Did Not Match</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, resp);
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
