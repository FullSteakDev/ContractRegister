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
import constants.db.ContractsDBConstants;

public class AddContractServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		
		res.setContentType(ContractRegisterConstants.CONTENT_TYPE_TEXT_HTML);
		
		String cId = req.getParameter("contractid");
		String cDate = req.getParameter("cdate");
		String cCompany = req.getParameter("company");
		String cDesc = req.getParameter("description");
		int cSignee = Integer.parseInt(req.getParameter("signee_id"));
		String cName = req.getParameter("signee_name");
		
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into contract values(?,?,?,?,?,?)");
			ps.setString(1, cId);
			ps.setString(2, cDate);
			ps.setString(3, cCompany);
			ps.setString(4, cDesc);
			ps.setInt(5, cSignee);
			ps.setString(6, cName);
			
			int k = ps.executeUpdate();
			if(k==1)
			{
				RequestDispatcher rd = req.getRequestDispatcher("AddContract.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Contract Detail Updated Successfully!<br/>Add More Contracts</div>");
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("AddContract.html");
				pw.println("<div class=\"tab\">Failed to Add Contracts! Fill up CareFully</div>");
				rd.include(req, res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
