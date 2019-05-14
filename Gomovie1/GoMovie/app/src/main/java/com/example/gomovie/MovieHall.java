package com.example.gomovie;

public class MovieHall {
	private int id;
	private int movieid;
	private int room;
	private String showaddress;
	private String dateStr;
	private String rank;
	private int price;

	public final int getId() {
		return id;
	}

	public final String getShowtime() {
		return dateStr;
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

	public final void setShowtime(String showtime) {
		this.dateStr = showtime;
	}

	public final String getShowaddress() {
		return showaddress;
	}

	public final void setShowaddress(String showaddress) {
		this.showaddress = showaddress;
	}

	public final void setPrice(int price){
		this.price = price;
	}

	public final int getPrice(){
		return this.price;
	}

	public final void setRank(String rank){
		this.rank = rank;
	}

	public final String getRank(){
		return rank;
	}

	@Override
	public String toString() {
		return "MovieHall [id=" + id + ", movieid=" + movieid + ", room="
				+ room + ", showaddress=" + showaddress + "]";
	}

}
