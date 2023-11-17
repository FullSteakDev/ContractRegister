package servlets;

import java.sql.*;
import javax.servlet.*;

import config.DBConnection;
import constants.db.SigneeDBConstants;

import java.io.*;

public class RemoveSigneeServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String bkid = req.getParameter("id");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"delete from " + SigneeDBConstants.TABLE_SIGNEE + "  where " + SigneeDBConstants.COLUMN_ID + "=?");
			ps.setString(1, bkid);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Signee Removed Successfully</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveSignee.html\">Remove more Signee</a></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Signee Not Available in the database</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveSignee.html\">Remove more Signee</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
