package com.zhoujiebiyesheji.messageboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhoujiebiyesheji.messageboard.jdbc.JDBCOption;
import com.zhoujiebiyesheji.messageboard.jdbc.SQLConstant;
import com.zhoujiebiyesheji.messageboard.tools.Common;
import com.zhoujiebiyesheji.messageboard.tools.Constant;

/**
 * 登陆
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String security = request.getParameter("security");
			if (!request.getSession().getAttribute("security").toString()
					.equals(security)) {
				out.println(Common.AlertBack("验证码不正确请重新输入验证码！"));//如果用户输入不成功
				return;
			}
			String userName = request.getParameter("username");//在请求当中得到username,password
			String password = request.getParameter("password");
			List<HashMap<String, Object>> list = JDBCOption.query(//调用loginQuery，的sql语句，将返回值赋值给list导入相关的包
					SQLConstant.loginQuery, userName, password);
			if (list.size() > 0) {//对list的长度进行一个判断，如果长度大于零代表成功登陆，会从list当中得到这个id,将id和用户名添加到我们的session当中
				Integer userID = (Integer) (list.get(0).get("id"));
				request.getSession().setAttribute(Constant.USERID_MARK, userID);
				request.getSession().setAttribute(Constant.USER_NAME_MARK,
						userName);
				response.sendRedirect("index2.jsp");
			} else {
				out.println(Common.AlertBack("用户名与密码有误，请重新输入！"));
			}
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
