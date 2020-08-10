package com.brandon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandbag.MyPersonalInformation;
import com.sandbag.PersonalInfo;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/PersonalInformationServlet")
public class PersonalInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalInformationServlet() {
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
        
        PersonalInfo info = MyPersonalInformation.getPersonalInformation(Integer.parseInt(id));
        Gson gsonBuilder = new GsonBuilder().create();
        String stringInfo = gsonBuilder.toJson(info);
        PrintWriter printer = response.getWriter();
        printer.print(stringInfo);
        printer.flush();
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
		PersonalInfo personalInfo = gsonBuilder.fromJson(info, PersonalInfo.class); 
		String result = "0";
    	if (MyPersonalInformation.savePersonalInformation(personalInfo)) {
    		
    		result = "1";
    	}
    	PrintWriter printer = response.getWriter();
        printer.print(result);
        printer.flush();
	}

}
