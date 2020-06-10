package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.MovieDao;
import model.entities.Movie;

public class MovieDaoJDBC implements MovieDao{

	private Connection conn;
	
	public MovieDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Movie obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Movie ob) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Movie selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
