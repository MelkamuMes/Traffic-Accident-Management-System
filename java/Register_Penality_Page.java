

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register_Penality_Page
 */
@WebServlet("/Register_Penality_Page")
public class Register_Penality_Page extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register_Penality_Page() {
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
		 
		out.print(" <html><head>");
		out.print("<title>Registration Student Profile</title>");
		out.println("<link rel='stylesheet' type='text/css' href='"+request.getContextPath()+"Style.css' />");	
		out.print("<h1 style=color:#000>Driver violation Punishment</h1>");

		if(submit==null)
		{
		out.print("<form action=Register_student_profile method=post >");
		out.print("<div class=\"form-group\">");
		out.print("<label >Driver ID:</label>");
		out.print(" Student ID: <input type=text name=studid size=20 required /><br>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label >Driver Name:</label>");
		out.print(" <input type=text name=firstname size=30 required /><br>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label >Driver Second Name:</label>");
		out.print(" <input type=text name=secondname size=30 required /><br>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label > Date of offence::</label>");
		out.print("<input type=\"date\" class=\"form-control\" id=\"date1\" placeholder=\"\" name=\"date1\" required>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label >Entry Year:</label>");
		out.print("<input type=number name=entryyear required /><br>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
		out.print("<label >licence plate:</label>");
		out.print("<input type=\"text\" class=\"form-control\" id=\"plate\" placeholder=\"Enter licence plate\" name=\"plate\" required>");
		out.print("</div>");
		out.print("<div class=\"form-group\">");
        out.print("<label >Amount of fee[ETB]:</label>");
        out.print("<input type=\"text\" class=\"form-control\" id=\"amount\" placeholder=\"Enter amout\" name=\"amount\" required>");
        out.print("</div>");
        out.print("<div class=\"form-group\">");
        out.print("<label >Date line of payment:</label>");
    	out.print("<input type=\"date\" class=\"form-control\" id=\"date2\" placeholder=\"\" name=\"date2\" required>");
    	out.print("</div>");
    	out.print("<select name=\"status\" id=\"main_select\" >");
    	out.print("\"Payment Status\" </option>");
        out.print("<option value=\"Pending\">Pending</option>");
        out.print("</select>"); 
		
		out.print("<input type=submit name=submit value=Register /><input type=reset value=Clear>");
		out.print("</form>");
		out.print("<button> <a href=\"Traffic_Home_Page\">Return to Home</a></button>");

		
		}else {
			String sid = request.getParameter("studid");
			 String fname = request.getParameter("firstname");
			 String sname = request.getParameter("secondname");
			 String dobirth = request.getParameter("dob");
			 String eyear = request.getParameter("entryyear");
			
//       int doB=Integer.parseInt(dobirth);

			 int ey=Integer.parseInt(eyear);
			 
			 String driverName="com.mysql.jdbc.Driver";
			 String dbUrl="jdbc:mysql://localhost:3306/testdb";
			String dbusername="root";
			String dbpassword="root";
			 
			 Connection conn = null;
			 PreparedStatement ps=null;
			 
			 try{
			 Class.forName(driverName);
			 }catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
			}
			try {
				out.print("hello ");			
				conn=DriverManager.getConnection(dbUrl,dbusername,dbpassword);
			String sql="insert into stud_data(stud_id, stud_fname,stud_lname,date_of_birth,entry_year) VALUES (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,sid);
			ps.setString(2,fname);
			ps.setString(3,sname);
			ps.setString(4,dobirth);
			ps.setInt(5,ey);
			 int i = ps.executeUpdate();
			 if(i>0) {
			out.println("<font color=red>The following record has been inserted into student_profile table</font>");
			 
			 out.print("<form action= Register_student_profile method=post >");
			out.print(" Student ID: <input type=text name=studid value="+sid+" readonly /><br>");
			out.print(" First Name: <input type=text name=firstname value="+fname+" readonly /><br>");
			out.print(" Second Name: <input type=text name=secondname value="+sname+" readonly /><br>");
			out.print(" Date of Birth: <input type=date name=dob value="+dobirth+" readonly /><br>");
			out.print(" Entry Year: <input type=number name=entryyear value="+eyear+" readonly /><br>");
			out.print("<input type=submit value=Close />");
			out.print("</form>");
			 }else
			 out.println("Failed to insert the data");
			 out.println("</body></html>");
			}catch (SQLException e){
				out.print("error on catch");
			 out.println(e.getMessage());
			 }
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
