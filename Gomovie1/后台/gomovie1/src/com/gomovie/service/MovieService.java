package com.gomovie.service;

import com.gomovie.model.Movie;

import com.gomovie.model.MovieShow;

import java.util.List;

public interface MovieService {
	List<Movie> queryMovie();
	Movie queryMovie(int id);
    List<MovieShow> queryMovieShow(int id);
    List<Movie> likeQuery(String likeParm);
}
