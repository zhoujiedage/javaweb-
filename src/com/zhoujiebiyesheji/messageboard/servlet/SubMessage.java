package com.zhoujiebiyesheji.messageboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

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
 * Servlet implementation class SubMessage
 */
@WebServlet("/SubMessage")//3.0的动态网页技术。是通过注释来完成servlet应用
public class SubMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		String message=request.getParameter("message");//用字符串接收从页面提取过来的message
		java.util.Date d=new java.util.Date();//系统当前的时间
		long userID = 0;
		if (request.getSession().getAttribute(Constant.USERID_MARK) == null) {
			out.println(Common.AlertBack("请登录后发布信息"));
			return;
		}
		else
		{
			userID = Long.parseLong(request.getSession()
					.getAttribute(Constant.USERID_MARK).toString());
		}
		JDBCOption.update(SQLConstant.addMessage,userID,message,new Timestamp(d.getTime()) );
		response.sendRedirect("index2.jsp");//调用常量类当中的jdbcoption的update上传数据方法
}
}