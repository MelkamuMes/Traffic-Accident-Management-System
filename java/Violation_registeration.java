

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Violation_registeration
 */
@WebServlet("/Violation_registeration")
public class Violation_registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Violation_registeration() {
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
	    String submit = request.getParameter("submit");
	    
		try {
		 // TODO output your page here
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"ISO-8859-1\">");
		out.print("<link rel=\"stylesheet\" href=\"nav.css\">");
		out.print("<title>Home Page</title>"); 
		out.print("</head>");
		 out.print("<body >");
		 out.print("<div class= 'Login-button'><input value=\"Login\" type=\"submit\"> </div>");

		//Loading Navigation 
		    RequestDispatcher rd = request.getRequestDispatcher("Navigation");
			rd.include(request, response);
			
	        out.print("<h1>TRAFFIC OFFICER</h1>");
	        out.print("<div class = 'Offimage'><img src=\"img/Officer.jpg\"></div>");
	        out.print("<div id = 'Redirect'>");
	        out.print("<div id = 'ancher1'><a href = 'Accident'>Register Accident</a></div>");
	        out.print("<div id = 'ancher2'><a class = ''>Register Violation</a></div>");
	        out.print("</div>");
	       

				} catch(Exception e) { 
				 out.print(e.getMessage());
				 }

		 if (request.getMethod().equalsIgnoreCase("post")) {
	            registerAccident(request, response, out);
	            return; // exit method after processing POST request
	        }
			
		
	 	out.print("<div id = 'form'>");
		out.print("<form action=\"Violation_registeration\" method=post >");
		out.print("<div class=\"form-group\">");
		out.print("<label >Driver Name:</label><br>");
		out.print("<input type=text name=Drivername size=20 required /><br>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label >licence plate:</label><br>");
		out.print("<input type=\"text\" class=\"form-control\" id=\"plate\"  name=\"plate\" required>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label >Location Point:</label><br>");
		out.print(" <input type=text name=Locationpoint size=30 required /><br>");
		out.print("</div>");
//		out.print("<div class=\"form-group\">");
//		out.print("<label >Reported By(Enter Your Name officer)</label><br>");
//		out.print(" <input type=text name=Officername size=30 required /><br>");
//		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label > Date of offence :</label><br>");
		out.print("<input type=\"datetime-local\" class=\"form-control\" id=\"offenceDate\" placeholder=\"\" name=\"offenceDate\" required>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label >Expiry Date of offence :</label><br>");
		out.print("<input type=\"datetime-local\" class=\"form-control\" id=\"expiryDate\"  name=\"expiryDate\" required>");
		out.print("</div>");		
		out.print("<div class=\"form-group\">");
        out.print("<label >Amount of fee[ETB]:</label><br>");
        out.print("<input type=\"text\" class=\"form-control\" id=\"PayAmount\"  name=\"PayAmount\" required>");
        out.print("</div>");
    		 out.print("<select name=\"status\" id=\"main_select\" > Payment Status ");
    		 out.print("<option value=\"Pending\">Pending</option>");
    		 out.print("</select>"); 
    		 out.print("<div class=\"form-group\">");
    	        out.print("<label >Payment Reason:</label><br>");
    	        out.println("<textarea type=\"text\" cols=\"55\"  class=\"form-control\"   name=\"description\" required></textarea><br><br>");
    	        out.print("</div>");
		out.print("<input type=submit name=submit value=submit /><input type=reset value=Clear>");
		out.print("</form>");
             
		out.print("</div>");
		
		}

    private void registerAccident(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
		
		
		 String Drivername = request.getParameter("Drivername");// driver table
		 String LicenseNo = request.getParameter("plate"); // driver table
		 String Locationpoint = request.getParameter("Locationpoint"); // violation table
//		 String Officername = request.getParameter("Officername"); // user table
		 String offenceDate = request.getParameter("offenceDate"); // violation table
		 String expiryDate = request.getParameter("expiryDate");  // violation table
		 String PayAmount = request.getParameter("PayAmount"); // violation table 
		 String status = request.getParameter("status");  // violation table 
		 String description = request.getParameter("description"); // violation table

		    String UserName = "root";
	        String Password = "root";
	        String DB = "jdbc:mysql://localhost:3306/traffic_accident_information_management_system";

		 
	        // Existing code...

	        try (Connection connection = DriverManager.getConnection(DB, UserName, Password)) {
	            String sql = "INSERT INTO Violations(LocationPoint, OffenceDate, EXpiryDate,PenaltyAmount, Description, Status) VALUES (?,?, ?, ?,?,?)";
	            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, Locationpoint);
	            ps.setString(2, offenceDate);
	            ps.setString(3, expiryDate);
	            ps.setString(4, PayAmount);
	            ps.setString(5, description);
	            ps.setString(6, status);
	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                out.println("<p>Punishment registration successful!</p>");

	                // Driver Data Insertion
	                String sql2 = "INSERT INTO drivers(DriverName, DriverLicenseNumber) VALUES(?,?)";
	                PreparedStatement ps2 = connection.prepareStatement(sql2);
	                ps2.setString(1, Drivername);
	                ps2.setString(2, LicenseNo);
	                int rowsAffected2 = ps2.executeUpdate();
	                if (rowsAffected2 > 0) {
	                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	                        if (generatedKeys.next()) {
	                            int violationId = generatedKeys.getInt(1);
	                            out.println("<p>Violation ID: " + violationId + "</p>");

	                            String driverId = null;
	                            String sql3 = "SELECT DriverID FROM drivers WHERE DriverName = ?";
	                            PreparedStatement ps3 = connection.prepareStatement(sql3);
	                            ps3.setString(1, Drivername);
	                            ResultSet rs = ps3.executeQuery();
	                            if (rs.next()) {
	                                driverId = rs.getString("DriverID");
	                                out.print(driverId);

	                                String sql4 = "UPDATE Violations SET DriverID = ? WHERE ViolationID = ?";
	                                PreparedStatement ps4 = connection.prepareStatement(sql4);
	                                ps4.setString(1, driverId);
	                                ps4.setInt(2, violationId);
	                                int rowsAffected4 = ps4.executeUpdate();
	                                if (rowsAffected4 > 0) {
	                                	  out.print("<div class='Logbody' >");
	                  	                out.println("<h2 style=color:red;>DriverID updated successfully!</h2>");
	                  	                out.println("<a class=\"btn\" style=text-decoration:none; href=\"Accident\">Register Accident</a>");
	                  	    			out.print("</div>");	
	                                } else {
	                                    out.println("<p>Error occurred while updating the DriverID.</p>");
	                                }
	                            } else {
	                                out.println("<p>Error occurred while retrieving the DriverID.</p>");
	                            }
	                        } else {
	                            out.println("<p>Error occurred while retrieving the accident ID.</p>");
	                        }
	                    }
	                } else {
	                    out.println("<p>Error occurred while registering the accident.</p>");
	                }
	            } else {
	                out.println("<p>Error occurred while registering the accident.</p>");
	            }
	        } catch (SQLException e) {
	            out.println("<p>Error occurred while registering the accident: " + e.getMessage() + "</p>");
	            e.printStackTrace(); // Log the exception for debugging
	        }}}  