package com.gomovie.model;

import java.util.Date;
import java.util.List;

public class Movie {
	int id;
	String moviename;
	String movieicon;
	Date date;
	String datestr;
	public String getDatestr() {
		return datestr = this.date.toString();
	}
	String type;
	String director;
//	List<Actor> list;
	String actors;
	String desc;
	String isshow;
	String rank;
	int price;
   

	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getMovieicon() {
		return movieicon;
	}
	public void setMovieicon(String movieicon) {
		this.movieicon = movieicon;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getRank() {
		return rank;
	}
//	public List<Actor> getList() {
//		return list;
//	}
//	public void setList(List<Actor> list) {
//		this.list = list;
//	}
	public String getDesc() {
		return desc;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Movie(int id, String moviename, String movieicon, Date date, String type, String director, String actors,
			String desc,int price, String rank) {
		super();
		this.id = id;
		this.moviename = moviename;
		this.movieicon = movieicon;
		this.date = date;
		this.type = type;
		this.director = director;
		this.actors = actors;
		this.desc = desc;
		this.rank = rank;
		this.price = price;
	}
	public Movie() {
		super();
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", moviename=" + moviename + ", movieicon=" + movieicon + ", date=" + date
				+ ", type=" + type + ", director=" + director + ", list=" + actors + ", desc=" + desc + ", price="+price + ", rank="+rank+"]";
	}
	
}
