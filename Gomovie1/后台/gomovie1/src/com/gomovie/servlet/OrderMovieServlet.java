package com.gomovie.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gomovie.model.Seat;
import com.gomovie.service.impl.SeatServiceImpl;

import net.sf.json.JSONArray;

/**
 * 电影订单
 */
public class OrderMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SeatServiceImpl seatService = new SeatServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Seat> list = seatService.queryOrderMovie();
		resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");//设置传输编码
		System.out.println("OrderMovieServlet电影订单====="+JSONArray.fromObject(list).toString());
		resp.getWriter().write(JSONArray.fromObject(list).toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
