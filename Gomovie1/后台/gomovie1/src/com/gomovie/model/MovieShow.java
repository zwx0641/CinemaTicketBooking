package com.gomovie.model;

import java.util.Date;

/**
 * Created by rlb on 2017/4/22.
 */
public class MovieShow {
    int id;
    String dateStr;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    int movieid;
    String showaddress;
    int room;
    String moviename;
    String movieicon;
    Date date;

    String type;
    String director;
    //	List<Actor> list;
    String actors;
    String desc;
    String isshow;
    int price;
    String rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getShowaddress() {
        return showaddress;
    }

    public void setShowaddress(String showaddress) {
        this.showaddress = showaddress;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
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

    public Date getDate() {
        return date;
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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
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
}
