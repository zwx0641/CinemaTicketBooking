package com.example.gomovie;

public class Seat {
	private String address;
	private int id;
	private String isxd;
	private int movieid;
	private int room;
	private int seatx;
	private int seaty;
	private int userid;

	/**
	 * @return the address
	 */
	public final String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public final void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the isxd
	 */
	public final String getIsxd() {
		return isxd;
	}

	/**
	 * @param isxd
	 *            the isxd to set
	 */
	public final void setIsxd(String isxd) {
		this.isxd = isxd;
	}

	/**
	 * @return the movieid
	 */
	public final int getMovieid() {
		return movieid;
	}

	/**
	 * @param movieid
	 *            the movieid to set
	 */
	public final void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	/**
	 * @return the room
	 */
	public final int getRoom() {
		return room;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public final void setRoom(int room) {
		this.room = room;
	}

	/**
	 * @return the seatx
	 */
	public final int getSeatx() {
		return seatx;
	}

	/**
	 * @param seatx
	 *            the seatx to set
	 */
	public final void setSeatx(int seatx) {
		this.seatx = seatx;
	}

	/**
	 * @return the seaty
	 */
	public final int getSeaty() {
		return seaty;
	}

	/**
	 * @param seaty
	 *            the seaty to set
	 */
	public final void setSeaty(int seaty) {
		this.seaty = seaty;
	}

	/**
	 * @return the userid
	 */
	public final int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public final void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Seat [address=" + address + ", id=" + id + ", isxd=" + isxd
				+ ", movieid=" + movieid + ", room=" + room + ", seatx="
				+ seatx + ", seaty=" + seaty + ", userid=" + userid + "]";
	}

}
