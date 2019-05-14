package com.gomovie.dao.impl;

import com.gomovie.dao.MovieDao;
import com.gomovie.model.Movie;
import com.gomovie.model.MovieShow;
import com.gomovie.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
	
	JdbcUtil jdbcUtil = new JdbcUtil();
	
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	@Override
	public Movie queryMovie(int id) {
		String sql = "select * from movie where id = " + id;
        Movie movieRus = new Movie();
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while(res.next()){
				id = res.getInt("id");
				String moviename = res.getString("moviename");
				String movieicon = res.getString("movieicon");
				Date date = res.getDate("date");
				String type = res.getString("type");
				String director = res.getString("director");
				String actors = res.getString("actors");
				String desc = res.getString("desc");
				String isshow = res.getString("isshow");
				String rank = res.getString("rank");
				int price = res.getInt("price");
				

                movieRus.setId(id);
                movieRus.setMoviename(moviename);
                movieRus.setMovieicon(movieicon);
                movieRus.setDate(date);
                movieRus.setType(type);
                movieRus.setDirector(director);
                movieRus.setActors(actors);
                movieRus.setDesc(desc);
                movieRus.setIsshow(isshow);
                movieRus.setPrice(price);
                movieRus.setRank(rank);
                
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
//			jdbcUtil.close(conn, stat, res);
		}
		return movieRus;
	}



    @Override
	public List<Movie> queryMovie() {
		String sql = "select * from movie";
        List<Movie> list = new ArrayList<>();
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);

			while(res.next()){
				int id = res.getInt("id");
				String moviename = res.getString("moviename");
				String movieicon = res.getString("movieicon");
                Date date = res.getDate("date");
				String type = res.getString("type");
				String director = res.getString("director");
				String actors = res.getString("actors");
				String desc = res.getString("desc");
				String isshow = res.getString("isshow");
				String rank = res.getString("rank");
				int price = res.getInt("price");
                Movie movieRus = new Movie();
                movieRus.setId(id);
                movieRus.setMoviename(moviename);
                movieRus.setMovieicon(movieicon);
                movieRus.setDate(date);
                movieRus.setType(type);
                movieRus.setDirector(director);
                movieRus.setActors(actors);
                movieRus.setDesc(desc);
                movieRus.setIsshow(isshow);
                movieRus.setPrice(price);
                movieRus.setRank(rank);
                list.add(movieRus);
                System.out.println(list.size());
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


    @Override
    public List<MovieShow> queryMovieShow(int id) {
        String sql = "SELECT * FROM showmovie AS s LEFT JOIN movie AS m ON s.movieid = m.id WHERE m.id = " + id;
        List<MovieShow> list1 = new ArrayList<MovieShow>();
        try {
            conn = JdbcUtil.getConnection();
            stat = conn.createStatement();
            res = stat.executeQuery(sql);
            while(res.next()){
                id = res.getInt("id");
                 String dateStr = ""; 

                 DateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                  
                
                Timestamp showoftime = res.getTimestamp("showdate");
                dateStr  = sdf.format(showoftime);
                
                int movieid = res.getInt("movieid");
                int room = res.getInt("room");
                String showaddress = res.getString("showaddress");
                String moviename = res.getString("moviename");
                String movieicon = res.getString("movieicon");
                String type = res.getString("type");
                String director = res.getString("director");
                String actors = res.getString("actors");
                String desc = res.getString("desc");
                String isshow = res.getString("isshow");
                String rank = res.getString("rank");
                int price = res.getInt("price");

                MovieShow movieshow = new MovieShow();
                movieshow.setId(id);
                movieshow.setDateStr(dateStr);
                movieshow.setMovieid(movieid);
                movieshow.setMoviename(moviename);
                movieshow.setMovieicon(movieicon);
                movieshow.setType(type);
                movieshow.setDirector(director);
                movieshow.setActors(actors);
                movieshow.setDesc(desc);
                movieshow.setRoom(room);
                movieshow.setShowaddress(showaddress);
                movieshow.setIsshow(isshow);
                movieshow.setPrice(price);
                movieshow.setRank(rank);
                list1.add(movieshow);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
//			jdbcUtil.close(conn, stat, res);
        }
        return list1;
    }

    @Override
    public List<Movie> likeQuery(String likeParm) {
        String sql = "SELECT * FROM movie WHERE moviename like '"+likeParm+"%'";
        List<Movie> list = new ArrayList<>();
        System.out.println(sql);
        try {
            conn = JdbcUtil.getConnection();
            stat = conn.createStatement();
            res = stat.executeQuery(sql);

            while(res.next()){
                int id = res.getInt("id");
                String moviename = res.getString("moviename");
                String movieicon = res.getString("movieicon");
                Date date = res.getDate("date");
                String type = res.getString("type");
                String director = res.getString("director");
                String actors = res.getString("actors");
                String desc = res.getString("desc");
                String isshow = res.getString("isshow");
                Movie movieRus = new Movie();
                movieRus.setId(id);
                movieRus.setMoviename(moviename);
                movieRus.setMovieicon(movieicon);
                movieRus.setDate(date);
                movieRus.setType(type);
                movieRus.setDirector(director);
                movieRus.setActors(actors);
                movieRus.setDesc(desc);
                movieRus.setIsshow(isshow);
                list.add(movieRus);
                System.out.println(list.size());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

}
