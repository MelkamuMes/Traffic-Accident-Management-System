

//import java.io.IOException;

//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class Traffic_Home_Page
 */
@WebServlet("/Traffic_Home_Page")
public class Traffic_Home_Page extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Traffic_Home_Page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Pragma","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setHeader("Expires","0"); 
		response.setDateHeader("Expires",-1);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("usertype");

        if (userType != null && userType.equals("TrafficPolice")) {
            // Perform actions specific to Traffic Police user
            // ...

            // Redirect to the appropriate page
//            response.sendRedirect("Traffic_Home_Page");
       
		 
		out.print(" <html><head>");
		out.print("<title>Traffic Home Page</title>");
		out.print("<link rel=\"stylesheet\" href=\"nav.css\">");
		out.print("<body>");
		 out.print("<div class= 'Login-button'><input value=\"Login\" type=\"submit\"> </div>");
		 out.print("<h1>TRAFFIC OFFICER</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("Navigation");
			rd.include(request, response);
	        out.print("<div class='body' >");
		out.print("<div class = 'buttons'>");
		out.print("<button><a href=\"Accident\">Register Accident</a></button>");
		out.print("<button><a href=\"Violation_registeration\">Recored Punishment</a></button>");
		out.print("<button><a href=\"View_Punishment\">View Punishment</a></button>");
		out.print("<button><a href=\"Daily_Report\">View Report</a></button>");
		out.print(" </div>");
		out.print("</div>");
		out.print("<div class = 'Offimage'><img src=\"img/Officer.jpg\"></div>");
		out.print("</body></html>");
		
        } else {
            // Redirect to a default page if the user type is not recognized
            response.sendRedirect("Login_Page");
        }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		PrintWriter out = response.getWriter();
//		request.getRequestDispatcher("Home.java").include(request, response);
//		
//        Cookie ck[] = request.getCookies();
//        if(ck!= null) {
//        	String name = ck[0].getValue();
//        	if(!name.equals("")|| name!=null) {
//        		out.print("Wellcome");
//        		out.print("Hello "+ name);
//        	}
//        } else {
//        	out.print("please login first");
//    		request.getRequestDispatcher("Login_Page.java").include(request, response);
//
//        }
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
