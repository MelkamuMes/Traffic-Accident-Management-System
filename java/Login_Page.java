
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Login_Page
 */
@WebServlet("/Login_Page")
public class Login_Page extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_Page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		response.setHeader("Pragma","no-cache"); 
//		response.setHeader("Cache-Control","no-store"); 
//		response.setHeader("Expires","0"); 
//		response.setDateHeader("Expires",-1);
		
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
		 // TODO output your page here
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"ISO-8859-1\">");
		out.print("<link rel=\"stylesheet\" href=\"nav.css\">");
		out.print("<title>Home Page</title>"); 
		out.print("</head>");
		 out.print("<body >");
		 out.print("<h1>WELCOME <br> TO TRAFFIC ACCIDENT MANAGMENT SYSTEM</h1>");
	        out.print("<div class= 'navigation'>");
	        out.print("<nav>");	 
	        out.print("<ul>");
	        out.print("<li id=\"Up\"><a href=\"Home\"> <img src=\"img/home.png\"></a></li>");
	        out.print("<li><a href=\"Home\"><img src=\"img/contact.png\"></a></li>");
	        out.print("<li><a href=\"Home\"><img src=\"img/about.png\"></a></li>");
	        out.print("<li id=\"Down\"><a href=\"Home\"><img src=\"img/emergency.png\"></a></li>");
	        out.print("</ul>");
	        out.print("</nav>");
	        out.print("</div>");
	        
	        out.print("<div class = 'LogHomeimage'><img src=\"img/HomeBg.jpg\"></div>");
	        out.print("<div class='Logbody' >");
//			out.print(" <form action=\"Autenticate_user\" method=\"post\">");
//			out.print("Username: <input type='text' name='username' required /><br><br>");
//			out.print(" Password: <input type='password' name='pasword' required /><br><br>");
//			out.print("<input type='submit' value='Login' > ");
//			out.print("</form> ");
	        
			    out.println("<form action=\"Validateuser\" method=\"post\">");
		        out.println("<label for=\"username\">Username:</label>");
		        out.println("<input type=\"text\" id=\"username\" name=\"username\" required><br><br>");
		        out.println("<label for=\"password\">Password:</label>");
		        out.println("<input type=\"password\" id=\"password\" name=\"password\" required><br><br>");
		        out.println("<input type=\"submit\" value=\"Login\" class=\"btn\">");
		        out.println("</form>");
		     	out.print("</div>");	 
				out.print("</body></html>");
				} catch(Exception e) { 
				 out.print(e.getMessage());
				 }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
