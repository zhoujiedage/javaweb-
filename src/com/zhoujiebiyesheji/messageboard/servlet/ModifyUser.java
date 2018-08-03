package com.zhoujiebiyesheji.messageboard.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhoujiebiyesheji.messageboard.DataBean.Buser;
import com.zhoujiebiyesheji.messageboard.jdbc.JDBCOption;
import com.zhoujiebiyesheji.messageboard.jdbc.SQLConstant;
import com.zhoujiebiyesheji.messageboard.tools.Common;

/**
 * 修改用户注册信息
 */
@WebServlet("/ModifyUser")
public class ModifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("id");
    	if(!Common.isEmptyByString(id))
    	{
    		List<HashMap<String, Object>> list=JDBCOption.query(SQLConstant.getUserByID, id);
    		if(list.size()>0)
    		{
    			Buser oneUser = new Buser();
    			HashMap hm = list.get(0);
    			oneUser.setId(Integer.parseInt(hm.get("id").toString()));
				oneUser.setUserName(hm.get("userName").toString());
				oneUser.setEmail(hm.get("email").toString());
				oneUser.setPassword(hm.get("password").toString());
				oneUser.setGender(hm.get("gender").toString().equals("false") ? (byte) 0
						: (byte) 1);
				oneUser.setBirthdate((java.util.Date) hm.get("birthdate"));
				request.setAttribute("oneUser", oneUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher("modifyUser.jsp"); 
				dispatcher .forward(request, response); 
    		}
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
