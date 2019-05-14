package com.example.gomovie;

import java.io.Serializable;

public class Seat implements Serializable {
	private String address;
	private int id;
	private String isxd;
	private int showid;
	private int room;
	private int seatx;
	private int seaty;
	private int userid;
	private int price;

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
	public final int getShowid() {
		return showid;
	}

	/**
	 * @param movieid
	 *            the movieid to set
	 */
	public final void setShowid(int showid) {
		this.showid = showid;
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

	public final void setPrice(int price){this.price=price;}
	public final int getPrice(){return price;}

	@Override
	public String toString() {
		return "Seat [address=" + address + ", id=" + id + ", isxd=" + isxd
				+ ", showid=" + showid + ", room=" + room + ", seatx="
				+ seatx + ", seaty=" + seaty + ", userid=" + userid + "]";
	}

}
