package com.gomovie.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gomovie.model.User;
import com.gomovie.service.impl.UserServiceImpl;

//–ﬁ∏ƒ√‹¬Î
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserServiceImpl userService = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String repeatnewpassword = request.getParameter("repeatnewpassword");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(oldpassword);
		
		user = userService.update(user);
		System.out.println(user.toString());
        String msg = "";
        if(user.getResult()== 0){
            msg = "0";//username wrong
        }else{
        	 if(user.getResult()== 10){//luo
        		 msg = "10";//√‹¬Î¥ÌŒÛ
        		 
        	 }else{
//        		 msg = "11";//√‹¬Î’˝»∑
        		if(user.getResult()== 11){
	        		 if(newpassword.equals(repeatnewpassword)){
	        			 userService.modifyPassword(username, newpassword);
	        			 msg = "111"; //∆•≈‰ –ﬁ∏ƒ≥…π¶
	        		 }else{
	        			 msg = "110";//≤ª∆•≈‰
	        		 }
        		}
        	 }
		}
        
        String msgStr = "{\"msg\":\""+msg+"\"}";
        System.out.println("updateServlet  msgStr======"+msgStr);
        resp.getWriter().write(msgStr);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
