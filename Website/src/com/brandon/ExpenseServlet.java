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
@WebServlet("/Expenses")
public class ExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void setAccessControlHeaders(HttpServletResponse resp, String type) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.setHeader("Access-Control-Allow-Methods", type);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		setAccessControlHeaders(response, "GET");
		String id = request.getParameter("id");
        try {
	        
	        List<Expense> expenses = MyPersonalInformation.getExpenses(Integer.parseInt(id));
	        Gson gsonBuilder = new GsonBuilder().create();
	        String stringInfo = gsonBuilder.toJson(expenses);
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
		System.out.println("test");
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
			System.out.println(e);
		}
		info = data.toString();
		System.out.println(info);
		
		Gson gsonBuilder = new GsonBuilder().create();
		Expense expense = gsonBuilder.fromJson(info, Expense.class); 
		String result = "0";
    	if (MyPersonalInformation.addExpense(expense)) {
    		
    		result = "1";
    	}
    	PrintWriter printer = response.getWriter();
        printer.print(result);
        printer.flush();
	}

}
