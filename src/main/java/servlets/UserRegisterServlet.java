package servlets;

import java.sql.*;
import javax.servlet.*;

import config.DBConnection;
import constants.ContractRegisterConstants;
import constants.db.UsersDBConstants;

import java.io.*;

public class UserRegisterServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(ContractRegisterConstants.CONTENT_TYPE_TEXT_HTML);

		String uName = req.getParameter(UsersDBConstants.COLUMN_USERNAME);
		String pWord = req.getParameter(UsersDBConstants.COLUMN_PASSWORD);
		String fName = req.getParameter(UsersDBConstants.COLUMN_FIRSTNAME);
		String lName = req.getParameter(UsersDBConstants.COLUMN_LASTNAME);
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con
					.prepareStatement("insert into " + UsersDBConstants.TABLE_USERS + "  values(?,?,?,?,?)");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ps.setString(3, fName);
			ps.setString(4, lName);
			ps.setInt(5, 2);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				rd.include(req, res);
				pw.println("<h3 class='tab'>User Registered Successfully</h3>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("userreg");
				rd.include(req, res);
				pw.println("Sorry for interruption! Register again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
