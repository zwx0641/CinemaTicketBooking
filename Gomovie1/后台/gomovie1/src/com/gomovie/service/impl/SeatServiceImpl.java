package com.gomovie.service.impl;

import com.gomovie.dao.SeatDao;
import com.gomovie.dao.impl.SeatDaoImpl;
import com.gomovie.model.Seat;
import com.gomovie.service.SeatService;

import java.util.List;

public class SeatServiceImpl implements SeatService {

	SeatDao seatDao = new SeatDaoImpl();
	@Override
	public List<Seat> query(Seat seat) {
		return seatDao.query(seat);
	}


    @Override
	public void xdseat(Seat seat) {
		seatDao.xdseat(seat);
	}


	@Override
	public List<Seat> queryOrderMovie() {
		return seatDao.queryOrderMovie();
	}

}
