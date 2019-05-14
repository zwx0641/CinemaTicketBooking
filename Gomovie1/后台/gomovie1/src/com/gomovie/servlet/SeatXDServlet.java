package com.gomovie.servlet;

import com.gomovie.model.Seat;
import com.gomovie.service.SeatService;
import com.gomovie.service.impl.SeatServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SeatXDServlet extends HttpServlet {

	SeatService seatService = new SeatServiceImpl();
	Seat seat = new Seat();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seatxtemp = req.getParameter("seatx");
		int seatx = Integer.parseInt(seatxtemp);
		String seatytemp = req.getParameter("seaty");
		int seaty = Integer.parseInt(seatytemp);
        String showidtemp = req.getParameter("showid");

        int showid = Integer.valueOf(showidtemp);

		String roomtemp = req.getParameter("room");
		int room = Integer.parseInt(roomtemp);

        String useridtemp = req.getParameter("userid");
      
        int userid = Integer.parseInt(useridtemp);
        
        String pricetemp = req.getParameter("pricebyseat");
        double price = Double.parseDouble(pricetemp);
        
        
		seat.setSeatx(seatx);
		seat.setSeaty(seaty);
		seat.setRoom(room);
        seat.setShowid(showid);
        seat.setUserid(userid);
        seat.setPrice((int) price);
		seatService.xdseat(seat);
//		resp.setCharacterEncoding("utf-8");
//		System.out.println(JSONArray.fromObject(movie).toString());
//		resp.getWriter().write(JSONArray.fromObject(movie).toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
