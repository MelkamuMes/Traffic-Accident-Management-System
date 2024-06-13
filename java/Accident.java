import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Accident")
public class Accident extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Accident() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        response.setHeader("Pragma","no-cache"); 
        response.setHeader("Cache-Control","no-store"); 
        response.setHeader("Expires","0"); 
        response.setDateHeader("Expires",-1);
       
        // HTML Form Submission
        if (request.getMethod().equalsIgnoreCase("post")) {
            registerAccident(request, response, out);
            return; // exit method after processing POST request
        }

        // Display Accident Registration Form
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Accident Registration Form</title>");
        out.println("<script>");
        out.println("function showPopup() {");
        out.println("alert(\"Report is Registered Successfully!\");");
        out.println("}");
        out.println("</script>");
        out.println("<link rel=\"stylesheet\" href=\"nav.css\">");
        out.println("</head>");
        out.println("<body>");
        // Navigation Bar
        out.print("<div class= 'navigation'>");
        out.println("<div class= 'Login-button'><a href=\"Home\" >Logout</a> </div>");
        out.print("<nav>");	 
        out.print("<ul>");
        out.print("<li id=\"Up\"><a href=\"Home.java\"> <img src=\"img/home.png\"></a></li>");
        out.print("<li><a href=\"Home.java\"><img src=\"img/contact.png\"></a></li>");
        out.print("<li><a href=\"Home.java\"><img src=\"img/about.png\"></a></li>");
        out.print("<li id=\"Down\"><a href=\"Home.java\"><img src=\"img/emergency.png\"></a></li>");
        out.print("</ul>");
        out.print("</nav>");
        out.print("</div>");
        out.println("<h1>TRAFFIC OFFICER</h1>");
        out.println("<div class = 'Offimage'><img src=\"img/Officer.jpg\"></div>");
        out.println("<div id = 'Redirect'>");
        out.println("<div id = 'ancher1'><a href = ''>Register Accident</a></div>");
        out.println("<div id = 'ancher2'><a href='Violation_registeration'>Register Violation</a></div>");
        out.println("</div>");


        // Accident Registration Form
        out.println("<div id = 'form'>");
        out.println("<h2>Accident Registration Form</h2>");
        out.println("<form action=\"Accident\" method=\"post\">");
        out.println("<div class=\"form-group\">");
        out.println("<label>Driver Name:</label><br>");
        out.println("<input type=\"text\" name=\"name\" required /><br>");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label>Driver Driver License:</label><br>");
        out.println("<input type=\"text\" name=\"License\" required /><br>");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label>Location Point (Latitude, Longitude):</label><br>");
        out.println("<input type=\"text\" name=\"locationPoint\" required><br><br>");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label>Time:</label><br>");
        out.println("<input type=\"datetime-local\" name=\"time\" required><br><br>");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label>InvestigationOfficer Name:</label><br>");
        out.println("<input type=\"text\" name=\"InvestigationOfficer_Name\" required><br><br>");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label>Description:</label><br>");
        out.println("<textarea cols=\"55\" rows=\"3\" name=\"description\" required></textarea><br><br>");
        out.println("</div>");
        out.println("<input type=\"submit\" value=\"Submit\" onclick=\\\"showPopup()\\\">");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
    }

    // Method to handle accident registration
    private void registerAccident(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        String Drivername = request.getParameter("name");
        String LicenseNo = request.getParameter("License");
        String locationPoint = request.getParameter("locationPoint");
        String name = request.getParameter("InvestigationOfficer_Name");
        String time = request.getParameter("time");
        String description = request.getParameter("description");
        String UserName = "root";
        String Password = "root";
        String DB = "jdbc:mysql://localhost:3306/traffic_accident_information_management_system";


     // Existing code...
  
        
       

        try (
        		 
             
        		Connection connection = DriverManager.getConnection(DB, UserName, Password)) {
            String sql = "INSERT INTO accidents (LocationPoint, Time, Description, InvestigatingOfficerName ) VALUES (?, ?, ?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, locationPoint);
            ps.setString(2, time);
            ps.setString(3, description);
            ps.setString(4, name);
            
           
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
//                out.println("<p>Accident registration successful!</p>");

                // Driver Data Insertion
                String sql2 = "INSERT INTO drivers(DriverName, DriverLicenseNumber) VALUES(?,?)";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setString(1, Drivername);
                ps2.setString(2, LicenseNo);
                int rowsAffected2 = ps2.executeUpdate();
                if (rowsAffected2 > 0) {
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int accidentId = generatedKeys.getInt(1);

                            String driverId = null;
                            String sql3 = "SELECT DriverID FROM drivers WHERE DriverName = ?";
                            PreparedStatement ps3 = connection.prepareStatement(sql3);
                            ps3.setString(1, Drivername);
                            ResultSet rs = ps3.executeQuery();
                            if (rs.next()) {
                                driverId = rs.getString("DriverID");
                                String sql4 = "UPDATE accidents SET DriverID = ? WHERE AccidentID = ?";
                                PreparedStatement ps4 = connection.prepareStatement(sql4);
                                ps4.setString(1, driverId);
                                ps4.setInt(2, accidentId);
                                int rowsAffected4 = ps4.executeUpdate();
                                if (rowsAffected4 > 0) {
                                    out.print("<div class='Logbody' >");
                	                out.println("<h2 style=color:red;>Accident registration successful!</h2>");
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