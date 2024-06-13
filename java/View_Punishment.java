
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



/**
 * Servlet implementation class View_Punishment
 */
@WebServlet("/View_Punishment")
public class View_Punishment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View_Punishment() {
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
		out.print("<title>Home Page</title>"); 
		out.print("</head>");
		 out.print("<body >");
		 out.print("<div class= 'Login-button'><input value=\"Logout\" type=\"submit\"> </div>");
		//Loading Navigation 
		    RequestDispatcher rd = request.getRequestDispatcher("Navigation");
			rd.include(request, response);
			
	        out.print("<div class = 'title'><h1 style = \"position: absolute; top: 10%; left: 25%;\">Admin Officer Page</h1></div>");
	        out.print("<div id = 'Search'>");
	        out.print("<form action=View_Punishment method=post >");
	        out.print("<input type='text' placeholder = 'Name' name = 'Search'>");
	        out.print("<input type='submit' name= 'Submit' value = 'Search'>");
	        out.print("</form>");
	        out.print("</div>");
	        
	        String UserName = "root";
	        String Password = "root";
	        String DriverName = "com.mysql.cj.jdbc.Driver";
	        String DB = "jdbc:mysql://localhost:3306/traffic_accident_information_management_system";
	        
	        Connection conn = null;
	        Statement st = null;
	        Statement st1 = null;
	        ResultSet rs = null;
	        ResultSet rs1 = null;
	        
	        
	        try {
	        	Class.forName(DriverName);
	        } catch(ClassNotFoundException e) {
	        	System.out.println(e.getMessage());
	        }
	        
	        try {
	        	conn = DriverManager.getConnection(DB,UserName,Password);
	        	st = conn.createStatement();
	        	st1 = conn.createStatement();
	        	String Submit = null;
	        	String Search = null;
	        	Search = request.getParameter("Search");
	        	Submit = request.getParameter("Submit");
//	        	String Sql = "SELECT * FROM drivers";
//	        	rs = st.executeQuery(Sql);
	        	
	        	String Sql1 = "SELECT drivers.*,Violations.* FROM drivers LEFT JOIN Violations ON drivers.DriverID = Violations.DriverID ";
	        	rs = st.executeQuery(Sql1);
	        	
      if(Submit != null) { 
		        	
		        	String Sql2= "SELECT drivers.*,Violations.* FROM drivers LEFT JOIN Violations ON drivers.DriverID = Violations.DriverID WHERE drivers.DriverLicenseNumber ='"+Search+"'";
		        	rs1 = st1.executeQuery(Sql2);
		        	
		        	
		        	if(rs1.next()) {
		        		  out.print(" <table style =\" position: absolute; top: 24%; left: 23%;\">");
		        	out.print(" <tr>");
			        out.print("<td>"+rs1.getString("DriverID")+"</td>");
			        out.print("<td>"+rs1.getString("DriverName")+"</td>");
			        out.print("<td>"+rs1.getString("DriverLicenseNumber")+"</td>");
			        out.print("<td>"+rs1.getString("Description")+"</td>");
			        out.print("<td>"+rs1.getString("OffenceDate")+"</td>");
			        out.print("<td>"+rs1.getString("Status")+"</td>");
			        out.print("<td>");
			        out.print("<select name=\"status\" id=\"main_select\" > Report Status ");
		    		 out.print("<option value=\"Pending\">Report</option>");
		    		 out.print("</select>"); 
			        out.print("  </tr>");
			        out.print(" </table>");
			        }
		        	else out.print("ERRor1");
		        }else out.print("ERROr");
	        	
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
		        
		        while(rs.next()) {
			        out.print(" <tr>");
			        out.print("<td>"+rs.getString("DriverID")+"</td>");
			        out.print("<td>"+rs.getString("DriverName")+"</td>");
			        out.print("<td>"+rs.getString("DriverLicenseNumber")+"</td>");
			        out.print("<td>"+rs.getString("Description")+"</td>");
			        out.print("<td>"+rs.getString("OffenceDate")+"</td>");
			        out.print("<td>"+rs.getString("Status")+"</td>");
			        out.print("<td>");
			        out.print("<select name=\"status\" id=\"main_select\" onchange=\"redirectoCreate(this)\" > Report Status ");
		    		 out.print("<option value=\"Report\">Report</option>");
		    		 out.print("<option value=\"unReport\">UnReport</option>");
		    		 out.print("</select>"); 
		    		 String description1 = rs.getString("Description");
		    		 String Date = rs.getString("OffenceDate");
		    		 String Location = "Addis abeba";
		    		 
		    		 out.print("<script>");
		    		 out.print("function redirectoCreate(select) {"); 
		    		 out.print("var Description  = '"+description1+"';"); 
		    		 out.print("var Date  = '"+Date+"';");
		    		 out.print("var Location  = '"+Location+"';");
		    		 out.print("var url = 'Create_Report?Description=' + encodeURIComponent(Description) + '&Date=' + encodeURIComponent(Date) + '&Location=' + encodeURIComponent(Location);");
		    		 out.print("window.location.href = url;");
		    		 out.print("}");
		    		 out.print("</script>");
		    		 
		    		 
			        out.print("  </tr>");
		        }
		        
		  
		        
		        
		    	out.print(" </table>");
		        out.print("</div>");
	        	
	        	 
	        }catch(Exception e) {
	        	out.println(e.getMessage());
	        }
	        

//	        out.print("<div class = 'Admin_Pic' ><img src= \"img/Admin.jpg\"></div>");

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
