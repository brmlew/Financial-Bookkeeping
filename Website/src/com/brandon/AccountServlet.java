package com.brandon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandbag.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Accounts")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
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
		String name = request.getParameter("name");
		String id = request.getParameter("id");
        try {
        	List<Account> accounts;
	        if (name == null) {
	        	accounts = MyPersonalInformation.getAccountSummary(Integer.parseInt(id));
	        }
	        else {
	        	accounts = MyPersonalInformation.getAccounts(name, Integer.parseInt(id));
	        }
	        Gson gsonBuilder = new GsonBuilder().create();
	        String stringInfo = gsonBuilder.toJson(accounts);
	        PrintWriter printer = response.getWriter();
	        printer.print(stringInfo);
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
		BufferedReader br;
		StringBuilder data = new StringBuilder();
		String line = "";
		String info = "";
		try {
			br = request.getReader();
			while ((line = br.readLine()) != null) {
				data.append(line);
			}
		} catch (IOException e) {
			
		}
		info = data.toString();
		
		Gson gsonBuilder = new GsonBuilder().create();
		Account account = gsonBuilder.fromJson(info, Account.class); 
		String result = "0";
    	if (MyPersonalInformation.addAccount(account)) {
    		
    		result = "1";
    	}
    	PrintWriter printer = response.getWriter();
        printer.print(result);
        printer.flush();
	}

}
