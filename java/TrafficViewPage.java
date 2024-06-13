

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TrafficViewPage
 */
@WebServlet("/TrafficViewPage")
public class TrafficViewPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrafficViewPage() {
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
		out.print("<html><head>");
		out.print("<title>View Student Profile</title></head>");
		out.print("<body bgcolor=lightblue>");
		 
		 String driverName="com.mysql.cj.jdbc.Driver";
		String dbUrl="jdbc:mysql://localhost:3306/testdb";
		String dbusername="root";
		String dbpassword="root";
		 
		 Connection conn=null;
		 Statement stmt=null;
		 ResultSet rs=null;
		 
		 try{
		 Class.forName(driverName);
		 }catch(ClassNotFoundException e){
		System.out.println(e.getMessage());
		}
		 try {
		conn=DriverManager.getConnection(dbUrl,dbusername,dbpassword);
		stmt=conn.createStatement(); 
		rs=stmt.executeQuery("select * from stud_data");
		 
		out.print("<table border= 2>");
		out.print("<tr>");
		 out.print("<th>Student Id </th><th>First Name </th> <th>Second Name </th><th>Date Of Birth</th><th>Entry Year </th>");
		out.print("</tr>");
		while(rs.next())
		 { 
		out.print("<tr> ");
		out.print("<td>"+rs.getString("stud_id") + "</td><td>" +rs.getString("stud_fname")+"</td><td>" +rs.getString("stud_lname")+"</td><td>" +rs.getString("date_of_birth")+"</td> <td>"+rs.getString("entry_year")+"</td>" );
		 out.print("</tr> ");
		 }
		out.print ("</table>");
		}catch (Exception e){
		 out.println(e.getMessage());
		 }
		out.println("</body></html>");
		
		
		
		
		
		
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
