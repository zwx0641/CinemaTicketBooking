package com.gomovie.service;

import com.gomovie.model.Seat;

import java.util.List;

public interface SeatService {
	List<Seat> query(Seat seat);
	void xdseat(Seat seat);
	List<Seat> queryOrderMovie();
}
