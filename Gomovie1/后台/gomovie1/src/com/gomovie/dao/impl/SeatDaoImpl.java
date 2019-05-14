package com.gomovie.dao.impl;

import com.gomovie.dao.SeatDao;
import com.gomovie.model.Seat;
import com.gomovie.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SeatDaoImpl implements SeatDao {
	JdbcUtil jdbcUtil = new JdbcUtil();

	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	
	@Override
	public List<Seat> query(Seat seat){
        int showid = seat.getShowid();
        int room = seat.getRoom();
        List<Seat> list = new ArrayList<Seat>();

		String sql = "select * from seat where showid = "+showid+" and room = "+room;
		try {
			conn = jdbcUtil.getConnection();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while(res.next()){
				int id = res.getInt("id");
				int seatx = res.getInt("seatx");
				int seaty = res.getInt("seaty");
				int room1 = res.getInt("room");
				String address = res.getString("address");
				int showid1 = res.getInt("showid");
				String isxd = res.getString("isxd");
                int userid = res.getInt("userid");
                Seat seat1 = new Seat();
                seat1.setId(id);
				seat1.setSeatx(seatx);
				seat1.setSeaty(seaty);
				seat1.setRoom(room1);
				seat1.setAddress(address);
				seat1.setShowid(showid1);
				seat1.setIsxd(isxd);
                seat1.setUserid(userid);

                list.add(seat1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void xdseat(Seat seat) {
		int seatx = seat.getSeatx();
		int seaty = seat.getSeaty();
		int room = seat.getRoom();
        int showid = seat.getShowid();
        int userid = seat.getUserid();
        int price = seat.getPrice();
		
		String sql = "INSERT INTO seat (seatx,seaty,showid,room,userid,isxd,address,price) VALUES("+seatx+","+seaty+","+showid+","+room+","+userid+",'yes','丰台区万达影院',"+price+") ";
		try {
			conn = jdbcUtil.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		jdbcUtil.close(conn, stat, res);
		
	}

	//电影订单
	@Override
	public List<Seat> queryOrderMovie() {
		 List<Seat> list = new ArrayList<Seat>();

			String sql = "SELECT sea.* , sh.movieid, movie.moviename FROM (seat AS sea INNER JOIN showmovie AS sh ON sea.showid = sh.id) INNER JOIN movie ON movie.id = sh.movieid";
			try {
				conn = jdbcUtil.getConnection();
				stat = conn.createStatement();
				res = stat.executeQuery(sql);
				while(res.next()){
					int id = res.getInt("id");
					int seatx = res.getInt("seatx");
					int seaty = res.getInt("seaty");
					int room1 = res.getInt("room");
					String address = res.getString("address");
					int showid1 = res.getInt("showid");
					String isxd = res.getString("isxd");
	                int userid = res.getInt("userid");
	                String moviename = res.getString("moviename");
	                Seat seat1 = new Seat();
	                seat1.setId(id);
					seat1.setSeatx(seatx);
					seat1.setSeaty(seaty);
					seat1.setRoom(room1);
					seat1.setAddress(address);
					seat1.setShowid(showid1);
					seat1.setIsxd(isxd);
	                seat1.setUserid(userid);
	                seat1.setMoviename(moviename);

	                list.add(seat1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
	}

}
