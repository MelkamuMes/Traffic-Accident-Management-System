

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Navigation
 */
@WebServlet("/Navigation")
public class Navigation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Navigation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		try {
		 // TODO output your page here
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"ISO-8859-1\">");
		out.print("<title>Home Page</title>"); 
		out.print("</head>");
		 out.print("<body >");
		 out.print("<div class= 'Login-button'><input value=\"Login\" type=\"submit\"> </div>");
	        out.print("<div class= 'navigation'>");
	        out.print("<nav>");	 
	        out.print("<ul>");
	        out.print("<li id=\"Up\"><a href=\"Home.java\"> <img src=\"img/home.png\"></a></li>");
	        out.print("<li><a href=\"Home.java\"><img src=\"img/contact.png\"></a></li>");
	        out.print("<li><a href=\"Home.java\"><img src=\"img/about.png\"></a></li>");
	        out.print("<li id=\"Down\"><a href=\"Home.java\"><img src=\"img/emergency.png\"></a></li>");
	        out.print("</ul>");
	        out.print("</nav>");
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
