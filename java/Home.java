//Home for the system
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Generate HTML content to display accidents
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
		 out.print("<div class= 'Login-button'> <a href=\"Login_Page\" ><input value=\"Login\" type=\"submit\"><a/> </div>");
		 out.print("<h1>TRAFFIC ACCIDENT MANAGMENT SYSTEM</h1>");
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
	        
	        
	        out.print("<div class = 'Homeimage'><img src=\"img/HomeBg.jpg\"></div>");

	        out.print("<div class='Homebody' >");
			out.print("<div class = 'Homebuttons'>");
			out.print("<button><a href=\"Daily_Report\">Daily Report</a></button>");
			out.print("<button><a href=\"Check_Driver_History\">Check Driver History</a></button>");
			out.print(" </div>");
			out.print("</div>");
			
//			<div class="container">
//		    <div class="image">
//		        <img src="path_to_your_image" alt="Image">
//		    </div>
//		    <div class="button">
//		        <form>
//		            <button type="submit">Submit</button>
//		        </form>
//		    </div>
//		</div>

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
