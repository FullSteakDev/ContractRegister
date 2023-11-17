package servlets;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import config.DBConnection;
import constants.ContractRegisterConstants;
import constants.db.UsersDBConstants;

import java.io.*;
import java.sql.*;

public class AdminLoginServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(ContractRegisterConstants.CONTENT_TYPE_TEXT_HTML);
		String uName = req.getParameter(UsersDBConstants.COLUMN_USERNAME);
		String pWord = req.getParameter(UsersDBConstants.COLUMN_PASSWORD);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + UsersDBConstants.TABLE_USERS + " WHERE  "
					+ UsersDBConstants.COLUMN_USERNAME + "=? AND " + UsersDBConstants.COLUMN_PASSWORD + "=? AND "
					+ UsersDBConstants.COLUMN_USERTYPE + "=1");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				
				Cookie ck = new Cookie("usertype","admin");
				
				rd.include(req, res);
				pw.println("<div class=\"tab\">Admin login Successful</div>");
				pw.println("<div class=\"tab\"><br/><a href=\"AddContract.html\">ADD CONTRACTS</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"AddSignee.html\">ADD SIGNEE</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"RemoveContracts.html\">REMOVE CONTRACTS</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"RemoveSignee.html\">REMOVE SIGNEE</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"viewcontract\">VIEW CONTRACTS</a></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"viewsignee\">VIEW SIGNEE</a></div>");
			} else {

				RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect username or password</div>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}