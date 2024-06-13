

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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
			
	        out.print("<div class = 'title'><h1>Admin Officer Page</h1></div>");
	        out.print("<div class = 'Admin_Pic' ><img src= \"img/Admin.jpg\"></div>");
	        out.print("<div class = 'Atitle'><p>Register Traffic Officer or Finance Officer</p></div><div></div>");
	        out.print("<div id = 'RForm'>");
	        
	        
	        if (request.getMethod().equalsIgnoreCase("post")) {
				 RegisterUser(request, response, out);
		            return; // exit method after processing POST request
		        }
	        
	        out.print("<form action=Register method=post >");
	        out.print("<div></div>");
			out.print("<label >FirstName:</label><br>");
			out.print("<input type=text name=FirstName size=20 placeholder=\"Full Name\" required /><br>");
			out.print("<label >Middle Name</label><br>");
			out.print("<input type=text name=MiddleName size=20 placeholder=\"Full Name\" required /><br>");
			out.print("<label >Last Name:</label><br>");
			out.print("<input type=text name=LastName size=20 placeholder=\"Full Name\" required /><br>");
			out.print("<label >ID Number:</label><br>");
			out.print("<input type=text name=ID_Number placeholder=\"ID Number\" required /><br>");
			out.print("<label >Entry Year:</label><br>");
			out.print("<input type=date name=Entry_Year placeholder=\"Year\" required /><br>");
			out.print("<label >Email:</label><br>");
			out.print("<input type=email name=Email placeholder=\"ContactInformation\" required /><br>");
			out.print("<label >Phone Number:</label><br>");
			out.print("<input type=tel name=Phone_number placeholder=\"ID Number\" required /><br>");
			out.print("<label >Roll:</label><br>");
	    		 out.print("<select name=\"Roll\" id=\"main_select\" >");
	    		 out.print("\"Roll\" </option>");
	    		 out.print("<option value=\"TrafficPolice\">Traffic Officer</option>");
	    		 out.print("<option value=\"FinancialWorker\">Finance Officer</option>");
	    		 out.print("</select><br>"); 
			
			out.print("<input type=submit name=submit value=Register /><input type=reset value=Clear>");
			out.print("</form>");
	        out.print("</div>");
	        
	        
	        
	       
	       

				out.print("</body></html>");
				} catch(Exception e) { 
				 out.print(e.getMessage());
				 }
	}
	
    private void RegisterUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
		
		
		 String FirstName = request.getParameter("FirstName"); //users
		 String MiddleName = request.getParameter("MiddleName"); //users
		 String LastName = request.getParameter("LastName"); //users
		 String ID_Number = request.getParameter("ID_Number"); //user
		 String Entry_Year = request.getParameter("Entry_Year"); 
		 String phone_number = request.getParameter("Phone_number");
		 String Email = request.getParameter("Email");  
		 String Roll = request.getParameter("Roll"); 
		  String Status = "Active";
		    String UserName = "root";
	        String Password = "root";
	        String DB = "jdbc:mysql://localhost:3306/traffic_accident_information_management_system";

		 
	        // Existing code...

	        try (Connection connection = DriverManager.getConnection(DB, UserName, Password)) {
	            String sql = "INSERT INTO users(  username, password, usertype) VALUES ( ?,?,?)";
	            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, FirstName);
	            ps.setString(2, ID_Number);
	            ps.setString(3, Roll);
	            int rowsAffected = ps.executeUpdate();	            
	            
	            
	            if (rowsAffected > 0) {
	                out.println("<p>Punishment registration successful!</p>");
          switch(Roll) {
          case "TrafficPolice":
        	  String sql2 = "INSERT INTO traffic(firstName, middleName, lastName,ID_Number, Email, phone_number, Status, Entry_Year) VALUES(?,?,?,?,?,?,?,?)";
        	  PreparedStatement ps1 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
        	  ps1.setString(1, FirstName);
	            ps1.setString(2, MiddleName);
	            ps1.setString(3, LastName);
	            ps1.setString(4, ID_Number);
	            ps1.setString(5, Email);
	            ps1.setString(6, phone_number);
	            ps1.setString(7,Status);
	            ps1.setString(8, Entry_Year);

        	 
        	  ps1.executeUpdate();
        	  
        	  break;
          case "FinancialWorker":
        	  String sql3 = "INSERT INTO financialworkers(firstName, middleName, lastName, ID_Number, Email, phone_number, Status,Entry_Year) VALUES(?,?,?,?,?,?,?,?)";
        	  PreparedStatement ps2 = connection.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
        	  ps2.setString(1, FirstName);
	            ps2.setString(2, MiddleName);
	            ps2.setString(3, LastName);
	            ps2.setString(4, ID_Number);
	            ps2.setString(5, Email);
	            ps2.setString(6, phone_number);
	            ps2.setString(7,Status);
	            ps2.setString(8, Entry_Year);
        	    ps2.executeUpdate();
        	  break;
          }}
	        }catch (SQLException e) {
	            out.println("<p>Error occurred while registering the accident: " + e.getMessage() + "</p>");
	            e.printStackTrace(); // Log the exception for debugging
	        }}}