package com.gomovie.servlet;

import com.gomovie.model.Movie;
import com.gomovie.service.MovieService;
import com.gomovie.service.impl.MovieServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieServlet1 extends HttpServlet{
	
	MovieService movieService = new MovieServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Movie movie = new Movie();
    List<Movie> list = new ArrayList<>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idtemp = req.getParameter("id");
        int id = Integer.parseInt(idtemp);
		movie = movieService.queryMovie(id);
		if( null != movie){
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=UTF-8");
			
			System.out.println("MovieServlet1 queryMovie(id)======="+JSONArray.fromObject(movie).toString());
			resp.getWriter().write(JSONArray.fromObject(movie).toString());
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
