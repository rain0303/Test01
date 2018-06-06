package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewListServlet extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Reflesh", "1");
		ServletContext sc = this.getServletContext();
		Set<String> userList2 = (Set<String>)sc.getAttribute("userList");
		HttpSession httpSession = request.getSession();
		String newName = (String) httpSession.getAttribute("newName");
		response.setContentType("text/html;charSet=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");

		if (userList2 != null) {
			for (String userName : userList2) {
				if (newName != null && newName.equals(userName)) {
					out.println("<font color='red'>" +userName + "</font><br/>");
				} else {
					out.println("<font color='blue'>"+userName + "</font><br/>");
				}
			}
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
