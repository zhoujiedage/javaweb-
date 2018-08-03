package com.zhoujiebiyesheji.messageboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhoujiebiyesheji.messageboard.DataBean.Buser;
import com.zhoujiebiyesheji.messageboard.jdbc.JDBCOption;
import com.zhoujiebiyesheji.messageboard.jdbc.SQLConstant;
import com.zhoujiebiyesheji.messageboard.tools.Common;
import com.zhoujiebiyesheji.messageboard.tools.Constant;

/**
 * 保存用户注册信息
 */
@WebServlet("/SaveUser")
public class SaveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String security = request.getParameter("security");
			if (!request.getSession().getAttribute("security").toString()
					.equals(security)) {//验证一下这个验证码和session当中储存的是否一样，如果不一样
				out.println(Common.AlertBack("验证码不正确请重新输入验证码！"));
				return;
			} else {
				if(Common.isEmptyByString(request.getParameter("id")))
				{
					addUser(out, request);
				}
				else{
					alterUser(out, request);
				}
				
			}
		} finally {
			out.close();
		}
	}

	private void addUser(PrintWriter out, HttpServletRequest request) {
		List list = JDBCOption.query(SQLConstant.getUserByName,
				request.getParameter("username"));
		if (list.size() > 0) {//是一个list,如果长度大于零
			out.println(Common.AlertBack("用户名已经存在？请用其它的用户名"));
			return;
		}
		Buser oneUser = new Buser();
		oneUser.setUserName(request.getParameter("username"));
		oneUser.setPassword(request.getParameter("password"));
		oneUser.setGender(Byte.parseByte(request.getParameter("gender")));
		oneUser.setEmail(request.getParameter("email"));
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			oneUser.setBirthdate(formatDate.parse(request
					.getParameter("birthdate")));
		} catch (ParseException e) {
			oneUser.setBirthdate(null);
		}
		int id=JDBCOption.insertGetID(SQLConstant.addUser, oneUser.getUserName(), oneUser
				.getPassword(), oneUser.getGender(), oneUser.getEmail(),
				oneUser.getBirthdate() == null ? null : new Timestamp(oneUser
						.getBirthdate().getTime()));
		if(id>0)
		{
			request.getSession().setAttribute(Constant.USERID_MARK, id);
            request.getSession().setAttribute(Constant.USER_NAME_MARK, oneUser.getUserName());
			out.print(Common.AlertTo("恭喜" + oneUser.getUserName() + "注册成功！",
				"index.jsp"));
		}
	}
	private void alterUser(PrintWriter out, HttpServletRequest request) {
		Buser oneUser = new Buser();
		oneUser.setId(Integer.parseInt(request.getParameter("id")));
		oneUser.setPassword(request.getParameter("password"));
		oneUser.setGender(Byte.parseByte(request.getParameter("gender")));
		oneUser.setEmail(request.getParameter("email"));
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			oneUser.setBirthdate(formatDate.parse(request
					.getParameter("birthdate")));
		} catch (ParseException e) {
			oneUser.setBirthdate(null);
		}
		JDBCOption.update(SQLConstant.updateUser, oneUser
				.getPassword(), oneUser.getGender(), oneUser.getEmail(),
				oneUser.getBirthdate() == null ? null : new Timestamp(oneUser
						.getBirthdate().getTime()),oneUser.getId());
		out.print(Common.AlertTo("恭喜修改成功！",
				"index.jsp"));
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
