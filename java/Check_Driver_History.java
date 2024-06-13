

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Check_Driver_History
 */
@WebServlet("/Check_Driver_History")
public class Check_Driver_History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_Driver_History() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		 response.setContentType("text/html");
	     PrintWriter out = response.getWriter(); 
	    out.print("<html>");
		out.print("<head>");
	    out.print("<meta charset=\"ISO-8859-1\">");
	    out.print("<link rel=\"stylesheet\" href=\"new_nav.css\">");
	    out.print("<link rel=\"stylesheet\" href=\"nav.css\">");
	    out.print("<title>Home Page</title>"); 
	    out.print("</head>");
		out.print("<body >");
//        out.print("<div class = 'LogHomeimage'><img src=\"img/2.png\"></div>");
		 
        String UserName = "root";
        String Password = "root";
        String DriverName = "com.mysql.cj.jdbc.Driver";
        String DB = "jdbc:mysql://localhost:3306/traffic_accident_information_management_system";
        
        Connection conn = null;
        Statement st = null;
        Statement st1 = null;
        ResultSet rs = null;
        
        
        
        try {
        	Class.forName(DriverName);
        } catch(ClassNotFoundException e) {
        	System.out.println(e.getMessage());
        }
        
       
    	
	    out.print("<h1>Check Driver History</h1>");
	    out.print("<div class=\"Logbody1\" style = \"position: absolute; top: 16%; \" >");
	    out.print("<form action=\"\" method=\"post\">");
		out.print("<fieldset><legend>Search license</legend>");
		out.print("<input type=\"text\" name=\"plate\" placeholder=\"license plate\" required>");
		out.print("<input type=\"submit\" name=\"Submit\" value=\"Search\" class=\"DRbtn\"  style=background-color:black;color:white;>");
		out.print("</fieldset></form>");
		out.print("</div>");
		 try {
				conn = DriverManager.getConnection(DB,UserName,Password);
				st = conn.createStatement();
		    	String Submit = null;
		    	String Search = null;
		    	Search = request.getParameter("plate");
		    	Submit = request.getParameter("Submit");
		    	
if(Submit != null) { 
		        	
		        	String Sql2= "SELECT drivers.*,Violations.* FROM drivers LEFT JOIN Violations ON drivers.DriverID = Violations.DriverID WHERE drivers.DriverLicenseNumber ='"+Search+"'";
		        	rs = st.executeQuery(Sql2);
		        	
		        	
		        	if(rs.next()) {
		        	     out.print("<div class = 'Table'>");
		 		        out.print(" <table>");
		 		        out.print("<thead>");
		 		        out.print("  <tr>");
		 		        out.print("    <th>NO</th>");
		 		        out.print("    <th>Driver Name</th>");
		 		        out.print("    <th>Licence</th>");
		 		        out.print("    <th>Accidetn_Description</th>");
		 		        out.print("    <th>Date</th>");
		 		        out.print("    <th>Status</th>");
		 		        out.print("    <th>Report_Status</th>");
		 		        out.print("  </tr>");
		 		        out.print(" </thead>");
		 		       out.print(" <tr>");
				        out.print("<td>"+rs.getString("DriverID")+"</td>");
				        out.print("<td>"+rs.getString("DriverName")+"</td>");
				        out.print("<td>"+rs.getString("DriverLicenseNumber")+"</td>");
				        out.print("<td>"+rs.getString("Description")+"</td>");
				        out.print("<td>"+rs.getString("OffenceDate")+"</td>");
				        out.print("<td>"+rs.getString("Status")+"</td>");
				        out.print("<td>");
			        out.print("  </tr>");
			        out.print(" </table>");
			        }
		        	else out.print("ERRor1");
		        }else out.print("ERROr");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		out.print("</body></html>");

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
