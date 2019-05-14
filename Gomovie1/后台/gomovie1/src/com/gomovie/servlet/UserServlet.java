package com.gomovie.servlet;

import com.gomovie.model.User;
import com.gomovie.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserServiceImpl userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		user.setPassword(password);
		user.setUsername(username);
		System.out.println(user.toString());

		user = userService.selectOne(user);
		System.out.println(user.toString());
        String msg = "";
//		if(user != null){
        if(user.getResult()== 1){//luo
            msg = "1";//µÇÂ¼³É¹¦
		}else if(user.getResult()== 0){
            msg = "0";//username wrong
        }else if(user.getResult()== 2){
        	msg = "2"; //password wrong
        }
        String msgStr = "{\"msg\":\""+msg+"\",\"id\":\""+user.getId()+"\",\"email\":\""+user.getEmail()+"\",\"age\":\""+user.getAge()+"\"}";
        System.out.println("userServlet  msgStr======"+msgStr);
        resp.getWriter().write(msgStr);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	

}
