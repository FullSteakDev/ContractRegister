package servlets;

import java.sql.*;
import javax.servlet.*;

import config.DBConnection;
import constants.db.ContractsDBConstants;

import java.io.*;

public class RemoveContractServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String bkid = req.getParameter("contractid");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"delete from " + ContractsDBConstants.TABLE_CONTRACT + "  where " + ContractsDBConstants.COLUMN_CONTRACTID + "=?");
			ps.setString(1, bkid);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Contract Removed Successfully</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveContracts.html\">Remove more Contracts</a></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Contract Not Available in the database</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveContracts.html\">Remove more Contracts</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
