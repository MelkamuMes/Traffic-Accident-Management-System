

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update_Status
 */
@WebServlet("/Update_Status")
public class Update_Status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_Status() {
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
		out.print("<link rel=\"stylesheet\" href=\"new_nav.css\">");
		out.print("<title>Update_Status</title>"); 
		out.print("</head>");
		 out.print("<body >");
		 out.print("<div class= 'Login-button'><input value=\"Logout\" type=\"submit\"> </div>");
		
		 //Loading Navigation 
		    RequestDispatcher rd = request.getRequestDispatcher("Navigation");
			rd.include(request, response);
			
	        out.print("<div class = 'title'><h1>Admin Officer Page</h1></div>");
	        out.print("<div id = 'Search'>");
	        out.print("<form action=Register_Accident method=post >");
	        out.print("<input type='text' placeholder = 'Name'>");
	        out.print("<input type='submit' value = 'Search'>");
	        out.print("</form>");
	        out.print("</div>");
	        out.print("<div class = 'Table'>");
	        out.print(" <table>");
	        out.print("<thead>");
	        out.print("  <tr>");
	        out.print("    <th> NO</th>");
	        out.print("    <th> User Name</th>");
	        out.print("    <th> Roll</th>");
	        out.print("    <th> Entery Year</th> <!-- Added column for optional selection -->");
	        out.print("    <th> Status</th>");
	        out.print("  </tr>");
	        out.print(" </thead>");
	        out.print(" <tbody>");
	        out.print(" <tr>");
	        out.print("<td>1</td>");
	        out.print("<td>Getnet Gardew</td>");
	        out.print("<td>Traffic Officer</td>");
	        out.print("<td>2013/4/3</td>");
	        out.print("<td> ");
	        out.print("      <select>");
	        out.print("      <option value=\"Active\">Active</option>");
	        out.print("      <option value=\"D-Active\" selected>D-Active</option> <!-- Default selection -->");
	        out.print("      </select>");
	        out.print("    </td>");
	        out.print("  </tr>");
	    	out.print("  <!-- Add more rows as needed -->");
	    	out.print(" </tbody>");
	    	out.print(" </table>");
	        out.print("</div>");
	        out.print("<div class = 'Admin_Pic' ><img src= \"img/Admin.jpg\"></div>");

				out.print("</body></html>");
				} catch(Exception e) { 
				 out.print(e.getMessage());}
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
