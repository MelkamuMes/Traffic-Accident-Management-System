

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Finance_HomePage
 */
@WebServlet("/Finance_HomePage")
public class Finance_HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Finance_HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	PrintWriter out = response.getWriter();
		try {
//		  TODO output your page here
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"ISO-8859-1\">");
		out.print("<link rel=\"stylesheet\" href=\"new_nav.css\">");
		out.print("<title>Home Page</title>"); 
		out.print("</head>");
		 out.print("<body >");
		 out.print("<div class= 'Login-button'><input value=\"Logout\" type=\"submit\"> </div>");
           
//			RequestDispatcher rd = request.getRequestDispatcher("Navigation");
//			rd.include(request, response);
		
		 out.print("<div class= 'navigation'>");
	        out.print("<nav>");	 
	        out.print("<ul>");
	        out.print("<li id=\"Up\"><a href=\"Finance_HomePage\"> <img src=\"img/home.png\"></a></li>");
	        out.print("<li><a href=\"\"><img src=\"img/contact.png\"></a></li>");
	        out.print("<li><a href=\"\"><img src=\"img/about.png\"></a></li>");
	        out.print("<li id=\"Down\"><a href=\"\"><img src=\"img/emergency.png\"></a></li>");
	        out.print("</ul>");
	        out.print("</nav>");
	        out.print("</div>");
		 
		 
	        out.print("<div class = 'title'><h1>Finance Officer Page</h1></div>");
	        out.print("<div class = 'Admin_Pic' ><img src= \"img/finance.png\"></div>");
	        out.print("<div class = 'Abutton'>");
	        out.print("<a href=\"Finance_Update_PunismentRecord\">Update Punishment</a>");
	        out.print("<a href=\"Daily_Report\">View Report</a>");

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
