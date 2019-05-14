package com.gomovie.dao;

import com.gomovie.model.Movie;
import com.gomovie.model.MovieShow;

import java.util.List;

public interface MovieDao {
	List<Movie> queryMovie();
	Movie queryMovie(int id);
    List<MovieShow> queryMovieShow(int id);
    List<Movie> likeQuery(String likeParm);
    int addMovie(Movie movie);
}
