////Validateuser.java: used to validate user
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;




/**
 * Servlet implementation class Validateuser
 */
@WebServlet("/Validateuser")

public class Validateuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setHeader("Pragma","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setHeader("Expires","0"); 
		response.setDateHeader("Expires",-1);
		
		 String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        try {
	            // Set up database connection
	            String driverName = "com.mysql.cj.jdbc.Driver";
	            String dbUrl = "jdbc:mysql://localhost:3306/traffic_accident_information_management_system";
	            String dbUsername = "root";
	            String dbPassword = "root";

	            Class.forName(driverName);
	            Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

	            // Validate user
	            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                String userType = rs.getString("usertype");

	                // Set user type in session
	                HttpSession session = request.getSession();
	                session.setAttribute("usertype", userType);
	                session.setAttribute("uname",username);

	             // Redirect to appropriate page based on user type
	                if (userType.equals("TrafficPolice")) {
	                
	          
	                	 
	                    response.sendRedirect("Traffic_Home_Page");
	                } else if (userType.equals("Admin")) {
	                    response.sendRedirect("Admin_Home_Page");
	                }  else if (userType.equals("FinancialWorker")) {
	                    response.sendRedirect("Finance_HomePage");
	                } else {
	                    response.sendRedirect("Login_Page");
	                }
	             
	            }
	            else {
	                response.setContentType("text/html");
	                PrintWriter out = response.getWriter();
	            	out.print("<html>");
	        		out.print("<head>");
	        		out.print("<meta charset=\"ISO-8859-1\">");
	        		out.print("<link rel=\"stylesheet\" href=\"nav.css\">");
	        		out.print("<title>Home Page</title>"); 
	        		out.print("</head>");
	        		 out.print("<body >");
	    	        out.print("<div class='Logbody' >");
	                out.println("<h2 style=color:red;>Invalid username or password</h2>");
	                out.println("<a class=\"btn\" style=text-decoration:none; href=\"Login_Page\">Back to Login</a>");
	    			out.print("</div>");	 
					out.print("</body></html>");

	            }

	            // Close database resources
	            rs.close();
	            pstmt.close();
	            conn.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
