

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Finance_Update_PunismentRecord
 */
@WebServlet("/Finance_Update_PunismentRecord")
public class Finance_Update_PunismentRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Finance_Update_PunismentRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
        	out.print("<html>");
    		out.print("<head>");
    		out.print("<meta charset=\"ISO-8859-1\">");
    		out.print("<link rel=\"stylesheet\" href=\"new_nav.css\">");
    		out.print("<title>Home Page</title>"); 
    		out.print("</head>");
    		 out.print("<body >");
    		 out.print("<div class= 'Login-button'><input value=\"Logout\" type=\"submit\"> </div>");
               
//    			RequestDispatcher rd = request.getRequestDispatcher("Navigation");
//    			rd.include(request, response);
    		
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
				out.print("</body></html>");

            String UserName = "root";
            String Password = "root";
            String DriverName = "com.mysql.cj.jdbc.Driver";
            String DB = "jdbc:mysql://localhost:3306/traffic_accident_information_management_system";

            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;

            try {
                Class.forName(DriverName);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                conn = DriverManager.getConnection(DB, UserName, Password);
                st = conn.createStatement();
                String Submit = null;
                String Search = null;
                Search = request.getParameter("Search");
                Submit = request.getParameter("Submit");

                String Sql1 = "SELECT drivers.*,Violations.* FROM drivers LEFT JOIN Violations ON drivers.DriverID = Violations.DriverID ";
                rs = st.executeQuery(Sql1);

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

                while (rs.next()) {
                    out.print(" <tr>");
                    out.print("<td>" + rs.getString("DriverID") + "</td>");
                    out.print("<td>" + rs.getString("DriverName") + "</td>");
                    out.print("<td>" + rs.getString("DriverLicenseNumber") + "</td>");
                    out.print("<td>" + rs.getString("Description") + "</td>");
                    out.print("<td>" + rs.getString("OffenceDate") + "</td>");
                    out.print("<td>" + rs.getString("Status") + "</td>");
                    String violationId = rs.getString("ViolationID");
                    out.print("<td>");
                    out.print("<form action=\"Finance_Update_PunismentRecord\" method=\"post\">");
                    out.println("<select name=\"status_" + violationId + "\">");
                    out.println("<option value=\"Pending\">Pending</option>");
                    out.println("<option value=\"Paid\">Paid</option>");
                    out.println("</select>");
                    out.print("<input type=\"submit\" name=\"submit1\" value=\"Update\">");
                    out.print("</form>");
                    out.print("  </tr>");

                    String submit1 = request.getParameter("submit1");
                    String updateSql = null;
                    String Status = request.getParameter("status_" + violationId);

                    if (submit1 != null) {
                        updateSql = "UPDATE Violations SET Status = ? WHERE ViolationID = ?";
                        PreparedStatement preparedStatement = conn.prepareStatement(updateSql);
                        preparedStatement.setString(1, Status);
                        preparedStatement.setString(2, violationId);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    }
                }

                out.print(" </table>");
                out.print("</div>");

                if (request.getMethod().equals("POST")) {
                    String Sql = "SELECT drivers.*,Violations.* FROM drivers LEFT JOIN Violations ON drivers.DriverID = Violations.DriverID WHERE drivers.DriverLicenseNumber = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(Sql);
                    preparedStatement.setString(1, Search);
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        String violationId = rs.getString("ViolationID");
                        String driverName = rs.getString("DriverName");
                        String violationType = rs.getString("ViolationType");
                        String status = rs.getString("Status");

                        out.println("<p>Violation ID: " + violationId + "</p>");
                        out.println("<p>Driver Name: " + driverName + "</p>");
                        out.println("<p>Violation Type: " + violationType + "</p>");
                        out.println("<p>Status: " + status + "</p>");

                        out.println("<formaction=\"Finance_Update_PunismentRecord\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"violationId\" value=\"" + violationId + "\">");
                        out.println("<select name=\"status\">");
                        out.println("<option value=\"Pending\">Pending</option>");
                        out.println("<option value=\"Paid\">Paid</option>");
                        out.println("</select>");
                        out.println("<input type=\"submit\" value=\"Update\">");
                        out.println("</form>");
                    }
                }

                rs.close();
                st.close();
                conn.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            out.println(e);
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
