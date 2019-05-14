package com.gomovie.service.impl;

import com.gomovie.dao.MovieDao;

import com.gomovie.dao.impl.MovieDaoImpl;
import com.gomovie.model.Movie;
import com.gomovie.model.MovieShow;
import com.gomovie.service.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {

	MovieDao movieDao = new MovieDaoImpl();
	
	@Override
	public Movie queryMovie(int id) {
		return movieDao.queryMovie(id);
	}



    @Override
	public List<Movie> queryMovie() {
		return movieDao.queryMovie();
	}

    @Override
    public List<MovieShow> queryMovieShow(int id) {
        return movieDao.queryMovieShow(id);
    }

    @Override
    public List<Movie> likeQuery(String likeParm) {
        return movieDao.likeQuery(likeParm);
    }

}
