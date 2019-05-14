package com.gomovie.dao;

import com.gomovie.model.Seat;

import java.util.List;

public interface SeatDao {
	List<Seat> query(Seat seat);
	void xdseat(Seat seat);
	List<Seat> queryOrderMovie();
}
