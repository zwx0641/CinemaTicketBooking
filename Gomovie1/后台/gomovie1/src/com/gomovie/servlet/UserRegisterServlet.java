package com.gomovie.servlet;

import com.gomovie.model.User;
import com.gomovie.service.UserService;
import com.gomovie.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegisterServlet extends HttpServlet {
	
	UserService userService = new UserServiceImpl();
	User user = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = new User();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String passwordTwo = req.getParameter("repeatpasswordText");
		String email = req.getParameter("email");
		String ageText = req.getParameter("age");
		int age = Integer.parseInt(ageText);
        System.out.println("username============="+username);
        System.out.println("password============="+password);
        System.out.println("passwordTwo============="+passwordTwo);
        System.out.println("passwordTwo============="+email);
        user.setUsername(username);
		user = userService.registerVali(user);
		System.out.println(username+":::"+password);
//		System.out.println(user1.getUsername());
        String msg = "";
        if(!password.equals(passwordTwo)){
        	msg = "0";
        	  String msgstr ="{\"msg\":\""+msg+"\"}";
              resp.getWriter().write(msgstr);
        }else{
		if(null == user){
			User   userNew = new User();
			userNew.setUsername(username);
			userNew.setPassword(password);
			userNew.setEmail(email);
			userNew.setAge(age);			
			userService.insert(userNew);
			resp.setCharacterEncoding("utf-8");
            msg = "1";

		}else{
            msg = "0";
        }
        String msgstr ="{\"msg\":\""+msg+"\"}";
        resp.getWriter().write(msgstr);
        }
//        System.out.println(JSONObject.fromObject(msg).toString());
//        resp.getWriter().write(JSONObject.fromObject(msg).toString());
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
