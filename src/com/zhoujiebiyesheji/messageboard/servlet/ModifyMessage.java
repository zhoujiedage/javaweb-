package com.zhoujiebiyesheji.messageboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhoujiebiyesheji.messageboard.jdbc.JDBCOption;
import com.zhoujiebiyesheji.messageboard.jdbc.SQLConstant;
import com.zhoujiebiyesheji.messageboard.tools.Common;

/**
 * 修改信息
 */
@WebServlet("/ModifyMessage")
public class ModifyMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMessage() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	   PrintWriter out = response.getWriter();
    	   try {
    	   String messageID=request.getParameter("messageID");         
           String info=request.getParameter("message");      
           JDBCOption.update(SQLConstant.modifyMessage,info,messageID);//通过数据库操作语句
           out.println(Common.AlertTo("修改成功！","userCenter.jsp"));
    	   }
    	   finally
    	   {
    		   out.close();
    	   }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  processRequest(request, response);
	}

}
