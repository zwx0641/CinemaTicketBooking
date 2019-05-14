package com.gomovie.servlet;

import com.gomovie.model.Movie;
import com.gomovie.model.MovieShow;
import com.gomovie.service.MovieService;
import com.gomovie.service.impl.MovieServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MovieServlet2 extends HttpServlet{
	
	MovieService movieService = new MovieServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Movie movie = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idtemp = req.getParameter("id");
		System.out.println("idtemp:"+idtemp);
		int id = Integer.parseInt(idtemp);
		List<MovieShow> list = movieService.queryMovieShow(id);
		if(list.size()>0){
			resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=UTF-8");//设置传输编码
			System.out.println("MovieServlet2===="+JSONArray.fromObject(list).toString());
			resp.getWriter().write(JSONArray.fromObject(list).toString());
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
