package com.example.gomovie;

public class MovieHall {
	private int id;
	private int movieid;
	private int room;
	private String showaddress;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getMovieid() {
		return movieid;
	}

	public final void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public final int getRoom() {
		return room;
	}

	public final void setRoom(int room) {
		this.room = room;
	}

	public final String getShowaddress() {
		return showaddress;
	}

	public final void setShowaddress(String showaddress) {
		this.showaddress = showaddress;
	}

	@Override
	public String toString() {
		return "MovieHall [id=" + id + ", movieid=" + movieid + ", room="
				+ room + ", showaddress=" + showaddress + "]";
	}

}
