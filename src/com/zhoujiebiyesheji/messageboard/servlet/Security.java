package com.zhoujiebiyesheji.messageboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 */
@WebServlet("/Security")
public class Security extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Security() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		Random random = new Random();
		int firstNumber = (int) (Math.round(Math.random() * (10 - 0) + 0));//生成两个随机值，最大值与最小值两个之间的公式
		int secondNumber = (int) (Math.round(Math.random() * (10 - 0) + 0));
		int security = firstNumber + secondNumber;
		request.getSession().setAttribute("security", security);//计算结果保存到session当中
		String securityS = firstNumber + "+" + secondNumber;
		out.print(securityS);//生成的验证字符串发送到页面
	}

}
