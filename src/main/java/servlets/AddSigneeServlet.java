package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import config.DBConnection;
import constants.ContractRegisterConstants;
import constants.db.SigneeDBConstants;

public class AddSigneeServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		
		res.setContentType(ContractRegisterConstants.CONTENT_TYPE_TEXT_HTML);
		
		int Id = Integer.parseInt(req.getParameter(SigneeDBConstants.COLUMN_ID));
		String sId = req.getParameter(SigneeDBConstants.COLUMN_ID);
		String sName = req.getParameter(SigneeDBConstants.COLUMN_S_NAME);
		String sAddress = req.getParameter(SigneeDBConstants.COLUMN_ADDRESS);
		String sPhone = req.getParameter(SigneeDBConstants.COLUMN_PHONE);
		
		String SId = req.getParameter("id");
        String SName = req.getParameter("s_name");
        String SAddress = req.getParameter("address");
        String SPhone = req.getParameter("phone");
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into signee values(?,?,?,?)");
			ps.setString(1, SId);
			ps.setString(2, SName);
			ps.setString(3, SAddress);
			ps.setString(4, SPhone);
			int k = ps.executeUpdate();
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("AddSignee.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Signee Detail Updated Successfully!<br/>Add More Contracts</div>");
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("AddSignee.html");
				pw.println("<div class=\"tab\">Failed to Add Signee! Fill up CareFully</div>");
				rd.include(req, res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
