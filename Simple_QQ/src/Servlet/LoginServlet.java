package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

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
		String userName = request.getParameter("username");
		ServletContext sc = this.getServletContext();//获取所有登录的用户名称；
		Set<String> userList = (Set<String>)sc.getAttribute(userName);
		if(userList!=null && userList.contains(userName)){
			String msg = "该用户已经登录，请换一个名称登录";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("LoginPageServlet").forward(request, response);
		}
		else{
			if(userList==null){
				userList = new HashSet<String>();
				sc.setAttribute("userList", userList);
			}
			userList.add(userName);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("newName", userName);
			request.getRequestDispatcher("MainServlet").forward(request, response);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
