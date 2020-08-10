package com.brandon;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sandbag.MyPersonalInformation;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void setAccessControlHeaders(HttpServletResponse resp, String type) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", type);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		setAccessControlHeaders(response, "GET");
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
	        
	        int result = MyPersonalInformation.validateLogin(username, password);
	        PrintWriter printer = response.getWriter();
	        printer.print(result);
	        printer.flush();
        } catch (Exception e) {
        	System.out.println(e);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response, "POST");
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
        	String result = "0";
        	if (MyPersonalInformation.saveLoginInformation(username, password)) {
        		result = "1";
        	}
        	PrintWriter printer = response.getWriter();
	        printer.print(result);
	        printer.flush();
        }catch (Exception e) {
        	System.out.println(e);
        }
	}

}
