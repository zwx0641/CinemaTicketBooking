package com.gomovie.model;

import java.util.Date;

public class Seat {
	int id;
	int seatx;
	int seaty;
	int room;
	String address;
	int showid;
	String isxd;
    int userid;
    Date date;
    String dateStr;
    private String moviename;
    private int price;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIsxd() {
		return isxd;
	}
	public void setIsxd(String isxd) {
		this.isxd = isxd;
	}
	public int getShowid() {
		return showid;
	}
	public void setShowid(int showid) {
		this.showid = showid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeatx() {
		return seatx;
	}
	public void setSeatx(int seatx) {
		this.seatx = seatx;
	}
	public int getSeaty() {
		return seaty;
	}
	public void setSeaty(int seaty) {
		this.seaty = seaty;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    
    public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}

	public Seat() {
		super();
	}
	public Seat(int id, int seatx, int seaty, int room, String address) {
		super();
		this.id = id;
		this.seatx = seatx;
		this.seaty = seaty;
		this.room = room;
		this.address = address;
	}
	
}
