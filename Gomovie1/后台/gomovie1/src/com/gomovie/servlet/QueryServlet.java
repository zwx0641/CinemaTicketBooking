package com.gomovie.servlet;

import com.gomovie.model.Seat;
import com.gomovie.service.SeatService;
import com.gomovie.service.impl.SeatServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QueryServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SeatService seatService = new SeatServiceImpl();
	Seat seat = new Seat();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String showidtemp = req.getParameter("showid");
        
        int showid = Integer.parseInt(showidtemp);

        String roomtemp = req.getParameter("room");
        int room = Integer.parseInt(roomtemp);

        /*String datetemp = req.getParameter("date");
        Date date = DateUtils.strToDate(datetemp);*/

        seat.setRoom(room);
        seat.setShowid(showid);

		List<Seat> list = seatService.query(seat);
		resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");//设置传输编码
		System.out.println("QueryServlet====="+JSONArray.fromObject(list).toString());
		resp.getWriter().write(JSONArray.fromObject(list).toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
