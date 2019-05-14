package com.example.gomovie;

public class Movie {
	private int id;
	private String type;
	private String actors;
	private String datestr;
	private String desc;
	private String director;
	private String isshow;
	private String movieicon;
	private String moviename;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDatestr() {
		return datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public String getMovieicon() {
		return movieicon;
	}

	public void setMovieicon(String movieicon) {
		this.movieicon = movieicon;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", type=" + type + ", actors=" + actors
				+ ", datestr=" + datestr + ", desc=" + desc + ", director="
				+ director + ", isshow=" + isshow + ", movieicon=" + movieicon
				+ ", moviename=" + moviename + "]";
	}

}
