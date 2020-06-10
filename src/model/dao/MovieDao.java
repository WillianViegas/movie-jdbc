package model.dao;

import java.util.List;

import model.entities.Movie;

public interface MovieDao {
	
	void insert(Movie obj);
	void update(Movie ob);
	void deleteById(Integer id);
	Movie selectById(Integer id);
	List<Movie> selectAll();
	
}
