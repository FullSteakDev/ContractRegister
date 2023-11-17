package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

import config.DBConnection;
import constants.ContractRegisterConstants;
import constants.db.SigneeDBConstants;

public class ViewUserSigneeServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("Select * from " + SigneeDBConstants.TABLE_SIGNEE);
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("ViewSignee.html");
			rd.include(req, res);
			pw.println("<div class=\"d-flex\" id=\"wrapper\">");
            pw.println("<div class=\"bg-white\" id=\"sidebar-wrapper\">\r\n"
                    + "            <div class=\"sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom\"><i class=\"fa-solid fa-file-contract fa-2x\"></i><h3>Contract Register</h3></div>\r\n"
                    + "            <div class=\"list-group list-group-flush my-3\">\r\n"
                    + "                <a href=\"#\" class=\"list-group-item list-group-item-action bg-transparent second-text active\"><i\r\n"
                    + "                        class=\"fas fa-tachometer-alt me-2\"></i>Dashboard</a>\r\n"
                    + "                <a href=\"viewusercontract\" class=\"list-group-item list-group-item-action bg-transparent second-text fw-bold\"><i class=\"fa-solid fa-eye me-2\"></i>View Contracts</a>\r\n"
                    + "                <a href=\"viewusersignee\" class=\"list-group-item list-group-item-action bg-transparent second-text fw-bold\"><i class=\"fa-solid fa-eye me-2\"></i>View Signee</a>\r\n"
                    + "                <a href=\"login.html\" class=\"list-group-item list-group-item-action bg-transparent text-danger fw-bold\"><i\r\n"
                    + "                        class=\"fas fa-power-off me-2\"></i>Logout</a>\r\n"
                    + "            </div>\r\n"
                    + "        </div>");
            pw.println("<div id=\"page-content-wrapper\">");
            pw.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4\">\r\n"
                    + "                <div class=\"d-flex align-items-center\">\r\n"
                    + "                    <i class=\"fas fa-align-left primary-text fs-4 me-3\" id=\"menu-toggle\"></i>\r\n"
                    + "                    <h2 class=\"fs-2 m-0\">Dashboard</h2>\r\n"
                    + "                </div>\r\n"
                    + "\r\n"
                    + "            </nav>");
            pw.println("<div class=\"row my-5\">");
            pw.println("<h3 class=\"fs-4 mb-3\">View Signee</h3>\r\n" + 
                    "<div class=\"col\">\r\n" +
                    "       <table class=\"table bg-white rounded shadow-sm table-hover\">\r\n" + 
                    "<thead>" +
                    "           <tr>\r\n" + 
                    "               \r\n" + 
                    "               <th scope=\"col\">Signee ID</th>\r\n" + 
                    "               <th scope=\"col\">Signee Name</th>\r\n" + 
                    "               <th scope=\"col\">Signee Address</th>\r\n" + 
                    "               <th scope=\"col\">Signee Phone</th>\r\n" + 
                    "           </tr>" +
                    "</thead>" +
                    "<tbody>");
			while(rs.next())
			{
				String SId = rs.getString(1);
				String SName = rs.getString(2);
				String SAddress = rs.getString(3);
				String SPhone = rs.getString(4);
				pw.println("<tr><td>"+SId+"</td>");
				pw.println("<td>"+SName+"</td>");
				pw.println("<td>"+SAddress+"</td>");
				pw.println("<td>"+SPhone+"</td></tr>");
			}
			pw.println("</tbody>");
            pw.println("</table>\r\n" + 
                    "   </div>");
            pw.println("</div>");
            pw.println("</div>");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
